document.addEventListener('DOMContentLoaded', function () {
    const token = localStorage.getItem('authToken'); // Replace with your actual token

    // Make POST request to fetch cart data
    fetch('http://localhost:8080/api/v1/cart/get', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify({
            userId: '1c924cd0-f788-4297-b4ab-49f90d110508' // Add body if your API requires it
        })
    })
    .then(response => response.json())
    .then(cart => {
        // Lấy dữ liệu mảng giỏ hàng từ `cart.data`
        const cartItems = cart.data;

        // Kiểm tra nếu giỏ hàng trống
        if (!cartItems || cartItems.length === 0) {
            document.querySelector('.cart_inner').innerHTML = '<h3>Your cart is empty</h3>';
            return;
        }

        // Tính tổng tiền
        let total = 0;

        // Xây dựng HTML cho giỏ hàng
        const cartHTML = `
            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Name</th>
                            <th scope="col">Image</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Price</th>
                            <th scope="col">Total</th>
                            <th scope="col">Remove</th>
                        </tr>
                    </thead>
                    <tbody>
                        ${cartItems.map(item => {
            const itemPrice = item.price || 0; // Kiểm tra nếu không có giá
            const itemTotal = itemPrice * item.quantity;
            total += itemTotal;
            return `
                                <tr id="item-row-${item.productId}">
                                    <td>${item.productName}</td>
                                    <td><img style="width: 100px; height: 100px; border-radius: 10px" src="${item.image || 'default_image.png'}"></td>
                                    <td>
                                        <input type="number" name="quantity" value="${item.quantity}" min="1" onchange="updateQuantity('${item.productId}', this.value)"/>
                                    </td>
                                    <td>$${itemPrice.toFixed(2)}</td>
                                    <td id="item-total-${item.productId}">$${itemTotal.toFixed(2)}</td>
                                    <td>
                                        <button onclick="removeItem('${item.productId}')">Remove</button>
                                    </td>
                                </tr>
                            `;
        }).join('')}
                        <tr class="bottom_button">
                            <td colspan="5"></td>
                            <td><h5>Subtotal</h5></td>
                            <td><span style="color: red; font-size: 30px" id="cart-total">$${total.toFixed(2)}</span></td>
                        </tr>
                    </tbody>
                </table>
                
                <div class="checkout_btn_inner float-left">
                    <a class="btn_1" href="shop.html">Continue Shopping</a>
                </div>
                <div class="checkout_btn_inner float-right">
                    <a class="btn_1 checkout_btn_1" href="vnpay_view.html">
                        <img src="https://cdn.haitrieu.com/wp-content/uploads/2022/10/Logo-VNPAY-QR-1.png" alt="VNPay Logo" />VNPay
                    </a>
                </div>
                <div class="checkout_btn_inner float-right">
                    <a id="checkout-btn" class="btn_1" href="#">Check Out</a> 
                </div>
            </div>
        `;

        // Chèn HTML giỏ hàng vào trang
        document.querySelector('#cart_inner').innerHTML = cartHTML;

        const checkout_btn = document.getElementById('checkout-btn');
        checkout_btn.addEventListener('click', checkOutFunction);
    })
    .catch(error => console.error('Error fetching cart data:', error));
});


// Function to update the quantity and recalculate totals
function updateQuantity(productId, quantity) {
    let token = localStorage.getItem('authToken'); // Lấy token từ localStorage

    // Gọi API để cập nhật số lượng sản phẩm trong giỏ hàng
    fetch(`http://localhost:8080/api/v1/cart/update?productId=${productId}&quantity=${quantity}`, {
        method: 'PUT', // Sử dụng PUT để cập nhật
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}` // Thêm token để xác thực
        }
    })
    .then(response => {
        if (response.ok) {
            return response.json(); // Chuyển đổi phản hồi thành JSON
        } else {
            throw new Error('Failed to update the item quantity.');
        }
    })
    .then(data => {
        // Cập nhật số lượng trong giỏ hàng trên giao diện
        const cart = JSON.parse(localStorage.getItem('cart')) || [];
        const item = cart.find(item => item.productId === productId);

        if (item) {
            item.quantity = parseInt(quantity);
            localStorage.setItem('cart', JSON.stringify(cart)); // Cập nhật localStorage

            // Cập nhật tổng giá cho sản phẩm trong DOM
            const itemTotal = item.price * item.quantity;
            document.querySelector(`#item-total-${productId}`).textContent = `$${itemTotal.toFixed(2)}`;

            // Cập nhật tổng số tiền tổng thể
            updateTotal(cart);
        }
    })
    .catch(error => {
        console.error('Error updating item quantity:', error);
        alert('Error updating item quantity. Please try again later.');
    });
}


// Function to remove an item from the cart
function removeItem(productId) {
    let token = localStorage.getItem('authToken'); // Lấy token từ localStorage

    // Gọi API để xóa sản phẩm khỏi giỏ hàng
    fetch(`http://localhost:8080/api/v1/cart/delete?productId=${productId}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}` // Thêm token để xác thực
        }
    })
    .then(response => {
        if (response.ok) {
            alert('Item successfully removed from cart.');
            
            // Remove the item row from the DOM
            const itemRow = document.querySelector(`#item-row-${productId}`);
            if (itemRow) {
                itemRow.remove(); // Xóa dòng sản phẩm từ giao diện
            }

        
        } else {
            alert('Failed to remove the item. Please try again.');
        }
    })
    
}
    
// Function to handle checkout process
function checkOutFunction() {
    const token = localStorage.getItem('authToken');

    // Fetch cart data from the API
    fetch('http://localhost:8080/api/v1/cart/get', {
        method: 'POST', // Sử dụng GET để lấy dữ liệu
        headers: {
            'Authorization': `Bearer ${token}`, // Thêm token vào header
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`Error fetching cart data: ${response.status}`);
        }
        return response.json();
    })
    .then(data => {
        // Lấy giỏ hàng từ dữ liệu trả về
        const cart = data.data || []; // Dữ liệu giỏ hàng ở đây là data.data

        // Kiểm tra xem cart có phải là mảng không
        if (!Array.isArray(cart) || cart.length === 0) {
            throw new Error('Cart is empty or not an array');
        }

        // Kiểm tra tất cả các sản phẩm trong giỏ hàng
        const stockChecks = cart.map(item => {
            return fetch(`http://localhost:8080/api/v1/products/getProduct?productId=${item.productId}`) // Gọi API để lấy thông tin sản phẩm
                .then(response => {
                    if (!response.ok) {
                        throw new Error(`Error fetching product data for ${item.productName}: ${response.status}`);
                    }
                    return response.json();
                })
                .then(productData => {
                    if (productData.status === '200') {
                        const product = productData.data;
                        // Kiểm tra số lượng yêu cầu không vượt quá số lượng tồn kho
                        if (item.quantity > product.quantity) {
                            throw new Error(`Not enough stock for ${item.productName}. Available: ${product.quantity}, Requested: ${item.quantity}`);
                        }
                    } else {
                        throw new Error(`Error fetching product data: ${productData.message}`);
                    }
                });
        });

        return Promise.all(stockChecks).then(() => cart); // Trả về cart sau khi kiểm tra hàng tồn kho
    })
    .then(cart => {
        // Fetch user information
        return fetch('http://localhost:8080/api/v1/users/myInfo', {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Error fetching user information: ${response.status}`);
            }
            return response.json();
        })
        .then(userData => {
            if (userData && userData.data) {
                const user = userData.data;

                // Create the order request
                const total = cart.reduce((sum, item) => sum + (item.price * item.quantity), 0); // Tính tổng
                const orderRequest = {
                    username: user.username,
                    total: total,
                    orderDate: new Date().toISOString(),
                    cartItems: cart.map(item => ({
                        product: { // Đóng gói productName trong một đối tượng product
                            productName: item.productName
                        },
                        quantity: item.quantity
                    }))
                };

                // In ra orderRequest để kiểm tra
                console.log("Order Request:", orderRequest);

                // Gửi yêu cầu thanh toán
                return fetch('http://localhost:8080/api/v1/order/checkout', {
                    method: 'POST',
                    headers: {
                        'Authorization': `Bearer ${token}`,
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(orderRequest)
                });
            } else {
                throw new Error('User data not found.');
            }
        });
    })
    .then(response => {
        if (!response.ok) {
            return response.text().then(text => { throw new Error(`Error placing order: ${response.status} ${text}`); });
        }
        return response.json();
    })
    .then(data => {
        alert('Order placed successfully!');
        localStorage.removeItem('cart'); // Xóa giỏ hàng
        window.location.href = 'success.html'; // Chuyển hướng đến trang thành công
    })
    .catch(error => {
        console.error('Error:', error);
        alert(`Checkout failed: ${error.message}`);
    });
}
