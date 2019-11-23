package com.example.sportsbetting.controller;

import com.example.sportsbetting.domain.Player;
import com.example.sportsbetting.domain.User;
import com.example.sportsbetting.model.LoginRequest;
import com.example.sportsbetting.model.RegisterRequest;
import com.example.sportsbetting.service.SportsBettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
public class HomeController {
    @Autowired
    private SportsBettingService service;

    @GetMapping("/")
    public ModelAndView index(HttpSession session) {
        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/login")
    public ModelAndView getLogin(HttpSession session) {
        if (session.getAttribute("Id") != null) {
            return new ModelAndView("redirect:/user");
        }

        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView doLogin(HttpSession session, LoginRequest loginRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors() || !loginRequest.validate()) {
            return new ModelAndView("redirect:/login");
        }

        loginRequest.setPassword(getHash(loginRequest.getPassword(), loginRequest.getEmail()));
        Player player = service.findPlayer(loginRequest.getEmail(), loginRequest.getPassword());

        if (player != null) {
            session.setAttribute("Id", player.getId());
            return new ModelAndView("redirect:/user");
        } else {
            return new ModelAndView("redirect:/login");
        }
    }

    @GetMapping("/register")
    public ModelAndView getRegister(HttpSession session) {
        if (session.getAttribute("Id") != null) {
            return new ModelAndView("redirect:/user");
        }

        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView doRegister(HttpSession session, RegisterRequest registerRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || !registerRequest.validate()) {
            return new ModelAndView("register");
        }

        Player player = new Player();
        player.setBalance(registerRequest.getBalance());
        player.setCurrency(registerRequest.getCurrency());
        player.setBirth(registerRequest.getBirth());
        player.setName(registerRequest.getName());
        player.setEmail(registerRequest.getEmail());
        player.setPassword(getHash(registerRequest.getPassword(),registerRequest.getEmail()));
        player.setAccountNumber(registerRequest.getAccountNumber());

        service.savePlayer(player);
        session.setAttribute("Id", player.getId());

        return new ModelAndView("redirect:/user");
    }

    private String getHash(String passwordToHash, String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
