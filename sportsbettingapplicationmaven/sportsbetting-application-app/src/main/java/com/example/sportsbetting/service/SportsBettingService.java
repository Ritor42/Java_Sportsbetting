package com.example.sportsbetting.service;


import com.example.sportsbetting.domain.*;
import com.example.sportsbetting.dto.*;
import com.example.sportsbetting.exception.CurrencyMismatchException;
import com.example.sportsbetting.exception.NotEnoughBalanceException;
import com.example.sportsbetting.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class SportsBettingService {
    private static final Logger LOG = LoggerFactory.getLogger(SportsBettingService.class);
    private static Random rand = new Random();
    @Autowired
    private WagerRepository wagerRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private OutcomeOddRepository oddRepository;
    @Autowired
    private SportEventRepository eventRepository;

    public SportsBettingService() {
        LOG.info("SportsBettingService is initializing.");
    }

    public void saveWager(Wager wager) throws NotEnoughBalanceException {

        if (wager != null && wager.getPlayer() != null && wager.getAmount().doubleValue() > 0) {
            Player player = wager.getPlayer();

            if (player.getBalance().compareTo(wager.getAmount()) != -1) {
                player.setBalance(player.getBalance().subtract(wager.getAmount()));
                wager.setTimestampCreated(new Date());
                wager.setProcessed(false);
                wager.setWin(false);

                playerRepository.save(player);
                wagerRepository.save(wager);
                LOG.info("Wager saved for player:" + player.getName());
            } else {
                LOG.info("Wager saving threw NotEnoughBalanceException for player: " + player.getName());
                throw new NotEnoughBalanceException();
            }
        } else {
            LOG.info("Wager saving threw IllegalArgumentException");
            throw new IllegalArgumentException();
        }
    }

    public void deleteWager(Integer id) throws CurrencyMismatchException {
        deleteWager(wagerRepository.findById(id).orElse(null));
    }

    public void deleteWager(Wager wager) throws CurrencyMismatchException {
        if (wager != null && wager.getPlayer() != null && !wager.isProcessed()) {
            Player player = wager.getPlayer();

            if (wager.getOutcomeOdd().getValidFrom().compareTo(new Date()) != -1) {
                if (player.getCurrency() == wager.getCurrency()) {
                    player.setBalance(player.getBalance().add(wager.getAmount()));
                    playerRepository.save(player);
                    wagerRepository.delete(wager);
                } else {
                    LOG.warn("Wager deletion threw CurrencyMismatchException for player: " + player.getName());
                    throw new CurrencyMismatchException();
                }
            }
        }
    }

    public Player findPlayer(Integer id) {
        return playerRepository.findById(id).orElse(null);
    }

    public Player findPlayer(String email, String password) {
        return playerRepository.findByEmailAndPassword(email, password).orElse(null);
    }

    public PlayerDto findPlayerDto(Integer id) {
        Optional<Player> player = playerRepository.findById(id);
        return player.map(PlayerDto::new).orElse(null);
    }

    public void savePlayer(Player player) {
        if (player != null) {
            playerRepository.save(player);
        } else {
            LOG.info("Player save threw IllegalArgumentException");
            throw new IllegalArgumentException();
        }
    }

    @Transactional
    public Iterable<OutcomeOddDto> findAllOutcomeOddDtos() {
        List<OutcomeOddDto> dtos = new ArrayList<>();

        for (SportEventDto eventDto : findAllSportEventDtos()) {
            for (BetDto betDto : eventDto.getBets()) {
                for (OutcomeDto outcomeDto : betDto.getOutcomes()) {
                    for (OutcomeOddDto oddDto : outcomeDto.getOutcomeOdds()) {
                        dtos.add((oddDto));
                    }
                }
            }
        }

        return dtos;
    }

    public OutcomeOdd findOutcomeOdd(Integer id) {
        return oddRepository.findById(id).orElse(null);
    }

    @Transactional
    public Iterable<SportEventDto> findAllSportEventDtos() {
        List<SportEventDto> dtos = new ArrayList<>();
        for (SportEvent event : eventRepository.findAll()) {
            dtos.add(new SportEventDto(event));
        }

        return dtos;
    }

    @Transactional
    public Iterable<WagerDto> findAllPlayerWagerDtos(Integer playerId) {
        Player player = findPlayer(playerId);
        List<WagerDto> dtos = new ArrayList<>();
        List<SportEventDto> eventDtos = new ArrayList<>();

        for (Wager wager : wagerRepository.findByPlayer(player)) {
            WagerDto wagerDto = new WagerDto(wager);
            SportEvent event = wager.getOutcomeOdd().getOutcome().getBet().getEvent();
            SportEventDto eventDto = eventDtos.stream().filter(x -> x.getId() == event.getId()).findFirst().orElse(null);

            if (eventDto == null) {
                eventDto = new SportEventDto(event);
                eventDtos.add(eventDto);
            }

            for (BetDto betDto : eventDto.getBets()) {
                for (OutcomeDto outcomeDto : betDto.getOutcomes()) {
                    for (OutcomeOddDto oddDto : outcomeDto.getOutcomeOdds()) {
                        if (oddDto.getId() == wager.getOutcomeOdd().getId()) {
                            wagerDto.setOutcomeOdd(oddDto);
                            break; // break; break;
                        }
                    }
                }
            }

            dtos.add(wagerDto);
        }

        return dtos;
    }

    @Transactional
    public void calculateResults() throws CurrencyMismatchException {
        for (SportEvent event : eventRepository.findAll()) {

            Result result = (event.getResult() == null) ? new Result() : event.getResult();

            List<Wager> wagers = StreamSupport.stream(wagerRepository.findAll().spliterator(), false)
                    .filter(x -> x.getOutcomeOdd().getOutcome().getBet().getEvent().getId() == event.getId()).collect(Collectors.toList());

            for (Wager wager : wagers) {
                if (!wager.isProcessed()) {
                    if (rand.nextBoolean()) {
                        OutcomeOdd odd = wager.getOutcomeOdd();
                        result.addWinnerOutcome(odd.getOutcome());

                        Player player = wager.getPlayer();
                        wager.setWin(true);

                        if (wager.getCurrency().equals(player.getCurrency())) {
                            player.setBalance(player.getBalance().add(wager.getAmount().multiply(odd.getValue())));
                            playerRepository.save(player);
                        } else {
                            LOG.warn("Calculate results threw CurrencyMismatchException for player: " + player.getName());
                            throw new CurrencyMismatchException();
                        }
                    }
                    wager.setProcessed(true);
                    wagerRepository.save(wager);
                }
            }

            event.setResult(result);
            resultRepository.save(result);
            eventRepository.save(event);
        }
    }
}
