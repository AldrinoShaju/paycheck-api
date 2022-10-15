package com.familyshop.paybackcheckapi.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @Generated
    private String txnId;
    private String txnNote;
    private Integer totalAmount;
    private Integer payable;
    private String createdOn;
    private String updatedOn;
}

/*{
      "txnId": "string",
      "txnNote": "string",
      "totalAmount": "number",
      "payable": "number",
      "createdOn": "string",
      "updatedOn": "string"
    }
    */