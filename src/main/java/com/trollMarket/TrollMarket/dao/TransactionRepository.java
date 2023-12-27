package com.trollMarket.TrollMarket.dao;

import com.trollMarket.TrollMarket.dto.transaction.TransactionHistoryRowDTO;
import com.trollMarket.TrollMarket.dto.transaction.TransactionRowDTO;
import com.trollMarket.TrollMarket.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Orders,Long> {

    @Query("""
            SELECT new com.trollMarket.TrollMarket.dto.transaction.TransactionRowDTO(ord.id, ord.date, pro.name, ord.quantity, 
            shi.name, (ord.quantity*ord.productPrice)+ord.shipmentPrice)
            FROM Orders AS ord
            JOIN ord.product AS pro
            JOIN ord.shipment AS shi
            JOIN ord.buyer AS buy
            JOIN pro.seller AS sel
            WHERE buy.username =:username OR sel.username = :username
            """)
    public Page<TransactionRowDTO> getRow(@Param("username") String username, Pageable pageable);

    @Query("""
            SELECT SUM((ord.quantity*pro.price)+shi.price)
            FROM Orders AS ord
            JOIN ord.product AS pro
            JOIN pro.seller AS sel
            JOIN ord.shipment AS shi
            WHERE sel.username = :username
            """)
    public Double getBalanceSeller(@Param("username") String username);

    @Query("""
            SELECT COUNT(ord.id)
            FROM Orders AS ord
            WHERE ord.productId = :productId
            """)
    public Long getTransactionByProduct(@Param("productId") Long productId);

    @Query("""
            SELECT COUNT(ord.id)
            FROM Orders AS ord
            WHERE ord.shipmentId = :shipmentId
            """)
    public Long getTransactionByShipment(@Param("shipmentId") Long shipmentId);

    @Query("""
            SELECT new com.trollMarket.TrollMarket.dto.transaction.TransactionHistoryRowDTO(ord.id, ord.date, sel.name,buy.name,
            pro.name, ord.quantity, shi.name, (ord.quantity*ord.productPrice)+ord.shipmentPrice)
            FROM Orders AS ord
            JOIN ord.product AS pro
            JOIN ord.shipment AS shi
            JOIN ord.buyer AS buy
            JOIN pro.seller AS sel
            WHERE 
            (sel.name = :seller OR :seller IS NULL) AND
            (buy.name= :buyer OR :buyer IS NULL)
            """)
    public Page<TransactionHistoryRowDTO> getHistoryRow(@Param("seller") String seller, @Param("buyer") String buyer,
                                                        Pageable pageable);
}
