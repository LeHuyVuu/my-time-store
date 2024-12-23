import React from 'react';

const Benefits = () => {
    return (
        <div>
            <section className="benefits py-5">
                <div className="container text-center">
                    <div className="row g-4">
                        <div className="col-md-4">
                            <div className="benefit-item d-flex flex-column align-items-center">
                                <i className="bi bi-truck fs-1 text-primary"></i>
                                <h5 className="fw-bold mt-3">Free Shipping</h5>
                                <p className="text-muted small">For all orders over $99</p>
                            </div>
                        </div>
                        <div className="col-md-4">
                            <div className="benefit-item d-flex flex-column align-items-center">
                                <i className="bi bi-clock fs-1 text-primary"></i>
                                <h5 className="fw-bold mt-3">Delivery On Time</h5>
                                <p className="text-muted small">If goods have problems</p>
                            </div>
                        </div>
                        <div className="col-md-4">
                            <div className="benefit-item d-flex flex-column align-items-center">
                                <i className="bi bi-shield-lock fs-1 text-primary"></i>
                                <h5 className="fw-bold mt-3">Secure Payment</h5>
                                <p className="text-muted small">100% secure payment</p>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    );
};

export default Benefits;
