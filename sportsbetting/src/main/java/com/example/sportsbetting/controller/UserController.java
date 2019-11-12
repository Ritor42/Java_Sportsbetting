package com.example.sportsbetting.controller;

import com.example.sportsbetting.domain.Currency;
import com.example.sportsbetting.domain.Player;
import com.example.sportsbetting.domain.Wager;
import com.example.sportsbetting.exception.CurrencyMismatchException;
import com.example.sportsbetting.service.ISportsBettingService;
import com.example.sportsbetting.service.SportsBettingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

@Controller
public class UserController {
    private ISportsBettingService service = SportsBettingService.GetInstance();

    @GetMapping("/user")
    public ModelAndView index(HttpSession session) {
        if (session.getAttribute("Id") == null) {
            return new ModelAndView("redirect:/login");
        }

        return new ModelAndView("redirect:/user/dashboard");
    }

    @GetMapping("/user/dashboard")
    public ModelAndView getDashboard(HttpSession session) {
        if (session.getAttribute("Id") == null) {
            return new ModelAndView("redirect:/login");
        }

        return new ModelAndView("user/dashboard");
    }

    @GetMapping("/user/wager")
    public ModelAndView getWager(HttpSession session) {
        if (session.getAttribute("Id") == null) {
            return new ModelAndView("redirect:/login");
        }

        return new ModelAndView("user/wager");
    }

    @GetMapping("/user/events")
    public ModelAndView getEvents(HttpSession session) {
        if (session.getAttribute("Id") == null) {
            return new ModelAndView("redirect:/login");
        }

        return new ModelAndView("user/events");
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
        return new ModelAndView("user/dashboard");
    }

    @GetMapping("/user/logout")
    public ModelAndView getLogout(HttpSession session) {
        if (session.getAttribute("Id") != null) {
            session.removeAttribute("Id");
        }

        return new ModelAndView("redirect:/login");
    }

    @PostMapping("/user/wager")
    public ModelAndView addWager(HttpSession session, Integer odd, Currency currency, BigDecimal amount) {
        if (session.getAttribute("Id") == null) {
            return new ModelAndView("redirect:/login");
        }

        try {
            Integer playerId = (Integer) session.getAttribute("Id");
            Player player = service.findPlayer(playerId);

            Wager wager = new Wager();
            wager.setPlayer(player);
            wager.setCurrency(player.getCurrency());
            wager.setOutcomeOdd(service.findOutcomeOdd(odd));
            wager.setAmount(amount);

            service.saveWager(wager);
            return new ModelAndView("redirect:/user/dashboard");
        } catch (Exception e) {
            return new ModelAndView("redirect:/user/wager");
        }
    }

    @GetMapping(value = "/user/wager/delete")
    public ModelAndView removeWager(HttpSession session, Integer wagerId) {
        if (session.getAttribute("Id") == null) {
            return new ModelAndView("redirect:/login");
        }

        try {
            service.deleteWager(wagerId);
        } catch (CurrencyMismatchException c) {

        }

        return new ModelAndView("redirect:/user/dashboard");
    }

    @PostMapping("/user/dashboard")
    public ModelAndView saveDetails(HttpSession session, Player player) {
        if (session.getAttribute("Id") == null) {
            return new ModelAndView("redirect:/login");
        }

        try {
            Integer playerId = (Integer) session.getAttribute("Id");
            Player dbPlayer = service.findPlayer(playerId);

            dbPlayer.setName(player.getName());
            dbPlayer.setBirth(player.getBirth());
            dbPlayer.setAccountNumber(player.getAccountNumber());
            dbPlayer.setCurrency(player.getCurrency());
            dbPlayer.setBalance(player.getBalance());

            service.savePlayer(dbPlayer);
        } catch (Exception e) {
        }

        return new ModelAndView("redirect:/user/dashboard");
    }
}
