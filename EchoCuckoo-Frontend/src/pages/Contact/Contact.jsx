import './Contact.css'
const Contact = () => {
  
    return (
        <div className='contact-section'>
            <main className="container my-5">
                <div className="row g-4">
                    <div className="col-lg-7">
                        <h2 className="mb-4 fw-semibold text-navy">Get in Touch</h2>
                        <form className="contact-form">
                            <div className="mb-4">
                                <textarea className="form-control" rows="5" placeholder="Enter Message"></textarea>
                            </div>
                            <div className="row mb-4">
                                <div className="col-md-6 mb-4 mb-md-0">
                                    <input type="text" className="form-control" placeholder="Enter Name" />
                                </div>
                                <div className="col-md-6">
                                    <input type="email" className="form-control" placeholder="Enter Email" />
                                </div>
                            </div>
                            <div className="mb-4">
                                <input type="text" className="form-control" placeholder="Enter Subject" />
                            </div>
                            <button type="submit" className="btn btn-danger px-4 py-2">SEND</button>
                        </form>
                    </div>
                    <div className="col-lg-5">
                        <div className="contact-info ps-lg-4">
                            <div className="mb-4">
                                <div className="d-flex align-items-center">
                                    <i className="bi bi-geo-alt text-muted fs-4 me-3"></i>
                                    <div>
                                        <h5 className="fw-semibold mb-1">Address</h5>
                                        <p className="text-muted mb-0">123 Business Avenue, Suite 100<br />New York, NY 10001</p>
                                    </div>
                                </div>
                            </div>
                            <div className="mb-4">
                                <div className="d-flex align-items-center">
                                    <i className="bi bi-telephone text-muted fs-4 me-3"></i>
                                    <div>
                                        <h5 className="fw-semibold mb-1">Phone</h5>
                                        <p className="text-muted mb-0">+1 (555) 123-4567</p>
                                    </div>
                                </div>
                            </div>
                            <div className="mb-4">
                                <div className="d-flex align-items-center">
                                    <i className="bi bi-envelope text-muted fs-4 me-3"></i>
                                    <div>
                                        <h5 className="fw-semibold mb-1">Email</h5>
                                        <p className="text-muted mb-0">contact@company.com</p>
                                    </div>
                                </div>
                            </div>
                            <div className="mb-4">
                                <div className="d-flex align-items-center">
                                    <i className="bi bi-clock text-muted fs-4 me-3"></i>
                                    <div>
                                        <h5 className="fw-semibold mb-1">Working Hours</h5>
                                        <p className="text-muted mb-0">Mon - Fri: 9:00 AM - 6:00 PM<br />Sat - Sun: Closed</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>

            <br /><br /><br /><br /><br />

        </div>
    );
};

export default Contact;
