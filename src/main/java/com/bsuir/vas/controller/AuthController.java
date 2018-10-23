package com.bsuir.vas.controller;

import com.bsuir.vas.model.AdminModel;
import com.bsuir.vas.model.SignInModel;
import com.bsuir.vas.model.SignUpModel;
import com.bsuir.vas.model.TokenModel;
import com.bsuir.vas.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("auth/admins")
    public List<AdminModel> getAdmins() {

        return authService.getAdmins();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("auth/admins/{adminId}")
    public AdminModel getAdmin(@PathVariable long adminId) {

        return authService.getAdmin(adminId);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping("auth/admins/{adminId}")
    @ResponseStatus(HttpStatus.CREATED)
    public AdminModel updateAdmin(@PathVariable long adminId,
                                  @RequestBody SignUpModel signUpModel) {

        return authService.updateAdmin(adminId, signUpModel);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("auth/admins/{adminId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAdmin(@PathVariable long adminId) {

        authService.deleteAdmin(adminId);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("auth/signUp")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUpAdmin(@RequestBody SignUpModel signUpModel) {

        authService.signUpAdmin(signUpModel);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("auth/signIn")
    public TokenModel signInAdmin(@RequestBody SignInModel signInModel) {

        return authService.signInAdmin(signInModel);
    }
}