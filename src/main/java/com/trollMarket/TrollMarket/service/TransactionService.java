package com.trollMarket.TrollMarket.service;

import com.trollMarket.TrollMarket.dao.BuyerRepository;
import com.trollMarket.TrollMarket.dao.SellerRepository;
import com.trollMarket.TrollMarket.dao.TransactionRepository;
import com.trollMarket.TrollMarket.dto.transaction.TransactionHistoryRowDTO;
import com.trollMarket.TrollMarket.dto.transaction.TransactionRowDTO;
import com.trollMarket.TrollMarket.dto.utility.DropdownDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    public Page<TransactionRowDTO> getTransactionRows(String username,int page) {
        var pageable = PageRequest.of(page-1,10, Sort.by("id"));
        var rows = transactionRepository.getRow(username,pageable);
        return rows;
    }

    public Page<TransactionHistoryRowDTO> getHistoryRows(String seller, String buyer, int page) {
        var pageable = PageRequest.of(page-1,10, Sort.by("id"));
        var rows = transactionRepository.getHistoryRow(seller,buyer,pageable);
        return rows;
    }

    public List<String> getSellerDropdown(){
        return sellerRepository.getDropdownSeller();
    }

    public List<String> getBuyerDropdown(){
        return buyerRepository.getDropdownBuyer();
    }

}
