package com.bsuir.vas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipantModelForView {

    private long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private List<Long> paymentYears;
}
