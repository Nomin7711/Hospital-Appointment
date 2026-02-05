package edu.miu.nomin.jpa.hospitalappointment.credentials;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialsRepository extends JpaRepository<CredentialsModel, Long> {
    CredentialsModel findByUsername(String username);
}
