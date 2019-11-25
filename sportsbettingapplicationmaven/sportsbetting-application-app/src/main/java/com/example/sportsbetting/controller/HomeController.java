package com.example.sportsbetting.controller;

import com.example.sportsbetting.domain.Player;
import com.example.sportsbetting.model.LoginRequest;
import com.example.sportsbetting.model.RegisterRequest;
import com.example.sportsbetting.service.SportsBettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    private SportsBettingService service;

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/login")
    public ModelAndView getLogin(Principal principal) {
        if (principal != null) {
            return new ModelAndView("redirect:/user");
        }
        return new ModelAndView("login")
                .addObject("loginRequest", new LoginRequest());
    }

    @GetMapping("/register")
    public ModelAndView getRegister(Principal principal) {
        if (principal != null) {
            return new ModelAndView("redirect:/user");
        }
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setBalance(new BigDecimal(5000));
        registerRequest.setAccountNumber("HU42 0000 0000 0000 0000 0000 0000");
        return new ModelAndView("register")
                .addObject("registerRequest", registerRequest);
    }

    @PostMapping("/register")
    public ModelAndView doRegister(@Valid @ModelAttribute("registerRequest") RegisterRequest registerRequest, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            Player player = new Player();
            player.setBalance(registerRequest.getBalance());
            player.setCurrency(registerRequest.getCurrency());
            player.setBirth(registerRequest.getBirth());
            player.setName(registerRequest.getName());
            player.setEmail(registerRequest.getEmail());
            player.setPassword(registerRequest.getPassword());
            player.setAccountNumber(registerRequest.getAccountNumber());

            try {
                service.savePlayer(player);
                return new ModelAndView("redirect:/login");
            } catch (Exception ignored) {
            }
        }
        return new ModelAndView("register")
                .addObject("registerRequest", registerRequest);
    }
}
