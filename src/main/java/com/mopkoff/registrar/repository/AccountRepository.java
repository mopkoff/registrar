package com.mopkoff.registrar.repository;

import com.mopkoff.registrar.repository.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}
