import React from 'react';
import { Link } from 'react-router-dom';
import './ListProduct.css'

const ListProduct = () => {
    return (
        <div>
            <main className="container my-5">
                <div className="filter-bar d-flex justify-content-between align-items-center mb-4">
                    <div className="sort-options">
                        <a href="#" className="me-4 active">Newest Arrivals</a>
                        <a href="#" className="me-4">Price High to Low</a>
                        <a href="#" className="me-4">Most Popular</a>
                    </div>
                    <select className="form-select w-auto">
                        <option>40 per page</option>
                        <option>20 per page</option>
                        <option>60 per page</option>
                    </select>
                </div>

                <div className="row g-4">
                    <div className="col-lg-4 col-md-6">
                        <div className="product-card">
                            <div className="image-wrapper">
                                <Link to="/product-detail">
                                    <img src="https://i.postimg.cc/1X7qvwNv/OID-1.jpg" alt="Luxury Watch" className="img-fluid"/>
                                </Link>
                                <div className="overlay">
                                    <button className="btn btn-light me-2"><i className="bi bi-cart-plus"></i></button>
                                    <button className="btn btn-light"><i className="bi bi-heart"></i></button>
                                </div>
                                <span className="badge bg-danger position-absolute top-0 start-0 m-3">New</span>
                            </div>
                            <div className="product-info p-3">
                                <Link to="/product-detail">
                                    <h3 className="product-name">Classic Chronograph</h3>
                                </Link>
                                <p className="product-price">$299.99</p>
                            </div>
                        </div>
                    </div>
                    <div className="col-lg-4 col-md-6">
                        <div className="product-card">
                            <div className="image-wrapper">
                                <Link to="/product-detail">
                                    <img src="https://i.postimg.cc/1X7qvwNv/OID-1.jpg" alt="Elegant Watch" className="img-fluid"/>
                                </Link>
                                <div className="overlay">
                                    <button className="btn btn-light me-2"><i className="bi bi-cart-plus"></i></button>
                                    <button className="btn btn-light"><i className="bi bi-heart"></i></button>
                                </div>
                            </div>
                            <div className="product-info p-3">
                                <Link to="/product-detail">
                                    <h3 className="product-name">Elegant Timepiece</h3>
                                </Link>
                                <p className="product-price">$399.99</p>
                            </div>
                        </div>
                    </div>
                    <div className="col-lg-4 col-md-6">
                        <div className="product-card">
                            <div className="image-wrapper">
                                <Link to="/product-detail">
                                    <img src="https://i.postimg.cc/1X7qvwNv/OID-1.jpg" alt="Smart Watch" className="img-fluid"/>
                                </Link>
                                <div className="overlay">
                                    <button className="btn btn-light me-2"><i className="bi bi-cart-plus"></i></button>
                                    <button className="btn btn-light"><i className="bi bi-heart"></i></button>
                                </div>
                            </div>
                            <div className="product-info p-3">
                                <Link to="/product-detail">
                                    <h3 className="product-name">Modern Smartwatch</h3>
                                </Link>
                                <p className="product-price">$199.99</p>
                            </div>
                        </div>
                    </div>
                </div>

                <nav className="mt-5">
                    <ul className="pagination justify-content-center">
                        <li className="page-item"><a className="page-link" href="#"><i className="bi bi-chevron-left"></i></a></li>
                        <li className="page-item active"><a className="page-link" href="#">1</a></li>
                        <li className="page-item"><a className="page-link" href="#">2</a></li>
                        <li className="page-item"><a className="page-link" href="#">3</a></li>
                        <li className="page-item"><a className="page-link" href="#"><i className="bi bi-chevron-right"></i></a></li>
                    </ul>
                </nav>
            </main>

        </div>
    )
}

export default ListProduct
