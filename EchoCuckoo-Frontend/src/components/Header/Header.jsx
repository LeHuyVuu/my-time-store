import './Header.css'; 
const Header = () => {
    return (
        <div>
            <div className="header-section">
                <nav id="navbar"
                    className="navbar navbar-expand-lg bg-white py-3 sticky-top">
                    <div className="container">
                        <a className="navbar-brand fw-bold" href="#">Echo<span
                            className="text-danger">Cuckoo</span></a>
                        <button className="navbar-toggler" type="button"
                            data-bs-toggle="collapse" data-bs-target="#navbarNav"
                            aria-controls="navbarNav" aria-expanded="false"
                            aria-label="Toggle navigation">
                            <span className="navbar-toggler-icon"></span>
                        </button>
                        <div className="collapse navbar-collapse" id="navbarNav">
                            <ul className="navbar-nav mx-auto">
                                <li className="nav-item"><a className="nav-link"
                                    href="#">Home</a></li>
                                <li className="nav-item"><a className="nav-link"
                                    href="#">Shop</a></li>
                                <li className="nav-item"><a className="nav-link"
                                    href="#">About</a></li>
                                <li className="nav-item"><a className="nav-link"
                                    href="#">Latest</a></li>
                                <li className="nav-item"><a className="nav-link"
                                    href="#">Blog</a></li>
                                <li className="nav-item"><a className="nav-link"
                                    href="#">Pages</a></li>
                                <li className="nav-item"><a className="nav-link"
                                    href="#">Contact</a></li>
                            </ul>
                            <div className="d-flex align-items-center ms-3">
                                <a href="#" className="text-dark me-3"><i
                                    className="bi bi-search"></i></a>
                                <a href="#" className="text-dark me-3"><i
                                    className="bi bi-person"></i></a>
                                <a href="#" className="text-dark"><i
                                    className="bi bi-cart"></i></a>
                            </div>
                        </div>
                    </div>
                </nav>
            </div>
        </div>
    )
}

export default Header