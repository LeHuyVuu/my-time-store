import './CartList.css'
const CartList = () => {
   
    return (
        <div>
            <main className="main-content py-5">
                <div className="container">
                    <div className="row">
                        {/* Cart Items */}
                        <div className="col-lg-8">
                            <div className="cart-items">
                                <h3 className="mb-4">Your Cart</h3>
                                {/* Single Cart Item 1 */}
                                <div className="cart-item d-flex align-items-center justify-content-between border-bottom py-3">
                                    <div className="d-flex align-items-center">
                                        <img src="/Image/OID-1.jpg" alt="Product" className="img-fluid rounded me-3" style={{ width: '80px', height: '80px' }} />
                                        <div>
                                            <h5 className="mb-1">Elegant Wooden Clock</h5>
                                            <p className="text-muted mb-0">Price: $120.00</p>
                                        </div>
                                    </div>
                                    <div className="d-flex align-items-center">
                                        <input type="number" className="form-control form-control-sm me-3" defaultValue="1" min="1" style={{ width: '60px' }} />
                                        <button className="btn btn-danger btn-sm">Remove</button>
                                    </div>
                                </div>
                                {/* Single Cart Item 2 */}
                                <div className="cart-item d-flex align-items-center justify-content-between border-bottom py-3">
                                    <div className="d-flex align-items-center">
                                        <img src="/Image/OID-1.jpg" alt="Product" className="img-fluid rounded me-3" style={{ width: '80px', height: '80px' }} />
                                        <div>
                                            <h5 className="mb-1">Classic Wall Clock</h5>
                                            <p className="text-muted mb-0">Price: $90.00</p>
                                        </div>
                                    </div>
                                    <div className="d-flex align-items-center">
                                        <input type="number" className="form-control form-control-sm me-3" defaultValue="1" min="1" style={{ width: '60px' }} />
                                        <button className="btn btn-danger btn-sm">Remove</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        {/* Order Summary */}
                        <div className="col-lg-4">
                            <div className="order-summary p-4 bg-light rounded shadow">
                                <h3 className="mb-4">Order Summary</h3>
                                <div className="d-flex justify-content-between">
                                    <p>Subtotal:</p>
                                    <p className="fw-bold">$120.00</p>
                                </div>
                                <div className="d-flex justify-content-between">
                                    <p>Tax (10%):</p>
                                    <p className="fw-bold">$12.00</p>
                                </div>
                                <div className="d-flex justify-content-between border-top pt-3">
                                    <h5>Total:</h5>
                                    <h5 className="fw-bold">$132.00</h5>
                                </div>
                                <div className="mt-4 d-flex justify-content-between gap-3">
                                    {/* VNPay Button */}
                                    <button className="btn btn-vnpay rounded-circle d-flex align-items-center justify-content-center">
                                        <img src="/Image/Payment Logo/Logo-VNPAY-QR-1.webp" alt="VNPay" className="payment-logo" />
                                    </button>

                                    {/* Momo Button */}
                                    <button className="btn btn-momo rounded-circle d-flex align-items-center justify-content-center">
                                        <img src="/Image/Payment Logo/logo-momo-4.jpg" alt="Momo" className="payment-logo" />
                                    </button>

                                    {/* PayPal Button */}
                                    <button className="btn btn-paypal rounded-circle d-flex align-items-center justify-content-center">
                                        <img src="/Image/Payment Logo/images.png" alt="PayPal" className="payment-logo" />
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    );
};

export default CartList;
