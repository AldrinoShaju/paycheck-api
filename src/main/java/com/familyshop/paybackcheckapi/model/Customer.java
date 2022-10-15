package com.familyshop.paybackcheckapi.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "paycheck")
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    private String custId;
    private String custName;
    private String phoneNumber;
    private String createdOn;
    private Integer totalPayable;
    private List<Transaction> txnList;
}
