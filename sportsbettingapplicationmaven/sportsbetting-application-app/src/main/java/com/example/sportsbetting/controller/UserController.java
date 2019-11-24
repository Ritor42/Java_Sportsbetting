package com.example.sportsbetting.controller;

import com.example.sportsbetting.domain.Player;
import com.example.sportsbetting.domain.Wager;
import com.example.sportsbetting.dto.OutcomeOddDto;
import com.example.sportsbetting.dto.PlayerDto;
import com.example.sportsbetting.dto.SportEventDto;
import com.example.sportsbetting.dto.WagerDto;
import com.example.sportsbetting.exception.CurrencyMismatchException;
import com.example.sportsbetting.exception.WagerProcessedException;
import com.example.sportsbetting.model.DetailsRequest;
import com.example.sportsbetting.model.WagerRequest;
import com.example.sportsbetting.service.SportsBettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    private SportsBettingService service;

    @GetMapping("/user")
    public ModelAndView index() {
        return new ModelAndView("redirect:/user/dashboard");
    }

    @GetMapping("/user/dashboard")
    public ModelAndView getDashboard(Principal principal) {
        Player player = service.findPlayer(principal.getName());
        Iterable<WagerDto> wagers = service.findAllPlayerWagerDtos(player);
        return new ModelAndView("user/dashboard")
                .addObject("player", new PlayerDto(player))
                .addObject("wagers", wagers);
    }

    @GetMapping("/user/wager")
    public ModelAndView getWager() {
        Iterable<OutcomeOddDto> odds = service.findAllOutcomeOddDtos();
        return new ModelAndView("user/wager")
                .addObject("odds", odds);
    }

    @GetMapping("/user/events")
    public ModelAndView getEvents() {
        Iterable<SportEventDto> events = service.findAllSportEventDtos();
        return new ModelAndView("user/events").addObject("events", events);
    }

    @GetMapping("/user/calculate")
    public ModelAndView getCalculate() {
        try {
            service.calculateResults();
        } catch (CurrencyMismatchException ignored) {
        }
        return new ModelAndView("redirect:/user/dashboard");
    }

    @PostMapping("/user/dashboard")
    public ModelAndView saveDetails(Principal principal, DetailsRequest detailsRequest, BindingResult bindingResult) {
        if (!bindingResult.hasErrors() && detailsRequest.validate()) {
            try {
                Player dbPlayer = service.findPlayer(principal.getName());
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

    @PostMapping("/user/wager")
    public ModelAndView addWager(Principal principal, WagerRequest wagerRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || !wagerRequest.validate()) {
            return new ModelAndView("redirect:/user/wager");
        }

        try {
            Player player = service.findPlayer(principal.getName());
            Wager wager = new Wager();
            wager.setPlayer(player);
            wager.setCurrency(player.getCurrency());
            wager.setAmount(wagerRequest.getAmount());
            service.saveWager(wager, wagerRequest.getOddId());
            return new ModelAndView("redirect:/user/dashboard");
        } catch (Exception e) {
            return new ModelAndView("redirect:/user/wager");
        }
    }

    @GetMapping(value = "/user/wager/delete")
    public ModelAndView removeWager(Integer wagerId) {
        if (wagerId != null) {
            try {
                service.deleteWager(wagerId);
            } catch (CurrencyMismatchException | WagerProcessedException ignored) {
            }
        }

        return new ModelAndView("redirect:/user/dashboard");
    }
}
