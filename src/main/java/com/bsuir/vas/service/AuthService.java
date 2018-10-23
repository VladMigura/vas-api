package com.bsuir.vas.service;

import com.bsuir.vas.model.AdminModel;
import com.bsuir.vas.model.SignInModel;
import com.bsuir.vas.model.SignUpModel;
import com.bsuir.vas.model.TokenModel;

import java.util.List;

public interface AuthService {

    List<AdminModel> getAdmins();
    AdminModel getAdmin(long adminId);
    AdminModel updateAdmin(long adminId, SignUpModel signUpModel);
    void deleteAdmin(long adminId);

    void signUpAdmin(SignUpModel signUpModel);
    TokenModel signInAdmin(SignInModel signInModel);
}
