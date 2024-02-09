package com.jnit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface Login_CredentialsRepository extends JpaRepository<Login_Credentials, Integer> {
	Login_Credentials findByUsername(String username);
}
