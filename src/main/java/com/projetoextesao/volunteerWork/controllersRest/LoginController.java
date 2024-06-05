package com.projetoextesao.volunteerWork.controllersRest;

import com.projetoextesao.volunteerWork.dtos.LoginDto;
import com.projetoextesao.volunteerWork.models.RegisterModel;
import com.projetoextesao.volunteerWork.models.UserModel;
import com.projetoextesao.volunteerWork.responses.LoginResponse;
import com.projetoextesao.volunteerWork.services.RegisterService;
import com.projetoextesao.volunteerWork.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/login")
public class LoginController {

    final UserService userService;

    public LoginController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> saveLogin(@RequestBody @Valid LoginDto loginDto){
        Optional<UserModel> user = this.userService.findByUserMail(loginDto.getLoginMail());
        if (!user.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Invalid E-mail or Password");
        } else if (!user.get().getUserMail().equals(loginDto.getLoginMail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Invalid E-mail or Password");
        } else if (!user.get().getUserPwd().equals(loginDto.getLoginPwd())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Invalid E-mail or Password");
        }

        String token = generateToken(user.get());
        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponse(user.get().getUserId(), token));
    }

    @PostMapping("/profile")
    public ResponseEntity<?> profileUser(@RequestHeader("Authorization") String token){
        String userId = validateTokenAndGetUserId(token);
        if (userId != null){
            Optional<UserModel> user = this.userService.findUserById(UUID.fromString(userId));
            if (user.isPresent()) {
                return ResponseEntity.ok(user.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Token");
        }
    }

    private String generateToken(UserModel userModel){
        return Base64.getEncoder().encodeToString((userModel.getUserId() + ":" + userModel.getUserMail()).getBytes());
    }

    private String validateTokenAndGetUserId(String token){
        try {
            String decoded = new String(Base64.getDecoder().decode(token.replace("Bearer ", "")));
            return decoded.split(":")[0];
        } catch (Exception e){
            return null;
        }
    }

}
