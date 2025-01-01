import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import './ListProduct.css';
import { getProducts } from '../../apis/Product API/product';
import { toast } from 'react-toastify';

const ListProduct = () => {
    const [products, setProducts] = useState([]); // Initialize as an empty array
    const [page, setPage] = useState(1); // Initialize with page 1

    useEffect(() => {
        // Fetch products from the API
        getProducts({ page, pageSize: 18 })
            .then((res) => {
                if (res.data && res.data.data) {
                    setProducts(res.data.data.data); // Update products state
                    toast.success("success");
                }
            })
            .catch((err) => {
                console.error('Error fetching products:', err);
                toast.error("error");
            });
    }, [page]); // Re-fetch products when page changes

    return (
        <div>
            <main className="container my-5">
                {/* Filter Bar */}
                <div className="filter-bar mb-4">
                    <div className="row align-items-center">
                        <div className="col-12 col-md-8 mb-2 mb-md-0">
                            <div className="sort-options d-flex flex-wrap justify-content-center justify-content-md-start">
                                <a href="#" className="me-3 active">All Items</a>
                                <a href="#" className="me-3">Price Low to High</a>
                                <a href="#" className="me-3">Most Popular</a>
                            </div>
                        </div>
                    </div>
                </div>

                {/* Product Cards */}
                <div className="row g-4">
                    {products.map((product) => (
                        <div className="col-lg-4 col-md-6" key={product.productId}>
                            <div className="product-card">
                                <div className="image-wrapper">
                                    <Link to={`/product-detail/${product.productId}`}>
                                        <img
                                            src={product.image || 'https://via.placeholder.com/150'}
                                            alt={product.name}
                                            className="img-fluid"
                                        />
                                    </Link>
                                    <div className="overlay">
                                        <button className="btn btn-light me-2">
                                            <i className="bi bi-cart-plus"></i>
                                        </button>
                                        <button className="btn btn-light">
                                            <i className="bi bi-heart"></i>
                                        </button>
                                    </div>
                                    {product.isNew && (
                                        <span className="badge bg-danger position-absolute top-0 start-0 m-3">New</span>
                                    )}
                                </div>
                                <div className="product-info p-3">
                                    <Link to={`/product-detail/${product.productId}`}>
                                        <h3 className="product-name">{product.productName}</h3>
                                    </Link>
                                    <p className="product-price">${product.price}</p>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>

                {/* Pagination */}
                <nav className="mt-5">
                    <ul className="pagination justify-content-center">
                        <li className={`page-item ${page === 1 ? 'disabled' : ''}`}>
                            <button
                                className="page-link"
                                onClick={() => {
                                    setPage(page - 1);
                    
                                }}

                                disabled={page === 1}
                            >
                                <i className="bi bi-chevron-left"></i>
                            </button>
                        </li>
                        {[1, 2, 3].map((p) => (
                            <li className={`page-item ${page === p ? 'active' : ''}`} key={p}>
                                <button className="page-link" onClick={() => {
                                    setPage(p);
                                  
                                }}>
                                    {p}
                                </button>
                            </li>
                        ))}
                        <li className="page-item">
                            <button className="page-link" onClick={() => {

                                setPage(page + 1);
                               
                            }}>
                                <i className="bi bi-chevron-right"></i>
                            </button>
                        </li>
                    </ul>
                </nav>
            </main>
        </div>
    );
};

export default ListProduct;
