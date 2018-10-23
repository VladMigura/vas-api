package com.bsuir.vas.converter;

import com.bsuir.vas.entity.AdminEntity;
import com.bsuir.vas.entity.TokenEntity;
import com.bsuir.vas.model.SignInModel;
import com.bsuir.vas.model.TokenModel;
import org.apache.commons.lang3.RandomStringUtils;

public class TokenConverter {

    public static TokenModel toTokenModel(TokenEntity tokenEntity) {

        return TokenModel.builder()
                .id(tokenEntity.getId())
                .tokenValue(tokenEntity.getValue())
                .adminId(tokenEntity.getAdminEntity().getId())
                .build();
    }

    public static TokenEntity toTokenEntity(SignInModel signInModel, long adminId) {

        return TokenEntity.builder()
                .value(RandomStringUtils.random(10, true, true))
                .adminEntity(AdminEntity.builder().id(adminId).build())
                .build();
    }
}
