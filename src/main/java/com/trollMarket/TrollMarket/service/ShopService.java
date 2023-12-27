package com.trollMarket.TrollMarket.service;

import com.trollMarket.TrollMarket.dao.BuyerRepository;
import com.trollMarket.TrollMarket.dao.CartRepository;
import com.trollMarket.TrollMarket.dao.ShipmentRepository;
import com.trollMarket.TrollMarket.dto.shop.AddCartDTO;
import com.trollMarket.TrollMarket.dto.utility.DropdownDTO;
import com.trollMarket.TrollMarket.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;

    public void addCart(AddCartDTO dto){
        var buyerId = buyerRepository.findBuyerIdByUsername(dto.getUsernameBuyer());
        var entity = new Cart();
        entity.setProductId(dto.getProductId());
        entity.setBuyerId(buyerId);
        entity.setShipmentId(dto.getShipmentId());
        entity.setQuantity(dto.getQuantity());
        cartRepository.save(entity);
    }

    public List<DropdownDTO> getDropdownShipment(){
        return shipmentRepository.getDropdownShipment();
    }
}
