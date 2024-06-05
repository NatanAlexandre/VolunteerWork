package com.projetoextesao.volunteerWork.services;

import com.projetoextesao.volunteerWork.models.RegisterModel;
import com.projetoextesao.volunteerWork.repositories.RegisterRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RegisterService {

    final RegisterRepository registerRepository;

    public RegisterService(RegisterRepository registerRepository){
        this.registerRepository = registerRepository;
    }

    @Transactional
    public RegisterModel saveRegister(RegisterModel registerModel){
        return this.registerRepository.save(registerModel);
    }

    public List<RegisterModel> findAllRegisters(){
        return this.registerRepository.findAll();
    }

    public Optional findByRegisterId(UUID id){
        return this.registerRepository.findById(id);
    }

    @Transactional
    public void deleteRegister(RegisterModel registerModel){
        this.registerRepository.delete(registerModel);
    }

    public boolean existsByRegisterMail(String registerMail){
        return this.registerRepository.existsByRegisterMail(registerMail);
    }

    public boolean existsByRegisterPwd(String registerPwd){
        return this.registerRepository.existsByRegisterPwd(registerPwd);
    }

    public Optional<RegisterModel> findByRegisterMail(String registerMail){
        return this.registerRepository.findByRegisterMail(registerMail);
    }

    public Optional<RegisterModel> findByRegisterPwd(String registerPwd){
        return this.registerRepository.findByRegisterPwd(registerPwd);
    }

}
