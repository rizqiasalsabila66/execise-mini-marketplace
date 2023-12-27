package com.trollMarket.TrollMarket.controller;

import com.trollMarket.TrollMarket.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/index")
    public String getProductRow(@RequestParam(defaultValue = "1") int page,
                                @RequestParam(required = false) String seller,
                                @RequestParam(required = false) String buyer,
                                Model model){
        if(seller !=null){
            if(seller.equals("")){
                seller=null;
            }
        }
        if(buyer != null){
            if(buyer.equals("")){
                buyer=null;
            }
        }
        var rows = transactionService.getHistoryRows(seller,buyer,page);
        var dropdownSeller = transactionService.getSellerDropdown();
        var dropdownBuyer=transactionService.getBuyerDropdown();
        model.addAttribute("grid", rows);
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("dropdownSeller",dropdownSeller);
        model.addAttribute("dropdownBuyer",dropdownBuyer);
        model.addAttribute("seller",seller);
        model.addAttribute("buyer",buyer);
        return "history/history-index";
    }
}
