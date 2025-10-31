package com.katkat_maker.Blog.Security;

import com.katkat_maker.Blog.Domain.entities.User;
import com.katkat_maker.Blog.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BlogUserDetails implements UserDetailsService {
   private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).
                orElseThrow(() -> new UsernameNotFoundException("User not foud by username:  " + username));
    return new BlogUserSecurity(user);
    }

}
