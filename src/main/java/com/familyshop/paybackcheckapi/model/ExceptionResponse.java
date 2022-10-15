package com.familyshop.paybackcheckapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    private String errorName;
    private String errorMessage;
    private String currentTimeStamp;
}
