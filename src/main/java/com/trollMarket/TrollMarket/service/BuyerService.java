package com.trollMarket.TrollMarket.service;

import com.trollMarket.TrollMarket.dao.BuyerRepository;
import com.trollMarket.TrollMarket.dto.utility.HeaderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerService {

    @Autowired
    private BuyerRepository buyerRepository;

    public HeaderDTO getHeader(String username){
        var dto = buyerRepository.getHeader(username);
        return dto;
    }

    public void topUpBalance(Long id, Double topUp){
        var buyer = buyerRepository.findById(id).get();
        Double newBalanace = buyer.getBalance()+topUp;
        buyer.setBalance(newBalanace);
        buyerRepository.save(buyer);
    }

}
