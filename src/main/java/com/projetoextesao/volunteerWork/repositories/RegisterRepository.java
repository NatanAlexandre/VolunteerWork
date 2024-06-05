package com.projetoextesao.volunteerWork.repositories;

import com.projetoextesao.volunteerWork.models.RegisterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RegisterRepository extends JpaRepository<RegisterModel, UUID> {

    boolean existsByRegisterMail(String registerMail);
    boolean existsByRegisterPwd(String registerPwd);
    Optional<RegisterModel> findByRegisterMail(String registerMail);
    Optional<RegisterModel> findByRegisterPwd(String registerPwd);

}
