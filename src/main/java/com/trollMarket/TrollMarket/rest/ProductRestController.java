package com.trollMarket.TrollMarket.rest;

import com.trollMarket.TrollMarket.dto.product.ProductDetailDTO;
import com.trollMarket.TrollMarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductRestController {

    @Autowired
    private ProductService service;

    @GetMapping("/detail/{id}")
    public ProductDetailDTO getDetail(@PathVariable(required = false) Long id){
        var dto= service.getDetailProduct(id);
        return dto;
    }

}
