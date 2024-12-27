import { useEffect, useState } from 'react';
import './FeatureCollections.css'
import { Link } from 'react-router-dom'
import {  getProducts } from '../../../apis/Product API/product';

const FeatureCollections = () => {
  
  const [products, setProducts] = useState([]);
  const [page, setPage] = useState(1); // State quản lý số trang
  const pageSize = 3; // Số sản phẩm trên mỗi trang

  useEffect(() => {
    // Gọi API để lấy danh sách sản phẩm với tham số page và pageSize
    getProducts({ page, pageSize })
      .then((res) => {
        if (res.data && res.data.data) {
          setProducts(res.data.data.data); // Lấy danh sách sản phẩm từ API
        }
      })
      .catch((err) => {
        console.error('Error fetching products:', err);
      });
  }, [page]);


  console.log(products);

  return (
    <div>
      {/* Feature collection */}
      <section className="featured-collections py-5">
        <div className="container text-center">
          <h2 className="fw-bold mb-4">Featured Collections</h2>
          <div className="row g-4">
            {products.map((product, index) => (
            <div className="col-md-4" key={index}>
              <Link to={`/product-detail/${product.productId}`}>
                <div className="collection-item position-relative">
                  <img
                    src={product.image}
                    className="img-fluid rounded shadow"
                    alt="Elegant Watches"
                  />
                  <div className="overlay position-absolute w-100 h-100 top-0 start-0 d-flex align-items-center justify-content-center">
                    <div className="text-white text-center">
                      <h5 className="fw-bold">{product.productName}</h5>
                      <p className="small">{product.description}</p>
                      <a href="#" className="btn btn-outline-light">
                        Explore Now
                      </a>
                    </div>
                  </div>
                </div>
              </Link>
            </div>
            ))}
          </div>
        </div>
      </section>
    </div>
  );
};

export default FeatureCollections;
