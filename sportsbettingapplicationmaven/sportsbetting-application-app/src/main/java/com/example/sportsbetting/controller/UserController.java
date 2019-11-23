package com.example.sportsbetting.controller;

import com.example.sportsbetting.domain.Currency;
import com.example.sportsbetting.domain.OutcomeOdd;
import com.example.sportsbetting.domain.Player;
import com.example.sportsbetting.domain.Wager;
import com.example.sportsbetting.dto.OutcomeOddDto;
import com.example.sportsbetting.dto.PlayerDto;
import com.example.sportsbetting.dto.SportEventDto;
import com.example.sportsbetting.dto.WagerDto;
import com.example.sportsbetting.exception.CurrencyMismatchException;
import com.example.sportsbetting.model.DetailsRequest;
import com.example.sportsbetting.model.WagerRequest;
import com.example.sportsbetting.service.SportsBettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

@Controller
public class UserController {
    @Autowired
    private SportsBettingService service;

    @GetMapping("/user")
    public ModelAndView index(HttpSession session) {
        if (session.getAttribute("Id") == null) {
            return new ModelAndView("redirect:/login");
        }

        Integer playerId = (Integer) session.getAttribute("Id");
        PlayerDto player = service.findPlayerDto(playerId);
        Iterable<WagerDto> wagers = service.findAllPlayerWagerDtos(playerId);

        return new ModelAndView("redirect:/user/dashboard");
    }

    @GetMapping("/user/dashboard")
    public ModelAndView getDashboard(HttpSession session) {
        if (session.getAttribute("Id") == null) {
            return new ModelAndView("redirect:/login");
        }

        Integer playerId = (Integer) session.getAttribute("Id");
        PlayerDto player = service.findPlayerDto(playerId);
        Iterable<WagerDto> wagers = service.findAllPlayerWagerDtos(playerId);

        return new ModelAndView("user/dashboard")
                .addObject("player", player)
                .addObject("wagers", wagers);
    }

    @GetMapping("/user/wager")
    public ModelAndView getWager(HttpSession session) {
        if (session.getAttribute("Id") == null) {
            return new ModelAndView("redirect:/login");
        }

        Iterable<OutcomeOddDto> odds = service.findAllOutcomeOddDtos();
        return new ModelAndView("user/wager")
                .addObject("odds", odds);
    }

    @GetMapping("/user/events")
    public ModelAndView getEvents(HttpSession session) {
        if (session.getAttribute("Id") == null) {
            return new ModelAndView("redirect:/login");
        }

        Iterable<SportEventDto> events = service.findAllSportEventDtos();
        return new ModelAndView("user/events").addObject("events", events);
    }

    @GetMapping("/user/calculate")
    public ModelAndView getCalculate(HttpSession session) {
        if (session.getAttribute("Id") == null) {
            return new ModelAndView("redirect:/login");
        }

        try {
            service.calculateResults();
        } catch (CurrencyMismatchException e) {

        }
        return new ModelAndView("redirect:/user/dashboard");
    }

    @GetMapping("/user/logout")
    public ModelAndView getLogout(HttpSession session) {
        if (session.getAttribute("Id") != null) {
            session.removeAttribute("Id");
        }

        return new ModelAndView("redirect:/login");
    }

    @PostMapping("/user/wager")
    public ModelAndView addWager(HttpSession session, WagerRequest wagerRequest, BindingResult bindingResult) {
        if (session.getAttribute("Id") == null) {
            return new ModelAndView("redirect:/login");
        }

        if(bindingResult.hasErrors() || !wagerRequest.validate()) {
            return new ModelAndView("redirect:/user/wager");
        }

        try {
            int playerId = (int)session.getAttribute("Id");
            Player player = service.findPlayer(playerId);
            OutcomeOdd outcomeOdd = service.findOutcomeOdd(wagerRequest.getOddId());

            if(outcomeOdd != null) {
                Wager wager = new Wager();
                wager.setPlayer(player);
                wager.setCurrency(player.getCurrency());
                wager.setOutcomeOdd(outcomeOdd);
                wager.setAmount(wagerRequest.getAmount());

                service.saveWager(wager);
                return new ModelAndView("redirect:/user/dashboard");
            }
        } catch (Exception e) {
        }

        return new ModelAndView("redirect:/user/wager");
    }

    @GetMapping(value = "/user/wager/delete")
    public ModelAndView removeWager(HttpSession session, Integer wagerId) {
        if (session.getAttribute("Id") == null) {
            return new ModelAndView("redirect:/login");
        }

        if (wagerId != null) {
            try {
                service.deleteWager(wagerId);
            } catch (CurrencyMismatchException ignored) {
            }
        }

        return new ModelAndView("redirect:/user/dashboard");
    }

    @PostMapping("/user/dashboard")
    public ModelAndView saveDetails(HttpSession session, DetailsRequest detailsRequest, BindingResult bindingResult) {
        if (session.getAttribute("Id") == null) {
            return new ModelAndView("redirect:/login");
        }

        if(!bindingResult.hasErrors() && detailsRequest.validate()) {
            try {
                Integer playerId = (Integer) session.getAttribute("Id");
                Player dbPlayer = service.findPlayer(playerId);

                dbPlayer.setName(detailsRequest.getName());
                dbPlayer.setBirth(detailsRequest.getBirth());
                dbPlayer.setAccountNumber(detailsRequest.getAccountNumber());
                dbPlayer.setCurrency(detailsRequest.getCurrency());
                dbPlayer.setBalance(detailsRequest.getBalance());

                service.savePlayer(dbPlayer);
            } catch (Exception e) {
            }
        }
        return new ModelAndView("redirect:/user/dashboard");
    }
}
