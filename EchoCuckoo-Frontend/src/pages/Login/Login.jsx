
import './Login.css';
import { Link } from 'react-router-dom';

const Login = () => {
  
    return (
        <div>
            <main className="main-content py-5">
                <div className="container">
                    <div className="row g-4">
                        <div className="col-md-6">
                            <div className="new-user-box h-100 p-4 d-flex flex-column justify-content-center align-items-center text-center">
                                <h2 className="mb-4">New to our Shop?</h2>
                                <p className="mb-4">Join us today and discover a world of amazing products and exclusive offers!</p>
                                <Link to="/register"> <button className="btn btn-light btn-lg rounded-pill shadow-sm">Register Now</button></Link>
                            </div>
                        </div>
                        <div className="col-md-6">
                            <div className="login-form-box p-4">
                                <h2 className="mb-4 text-center">Welcome Back</h2>
                                <form>
                                    <div className="mb-3">
                                        <input type="email" className="form-control form-control-lg rounded-pill" placeholder="Email Address" required />
                                    </div>
                                    <div className="mb-3">
                                        <input type="password" className="form-control form-control-lg rounded-pill" placeholder="Password" required />
                                    </div>
                                    <div className="mb-3 form-check">
                                        <input type="checkbox" className="form-check-input" id="rememberMe" />
                                        <label className="form-check-label" htmlFor="rememberMe">Remember me</label>
                                    </div>
                                    <button type="submit" className="btn btn-primary btn-lg w-100 rounded-pill mb-3">Login</button>
                                    <div className="text-center">
                                        <a href="#" className="text-decoration-none">Forgot Password?</a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    );
};

export default Login;
