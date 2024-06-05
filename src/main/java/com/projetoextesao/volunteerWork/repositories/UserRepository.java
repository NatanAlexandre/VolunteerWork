package com.projetoextesao.volunteerWork.repositories;

import com.projetoextesao.volunteerWork.dtos.UserDto;
import com.projetoextesao.volunteerWork.models.RegisterModel;
import com.projetoextesao.volunteerWork.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
    Optional<UserModel> findByUserMail(String registerMail);
    Optional<UserModel> findByUserPwd(String registerPwd);
}
