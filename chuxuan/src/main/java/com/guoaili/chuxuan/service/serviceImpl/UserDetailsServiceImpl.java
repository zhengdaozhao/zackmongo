package com.guoaili.chuxuan.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.guoaili.chuxuan.entity.User;
import com.guoaili.chuxuan.enumT.RoleType;
import com.guoaili.chuxuan.repository.UserRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("无效的用户名和密码.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user.getRoles()));
    }

    private List<SimpleGrantedAuthority> getAuthority(List<RoleType> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getValue())).collect(Collectors.toUnmodifiableList());
    }


}
