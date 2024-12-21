import './FeatureCollections.css'
import { Link } from 'react-router-dom'

const FeatureCollections = () => {
  return (
    <div>
      {/* Feature collection */}
      <section className="featured-collections py-5">
        <div className="container text-center">
          <h2 className="fw-bold mb-4">Featured Collections</h2>
          <div className="row g-4">
            <div className="col-md-4">
              <Link to='/product-detail'>
                <div className="collection-item position-relative">
                  <img
                    src="https://i.postimg.cc/1X7qvwNv/OID-1.jpg"
                    className="img-fluid rounded shadow"
                    alt="Elegant Watches"
                  />
                  <div className="overlay position-absolute w-100 h-100 top-0 start-0 d-flex align-items-center justify-content-center">
                    <div className="text-white text-center">
                      <h5 className="fw-bold">Elegant Watches</h5>
                      <p className="small">Discover timeless pieces.</p>
                      <a href="#" className="btn btn-outline-light">
                        Explore Now
                      </a>
                    </div>
                  </div>
                </div>
              </Link>
            </div>
            <div className="col-md-4">
              <Link to='/product-detail'>
                <div className="collection-item position-relative">
                  <img
                    src="https://i.postimg.cc/1X7qvwNv/OID-1.jpg"
                    className="img-fluid rounded shadow"
                    alt="Luxury Bags"
                  />
                  <div className="overlay position-absolute w-100 h-100 top-0 start-0 d-flex align-items-center justify-content-center">
                    <div className="text-white text-center">
                      <h5 className="fw-bold">Luxury Bags</h5>
                      <p className="small">Premium quality and design.</p>
                      <a href="#" className="btn btn-outline-light">
                        Explore Now
                      </a>
                    </div>
                  </div>
                </div>
              </Link>
            </div>
            <div className="col-md-4">
              <Link to='/product-detail'>
                <div className="collection-item position-relative">
                  <img
                    src="https://i.postimg.cc/1X7qvwNv/OID-1.jpg"
                    className="img-fluid rounded shadow"
                    alt="Winter Essentials"
                  />
                  <div className="overlay position-absolute w-100 h-100 top-0 start-0 d-flex align-items-center justify-content-center">
                    <div className="text-white text-center">
                      <h5 className="fw-bold">Winter Essentials</h5>
                      <p className="small">Stay warm in style.</p>
                      <a href="#" className="btn btn-outline-light">
                        Explore Now
                      </a>
                    </div>
                  </div>
                </div>
              </Link>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
};

export default FeatureCollections;
