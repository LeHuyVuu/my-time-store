package com.boboibo.mytimestore.mapper;

import com.boboibo.mytimestore.model.entity.Customer;
import com.boboibo.mytimestore.model.entity.User;
import com.boboibo.mytimestore.model.request.UpdateUserRequest;
import com.boboibo.mytimestore.model.request.authentication.RegisterRequest;
import com.boboibo.mytimestore.model.response.user.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "status",ignore = true)
    @Mapping(target = "role",ignore = true)
    @Mapping(source ="fullname",target = "fullName")
    User toUser(RegisterRequest user);
//    @Mapping(source ="image",target = "image")
//    @Mapping(source ="fullName",target = "fullName")
//    @Mapping(source ="email",target = "email")
//    @Mapping(target = "role",ignore = true)
//    @Mapping(target = "status",ignore = true)
//    @Mapping(target = "username",ignore = true)
//    void updateUserToUser(UpdateUserRequest request);

    UserResponse toUserResponse(User user);
    @Mapping(source = "phoneNumber", target = "phone")
    @Mapping(source = "address", target = "address")
    Customer updateRequestToCustomer(UpdateUserRequest updateUserRequest);


    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "image", target = "image")
    @Mapping(target = "userId",ignore = true)
    User updateRequestToUser(UpdateUserRequest updateUserRequest);
    // Ánh xạ từ User sang UserResponse (tạo response trả về)
    UserResponse userToUserResponse(User user);



}
