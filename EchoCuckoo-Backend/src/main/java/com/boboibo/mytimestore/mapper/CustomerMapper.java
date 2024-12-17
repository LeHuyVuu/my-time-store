package com.boboibo.mytimestore.mapper;

import com.boboibo.mytimestore.model.entity.Customer;
import com.boboibo.mytimestore.model.entity.User;
import com.boboibo.mytimestore.model.request.UpdateUserRequest;
import com.boboibo.mytimestore.model.response.customer.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer userToCustomer(User user);
//    @Mapping(target = "userId",ignore = true)
    @Mapping(source ="phoneNumber",target = "phone")
    @Mapping(source ="address",target = "address")
    Customer updateUserToCustomer(UpdateUserRequest request);
    CustomerResponse customerToCustomerResponse(Customer customer);
}
