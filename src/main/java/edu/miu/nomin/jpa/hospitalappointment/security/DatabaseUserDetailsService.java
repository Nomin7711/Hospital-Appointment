package edu.miu.nomin.jpa.hospitalappointment.security;

import edu.miu.nomin.jpa.hospitalappointment.credentials.CredentialsModel;
import edu.miu.nomin.jpa.hospitalappointment.credentials.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {
    @Autowired
    private CredentialsRepository credentialsRepository;

    @Autowired
    public DatabaseUserDetailsService(CredentialsRepository credentialsRepository) {
        this.credentialsRepository = credentialsRepository;
    }

    public UserDetails loadUserByUsername(String username) {
        CredentialsModel credentialsModel = credentialsRepository.findByUsername(username);
        if (credentialsModel == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
//        return new User(credentialsModel.getUsername(), credentialsModel.getPassword(),
//                List.of(new SimpleGrantedAuthority("ROLE_USER")));
        return new UserPrincipal(credentialsModel);
    }
}
