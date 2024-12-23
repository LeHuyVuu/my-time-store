import './RelatedProduct.css';
import { Link } from 'react-router-dom';

const RelatedProduct = () => {
    return (
        <div>
            {/* Related Products Section */}
            <section className="related-products bg-light py-5">
                <div className="container">
                    <h3 className="text-center mb-5">Related Products</h3>
                    <div className="row g-4">
                        <div className="col-md-4">
                            <Link to="/product-detail">
                                <div className="card border-0 shadow-sm">
                                    <img src="https://i.postimg.cc/wTYNQbXx/cuckoo-august-schwer-036-50-47101c.jpg" alt="Related Product 1" className="card-img-top" />
                                    <div className="card-body">
                                        <h5 className="card-title">Classic Clock</h5>
                                        <p className="text-primary">$80.00</p>
                                    </div>
                                </div>
                            </Link>
                        </div>
                        <div className="col-md-4">
                            <Link to="/product-detail">
                                <div className="card border-0 shadow-sm">
                                    <img src="https://i.postimg.cc/66vRRNG1/cuckoo-august-schwer-036-50-75001c02-600x600.jpg" alt="Related Product 2" className="card-img-top" />
                                    <div className="card-body">
                                        <h5 className="card-title">Modern Wall Clock</h5>
                                        <p className="text-primary">$150.00</p>
                                    </div>
                                </div>
                            </Link>
                        </div>
                        <div className="col-md-4">
                            <Link to="/product-detail">
                                <div className="card border-0 shadow-sm">
                                    <img src="https://i.postimg.cc/XYQy3jTX/dh-718e78a4edbb4762b3005fe76313eb10-master.jpg" alt="Related Product 3" className="card-img-top" />
                                    <div className="card-body">
                                        <h5 className="card-title">Rustic Table Clock</h5>
                                        <p className="text-primary">$95.00</p>
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

export default RelatedProduct;
