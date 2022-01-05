package com.mopkoff.registrar.repository;

import com.mopkoff.registrar.model.repository.UserEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

}
