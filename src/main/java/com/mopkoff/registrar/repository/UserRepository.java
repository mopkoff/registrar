package com.mopkoff.registrar.repository;

import com.mopkoff.registrar.repository.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
