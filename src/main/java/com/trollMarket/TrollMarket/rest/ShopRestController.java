package com.trollMarket.TrollMarket.rest;

import com.trollMarket.TrollMarket.dto.shop.AddCartDTO;
import com.trollMarket.TrollMarket.service.ShopService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shop")
public class ShopRestController {

    @Autowired
    private ShopService shopService;

    @PostMapping
    public String post(@Valid @RequestBody AddCartDTO dto, BindingResult bindingResult){
        if(!bindingResult.hasErrors()) {
            shopService.addCart(dto);
            return "berhasil";
        }
        return "gagal";

    }
}
