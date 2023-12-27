package com.trollMarket.TrollMarket.service;

import com.trollMarket.TrollMarket.dao.ProductRepository;
import com.trollMarket.TrollMarket.dao.SellerRepository;
import com.trollMarket.TrollMarket.dao.TransactionRepository;
import com.trollMarket.TrollMarket.dto.utility.HeaderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ProductRepository productRepository;

    public HeaderDTO getHeader(String username){
        var dto = sellerRepository.getHeader(username);
        dto.setBalance(transactionRepository.getBalanceSeller(username));
        return dto;
    }

    public Long getIdByUsername(String username){
        var id = sellerRepository.findSellerIdByUsername(username);
        return id;
    }

    public Long getIdByProduct(Long idProduct){
        var product = productRepository.findById(idProduct).get();
        var id = product.getSeller().getId();
        return id;
    }




}
