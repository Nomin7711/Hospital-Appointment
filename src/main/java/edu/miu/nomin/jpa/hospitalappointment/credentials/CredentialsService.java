package edu.miu.nomin.jpa.hospitalappointment.credentials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class CredentialsService {
    @Autowired
    private CredentialsRepository credentialsRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private InMemoryUserDetailsManager userDetailsManager;

    public CredentialsModel register(CredentialsModel credentialsModel) {
//        String encodedPassword = passwordEncoder.encode(credentialsModel.getPassword());
//        credentialsModel.setPassword(encodedPassword);
//        return credentialsRepository.save(credentialsModel);
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode(credentialsModel.getPassword());
        UserDetails regularUser = User.builder()
                .username(credentialsModel.getUsername())
                .password(encodedPassword)
                .roles(credentialsModel.getRole())
                .build();
        credentialsModel.setPassword(encodedPassword);
        userDetailsManager.createUser(regularUser);
        return credentialsRepository.save(credentialsModel);
    }

}
