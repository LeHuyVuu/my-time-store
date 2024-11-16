document.addEventListener('DOMContentLoaded', function () {
    const token = localStorage.getItem('authToken'); // Lấy token từ localStorage

    // Nếu không có token, chuyển hướng đến trang đăng nhập
    if (!token) {
        window.location.href = 'login.html'; 
    }
    loadProducts(); // Tải danh sách sản phẩm khi trang được nạp
});

// Hàm tải danh sách sản phẩm
function loadProducts() {
    fetch('http://localhost:8080/api/v1/products')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            const productList = document.getElementById('product-list').getElementsByTagName('tbody')[0];
            productList.innerHTML = ''; // Xóa các hàng hiện tại

            data.forEach(product => {
                const row = productList.insertRow();
                row.innerHTML = `
                    <td>${product.productId}</td>
                    <td>${product.productName}</td>
                    <td>${product.price.toLocaleString()} đ</td>
                    <td>${product.quantity}</td>
                    <td>
                        <img src="${product.image}" alt="${product.productName}" style="width: 50px; height: 50px; object-fit: cover;">
                    </td>
                    <td class="action-buttons">
                        <button class="edit-btn" data-id="${product.productId}" data-name="${product.productName}" data-price="${product.price}" data-quantity="${product.quantity}">Sửa</button>
                        <button class="delete-btn" data-id="${product.productId}">Xóa</button>
                    </td>
                `;

                // Gắn sự kiện click cho nút "Sửa"
                row.querySelector('.edit-btn').addEventListener('click', function () {
                    const productId = this.getAttribute('data-id');
                    const productName = this.getAttribute('data-name');
                    const price = this.getAttribute('data-price');
                    const quantity = this.getAttribute('data-quantity');
                    showUpdateProductForm(productId, productName, price, quantity);
                });

                // Gắn sự kiện click cho nút "Xóa"
                row.querySelector('.delete-btn').addEventListener('click', function () {
                    const productId = this.getAttribute('data-id');
                    deleteProduct(productId);
                });
            });
        })
        .catch(error => console.error('Error fetching product data:', error));
}

// Hàm hiển thị form cập nhật sản phẩm
function showUpdateProductForm(productId, productName, price, quantity) {
    const form = document.getElementById('update-product-form');
    form.style.display = 'block'; // Hiện form cập nhật
    form.querySelector('input[name="productId"]').value = productId; // Điền ID sản phẩm vào form
    form.querySelector('input[name="productName"]').value = productName; // Điền tên sản phẩm vào form
    form.querySelector('input[name="price"]').value = price; // Điền giá sản phẩm vào form
    form.querySelector('input[name="quantity"]').value = quantity; // Điền số lượng sản phẩm vào form
}

// Hàm cập nhật sản phẩm
async function updateProduct(event) {
    event.preventDefault(); // Ngăn chặn gửi form

    const productId = document.querySelector('input[name="productId"]').value;
    const updatedProduct = {
        productName: document.querySelector('input[name="productName"]').value,
        price: document.querySelector('input[name="price"]').value,
        quantity: document.querySelector('input[name="quantity"]').value,
    };

    try {
        const response = await fetch(`http://localhost:8080/api/v1/products/${productId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${localStorage.getItem('authToken')}`, // Thêm token vào tiêu đề
            },
            body: JSON.stringify(updatedProduct),
        });

        const data = await response.json();

        if (!response.ok) {
            Swal.fire({
                title: 'Lỗi!',
                text: `Cập nhật sản phẩm không thành công: ${data.message || 'Đã xảy ra lỗi.'}`,
                icon: 'error',
                confirmButtonText: 'OK'
            });
        } else {
            Swal.fire({
                title: 'Thành công!',
                text: 'Sản phẩm đã được cập nhật thành công.',
                icon: 'success',
                confirmButtonText: 'OK'
            });

            loadProducts(); // Tải lại danh sách sản phẩm
            document.getElementById('update-product-form').reset(); // Xóa form
            document.getElementById('update-product-form').style.display = 'none'; // Ẩn form sau khi cập nhật
        }
    } catch (error) {
        Swal.fire({
            title: 'Lỗi!',
            text: 'Đã xảy ra lỗi trong quá trình cập nhật sản phẩm.',
            icon: 'error',
            confirmButtonText: 'OK'
        });
        console.error('Error updating product:', error);
    }
}

// Hàm xóa sản phẩm
async function deleteProduct(productId) {
    const token = localStorage.getItem('authToken'); // Ensure you replace this with the actual token

    Swal.fire({
        title: 'Bạn có chắc chắn?',
        text: "Hành động này không thể hoàn tác!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Có, xóa nó!'
    }).then(async (result) => {
        if (result.isConfirmed) {
            try {
                const response = await fetch(`http://localhost:8080/api/v1/products/${productId}`, {
                    method: 'DELETE',
                    headers: {
                        'Authorization': `Bearer ${token}`, // Thêm token vào tiêu đề
                    }
                });

                if (response.ok) {
                    Swal.fire(
                        'Đã xóa!',
                        'Sản phẩm đã được xóa thành công.',
                        'success'
                    );
                    loadProducts(); // Tải lại danh sách sản phẩm sau khi xóa
                } else {
                    Swal.fire({
                        title: 'Lỗi!',
                        text: 'Xóa sản phẩm không thành công.',
                        icon: 'error',
                        confirmButtonText: 'OK'
                    });
                }
            } catch (error) {
                console.error('Error deleting product:', error);
                Swal.fire({
                    title: 'Lỗi!',
                    text: 'Đã xảy ra lỗi trong quá trình xóa sản phẩm.',
                    icon: 'error',
                    confirmButtonText: 'OK'
                });
            }
        }
    });
}
document.addEventListener('DOMContentLoaded', function () {
    loadProducts(); // Tải danh sách sản phẩm khi trang được nạp
});

// Hàm tải danh sách sản phẩm
function loadProducts() {
    fetch('http://localhost:8080/api/v1/products')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            const productList = document.getElementById('product-list').getElementsByTagName('tbody')[0];
            productList.innerHTML = ''; // Xóa các hàng hiện tại

            data.forEach(product => {
                const row = productList.insertRow();
                row.innerHTML = `
                    <td>${product.productId}</td>
                    <td>${product.productName}</td>
                    <td>${product.price.toLocaleString()} đ</td>
                    <td>${product.quantity}</td>
                    <td>
                        <img src="${product.image}" alt="${product.productName}" style="width: 50px; height: 50px; object-fit: cover;">
                    </td>
                    <td class="action-buttons">
                        <button class="edit-btn" data-id="${product.productId}" data-name="${product.productName}" data-price="${product.price}" data-quantity="${product.quantity}">Sửa</button>
                        <button class="delete-btn" data-id="${product.productId}">Xóa</button>
                    </td>
                `;

                // Gắn sự kiện click cho nút "Sửa"
                row.querySelector('.edit-btn').addEventListener('click', function () {
                    const productId = this.getAttribute('data-id');
                    const productName = this.getAttribute('data-name');
                    const price = this.getAttribute('data-price');
                    const quantity = this.getAttribute('data-quantity');
                    showUpdateProductForm(productId, productName, price, quantity);
                });

                // Gắn sự kiện click cho nút "Xóa"
                row.querySelector('.delete-btn').addEventListener('click', function () {
                    const productId = this.getAttribute('data-id');
                    deleteProduct(productId);
                });
            });
        })
        .catch(error => console.error('Error fetching product data:', error));
}

// Hàm hiển thị form cập nhật sản phẩm
async function showUpdateProductForm(product) {
    const { value: formValues } = await Swal.fire({
        title: 'Cập Nhật Sản Phẩm',
        html: `
            <form id="update-form">
                <input type="hidden" name="productId" value="${product.id}" required>
                <div>
                    <label for="productName">Tên Sản Phẩm:</label>
                    <input type="text" name="productName" value="${product.name}" required>
                </div>
                <div>
                    <label for="price">Giá:</label>
                    <input type="number" name="price" value="${product.price}" required>
                </div>
                <div>
                    <label for="quantity">Số Lượng:</label>
                    <input type="number" name="quantity" value="${product.quantity}" required>
                </div>
            </form>
        `,
        showCancelButton: true,
        confirmButtonText: 'Cập Nhật',
        cancelButtonText: 'Hủy',
        preConfirm: () => {
            const form = document.getElementById('update-form');
            const formData = new FormData(form);
            const updatedProduct = {
                productId: formData.get('productId'),
                productName: formData.get('productName'),
                price: formData.get('price'),
                quantity: formData.get('quantity'),
            };
            return updateProduct(updatedProduct); // Trả về Promise
        }
    });

    if (formValues) {
        Swal.fire('Thành công!', 'Sản phẩm đã được cập nhật.', 'success');
        loadProducts(); // Tải lại danh sách sản phẩm
    }
}

async function updateProduct(updatedProduct) {
    try {
        const response = await fetch(`http://localhost:8080/api/v1/products/${updatedProduct.productId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${localStorage.getItem('authToken')}`,
            },
            body: JSON.stringify({
                productName: updatedProduct.productName,
                price: updatedProduct.price,
                quantity: updatedProduct.quantity,
            }),
        });

        if (!response.ok) {
            const data = await response.json();
            throw new Error(data.message || 'Đã xảy ra lỗi.');
        }
    } catch (error) {
        Swal.fire('Lỗi!', `Cập nhật sản phẩm không thành công: ${error.message}`, 'error');
        throw error; // Để SweetAlert biết rằng có lỗi xảy ra
    }
}

// Ví dụ: Sử dụng khi nhấn nút "Sửa"
document.addEventListener('click', (event) => {
    if (event.target.classList.contains('edit-btn')) {
        const product = {
            id: event.target.dataset.id,
            name: event.target.dataset.name,
            price: event.target.dataset.price,
            quantity: event.target.dataset.quantity,
        };
        showUpdateProductForm(product);
    }
});



// Hàm xóa sản phẩm
async function deleteProduct(productId) {
    const token = localStorage.getItem('authToken'); // Ensure you replace this with the actual token

    Swal.fire({
        title: 'Bạn có chắc chắn?',
        text: "Hành động này không thể hoàn tác!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Có, xóa nó!'
    }).then(async (result) => {
        if (result.isConfirmed) {
            try {
                const response = await fetch(`http://localhost:8080/api/v1/products/${productId}`, {
                    method: 'DELETE',
                    headers: {
                        'Authorization': `Bearer ${token}`, // Thêm token vào tiêu đề
                    }
                });

                if (response.ok) {
                    Swal.fire(
                        'Đã xóa!',
                        'Sản phẩm đã được xóa thành công.',
                        'success'
                    );
                    loadProducts(); // Tải lại danh sách sản phẩm sau khi xóa
                } else {
                    Swal.fire({
                        title: 'Lỗi!',
                        text: 'Xóa sản phẩm không thành công.',
                        icon: 'error',
                        confirmButtonText: 'OK'
                    });
                }
            } catch (error) {
                console.error('Error deleting product:', error);
                Swal.fire({
                    title: 'Lỗi!',
                    text: 'Đã xảy ra lỗi trong quá trình xóa sản phẩm.',
                    icon: 'error',
                    confirmButtonText: 'OK'
                });
            }
        }
    });
}


document.addEventListener('DOMContentLoaded', () => {


    document.getElementById("add-product-btn").addEventListener("click", function() {
        // Hiển thị form thêm sản phẩm
        Swal.fire({
            title: 'Thêm Sản Phẩm',
            html: `
                <div id="add-form">
                    <div>
                        <label for="productName">Tên Sản Phẩm:</label>
                        <input type="text" id="add-productName" required>
                    </div>
                    <div>
                        <label for="image">Hình Ảnh:</label>
                        <input type="text" id="add-image" required>
                    </div>
                    <div>
                        <label for="price">Giá:</label>
                        <input type="number" id="add-price" required>
                    </div>
                    <div>
                        <label for="quantity">Số Lượng:</label>
                        <input type="number" id="add-quantity" required>
                    </div>
                </div>
            `,
            focusConfirm: false,
            showCancelButton: true,
            confirmButtonText: 'Thêm',
            cancelButtonText: 'Hủy',
            preConfirm: () => {
                const productName = document.getElementById('add-productName').value;
                const image = document.getElementById('add-image').value;
                const price = document.getElementById('add-price').value;
                const quantity = document.getElementById('add-quantity').value;
        

                return { productName, image, price, quantity};
            }
        }).then((result) => {
            if (result.isConfirmed) {
                const newProduct = result.value;
                var token = localStorage.getItem('authToken');
                // Gửi yêu cầu POST để thêm sản phẩm mới
                fetch('http://localhost:8080/api/v1/products/insert', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}` // Thêm token vào tiêu đề
                    },
                    body: JSON.stringify(newProduct)
                })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Có lỗi xảy ra khi thêm sản phẩm');
                    }
                    return response.json();
                })
                .then(data => {
                    Swal.fire('Thành công!', 'Sản phẩm đã được thêm.', 'success');
                    // Cập nhật danh sách sản phẩm sau khi thêm
                    loadProducts();
                })
                .catch(error => {
                    Swal.fire('Lỗi!', error.message, 'error');
                });
            }
        });
    });
});

document.getElementById('searchProductButton').addEventListener('click', async function () {
    const productName = document.getElementById('searchProductName').value;

    try {
        const response = await fetch(`http://localhost:8080/api/v1/products/search?productName=${encodeURIComponent(productName)}`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('authToken')}`, // Thêm token vào tiêu đề
            },
        });

        if (!response.ok) {
            const data = await response.json();
            throw new Error(data.message || 'Đã xảy ra lỗi.');
        }

        const products = await response.json();
        console.log(products); // Kiểm tra phản hồi từ API
        displaySearchResults(products); // Hiển thị kết quả tìm kiếm
    } catch (error) {
        Swal.fire('Lỗi!', `Tìm kiếm không thành công: ${error.message}`, 'error');
    }
});

// Log out
document.getElementById('LogoutButton').addEventListener('click', async function () {
    const token = localStorage.getItem('authToken'); // Lấy token từ localStorage


    try {
        const response = await fetch('http://localhost:8080/api/v1/logout', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json', // Định dạng nội dung
                'Authorization': `Bearer ${token}` // Thêm token vào tiêu đề
            },
            body: JSON.stringify({ token }) // Gửi token trong request body
        });

        if (!response.ok) {
            const data = await response.json();
            throw new Error(data.message || 'Đã xảy ra lỗi khi đăng xuất.');
        }

        // Xóa token khỏi localStorage
        localStorage.removeItem('authToken');
        
        // Hiển thị thông báo thành công
        Swal.fire('Thành công!', 'Bạn đã đăng xuất thành công.', 'success');

        // Chuyển hướng người dùng đến trang đăng nhập hoặc trang chính
        window.location.href = '/login.html'; // Đổi đường dẫn theo yêu cầu của bạn
    } catch (error) {
        Swal.fire('Lỗi!', `Đăng xuất không thành công: ${error.message}`, 'error');
    }
});
 