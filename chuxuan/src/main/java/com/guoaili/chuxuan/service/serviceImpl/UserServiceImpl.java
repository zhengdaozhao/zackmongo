package com.guoaili.chuxuan.service.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.guoaili.chuxuan.DTO.UserVo;
import com.guoaili.chuxuan.config.JwtTokenService;
import com.guoaili.chuxuan.entity.User;
import com.guoaili.chuxuan.enumT.RoleType;
import com.guoaili.chuxuan.repository.UserRepository;
import com.guoaili.chuxuan.service.UserService;

@Service
public class UserServiceImpl implements UserService  {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findByName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(UserVo user) {
        User zpd=new User();
        zpd.setUsername(user.getUsername());
        zpd.setPassword(passwordEncoder.encode(user.getPassword()));

        List<RoleType> zpd001=new ArrayList<>();
        if(user.getUsername().contains("zhiping")) {
            zpd001.add(RoleType.USER);
            zpd001.add(RoleType.HZP);
        }
        else if(user.getUsername().contains("chuxuan")){
            zpd001.add(RoleType.USER);
            zpd001.add(RoleType.LCX);
        }
        else if(user.getUsername().contains("minjuan")){
            zpd001.add(RoleType.USER);
            zpd001.add(RoleType.LMJ);
        }
        else if(user.getUsername().contains("lina")){
            zpd001.add(RoleType.USER);
            zpd001.add(RoleType.TLN);
        }
        else if(user.getUsername().contains("jiandong")){
            zpd001.add(RoleType.ADMIN);
        }
        else {
            zpd001.add(RoleType.USER);
        }
        zpd.setRoles(zpd001);
        userRepository.save(zpd);
    }

    @Override
    public UserVo login(UserVo uv) {

        try{
            final Authentication authentication = authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            uv.getUsername(),
                            uv.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch(Exception err){
            return null;
        }
        final User user = userRepository.findByUsername(uv.getUsername());
        String token= jwtTokenService.generateToken(uv.getUsername(),user.getRoles());

        // return the role to username 2024/7/4
        uv.setUsername(null);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            uv.setUsername("long");
        }
        uv.setPassword(token);
        return uv;
    }


    @Override
    public User getUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername());
    }
    
}
