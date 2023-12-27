package com.trollMarket.TrollMarket.controller;

import com.trollMarket.TrollMarket.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/shipment")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @GetMapping("/index")
    public String getProductRow(@RequestParam(defaultValue = "1") int page,
                                Model model){
        var rows = shipmentService.getTransactionRows(page);
        model.addAttribute("grid", rows);
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        return "shipment/shipment-index";
    }

    @GetMapping("/service")
    public String stopService(@RequestParam Long id){
        shipmentService.stopService(id);
        return "redirect:/shipment/index";
    }
}
