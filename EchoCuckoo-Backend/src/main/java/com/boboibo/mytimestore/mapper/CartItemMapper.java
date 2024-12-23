package com.boboibo.mytimestore.mapper;

import com.boboibo.mytimestore.model.entity.CartItem;
import com.boboibo.mytimestore.model.response.cart.CartItemResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    List<CartItemResponse> cartItemResponseList(List<CartItem> cartItem);
    CartItemResponse cartItemResponse(CartItem cartItem);
}
