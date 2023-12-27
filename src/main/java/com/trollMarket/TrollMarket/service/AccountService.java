package com.trollMarket.TrollMarket.service;

import com.trollMarket.TrollMarket.dao.AccountRepository;
import com.trollMarket.TrollMarket.dto.account.RegisterDTO;
import com.trollMarket.TrollMarket.entity.Account;
import com.trollMarket.TrollMarket.utility.ApplicationUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;


    public Boolean isUsernameExist(String username){
        var total = accountRepository.countExistingUser(username);
        return (total>0); //jika benar mengembalikan true
    }

    public void register(RegisterDTO dto){
        var entity  = new Account();
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());
        accountRepository.save(entity);
    }


    public String getRole(String username){
        var role = accountRepository.findById(username).get().getRole();
        return role;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        var account = accountRepository.findById(username).get();
//            var userDetail = new ApplicationUserDetails(account);
//            return userDetail;
//
//    }
}
