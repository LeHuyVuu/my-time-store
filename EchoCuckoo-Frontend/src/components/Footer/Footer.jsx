import './Footer.css'; 
const Footer = () => {
  return (
    <div>
      <footer className="bg-light text-dark py-5">
        <div className="container">
          <div className="row text-center text-md-start">
            {/* About Section */}
            <div className="col-md-4 col-12 mb-4">
              <h5 className="fw-bold mb-4">About EchoCuckoo</h5>
              <p>
                We bring you timeless designs and quality products that enhance your lifestyle. Discover our curated collections and experience luxury at its finest.
              </p>
            </div>

            {/* Quick Links */}
            <div className="col-md-4 col-12 mb-4">
              <h5 className="fw-bold mb-4">Quick Links</h5>
              <ul className="list-unstyled">
                <li>
                  <a href="#" className="text-black text-decoration-none d-block">Home</a>
                </li>
                <li>
                  <a href="#" className="text-black text-decoration-none d-block">Shop</a>
                </li>
                <li>
                  <a href="#" className="text-black text-decoration-none d-block">About</a>
                </li>
                <li>
                  <a href="#" className="text-black text-decoration-none d-block">Contact</a>
                </li>
                <li>
                  <a href="#" className="text-black text-decoration-none d-block">Privacy Policy</a>
                </li>
              </ul>
            </div>

            {/* Contact Section */}
            <div className="col-md-4 col-12 mb-4">
              <h5 className="fw-bold mb-4">Get in Touch</h5>
              <p><i className="bi bi-geo-alt"></i> 123 Echo Street, Fashion City</p>
              <p><i className="bi bi-telephone"></i> +1 234 567 890</p>
              <p><i className="bi bi-envelope"></i> info@echocuckoo.com</p>

              <div className="social-icons mt-3 d-flex justify-content-center">
                <a href="#" className="text-dark me-3">
                  <i className="bi bi-facebook"></i>
                </a>
                <a href="#" className="text-dark me-3">
                  <i className="bi bi-twitter"></i>
                </a>
                <a href="#" className="text-dark me-3">
                  <i className="bi bi-instagram"></i>
                </a>
                <a href="#" className="text-dark">
                  <i className="bi bi-youtube"></i>
                </a>
              </div>
            </div>
          </div>

          <div className="text-center mt-4">
            <p className="mb-0">&copy; 2024 EchoCuckoo. All rights reserved.</p>
          </div>
        </div>
      </footer>
    </div>
  );
};

export default Footer;
