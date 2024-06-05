package com.projetoextesao.volunteerWork.controllersRest;

import com.projetoextesao.volunteerWork.dtos.LoginDto;
import com.projetoextesao.volunteerWork.dtos.UserDto;
import com.projetoextesao.volunteerWork.models.UserModel;
import com.projetoextesao.volunteerWork.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDto userDto){
        if (!this.userService.userValidation(userDto)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Invalid Information!");
        }
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers(){
        List<UserModel> listUser = userService.findALlUsers();
        if (!listUser.isEmpty()){
            for (UserModel user : listUser){
                UUID id = user.getUserId();
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(listUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "id") UUID id){
        Optional<UserModel> user = userService.findUserById(id);
        if (!user.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id: " + id + ", not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(user.get());
    }



    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id") UUID id,
                                             @RequestBody @Valid UserDto userDto){
        Optional<UserModel> user = userService.findUserById(id);
        if (!user.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id: " + id + ", not found!");
        }
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setUserId(user.get().getUserId());
        return ResponseEntity.status(HttpStatus.OK).body(userService.save(userModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") UUID id){
        Optional<UserModel> user = userService.findUserById(id);
        if (!user.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id: " + id + ", not found!");
        }
        userService.delete(user.get());
        return ResponseEntity.status(HttpStatus.OK).body("User: " + user.get().getUserName() + " deleted successfully");
    }
}
