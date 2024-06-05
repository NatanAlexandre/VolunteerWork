package com.projetoextesao.volunteerWork.services;

import com.projetoextesao.volunteerWork.dtos.ProjectDto;
import com.projetoextesao.volunteerWork.dtos.UserDto;
import com.projetoextesao.volunteerWork.models.RegisterModel;
import com.projetoextesao.volunteerWork.models.UserModel;
import com.projetoextesao.volunteerWork.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public UserModel save(UserModel user){
        return this.userRepository.save(user);
    }

    public Optional<UserModel> findUserById(UUID id){
        return this.userRepository.findById(id);
    }

    public List<UserModel> findALlUsers(){
        return this.userRepository.findAll();
    }

    @Transactional
    public void delete(UserModel user){
        this.userRepository.delete(user);
    }

    public boolean userValidation(UserDto user){
        boolean validated;
        if (user.getUserName().isEmpty()){
            validated = false;
        } else if (user.getUserPwd().isEmpty()){
            validated = false;
        } else if (user.getUserMail().isEmpty()){
            validated = false;
        } else {
            validated = true;
        }
        return validated;
    }

    public Optional<UserModel> findByUserMail(String userMail){
        return this.userRepository.findByUserMail(userMail);
    }

    public Optional<UserModel> findByUserPwd(String userPwd){
        return this.userRepository.findByUserPwd(userPwd);
    }

}
