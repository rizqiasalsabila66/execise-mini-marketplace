package com.trollMarket.TrollMarket.service;

import com.fasterxml.jackson.databind.util.ArrayIterator;
import com.trollMarket.TrollMarket.dao.BuyerRepository;
import com.trollMarket.TrollMarket.dao.CartRepository;
import com.trollMarket.TrollMarket.dao.TransactionRepository;
import com.trollMarket.TrollMarket.dto.cart.CartRowDTO;
import com.trollMarket.TrollMarket.dto.cart.PurchaseRowDTO;
import com.trollMarket.TrollMarket.dto.transaction.TransactionRowDTO;
import com.trollMarket.TrollMarket.entity.Orders;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    public Page<CartRowDTO> getCartRows(String username, int page) {
        var pageable = PageRequest.of(page-1,10, Sort.by("id"));
        var rows = cartRepository.getRow(username,pageable);
        return rows;
    }

    public void delete(Long id){
        cartRepository.deleteById(id);
    }

    public void purchaseAll(String username){
        var listCart = cartRepository.getAllCart(username);
        for (PurchaseRowDTO cart : listCart) {
            var order = new Orders();
            order.setDate(LocalDate.now());
            order.setBuyerId(cart.getBuyerId());
            order.setProductId(cart.getProductId());
            order.setQuantity(cart.getQuantity());
            order.setShipmentId(cart.getShipmentId());
            order.setProductPrice(cart.getProductPrice());
            order.setShipmentPrice(cart.getShipmentPrice());
            transactionRepository.save(order);
            var buyer = buyerRepository.findById(cart.getBuyerId()).get();
            buyer.setBalance(buyer.getBalance()-((cart.getProductPrice()* cart.getQuantity())+ cart.getShipmentPrice()));
            buyerRepository.save(buyer);
        }
        cartRepository.deleteAll();
    }

    public Double notEnoughBalance(String username){
        var total = cartRepository.getTotal(username);
        var buyerId = buyerRepository.findBuyerIdByUsername(username);
        var balance = buyerRepository.findById(buyerId).get().getBalance();
        return total-balance;
    }
}
