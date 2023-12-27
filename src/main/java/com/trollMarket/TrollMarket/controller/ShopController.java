package com.trollMarket.TrollMarket.controller;

import com.trollMarket.TrollMarket.service.ProductService;
import com.trollMarket.TrollMarket.service.ShopService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ShopService shopService;

    @GetMapping("/index")
    public String getProductRow(@RequestParam(required = false) String name,
                                @RequestParam(required = false) String category,
                                @RequestParam(required = false) String description,
                                @RequestParam String username,
                                @RequestParam(defaultValue = "1") int page,
                                Model model){
        var rows = productService.getProductShopRow(page,name,category,description);
        var dropdownShipment = shopService.getDropdownShipment();
        model.addAttribute("grid", rows);
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("name",name);
        model.addAttribute("category",category);
        model.addAttribute("description",description);
        model.addAttribute("username",username);
        model.addAttribute("dropdownShipment",dropdownShipment);
        return "shop/shop-index";
    }


}
