package com.trollMarket.TrollMarket.rest;

import com.trollMarket.TrollMarket.dto.buyer.BuyerBalanceDTO;
import com.trollMarket.TrollMarket.service.BuyerService;
import jakarta.validation.Valid;
import org.hibernate.dialect.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/api/buyer")
public class BuyerRestController {

    @Autowired
    private BuyerService buyerService;

    @PatchMapping
    public String topUpBalance(@Valid @RequestBody BuyerBalanceDTO dto, BindingResult bindingResult
                               ){
        if (bindingResult.hasErrors() == false) {
            buyerService.topUpBalance(dto.getId(),dto.getBalance());
            return "berhasil";
        }
        return "gagal";
    }
}
