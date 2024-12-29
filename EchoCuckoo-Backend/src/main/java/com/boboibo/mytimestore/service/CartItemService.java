package com.boboibo.mytimestore.service;

import com.boboibo.mytimestore.exception.AppException;
import com.boboibo.mytimestore.exception.ErrorCode;
import com.boboibo.mytimestore.mapper.CartItemMapper;
import com.boboibo.mytimestore.model.entity.CartItem;
import com.boboibo.mytimestore.model.entity.Product;
import com.boboibo.mytimestore.model.entity.User;
import com.boboibo.mytimestore.model.request.CartItemRequest;
import com.boboibo.mytimestore.model.response.cart.CartItemResponse;
import com.boboibo.mytimestore.repository.CartItemRepository;
import com.boboibo.mytimestore.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    UserService userService;

    public List<CartItemResponse> getAll(){
        try{
            List<CartItem> cartItemList= cartItemRepository.findAll();
            List<CartItemResponse> cartItemResponseList = new ArrayList<>();
            for (CartItem cartItem : cartItemList) {
                cartItemResponseList.add(convertToCartItemResponses(cartItem));
            }
            return cartItemResponseList;
        }
        catch (DataAccessException e) {
            log.error("Database error occurred while fetching active products", e);
            throw new AppException(ErrorCode.DATABASE_EXCEPTION, "Error while fetching active products");
        } catch (Exception e) {
            log.error("Unexpected error occurred", e);
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }
    public List<CartItemResponse> getCartItemByUserId(Long userId){
        List<CartItemResponse> cartItemResponseList = new ArrayList<>();
        try {
            List<CartItem> cartItemList = cartItemRepository.findByUser_UserId(userId);
            for (CartItem cartItem : cartItemList) {
                cartItemResponseList.add(convertToCartItemResponses(cartItem));
            }
        }
        catch (DataAccessException e) {
            log.error("Database error occurred while fetching active products", e);
            throw new AppException(ErrorCode.DATABASE_EXCEPTION, "Error while fetching active products");
        } catch (Exception e) {
            log.error("Unexpected error occurred", e);
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
        return cartItemResponseList;
    }
 public CartItemResponse convertToCartItemResponses(CartItem cartItem){
         Product product = productService.getProductById(cartItem.getProduct().getProductId()).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_ID_NOT_EXIST));
         CartItemResponse cartItemResponse = CartItemResponse.builder()
                 .price(product.getPrice())
                 .image(product.getImage())
                 .productName(product.getProductName())
                 .quantity(cartItem.getQuantity())
                 .userId(cartItem.getUser().getUserId())
                 .build();
         return cartItemResponse;
 }
    public void createCartItems(Long userId,CartItemRequest cartItemRequest) {
            try {
                // Tìm CartItem với userId và productId
                Optional<CartItem> existingCartItem = cartItemRepository.findByUser_UserIdAndProduct_ProductId(userId, cartItemRequest.getProductId());
                CartItem cartItem;
                if (existingCartItem.isPresent()) {
                    // Nếu tồn tại, cộng dồn số lượng
                    cartItem = existingCartItem.get();
                    cartItem.setQuantity(cartItem.getQuantity() + cartItemRequest.getQuantity());
                } else {
                    // Nếu không tồn tại, tạo mới
                    User user = userService.getUserByUserId(userId);
                    Optional<Product> product = productService.getProductById(cartItemRequest.getProductId());
                    if (product.isEmpty()) {
                        log.error("Product not found with ID: {}", cartItemRequest.getProductId());
                        throw new AppException(ErrorCode.PRODUCT_ID_NOT_EXIST, "Product not found for the given ID");
                    }
                    Product productEntity = product.get();
                    cartItem = new CartItem();
                    cartItem.setUser(user);
                    cartItem.setProduct(productEntity);
                    cartItem.setQuantity(cartItemRequest.getQuantity());
                }
                cartItemRepository.save(cartItem);

            } catch (Exception e) {
                log.error("Can't add CartItem for user ID: {}, product ID: {}", userId, cartItemRequest.getProductId(), e);
                throw new AppException(ErrorCode.CART_NOT_EXIST);
            }
    }
    public void updateCartitem(Long userId,CartItemRequest cartItemRequest){
        try{
            CartItem existingCartItem = findCartItemByUserIdAndProductId(userId, cartItemRequest.getProductId());
            CartItem cartItem;
            if (existingCartItem != null) {
                cartItem = existingCartItem;
                log.info("Quantity Of CartItem ; "+ cartItemRequest.getQuantity());
                cartItem.setQuantity(cartItemRequest.getQuantity());
                cartItemRepository.save(cartItem);
            }
        }catch (Exception e) {
            log.error("Update CartItem for user ID: {}, product ID: {}", userId, cartItemRequest.getProductId(), e);
            throw new AppException(ErrorCode.CART_NOT_EXIST);
        }
    }
    public void deleteCartitem(Long userId,Long productId){
        Optional<CartItem> existingCartItem = cartItemRepository.findByUser_UserIdAndProduct_ProductId(userId,productId);
        if (existingCartItem.isPresent()) {
            cartItemRepository.delete(existingCartItem.get());
        } else {
            throw new AppException(ErrorCode.CART_ITEM_NOT_EXIST);
        }
    }
    private CartItem findCartItemByUserIdAndProductId(Long userId,Long productId){
        CartItem existingCartItem = cartItemRepository.findByUser_UserIdAndProduct_ProductId(userId,productId).orElseThrow((() ->
                new AppException(ErrorCode.PRODUCT_ID_NOT_EXIST)));
        return existingCartItem;
    }
    public CartItemResponse getById(Long id){
        CartItem cartItems = cartItemRepository.findById(id).orElse(null);
        if(cartItems == null){
            throw new AppException(ErrorCode.CART_NOT_EXIST);
        }
        return cartItemMapper.cartItemResponse(cartItems);
    }
    public List<CartItem> getByUserId(Long userId){
        List<CartItem> cartItemList = cartItemRepository.findByUser_UserId(userId);
        if(!cartItemList.isEmpty()){
            return cartItemList;
        }else{
            throw new AppException(ErrorCode.CART_ITEM_NOT_EXIST_BY_USERID);
        }
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
