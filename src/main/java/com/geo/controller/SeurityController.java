package com.geo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.geo.entities.Token;
import com.geo.entities.User;
import com.geo.repository.TokenRepository;
import com.geo.repository.UserRepository;
import com.geo.security.SecurityUtils;

@RestController
@RequestMapping(value = "/security")
public class SeurityController {


    @Autowired
    private UserRepository userRepo;


    @Autowired
    private TokenRepository tokenRepo;

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public @ResponseBody
    User getUserAccount()  {
        User user = userRepo.findByLogin(SecurityUtils.getCurrentLogin());
        user.setPassword(null);
        return user;
    }


    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/tokens", method = RequestMethod.GET)
    public @ResponseBody
    List<Token> getTokens () {
        List<Token> tokens = tokenRepo.findAll();
        for(Token t:tokens) {
            t.setSeries(null);
            t.setValue(null);
        }
        return tokens;
    }
    
    
    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Authentication authentication) {
        return authentication.getName();
    }
}
