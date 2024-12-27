
import { useParams } from 'react-router-dom';
import RelatedProduct from '../../components/_ProductDetail/RelatedProduct';
import './ProductDetail.css'
import { useEffect, useState } from 'react';
import { getProductById } from '../../apis/Product API/product';
const ProductDetail = () => {

    const [product, setProduct] = useState(null);

    const { id } = useParams();

    useEffect(() => {
        const fetchProduct = async () => {
            try {
                const res = await getProductById(id); // Gọi API để lấy thông tin sản phẩm
                if (res.data && res.data.data) {
                    setProduct(res.data.data); // Cập nhật state với thông tin sản phẩm
                } else {
                    console.error("Unexpected response format:", res);
                }
            } catch (err) {
                console.error("Error fetching product:", err);
                // Bạn có thể thêm logic thông báo lỗi hoặc điều hướng ở đây
            }
        };

        if (id) { // Chỉ gọi API nếu `id` tồn tại
            fetchProduct();
        }
    }, [id]); // useEffect sẽ chạy lại khi `id` thay đổi



    return (
        <div className='product-detail'>
            <main className="main-content mt-5 mb-5">
                <div className="container">
                    <div className="row g-4">
                        {/* Product Image */}
                        <div className="col-md-6 text-center">
                            {/* Lightbox Wrapper */}
                            <a href="#" data-bs-toggle="modal" data-bs-target="#imageModal">
                                <img src={product?.image} alt="Product" className="img-fluid product-image rounded" />
                            </a>
                        </div>

                        <div className="col-md-6 product-details">
                            <h2 className="product-title">{product?.productName}</h2>
                            <div className="product-price">
                                <span className="old-price">{product?.price} $</span>
                                <span className="new-price text-danger">{product?.discountPrice} $</span>
                            </div>
                            <p className="product-description mt-3">{product?.description}</p>
                            <div className="quantity-controls my-4 d-flex align-items-center">
                                <label htmlFor="quantity" className="me-3 fw-bold">Quantity:</label>
                                <input
                                    type="number"
                                    id="quantity"
                                    className="form-control quantity-input w-25"
                                    min="1"
                                    defaultValue="1"
                                />
                            </div>
                            <div className="d-flex gap-3">
                                <button className="btn btn-primary btn-lg rounded-pill flex-grow-1 add-to-cart-btn">Add to Cart</button>
                                <button className="btn btn-outline-secondary btn-lg rounded-pill flex-grow-1 buy-now-btn">Buy Now</button>
                            </div>
                        </div>


                    </div>

                    {/* Product Details */}
                    <div className="row mt-5">
                        <div className="col">
                            <h3 className="mb-4">Product Details</h3>
                            <ul>
                                <li>Material: Premium quality wood</li>
                                <li>Dimensions: 30cm x 20cm x 10cm</li>
                                <li>Warranty: 1 Year</li>
                                <li>Designed for living rooms, offices, and bedrooms</li>
                            </ul>
                        </div>
                    </div>

                </div>
            </main>
            <div>
                <RelatedProduct />
            </div>
        </div>
    );
};

export default ProductDetail;
