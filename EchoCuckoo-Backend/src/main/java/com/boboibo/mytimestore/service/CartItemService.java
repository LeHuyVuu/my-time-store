package com.boboibo.mytimestore.service;

import com.boboibo.mytimestore.exception.AppException;
import com.boboibo.mytimestore.exception.ErrorCode;
import com.boboibo.mytimestore.mapper.CartItemMapper;
import com.boboibo.mytimestore.model.entity.CartItem;
import com.boboibo.mytimestore.model.request.CartItemRequest;
import com.boboibo.mytimestore.model.response.cart.CartItemResponse;
import com.boboibo.mytimestore.repository.CartItemRepository;
import com.boboibo.mytimestore.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CartItemService {
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    CartItemMapper cartItemMapper ;

    @Autowired
    ProductService productService;

    public List<CartItemResponse> getAll(){
        try{
            List<CartItem> cartItems = cartItemRepository.findAll();
            return cartItemMapper.cartItemResponseList(cartItems);  }
        catch (DataAccessException e) {
            log.error("Database error occurred while fetching active products", e);
            throw new AppException(ErrorCode.DATABASE_EXCEPTION, "Error while fetching active products");
        } catch (Exception e) {
            log.error("Unexpected error occurred", e);
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }
//    public CartItemResponse createCartItems(Long userId,CartItemRequest cartItemRequest){
//        long productId = 1 ;
//        long userId1 = 1 ;
//        CartItem cartItem = cartItemRepository.findByProduct_ProductIdAndUser_UserId(productId,userId1);
//        return
//        }
    public CartItemResponse getById(Long id){
        CartItem cartItems = cartItemRepository.findById(id).orElse(null);
        if(cartItems == null){
            throw new AppException(ErrorCode.CART_NOT_EXIST);
        }
        return cartItemMapper.cartItemResponse(cartItems);
    }



















































































































//
//    // get by userID
//    public List<CartResponse> getMyCart(String userId) {
//        List<CartItem> list = cartItemRepository.findByUser_UserId(userId);
//        List<CartResponse> cartResponseList = new ArrayList<>();
//        for (CartItem cartItem : list) {
//            CartResponse cartResponse = CartResponse.builder()
//                    .id(cartItem.getId())
//                    .productId(cartItem.getProduct().getProductId())
//                    .productName(cartItem.getProduct().getProductName())
//                    .quantity(cartItem.getQuantity())
//                    .image(cartItem.getProduct().getImage())
//                    .userId(cartItem.getUser().getUserId())
//                    .price(cartItem.getProduct().getPrice())
//                    .build();
//            cartResponseList.add(cartResponse);
//        }
//        return cartResponseList;
//    }
//
//
//    public boolean addToCart(User user, CartItemRequest cartItemRequest) {
//        // Tạo đối tượng Product chỉ với productId
//        Product product = productRepository.findProductByProductId(cartItemRequest.getProductId());
//        // Tạo CartItem và gán product tạm thời
//        List<CartItem> existCartItem = cartItemRepository.findByUser_UserIdAndProduct_ProductId(user.getUserId(), product.getProductId());
//        if (existCartItem.size() > 0) {
//            existCartItem.forEach(existCartItem1 -> {
//                existCartItem1.setQuantity(existCartItem1.getQuantity() + cartItemRequest.getQuantity());
//                cartItemRepository.save(existCartItem1);
//            });
//
//        }else{
//            CartItem cartItem = CartItem.builder()
//                    .quantity(cartItemRequest.getQuantity())
//                    .product(product) // Gán product tạm thời với productId
//                    .user(user)
//                    .build();
//
//            // Lưu CartItem vào cơ sở dữ liệu
//            cartItemRepository.save(cartItem);
//        }
//
//
//        return true;
//    }
//
//    public boolean deleteItem(User user, String productId) {
//        // Tìm giỏ hàng của người dùng (danh sách các CartItem)
//        List<CartItem> cart = cartItemRepository.findByUser_UserId(user.getUserId());
//
//        if (cart == null || cart.isEmpty()) {
//            // Nếu giỏ hàng không tồn tại hoặc trống
//            return false;
//        }
//
//        // Tìm sản phẩm trong giỏ hàng theo productId
//        CartItem itemToRemove = cart.stream()
//                .filter(item -> item.getProduct().getProductId().equals(productId))
//                .findFirst()
//                .orElse(null);
//
//        if (itemToRemove == null) {
//            // Nếu sản phẩm không có trong giỏ hàng
//            return false;
//        }
//
//        // Xóa sản phẩm khỏi giỏ hàng
//        cartItemRepository.delete(itemToRemove);
//
//        return true; // Xóa thành công
//    }
//
//    public boolean updateItem(User myInfo, String productId, int newQuantity) {
//        // Tìm giỏ hàng của người dùng (danh sách các CartItem)
//        List<CartItem> cart = cartItemRepository.findByUser_UserId(myInfo.getUserId());
//
//        if (cart == null || cart.isEmpty()) {
//            // Nếu giỏ hàng không tồn tại hoặc trống
//            return false;
//        }
//
//        // Tìm sản phẩm trong giỏ hàng theo productId
//        CartItem itemToUpdate = cart.stream()
//                .filter(item -> item.getProduct().getProductId().equals(productId))
//                .findFirst()
//                .orElse(null);
//
//        if (itemToUpdate == null) {
//            // Nếu sản phẩm không có trong giỏ hàng
//            return false;
//        }
//
//        // Cập nhật số lượng sản phẩm nếu số lượng mới hợp lệ
//        if (newQuantity > 0) {
//            itemToUpdate.setQuantity(newQuantity);
//            cartItemRepository.save(itemToUpdate); // Lưu thay đổi vào cơ sở dữ liệu
//            return true; // Cập nhật thành công
//        } else {
//            return false; // Số lượng không hợp lệ
//        }
//    }
//
//    @Transactional
//    public boolean deleteAll(User myInfo) {
//        try {
//            // Call the method to delete cart items for the specified user
//            cartItemRepository.deleteByUser_UserId(myInfo.getUserId());
//            return true; // Return true if deletion was successful
//        } catch (Exception e) {
//            // Log the error and return false if there was an issue
//            System.err.println("Error deleting cart items: " + e.getMessage());
//            return false;
//        }
//    }
}
