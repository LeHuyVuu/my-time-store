import { Link } from 'react-router-dom';
import './Header.css';
const Header = () => {
    return (
        <div>
            <nav id="navbar"
                className="navbar navbar-expand-lg bg-white py-3 sticky-top">
                <div className="container">
                    <Link className="navbar-brand fw-bold" to='/'>Echo<span
                        className="text-danger">Cuckoo</span></Link>
                    <button className="navbar-toggler" type="button"
                        data-bs-toggle="collapse" data-bs-target="#navbarNav"
                        aria-controls="navbarNav" aria-expanded="false"
                        aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarNav">
                        <ul className="navbar-nav mx-auto">
                            <li className="nav-item">
                                <Link className="nav-link" to="/">Home</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/shop">Shop</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/about">About</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/contact">Contact</Link>
                            </li>
                        </ul>
                        <div className="d-flex align-items-center ms-3">
                            <div to="/search" className="text-dark me-3" style={{ cursor: 'pointer' }}><i className="bi bi-search"></i></div>
                            <Link to="/login" className="text-dark me-3"><i className="bi bi-person"></i></Link>
                            <Link to="/cart" className="text-dark"><i className="bi bi-cart"></i></Link>
                        </div>
                    </div>
                </div>
            </nav>

        </div>
    )
}

export default Header