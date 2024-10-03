document.addEventListener('DOMContentLoaded', function () {
    var cart = JSON.parse(localStorage.getItem('cart')) || []; // Parse the cart data from localStorage

    // Check if cart is empty
    if (cart.length === 0) {
        document.querySelector('.cart_inner').innerHTML = '<h3>Your cart is empty</h3>';
        return;
    }

    // Calculate the total
    let total = 0;

    // Build the cart HTML
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
                    ${cart.map(item => {
        const itemTotal = item.price * item.quantity;
        total += itemTotal;
        return `
                            <tr id="item-row-${item.productId}">
                                <td>${item.productName}</td>
                                <td><img style="width: 100px; height: 100px; border-radius: 10px" src="${item.image}"></td>
                                <td>
                                    <input type="number" name="quantity" value="${item.quantity}" min="1" onchange="updateQuantity('${item.productId}', this.value)"/>
                                </td>
                                <td>$${item.price.toFixed(2)}</td>
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

    document.querySelector('#cart_inner').innerHTML = cartHTML; // Insert the cart HTML into the page

    const checkout_btn = document.getElementById('checkout-btn');
    checkout_btn.addEventListener('click', checkOutFunction);
});

// Function to update the quantity and recalculate totals
function updateQuantity(productId, quantity) {
    const cart = JSON.parse(localStorage.getItem('cart')) || [];
    const item = cart.find(item => item.productId === productId);

    if (item) {
        item.quantity = parseInt(quantity);
        localStorage.setItem('cart', JSON.stringify(cart)); // Update localStorage

        // Update the total for this item in the DOM
        const itemTotal = item.price * item.quantity;
        document.querySelector(`#item-total-${productId}`).textContent = `$${itemTotal.toFixed(2)}`;

        // Update the overall total
        updateTotal();
    }
}

// Function to remove an item from the cart
function removeItem(productId) {
    let cart = JSON.parse(localStorage.getItem('cart')) || [];

    if (cart.length === 0) {
        alert('Your cart is already empty!');
        return;
    }

    const itemToRemove = cart.find(item => item.productId === productId);

    if (!itemToRemove) {
        alert('Item not found in the cart.');
        return;
    }

    cart = cart.filter(item => item.productId !== productId); // Remove the item
    localStorage.setItem('cart', JSON.stringify(cart)); // Update localStorage

    // Remove the item row from the DOM
    const itemRow = document.querySelector(`#item-row-${productId}`);
    if (itemRow) {
        itemRow.remove(); // Remove row from the table
    }

    // Update the overall total
    updateTotal();
}

// Function to update the overall total
function updateTotal() {
    const cart = JSON.parse(localStorage.getItem('cart')) || [];
    let total = cart.reduce((sum, item) => sum + (item.price * item.quantity), 0);
    document.querySelector('#cart-total').textContent = `$${total.toFixed(2)}`;

    // If the cart is empty after removal, show "Your cart is empty"
    if (cart.length === 0) {
        document.querySelector('.cart_inner').innerHTML = '<h3>Your cart is empty</h3>';
    }
}

// Function to handle checkout process
function checkOutFunction() {
    const token = localStorage.getItem('authToken');
    const cart = JSON.parse(localStorage.getItem('cart')) || []; // Get the cart from localStorage

    if (!token || cart.length === 0) {
        alert('Please login or add items to your cart before checking out.');
        return;
    }

    // Function to check stock for each item
    const checkStock = (item) => {
        return fetch(`http://localhost:8080/api/v1/products/getProduct?productId=${item.productId}`) // Gọi API để lấy thông tin sản phẩm
            .then(response => response.json())
            .then(data => {
                if (data.status === '200') {
                    const product = data.data;
                    // Kiểm tra số lượng yêu cầu không vượt quá số lượng tồn kho
                    if (item.quantity > product.quantity) {
                        throw new Error(`Not enough stock for ${item.productName}. Available: ${product.quantity}, Requested: ${item.quantity}`);
                    }
                } else {
                    throw new Error(`Error fetching product data: ${data.message}`);
                }
            });
    };

    // Kiểm tra tất cả các sản phẩm trong giỏ hàng
    const stockChecks = cart.map(checkStock);

    Promise.all(stockChecks) // Chờ cho tất cả các kiểm tra kho hoàn thành
        .then(() => {
            // Fetch user information
            return fetch('http://localhost:8080/api/v1/users/myInfo', {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });
        })
        .then(response => response.json())
        .then(data => {
            if (data && data.data) {
                const user = data.data;

                // Create the order request
                const total = cart.reduce((sum, item) => sum + (item.price * item.quantity), 0); // Calculate total
                const orderRequest = {
                    username: user.username,
                    total: total,
                    orderDate: new Date().toISOString(),
                    cartItems: cart.map(item => ({
                        product: { // Wrap productName inside a product object
                            productName: item.productName
                        },
                        quantity: item.quantity
                    }))
                };

                // In ra orderRequest để kiểm tra
                console.log("Order Request:", orderRequest);

                // Send the checkout request
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
        })
        .then(() => {
            alert('Order placed successfully!');
            localStorage.removeItem('cart'); // Clear the cart
            window.location.href = 'success.html'; // Redirect to success page
        })
        .catch(error => {
            console.error('Error:', error);
            alert(`Checkout failed: ${error.message}`);
        });
}
