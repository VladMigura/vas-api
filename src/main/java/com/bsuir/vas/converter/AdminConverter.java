package com.bsuir.vas.converter;

import com.bsuir.vas.entity.AdminEntity;
import com.bsuir.vas.model.AdminModel;
import com.bsuir.vas.model.SignUpModel;

import java.util.ArrayList;
import java.util.List;

public class AdminConverter {

    public static AdminModel toAdminModel(AdminEntity adminEntity) {

        return AdminModel.builder()
                .id(adminEntity.getId())
                .adminName(adminEntity.getAdminName())
                .email(adminEntity.getEmail())
                .build();
    }

    public static List<AdminModel> toAdminModels(List<AdminEntity> adminEntities) {

        List<AdminModel> adminModels = new ArrayList<>();

        adminEntities.forEach(adminEntity -> {
            adminModels.add(AdminConverter.toAdminModel(adminEntity));
        });

        return adminModels;
    }

    public static AdminEntity toAdminEntity(SignUpModel signUpModel, String hashPassword) {

        return AdminEntity.builder()
                .adminName(signUpModel.getAdminName())
                .hashPassword(hashPassword)
                .email(signUpModel.getEmail())
                .build();
    }
}
