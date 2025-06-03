package com.ges_abs.security;

import com.ges_abs.data.models.enumeration.Role;
import com.ges_abs.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import com.ges_abs.data.models.entity.User;
import java.util.Collections;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User utilisateur = userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec le login : " + login));

        Role role = utilisateur.getRole();
        if (role == null || !"ADMIN".equalsIgnoreCase(role.name())) {
            throw new UsernameNotFoundException("Accès refusé : seul un administrateur peut se connecter");
        }
        //User Spring Security
        return new org.springframework.security.core.userdetails.User(
                utilisateur.getLogin(),
                utilisateur.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + utilisateur.getRole().name()))
        );
    }
}
