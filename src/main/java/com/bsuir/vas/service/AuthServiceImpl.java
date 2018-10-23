package com.bsuir.vas.service;

import com.bsuir.vas.converter.AdminConverter;
import com.bsuir.vas.converter.TokenConverter;
import com.bsuir.vas.entity.AdminEntity;
import com.bsuir.vas.entity.TokenEntity;
import com.bsuir.vas.exception.BadRequestException;
import com.bsuir.vas.exception.NotFoundException;
import com.bsuir.vas.model.AdminModel;
import com.bsuir.vas.model.SignInModel;
import com.bsuir.vas.model.SignUpModel;
import com.bsuir.vas.model.TokenModel;
import com.bsuir.vas.repository.AdminRepository;
import com.bsuir.vas.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @PreAuthorize("@securityUtility.isAuthenticated()")
    public List<AdminModel> getAdmins() {

        return AdminConverter.toAdminModels(adminRepository.findAll());
    }

    @Override
    @PreAuthorize("@securityUtility.isAuthenticated()")
    public AdminModel getAdmin(long adminId) {

        AdminEntity adminEntity = adminRepository.findOneById(adminId);

        if(adminEntity != null) {
            return AdminConverter.toAdminModel(adminRepository.findOneById(adminId));
        }

        throw new NotFoundException("Admin is not found!");
    }

    @Override
    @PreAuthorize("@securityUtility.isAuthenticated()")
    public AdminModel updateAdmin(long adminId, SignUpModel signUpModel) {

        AdminEntity adminEntity = adminRepository.findOneById(adminId);

        if(adminEntity != null) {
            adminEntity.setAdminName(signUpModel.getAdminName());
            adminEntity.setEmail(signUpModel.getEmail());
            adminEntity.setHashPassword(passwordEncoder.encode(signUpModel.getPassword()));

            return AdminConverter.toAdminModel(adminRepository.save(adminEntity));
        }

        throw new NotFoundException("Admin is not found!");
    }

    @Override
    @PreAuthorize("@securityUtility.isAuthenticated()")
    public void deleteAdmin(long adminId) {

        AdminEntity adminEntity = adminRepository.findOneById(adminId);

        if(adminEntity != null) {
            adminRepository.deleteOneById(adminId);
        } else {
            throw new NotFoundException("Admin is not found!");
        }
    }

    @Override
    @PreAuthorize("@securityUtility.isAuthenticated()")
    public void signUpAdmin(SignUpModel signUpModel) {

        AdminEntity adminEntity = adminRepository.findOneByAdminName(signUpModel.getAdminName());

        if(adminEntity == null) {
            adminEntity = AdminConverter.toAdminEntity(signUpModel,
                    passwordEncoder.encode(signUpModel.getPassword()));

            adminRepository.save(adminEntity);
        } else {
            throw new BadRequestException("Admin with the same name already exists!");
        }
    }

    @Override
    public TokenModel signInAdmin(SignInModel signInModel) {

        AdminEntity adminEntity = adminRepository.findOneByAdminName(signInModel.getAdminName());

        if(adminEntity != null && passwordEncoder.matches(signInModel.getPassword(), adminEntity.getHashPassword())) {
            TokenEntity tokenEntity = TokenConverter.toTokenEntity(signInModel, adminEntity.getId());

            return TokenConverter.toTokenModel(tokenRepository.save(tokenEntity));
        }

        throw new NotFoundException("You typed incorrect admin name or password!");
    }
}
