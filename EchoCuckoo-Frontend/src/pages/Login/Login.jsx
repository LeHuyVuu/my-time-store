import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Login.css';
import { Link } from 'react-router-dom';
import { loginAPI } from '../../apis/Authenticate API/authenticate';
import { toast } from 'react-toastify';

const Login = () => {
    const [message, setMessage] = useState(null);
    const [formData, setFormData] = useState({
        email: '',
        password: ''
    });
    const navigate = useNavigate(); // Hook để chuyển hướng

    // Kiểm tra nếu người dùng đã đăng nhập
    useEffect(() => {
        const token = localStorage.getItem('token');
        if (token) {
            navigate('/profile'); // Nếu đã có token, chuyển hướng đến trang profile
        } else navigate('/login');
    }, [navigate]);

    // Xử lý đăng nhập
    const handleSubmit = async (e) => {
        e.preventDefault(); // Ngăn chặn reload trang
        setMessage(null); // Reset message
        try {
            const response = await loginAPI({
                email: formData.email,
                password: formData.password
            });

            // Lưu token vào localStorage
            if (response?.data) {
                localStorage.setItem('token', response.data);
                toast.success('Login successful!');
                navigate('/profile'); // Chuyển hướng đến trang profile sau khi đăng nhập thành công
            } else {
                toast.error('Unexpected error. Please try again.');
            }
        } catch (error) {
            const errorMsg = error.response?.data?.message || 'Login failed. Please try again.';
            setMessage(errorMsg);
            toast.error(errorMsg);
        }
    };

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value,
        });
    };

    return (
        <div>
            <main className="main-content py-5">
                <div className="container">
                    <div className="row g-4">
                        <div className="col-md-6">
                            <div className="new-user-box h-100 p-4 d-flex flex-column justify-content-center align-items-center text-center">
                                <h2 className="mb-4">New to our Shop?</h2>
                                <p className="mb-4">Join us today and discover a world of amazing products and exclusive offers!</p>
                                <Link to="/register">
                                    <button className="btn btn-light btn-lg rounded-pill shadow-sm">Register Now</button>
                                </Link>
                            </div>
                        </div>
                        <div className="col-md-6">
                            <div className="login-form-box p-4">
                                <h2 className="mb-4 text-center">Welcome Back</h2>
                                {message && <div className="alert alert-danger">{message}</div>}
                                <form onSubmit={handleSubmit}>
                                    <div className="mb-3">
                                        <input
                                            type="email"
                                            className="form-control form-control-lg rounded-pill"
                                            placeholder="Email Address"
                                            name="email"
                                            value={formData.email}
                                            onChange={handleChange}
                                            required
                                        />
                                    </div>
                                    <div className="mb-3">
                                        <input
                                            type="password"
                                            className="form-control form-control-lg rounded-pill"
                                            placeholder="Password"
                                            name="password"
                                            value={formData.password}
                                            onChange={handleChange}
                                            required
                                        />
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
