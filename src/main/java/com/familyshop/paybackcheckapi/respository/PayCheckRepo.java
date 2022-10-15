package com.familyshop.paybackcheckapi.respository;

import com.familyshop.paybackcheckapi.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PayCheckRepo extends MongoRepository<Customer, String> {

//    @Query(value = "{'custId': {$eq: ?0}}, 'txnId': {$eq: ?1}", fields = "{}")
//    Customer getTxndataByID(String custId, String txnId);
}
