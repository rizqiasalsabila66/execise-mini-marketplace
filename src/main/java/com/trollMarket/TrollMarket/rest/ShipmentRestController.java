package com.trollMarket.TrollMarket.rest;

import com.trollMarket.TrollMarket.dto.shipment.ShipmentUpsertDTO;
import com.trollMarket.TrollMarket.service.ShipmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipment")
public class ShipmentRestController {

    @Autowired
    private ShipmentService shipmentService;

    @PostMapping
    public String post(@Valid @RequestBody ShipmentUpsertDTO dto, BindingResult bindingResult){
        if(!bindingResult.hasErrors()) {
            shipmentService.save(dto);
            return "berhasil";
        }
        return "gagal";

    }
    @GetMapping("/{id}")
    public ShipmentUpsertDTO getData(@PathVariable(required = true) Long id){
        var dto = shipmentService.findOne(id);
        return  dto;
    }


    @PutMapping
    public String put(@RequestBody ShipmentUpsertDTO dto, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            shipmentService.save(dto);
            return "berhasil";
        }
        return "gagal";
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable(required = true) Long id)//kalau parameternya 1 pakai path variable
    {
        var dependencies = shipmentService.depandencies(id);
        if (dependencies == 0){
            shipmentService.delete(id);
            return dependencies;
        }
        return dependencies;
    }
}
