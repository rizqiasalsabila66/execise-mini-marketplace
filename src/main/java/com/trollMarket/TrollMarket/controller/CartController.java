package com.trollMarket.TrollMarket.controller;

import com.trollMarket.TrollMarket.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/index")
    public String getProductRow(@RequestParam(defaultValue = "1") int page,
                                @RequestParam(required = true) String username,
                                Model model){
        var rows = cartService.getCartRows(username,page);
        model.addAttribute("grid", rows);
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("username",username);
        return "cart/cart-index";
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public String delete(@RequestParam(required = true) String username,@RequestParam(required = true) Long id, Model model,
                         RedirectAttributes redirectAttributes){
        cartService.delete(id);
        redirectAttributes.addAttribute("username",username);
        return "redirect:/cart/index";
    }

    @GetMapping("/purchaseAll")
    public String purchaseAll(@RequestParam String username,
                              Model model,RedirectAttributes redirectAttributes){
        var minus = cartService.notEnoughBalance(username);
        if(minus>0){
            model.addAttribute("minus",minus);
            model.addAttribute("username",username);
            return "/cart/not-enough-balance";
        }
        cartService.purchaseAll(username);
        redirectAttributes.addAttribute("username",username);
        return "redirect:/cart/index";
    }

}
