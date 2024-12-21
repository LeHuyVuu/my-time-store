
import RelatedProduct from '../../components/_ProductDetail/RelatedProduct';
import './ProductDetail.css'
const ProductDetail = () => {
   
    return (
        <div className='product-detail'>
            <main className="main-content mt-5 mb-5">
                <div className="container">
                    <div className="row g-4">
                        {/* Product Image */}
                        <div className="col-md-6 text-center">
                            {/* Lightbox Wrapper */}
                            <a href="#" data-bs-toggle="modal" data-bs-target="#imageModal">
                                <img src="https://i.postimg.cc/1X7qvwNv/OID-1.jpg" alt="Product" className="img-fluid product-image rounded" />
                            </a>
                        </div>

                        {/* Product Info */}
                        <div className="col-md-6">
                            <h2 className="product-title">Elegant Wooden Clock</h2>
                            <p className="text-muted">SKU: #12345</p>
                            <div className="product-price mb-4">
                                <h4 className="text-primary">$120.00</h4>
                            </div>
                            <p className="product-description">
                                This elegant wooden clock is handcrafted to perfection, combining timeless design and modern craftsmanship. Perfect for enhancing the aesthetic of your living space.
                            </p>
                            <div className="quantity-controls my-4 d-flex align-items-center">
                                <label htmlFor="quantity" className="me-3">Quantity:</label>
                                <input type="number" id="quantity" className="form-control w-25 d-inline-block" min="1" defaultValue="1" />
                            </div>
                            <div className="d-flex gap-3">
                                <button className="btn btn-primary btn-lg rounded-pill flex-grow-1">Add to Cart</button>
                                <button className="btn btn-outline-secondary btn-lg rounded-pill flex-grow-1">Buy Now</button>
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
                <RelatedProduct/>
            </div>
        </div>
    );
};

export default ProductDetail;
