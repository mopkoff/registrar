package com.mopkoff.registrar.repository;

import com.mopkoff.registrar.model.repository.ProxyEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProxyRepository extends JpaRepository<ProxyEntity, UUID> {

}
