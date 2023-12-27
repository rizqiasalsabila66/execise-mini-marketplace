package com.trollMarket.TrollMarket.controller;

import com.trollMarket.TrollMarket.dto.utility.HeaderDTO;
import com.trollMarket.TrollMarket.service.AccountService;
import com.trollMarket.TrollMarket.service.BuyerService;
import com.trollMarket.TrollMarket.service.SellerService;
import com.trollMarket.TrollMarket.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/index")
    public String getHeader(@RequestParam(required = true) String username,
                            @RequestParam(defaultValue = "1") int page,
                            Model model
                            ){
        var role = accountService.getRole(username);
        var dto = new HeaderDTO();
        if(role.equals("Buyer")) {
            dto = buyerService.getHeader(username);
            model.addAttribute("dto", dto);
        } else if (role.equals("Seller")) {
            dto = sellerService.getHeader(username);
            model.addAttribute("dto", dto);
        }

        model.addAttribute("username", username);
        var rows = transactionService.getTransactionRows(username, page);
        model.addAttribute("grid", rows);
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        return "profile/profile-index";
    }


}
