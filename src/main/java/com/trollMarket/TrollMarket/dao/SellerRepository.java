package com.trollMarket.TrollMarket.dao;

import com.trollMarket.TrollMarket.dto.utility.DropdownDTO;
import com.trollMarket.TrollMarket.dto.utility.HeaderDTO;
import com.trollMarket.TrollMarket.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Long> {

    @Query("""
            SELECT new com.trollMarket.TrollMarket.dto.utility.HeaderDTO(sel.id, sel.name, acc.role,
            sel.address, sel.balance)
            FROM Seller AS sel
            JOIN sel.account AS acc
            WHERE sel.username = :username
            """)
    public HeaderDTO getHeader(@Param("username") String username);

    @Query("""
            SELECT sel.id
            FROM Seller AS sel
            WHERE sel.username = :username
            """)
    public Long findSellerIdByUsername(@Param("username") String username);

    @Query("""
            SELECT (sel.name)
            FROM Seller AS sel
            """)
    public List<String> getDropdownSeller();
}
