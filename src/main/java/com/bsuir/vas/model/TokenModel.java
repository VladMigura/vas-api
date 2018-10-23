package com.bsuir.vas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenModel {

    private long id;
    private String tokenValue;
    private long adminId;
}
