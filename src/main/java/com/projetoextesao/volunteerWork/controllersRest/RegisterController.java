package com.projetoextesao.volunteerWork.controllersRest;

import com.projetoextesao.volunteerWork.dtos.RegisterDto;
import com.projetoextesao.volunteerWork.models.RegisterModel;
import com.projetoextesao.volunteerWork.models.UserModel;
import com.projetoextesao.volunteerWork.services.RegisterService;
import com.projetoextesao.volunteerWork.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/registers")
public class RegisterController {

    final RegisterService registerService;
    final UserService userService;

    public RegisterController(RegisterService registerService, UserService userService){
        this.registerService = registerService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> saveRegister(@RequestBody @Valid RegisterDto registerDto){
        if (!registerDto.getRegisterMail().equals(registerDto.getRegisterMailConfirm())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Invalid E-mail!");
        } else if (!registerDto.getRegisterPwd().equals(registerDto.getRegisterPwdConfirm())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Invalid Password!");
        } else if (!registerDto.isTerm()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("unfortunately it is not possible to continue!");
        }
        var registerModel = new RegisterModel();
        var userModel = new UserModel();
        BeanUtils.copyProperties(registerDto, registerModel);
        userModel.setUserMail(registerDto.getRegisterMail());
        userModel.setUserName(registerDto.getRegisterName());
        userModel.setUserPwd(registerDto.getRegisterPwd());
        userModel.setActive(true);
        userModel.setVolunteer(registerDto.isVolunteer());
        userModel.setOngs(registerDto.getRegisterOng());
        this.userService.save(userModel);
        return ResponseEntity.status(HttpStatus.OK).body(this.registerService.saveRegister(registerModel));
    }

    @GetMapping
    public ResponseEntity<List<RegisterModel>> getAllRegisters(){
        List<RegisterModel> listRegister = this.registerService.findAllRegisters();
        if (!listRegister.isEmpty()){
            for (RegisterModel register : listRegister){
                UUID id = register.getRegisterId();
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(listRegister);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneRegister(@PathVariable(value = "id") UUID id){
        Optional<RegisterModel> register = this.registerService.findByRegisterId(id);
        if (!register.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Register with ID: " + id + " not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(register.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRegister(@PathVariable(value = "id") UUID id,
                                                 @RequestBody @Valid RegisterDto registerDto){
        Optional<RegisterModel> register = this.registerService.findByRegisterId(id);
        if (!register.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Register with ID: " + id + " not found!");
        }
        var registerModel = new RegisterModel();
        BeanUtils.copyProperties(registerDto, registerModel);
        registerModel.setRegisterId(register.get().getRegisterId());
        return ResponseEntity.status(HttpStatus.OK).body(this.registerService.saveRegister(registerModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRegister(@PathVariable(value = "id") UUID id){
        Optional<RegisterModel> register = this.registerService.findByRegisterId(id);
        if (!register.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Register with ID: " + id + " not found!");
        }
        this.registerService.deleteRegister(register.get());
        return ResponseEntity.status(HttpStatus.OK).body("Register deleted successfully");
    }

}
