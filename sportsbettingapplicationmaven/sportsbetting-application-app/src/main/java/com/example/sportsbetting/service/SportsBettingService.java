package com.example.sportsbetting.service;


import com.example.sportsbetting.domain.*;
import com.example.sportsbetting.dto.*;
import com.example.sportsbetting.exception.CurrencyMismatchException;
import com.example.sportsbetting.exception.NotEnoughBalanceException;
import com.example.sportsbetting.exception.WagerProcessedException;
import com.example.sportsbetting.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class SportsBettingService {
    private static final Logger LOG = LoggerFactory.getLogger(SportsBettingService.class);
    private static Random rand = new Random();

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
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
    @Autowired
    private RoleRepository roleRepository;

    public SportsBettingService() {
        LOG.info("SportsBettingService is initializing.");
    }

    public Player findPlayer(String email) {
        return playerRepository.findByEmail(email).orElse(null);
    }

    @Transactional
    public Iterable<WagerDto> findAllPlayerWagerDtos(Player player) {
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

    @Transactional
    public Iterable<SportEventDto> findAllSportEventDtos() {
        List<SportEventDto> dtos = new ArrayList<>();
        for (SportEvent event : eventRepository.findAll()) {
            dtos.add(new SportEventDto(event));
        }
        return dtos;
    }

    @Transactional
    public void savePlayer(Player player) {
        if (player != null) {
            if (!playerRepository.findById(player.getId()).isPresent()) {
                Role userRole = roleRepository.findByName("User").orElse(null);
                if (userRole == null) {
                    userRole = new Role();
                    userRole.setName("User");
                    roleRepository.save(userRole);
                }
                player.setPassword(passwordEncoder.encode(player.getPassword()));
                player.setRole(userRole);
            }

            playerRepository.save(player);
            LOG.info("Player saved: " + player.getName());
        } else {
            LOG.info("Player save threw IllegalArgumentException");
            throw new IllegalArgumentException();
        }
    }

    @Transactional
    public void saveWager(Wager wager, int oddId) throws NotEnoughBalanceException {
        if (wager != null && wager.getPlayer() != null) {
            if (wager.getAmount().doubleValue() > 0) {
                Player player = wager.getPlayer();
                OutcomeOdd odd = oddRepository.findById(oddId).orElseThrow(IllegalArgumentException::new);

                if (player.getBalance().compareTo(wager.getAmount()) != -1) {
                    player.setBalance(player.getBalance().subtract(wager.getAmount()));
                    wager.setTimestampCreated(new Date());
                    wager.setOutcomeOdd(odd);
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
    }

    @Transactional
    public void deleteWager(int wagerId) throws CurrencyMismatchException, WagerProcessedException {
        Wager wager = wagerRepository.findById(wagerId).orElse(null);
        if (wager != null && wager.getPlayer() != null) {
            Player player = wager.getPlayer();
            if (!wager.isProcessed()) {
                if (wager.getOutcomeOdd().getValidFrom().compareTo(new Date()) != -1) {
                    if (player.getCurrency() == wager.getCurrency()) {
                        player.setBalance(player.getBalance().add(wager.getAmount()));
                        playerRepository.save(player);
                        wagerRepository.delete(wager);
                        LOG.info("Wager deleted from player: " + player.getName());
                    } else {
                        LOG.warn("Wager deletion threw CurrencyMismatchException for player: " + player.getName());
                        throw new CurrencyMismatchException();
                    }
                }
            } else {
                LOG.warn("Wager deletion threw WagerProcessedException for player: " + player.getName());
                throw new WagerProcessedException();
            }
        }
    }

    @Transactional
    public void calculateResults() throws CurrencyMismatchException {
        for (SportEvent event : eventRepository.findAll()) {
            Result result = (event.getResult() == null) ? new Result() : event.getResult();
            List<Wager> wagers = StreamSupport.stream(wagerRepository.findAll().spliterator(), false)
                    .filter(x -> !x.isProcessed() && x.getOutcomeOdd().getOutcome().getBet().getEvent().getId() == event.getId()).collect(Collectors.toList());
            for (Wager wager : wagers) {
                Player player = wager.getPlayer();
                if (wager.getCurrency().equals(player.getCurrency())) {
                    wager.setProcessed(true);
                    if (rand.nextBoolean()) {
                        wager.setWin(true);
                        OutcomeOdd odd = wager.getOutcomeOdd();
                        result.addWinnerOutcome(odd.getOutcome());
                        player.setBalance(player.getBalance().add(wager.getAmount().multiply(odd.getValue())));
                        playerRepository.save(player);
                    } else {
                        wager.setWin(false);
                    }
                    wagerRepository.save(wager);
                } else {
                    LOG.warn("Calculate results threw CurrencyMismatchException for player: " + player.getName());
                    //throw new CurrencyMismatchException();
                }
            }
            event.setResult(result);
            resultRepository.save(result);
            eventRepository.save(event);
        }

        LOG.info("Calculated results for all events.");
    }
}
