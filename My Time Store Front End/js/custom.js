
// ********** GET ALL PRODUCT  *************** //

document.addEventListener('DOMContentLoaded', function () {
    fetch('http://localhost:8080/api/v1/products')
        .then(response => response.json())
        .then(data => {
            const productList = document.getElementById('product-list');
            productList.innerHTML = ''; // Clear any existing content

            data.forEach(product => {
                const productItem = document.createElement('div');
                productItem.classList.add('col-xl-4', 'col-lg-4', 'col-md-6', 'col-sm-6');

                if (product.quantity > 0) {
                    productItem.innerHTML = `
                        <div class="single-popular-items mb-50 text-center">
                            <div class="popular-img">
                                <img style="width: 200px; height: 200px;" src="${product.image}" alt="${product.productName}">
                                <div class="img-cap">
                                    <span>
                                        <a class="addToCart" href="detail.html" data-id="${product.productId}" data-productName="${product.productName}" data-price="${product.price}" >View Detail</a>
                                    </span> 
                                </div>
                            </div>
                            <div class="popular-caption">
                                <h3>${product.productName}</h3>
                                <span style="color: red">$ ${product.price}</span>
                            </div>
                        </div>
                    `;
                } else {
                    // Nếu sản phẩm không còn hàng
                    productItem.innerHTML = `
                        <div class="single-popular-items mb-50 text-center out-of-stock-effect">
                            <div class="popular-img">
                                <img style="width: 200px; height: 200px;" src="${product.image}" alt="${product.productName}">
                                <!-- Thêm thông báo hết hàng -->
                            </div>
                            <div class="popular-caption">
                                <h3 class="strikethrough">${product.productName}</h3> <!-- Thêm lớp CSS cho gạch ngang -->
                                <span style="color: red">$ ${product.price}</span>
                            </div>
                        </div>
                    `;

                }
                productList.appendChild(productItem);
            });
        })
        .catch(error => console.error('Error fetching product data:', error));
});






document.addEventListener('DOMContentLoaded', function () {
    // Tìm kiếm sản phẩm
    function searchByProductName(event) {
        event.preventDefault(); // Ngăn chặn hành động mặc định của biểu mẫu

        const searchValue = document.getElementById('search').value; // Lấy giá trị từ ô tìm kiếm
        const productList = document.getElementById('product-list');
        productList.innerHTML = ''; // Xóa nội dung hiện tại

        fetch(`http://localhost:8080/api/v1/products/search?productName=${encodeURIComponent(searchValue)}`, {
            method: 'GET',
        })
            .then(response => {
                if (response.ok) {
                    return response.json(); // Chuyển đổi phản hồi thành JSON
                } else {
                    throw new Error('Failed to fetch products');
                }
            })
            .then(data => {
                console.log('Data received from API:', data); // Kiểm tra phản hồi từ API

                if (data.status === '200' && Array.isArray(data.data)) { // Kiểm tra status và xem data có phải là mảng không
                    if (data.data.length > 0) { // Kiểm tra nếu có sản phẩm
                        data.data.forEach(product => {
                            const productItem = document.createElement('div');
                            productItem.classList.add('col-xl-4', 'col-lg-4', 'col-md-6', 'col-sm-6');
                            productItem.innerHTML = `
                            <div class="single-popular-items mb-50 text-center">
                                <div class="popular-img">
                                    <img style="width: 200px; height: 200px;" src="${product.image}" alt="${product.productName}">
                                     <div class="img-cap">
                                        <span>
                                           <a class="addToCart" href="detail.html" data-id="${product.productId}" data-productName="${product.productName}" data-price="${product.price}" >View Detail</a>
                                        </span>
                                     </div>
                                </div>
                                <div class="popular-caption">
                                    <h3>${product.productName}</h3>
                                    <span style="color: red">$ ${product.price}</span>
                                </div>
                            </div>
                        `;

                            productList.appendChild(productItem); // Thêm sản phẩm vào danh sách
                        });
                    } else {
                        productList.innerHTML = '<p>Không tìm thấy sản phẩm nào.</p>'; // Không tìm thấy sản phẩm
                    }
                } else {
                    console.error('Data is not valid:', data); // In ra nếu data không hợp lệ
                }
            })
            .catch(error => {
                console.error('Error:', error); // In lỗi
            });
    }

    // Gán sự kiện submit cho biểu mẫu
    const searchForm = document.getElementById('search-form');
    searchForm.addEventListener('submit', searchByProductName);

    // Event delegation - Lắng nghe sự kiện click từ cha của các nút "Add to Cart"
    document.getElementById('product-list').addEventListener('click', function (event) {
        if (event.target.classList.contains('addToCart')) {
            event.preventDefault();
            const productId = event.target.getAttribute('data-id');
            //   console.log(productId)
            window.location.href = `detail.html?productId=${encodeURIComponent(productId)}`;
        }
    });

});

// *********** detail product ***************** //

// When user clicks 'View Detail', redirect to detail page with productId in URL
document.addEventListener('DOMContentLoaded', function () {
    // Get productId from URL
    const urlParams = new URLSearchParams(window.location.search);
    const productId = urlParams.get('productId');
    console.log(productId);

    if (productId) {
        // Fetch product details using productId
        fetch(`http://localhost:8080/api/v1/products/getProduct?productId=${encodeURIComponent(productId)}`, {
            method: 'GET'
        })
            .then(response => {
                console.log('Raw response:', response);  // Log the raw response for debugging
                return response.json();
            })
            .then(data => {
                console.log('Parsed data:', data);  // Log the parsed data
                if (data.status === '200') {
                    const product = data.data;

                    // Create the HTML content
                    const productHTML = `
                <div class="col-lg-6 col-md-12">
                    <!-- Product Image -->
                    <div class="product-details-img text-center">
                        <img src="${product.image}" id="imgProductDetail" alt="Product Image">
                    </div>
                </div>
                <div class="col-lg-6 col-md-12">
                    <!-- Product Details -->
                    <div class="product-details-content">
                        <h2>${product.productName}</h2>
                        <div class="product-price">
                            <span>$ ${product.price}</span>
                        </div>
                        <p class="product-description">
                            This is a brief description of the product. It gives an overview of the main features and highlights of the product.
                        </p>
                        <div class="product-stock">
                            <span>Quantity in stock: <strong>${product.quantity}</strong></span>
                        </div>
                
                        <div class="product-quantity">
                            <span>Quantity:</span>
                            <select name="quantity">
                                <!-- Tạo danh sách lựa chọn từ 1 tới 10 -->
                                ${[...Array(10).keys()].map(i => `
                                    <option value="${i + 1}">${i + 1}</option>
                                `).join('')}
                            </select>
                        </div>

                        <div class="add-to-cart">
                            <button id="add-to-cart-btn" class="btn btn-primary">Add to Cart</button>
                        </div>
                    </div>
                </div>
            `;

                    // Insert the product HTML into the page
                    // Insert the product HTML into the page
                    const productContainer = document.getElementById('product-container');
                    productContainer.innerHTML = productHTML;

                    // Gán sự kiện click sau khi sản phẩm đã được render vào DOM
                    const addToCartButton = document.getElementById('add-to-cart-btn');
               
                        addToCartButton.addEventListener('click', function () {
                            // Lấy dữ liệu sản phẩm
                            const productId = new URLSearchParams(window.location.search).get('productId');
                            const productName = document.querySelector('.product-details-content h2').innerText;
                            const price = parseFloat(document.querySelector('.product-price span').innerText.replace('$', ''));
                            const quantity = parseInt(document.querySelector('.product-quantity select').value);
                            const image = document.getElementById('imgProductDetail').src;

                            // Gọi hàm addToCart
                            addToCart(productId, productName, price, quantity, image);
                        });
               
                 



                    // Log the product details
                    console.log('Product details loaded successfully:', product);
                } else {
                    console.error('Product not found');
                }
            })
            .catch(error => console.error('Error fetching product details:', error));

    } else {
        console.error('No productId provided in URL');
    }



});
// ********** ADD TO CART FUNCTION *************** //
function addToCart(productId, productName, price, quantity = 1, image) {
    // Lấy token từ localStorage
    let token = localStorage.getItem('authToken');
    
    // Kiểm tra nếu token không tồn tại
    if (!token) {
        alert('You must be logged in to add items to the cart.');
        return;
    }

    // Gọi API để lấy thông tin sản phẩm và số lượng tồn kho
    fetch(`http://localhost:8080/api/v1/products/getProduct?productId=${productId}`)
        .then(response => response.json())
        .then(responseData => {
            const product = responseData.data;
            const availableStock = product.quantity; // Lấy số lượng tồn kho từ phản hồi API

            // Kiểm tra nếu số lượng yêu cầu lớn hơn số lượng tồn kho
            if (quantity > availableStock) {
                alert(`Sorry, only ${availableStock} items are available in stock.`);
            } else {
                // Gửi yêu cầu thêm sản phẩm vào giỏ hàng lên server
                fetch(`http://localhost:8080/api/v1/cart/add`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}` // Gửi token kèm theo yêu cầu
                    },
                    body: JSON.stringify({
                        productId: productId,
                        quantity: quantity, // Gửi số lượng yêu cầu
                    })
                })
                    .then(response => {
                        if (response.ok) {
                            console.log('Product successfully added to the server cart!');
                            alert('Product added to cart!');
                        } else {
                            console.error('Failed to add product to the server cart');
                        }
                    })
                    .catch(error => console.error('Error adding product to server cart:', error));
            }
        })
        .catch(error => {
            console.error('Error fetching product data:', error);
            alert('Error adding product to cart. Please try again later.');
        });
}




