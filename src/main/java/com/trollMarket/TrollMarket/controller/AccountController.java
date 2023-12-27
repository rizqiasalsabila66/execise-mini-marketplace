package com.trollMarket.TrollMarket.controller;

import com.trollMarket.TrollMarket.dto.account.RegisterDTO;
import com.trollMarket.TrollMarket.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService service;

    @GetMapping("/registerForm")
    public String registerForm(@RequestParam String role, Model model){
        var dto = new RegisterDTO();
        model.addAttribute("dto", dto);
        model.addAttribute("role", role);
        return "account/register-form";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("dto")RegisterDTO dto,
                           BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "account/register-form";
        }
        service.register(dto);
        redirectAttributes.addAttribute("role",dto.getRole());
        return "redirect:/account/loginForm";
    }

    @GetMapping("/loginForm")
    public String loginForm(Model model){
        model.addAttribute("buyer","Buyer");
        model.addAttribute("seller","Seller");
        return "account/login-form";
    }

    @RequestMapping(value="/accessDenied", method = {RequestMethod.GET, RequestMethod.POST})
    public String accessDenied(Model model){
        return "account/access-denied";
    }

    @GetMapping("/faillogin")
    public String loginFailed(Model model){
        return "account/fail-login";
    }


}

