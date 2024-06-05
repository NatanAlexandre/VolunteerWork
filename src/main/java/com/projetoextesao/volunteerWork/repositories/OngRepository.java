package com.projetoextesao.volunteerWork.repositories;

import com.projetoextesao.volunteerWork.models.OngModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OngRepository extends JpaRepository<OngModel, UUID> {
}
