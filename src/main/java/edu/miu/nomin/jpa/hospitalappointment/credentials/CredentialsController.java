package edu.miu.nomin.jpa.hospitalappointment.credentials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CredentialsController {
    @Autowired
    CredentialsService credentialsService;

    @PostMapping("/users")
    public CredentialsModel register(@RequestBody CredentialsModel credentialsModel) {
        return credentialsService.register(credentialsModel);
    }
}
