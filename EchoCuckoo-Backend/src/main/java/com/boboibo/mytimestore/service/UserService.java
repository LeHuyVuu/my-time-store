package com.boboibo.mytimestore.service;

import com.boboibo.mytimestore.exception.AppException;
import com.boboibo.mytimestore.exception.ErrorCode;
import com.boboibo.mytimestore.mapper.CustomerMapper;
import com.boboibo.mytimestore.mapper.UserMapper;
import com.boboibo.mytimestore.model.RoleEnum;
import com.boboibo.mytimestore.model.entity.Customer;
import com.boboibo.mytimestore.model.entity.User;
import com.boboibo.mytimestore.model.enums.Role;
import com.boboibo.mytimestore.model.request.UpdatePasswordRequest;
import com.boboibo.mytimestore.model.request.UpdateUserRequest;
import com.boboibo.mytimestore.model.request.UserRequest;
import com.boboibo.mytimestore.model.request.authentication.RegisterRequest;
import com.boboibo.mytimestore.model.response.user.UserResponse;
import com.boboibo.mytimestore.repository.CustomerRepository;
import com.boboibo.mytimestore.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    UserMapper userMapper;
    @Autowired
    CustomerMapper customerMapper;

    @Value("${myapp.api-key}")
    private String privateKey;

    @Autowired
    PasswordEncoder encoder;


    public void createCustomer(RegisterRequest newUser) {
        User user = userMapper.toUser(newUser);
        user.setPassword(encoder.encode(newUser.getPassword()));
        user.setStatus(true);
        user.setRole(Role.CUSTOMER);
        userRepository.save(user);
        if (user.getRole() == Role.CUSTOMER) {
            Customer customer = customerMapper.userToCustomer(user);
//            customer.setAddress(newUser.getAddress());
            customer.setPhone(newUser.getPhone());
            customer.setUser(user);
            customerRepository.save(customer);
        }
    }
    public UserResponse updateUser(UpdateUserRequest updateUserRequest) {
        try {
            User user = getUserByUserId(updateUserRequest.getUserId());
            user.setImage(updateUserRequest.getImage());
            user.setFullName(updateUserRequest.getFullName());
            user.setEmail(updateUserRequest.getEmail());
            if (user.getRole().equals(Role.CUSTOMER)) {
                Customer customer = customerRepository.findByUser_UserId(updateUserRequest.getUserId());
                customer.setPhone(updateUserRequest.getPhoneNumber());
                customer.setAddress(updateUserRequest.getAddress());
                customerRepository.save(customer);
            }
            userRepository.save(user);
            UserResponse userResponse = userMapper.toUserResponse(user);
            return userResponse;
        } catch (Exception e) {
            return null;
        }
    }
    public UserResponse getMyInfo(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        String username;
        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
            Jws<Claims> jws = Jwts.parser() // Use parserBuilder() instead of parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            username = jws.getBody().getSubject();
        } catch (Exception e) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }
        return getUserByUsernameV2(username);
    }
    private UserResponse getUserByUsernameV2(String username) {
        User user = findUserByUsername(username);
        UserResponse userResponse = userMapper.toUserResponse(user);
        Customer customer = customerRepository.findByUser_UserId(user.getUserId());
        userResponse.setCustomerResponse(customerMapper.customerToCustomerResponse(customer));
       return userResponse;
    }
    public boolean deleteUser(Long userId) {
        try {
            User user = userRepository.findByUserId(userId);
            if (user.isStatus()) {
                user.setStatus(false);
            } else {
                user.setStatus(true);
            }
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public User getUserByUserId(Long userId) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new AppException(ErrorCode.USER_NOT_EXIST);
        }
        return user;
    }

    private User findUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new AppException(ErrorCode.USER_NOT_EXIST);
        }
        return null;
    }

    public boolean getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return false;
        }
        return true;
    }

    public boolean getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return false;
        }
        return true;
    }
}


//    @Autowired
//    private UserRepository userRepository;
//
//    @Value("${myapp.api-key}")
//    private String privateKey;
//    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
//
//    @Autowired
//    PasswordEncoder encoder;
//
//
//    public User getUserByUsername(String username) {
//        return userRepository.findUsersByUsername(username);
//    }
//
//    public List<User> getListUser() {
//        return userRepository.findAll();
//    }
//
//    public void createUser(UserRequest userRequest) {
//        User user = new User();
//        user.setUsername(userRequest.getUsername()); // Lấy tên người dùng từ UserRequest
//        user.setPassword( encoder.encode(userRequest.getPassword())); // Mã hóa và gán mật khẩu đã mã hóa
//        user.setEmail(userRequest.getEmail());// Lấy số điện thoại từ UserRequest
//        user.setRoles(RoleEnum.CUSTOMER);
//        user.setStatus(true);
//        userRepository.save(user); // Lưu người dùng vào cơ sở dữ liệu
//    }
//
//    public boolean deleteByUsername(@NotNull String username) {
//        User user = userRepository.findUsersByUsername(username);
//        if (user != null) {
//            user.setStatus(false);
//            userRepository.save(user); // Xóa người dùng dựa trên thực thể người dùng
//            return true; // Trả về true nếu xóa thành công
//        }
//        return false; // Trả về false nếu người dùng không tồn tại
//    }
//
//    public User getMyInfo(HttpServletRequest request) {
//        String token = request.getHeader("Authorization");
//        if (token != null && token.startsWith("Bearer ")) {
//            token = token.substring(7);
//        }
//
//        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
//        Jws<Claims> jws =  Jwts.parser() // Use parserBuilder() instead of parser()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token);
//        String username = jws.getBody().getSubject();
//        return getUserByUsername(username);
//    }
//
//
//    public boolean isUserExists(String userName) {
//        return userRepository.findUsersByUsername(userName) != null;
//    }
//
//    public User getUserByUserID(@Valid String userID) {
//        return userRepository.findById(userID).orElseThrow(() -> new RuntimeException("User not found"));
//    }
//
//    public User updateUser(UpdateUserRequest updatedUser, String userId) {
//        Optional<User> existingUserOpt = userRepository.findById(userId);
//
//        if (existingUserOpt.isPresent()) {
//            User existingUser = existingUserOpt.get();
//            existingUser.setUsername(updatedUser.getUsername());
//            existingUser.setEmail(updatedUser.getEmail());
//
//            return userRepository.save(existingUser);
//        }
//
//        throw new RuntimeException("User not found with id: " + userId);
//    }
//
//    @Transactional
//    public boolean updatePassword(User user, UpdatePasswordRequest passwordRequest) {
//        // Kiểm tra user không null
//
//        // Kiểm tra xem mật khẩu hiện tại có khớp không
//        if (encoder.matches(passwordRequest.getCurrentPassword(), user.getPassword())) {
//            userRepository.updatePassword(encoder.encode(passwordRequest.getNewPassword()), String.valueOf(user.getUserId()));
//            return true; // Cập nhật thành công
//        }
//
//        return false; // Mật khẩu hiện tại không khớp
//    }
//
//    public void saveUser(User user) {
//        userRepository.save(user);
//    }
//
//
//    public User getUserByEmail(String email) {
//       return userRepository.findByEmail(email);
//    }
//}