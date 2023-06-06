package com.example.security.Service;

import com.example.security.Model.MyUser;
import com.example.security.Repository.AuthRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final AuthRespository authRespository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser=authRespository.findMyUserByUsername(username);
        if(username==null){
            throw new UsernameNotFoundException("wrong username or password");
        }
        return myUser;
    }
}
