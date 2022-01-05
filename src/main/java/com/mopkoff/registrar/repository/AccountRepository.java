package com.mopkoff.registrar.repository;

import com.mopkoff.registrar.model.repository.AccountEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {

}
