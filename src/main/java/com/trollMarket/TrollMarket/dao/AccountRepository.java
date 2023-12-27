package com.trollMarket.TrollMarket.dao;

import com.trollMarket.TrollMarket.entity.Account;
import com.trollMarket.TrollMarket.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account,String> {

    @Query("""
            SELECT COUNT(acc.username)
            FROM Account AS acc
            WHERE acc.username = :username
            """)
    public Long countExistingUser(@Param("username") String username);
}

