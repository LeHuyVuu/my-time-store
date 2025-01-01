import { useEffect, useState } from 'react';
import './NewArrival.css';
import { Link } from 'react-router-dom';
import { getProducts } from '../../../apis/Product API/product';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';

const NewArrival = () => {
  const [products, setProducts] = useState([]);
  const [page, setPage] = useState(1); // State quản lý số trang
  const pageSize = 12; // Số sản phẩm trên mỗi trang

  useEffect(() => {
    // Gọi API để lấy danh sách sản phẩm
    getProducts({ page, pageSize })
      .then((res) => {
        if (res.data && res.data.data) {
          setProducts(res.data.data.data); // Lấy danh sách sản phẩm từ API
        }
      })
      .catch((err) => {
        console.error('Error fetching products:', err);
      });
  
    // Khởi tạo carousel thủ công
    const carouselElement = document.querySelector('#productCarousel');
    if (carouselElement) {
      const carousel = new bootstrap.Carousel(carouselElement, {
        interval: 2000, // Tự động chuyển sau 1 giây
        ride: 'carousel', // Kích hoạt tự động
      });
    }
  }, [page]);
  
  // Tạo danh sách các slide (mỗi slide chứa tối đa 3 sản phẩm)
  const slides = [];
  for (let i = 0; i < products.length; i += 3) {
    slides.push(products.slice(i, i + 3));
  }

  console.log("slides", slides);

  return (
    <div>
      <section className="new-arrivals py-5">
        <div className="container text-center">
          {/* Section Title */}
          <h2 className="fw-bold mb-4">New Arrivals</h2>

          {/* Bootstrap Carousel */}
          <div id="productCarousel" className="carousel slide" data-bs-ride="carousel" data-bs-interval="2000">
            {/* Carousel Indicators */}
            <div className="carousel-indicators">
              {slides.map((_, index) => (
                <button
                  key={index}
                  type="button"
                  data-bs-target="#productCarousel"
                  data-bs-slide-to={index}
                  className={index === 0 ? 'active' : ''}
                  aria-current={index === 0 ? 'true' : ''}
                  aria-label={`Slide ${index + 1}`}
                ></button>
              ))}
            </div>

            {/* Carousel Items */}
            <div className="carousel-inner">
              {slides.map((slide, index) => (
                <div key={index} className={`carousel-item ${index === 0 ? 'active' : ''}`}>
                  <div className="row">
                    {slide.map((product) => (
                      <div key={product.productId} className="col-md-4">
                        <Link to={`/product-detail/${product.productId}`}>
                          <div className="card border-0">
                            <img
                              src={product.image}
                              className="card-img-top img-fluid"
                              alt={product.productName}
                            />
                            <div className="card-body">
                              <h5 className="card-title">{product.productName}</h5>
                              <p className="text-danger fw-bold">${product.price}</p>
                              <p className="text-muted">{product.description}</p>
                            </div>
                          </div>
                        </Link>
                      </div>
                    ))}
                  </div>
                </div>
              ))}
            </div>

            {/* Carousel Controls */}
            <button
              className="carousel-control-prev"
              type="button"
              data-bs-target="#productCarousel"
              data-bs-slide="prev"
            >
              <span className="carousel-control-prev-icon" aria-hidden="true"></span>
              <span className="visually-hidden">Previous</span>
            </button>
            <button
              className="carousel-control-next"
              type="button"
              data-bs-target="#productCarousel"
              data-bs-slide="next"
            >
              <span className="carousel-control-next-icon" aria-hidden="true"></span>
              <span className="visually-hidden">Next</span>
            </button>
          </div>
        </div>
      </section>
    </div>
  );
};

export default NewArrival;
