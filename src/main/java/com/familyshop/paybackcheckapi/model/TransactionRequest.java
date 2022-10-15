package com.familyshop.paybackcheckapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
    private String txnNote;
    private Integer totalAmount;
    private Integer payed;
}

/*{
  "txnNote": "string",
  "totalAmount": "number",
  "payed": "number"
}*/