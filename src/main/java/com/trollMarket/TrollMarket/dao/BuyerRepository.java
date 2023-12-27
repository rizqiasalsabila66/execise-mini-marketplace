package com.trollMarket.TrollMarket.dao;


import com.trollMarket.TrollMarket.dto.utility.DropdownDTO;
import com.trollMarket.TrollMarket.dto.utility.HeaderDTO;
import com.trollMarket.TrollMarket.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer,Long> {

    @Query("""
            SELECT new com.trollMarket.TrollMarket.dto.utility.HeaderDTO(buy.id, buy.name, acc.role,
            buy.address, buy.balance)
            FROM Buyer AS buy
            JOIN buy.account AS acc
            WHERE buy.username = :username
            """)
    public HeaderDTO getHeader(@Param("username") String username);

    @Query("""
            SELECT (buy.name)
            FROM Buyer AS buy
            """)
    public List<String> getDropdownBuyer();

    @Query("""
            SELECT buy.id
            FROM Buyer AS buy
            WHERE buy.username = :username
            """)
    public Long findBuyerIdByUsername(@Param("username") String username);
}
