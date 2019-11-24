package com.example.sportsbetting.controller;

import com.example.sportsbetting.domain.Player;
import com.example.sportsbetting.model.RegisterRequest;
import com.example.sportsbetting.service.SportsBettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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

        return new ModelAndView("login");
    }

    @GetMapping("/register")
    public ModelAndView getRegister(Principal principal) {
        if (principal != null) {
            return new ModelAndView("redirect:/user");
        }

        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView doRegister(RegisterRequest registerRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || !registerRequest.validate()) {
            return new ModelAndView("register");
        }

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
        } catch(Exception e) {
            return new ModelAndView("redirect:/register");
        }
    }
}
