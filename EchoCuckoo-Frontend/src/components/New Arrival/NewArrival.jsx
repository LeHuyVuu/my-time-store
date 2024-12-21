import './NewArrival.css';

const NewArrival = () => {
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
              <button type="button" data-bs-target="#productCarousel" data-bs-slide-to="0" className="active" aria-current="true" aria-label="Slide 1"></button>
              <button type="button" data-bs-target="#productCarousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
              <button type="button" data-bs-target="#productCarousel" data-bs-slide-to="2" aria-label="Slide 3"></button>
            </div>

            {/* Carousel Items */}
            <div className="carousel-inner">
              {/* Slide 1 */}
              <div className="carousel-item active">
                <div className="row">
                  <div className="col-md-4">
                    <div className="card border-0">
                      <img src="https://i.postimg.cc/qR0yqkTx/Nobackground.png" className="card-img-top img-fluid" alt="Product 1" />
                      <div className="card-body">
                        <h5 className="card-title">Thermo Ball Etip Gloves</h5>
                        <p className="text-danger fw-bold">$45,743</p>
                      </div>
                    </div>
                  </div>
                  <div className="col-md-4">
                    <div className="card border-0">
                      <img src="https://i.postimg.cc/qR0yqkTx/Nobackground.png" className="card-img-top img-fluid" alt="Product 2" />
                      <div className="card-body">
                        <h5 className="card-title">Thermo Ball Etip Gloves</h5>
                        <p className="text-danger fw-bold">$45,743</p>
                      </div>
                    </div>
                  </div>
                  <div className="col-md-4">
                    <div className="card border-0">
                      <img src="https://i.postimg.cc/1X7qvwNv/OID-1.jpg" className="card-img-top img-fluid" alt="Product 3" />
                      <div className="card-body">
                        <h5 className="card-title">Thermo Ball Etip Gloves</h5>
                        <p className="text-danger fw-bold">$45,743</p>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              {/* Slide 2 */}
              <div className="carousel-item">
                <div className="row">
                  <div className="col-md-4">
                    <div className="card border-0">
                      <img src="https://i.postimg.cc/1X7qvwNv/OID-1.jpg" className="card-img-top img-fluid" alt="Product 4" />
                      <div className="card-body">
                        <h5 className="card-title">Winter Jacket</h5>
                        <p className="text-danger fw-bold">$60,000</p>
                      </div>
                    </div>
                  </div>
                  <div className="col-md-4">
                    <div className="card border-0">
                      <img src="https://i.postimg.cc/1X7qvwNv/OID-1.jpg" className="card-img-top img-fluid" alt="Product 5" />
                      <div className="card-body">
                        <h5 className="card-title">Leather Bag</h5>
                        <p className="text-danger fw-bold">$80,000</p>
                      </div>
                    </div>
                  </div>
                  <div className="col-md-4">
                    <div className="card border-0">
                      <img src="https://i.postimg.cc/1X7qvwNv/OID-1.jpg" className="card-img-top img-fluid" alt="Product 6" />
                      <div className="card-body">
                        <h5 className="card-title">Stylish Hat</h5>
                        <p className="text-danger fw-bold">$35,000</p>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              {/* Slide 3 */}
              <div className="carousel-item">
                <div className="row">
                  <div className="col-md-4">
                    <div className="card border-0">
                      <img src="https://i.postimg.cc/1X7qvwNv/OID-1.jpg" className="card-img-top img-fluid" alt="Product 7" />
                      <div className="card-body">
                        <h5 className="card-title">Classic Watch</h5>
                        <p className="text-danger fw-bold">$100,000</p>
                      </div>
                    </div>
                  </div>
                  <div className="col-md-4">
                    <div className="card border-0">
                      <img src="https://i.postimg.cc/1X7qvwNv/OID-1.jpg" className="card-img-top img-fluid" alt="Product 8" />
                      <div className="card-body">
                        <h5 className="card-title">Running Shoes</h5>
                        <p className="text-danger fw-bold">$75,000</p>
                      </div>
                    </div>
                  </div>
                  <div className="col-md-4">
                    <div className="card border-0">
                      <img src="https://i.postimg.cc/1X7qvwNv/OID-1.jpg" className="card-img-top img-fluid" alt="Product 9" />
                      <div className="card-body">
                        <h5 className="card-title">Sports Cap</h5>
                        <p className="text-danger fw-bold">$25,000</p>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            {/* Carousel Controls */}
            <button className="carousel-control-prev" type="button" data-bs-target="#productCarousel" data-bs-slide="prev">
              <span className="carousel-control-prev-icon" aria-hidden="true"></span>
              <span className="visually-hidden">Previous</span>
            </button>
            <button className="carousel-control-next" type="button" data-bs-target="#productCarousel" data-bs-slide="next">
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
