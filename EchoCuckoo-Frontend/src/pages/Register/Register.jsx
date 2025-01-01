import { Link } from 'react-router-dom';
import './Register.css';
import { useEffect, useState } from 'react';
import { registerAPI } from '../../apis/Authenticate API/authenticate';
import { toast } from 'react-toastify';

const Register = () => {
  // State để lưu trữ giá trị biểu mẫu
  const [formData, setFormData] = useState({
    fullname: '',
    email: '',
    password: '',
    confirmPassword: '',
  });

  const [error, setError] = useState(null);
  const [successMessage, setSuccessMessage] = useState(null);

  useEffect(() => {
    window.scroll(0, 0);
  }, []);

  // Hàm xử lý thay đổi biểu mẫu
  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  // Hàm xử lý gửi biểu mẫu
  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null);
    setSuccessMessage(null);

    // Kiểm tra mật khẩu khớp
    if (formData.password !== formData.confirmPassword) {
      setError('Passwords do not match!');
      {toast.error(error)}
      return;
    }

    try {
      // Gọi API
      const response = await registerAPI({
        fullname: formData.fullname,
        email: formData.email,
        password: formData.password,
      });

      // Xử lý kết quả
      toast.success('Registration successful! You can now log in.')

    } catch (err) {
      setError(err.response?.data?.message || 'An error occurred. Please try again.');
      {toast.error(error)}
    }
  };

  return (
    <div className="register-page">
      <video
        className="background-video"
        autoPlay
        loop
        muted
      >
        <source src="https://o9tsmhgampqsoqts.public.blob.vercel-storage.com/v-ta8BeogxN4UVwvgbs0l3jzDOP1Azcf.mp4" type="video/mp4" />
        Your browser does not support the video tag.
      </video>
      <main className="main-content">
        <div className="container">
          <div className="row g-4">
            {/* Register Form */}
            <div className="col-md-6 col-lg-5 mx-auto">
              <div className="register-form-box p-4">
                <h2 className="mb-4 text-center">Create Your Account</h2>
                <form onSubmit={handleSubmit}>
                  {/* Full Name */}
                  <div className="mb-3">
                    <input
                      type="text"
                      name="fullname"
                      className="form-control form-control-lg rounded-pill"
                      placeholder="Full Name"
                      value={formData.fullname}
                      onChange={handleChange}
                      required
                    />
                  </div>
                  {/* Email */}
                  <div className="mb-3">
                    <input
                      type="email"
                      name="email"
                      className="form-control form-control-lg rounded-pill"
                      placeholder="Email Address"
                      value={formData.email}
                      onChange={handleChange}
                      required
                    />
                  </div>
                  {/* Password */}
                  <div className="mb-3">
                    <input
                      type="password"
                      name="password"
                      className="form-control form-control-lg rounded-pill"
                      placeholder="Password"
                      value={formData.password}
                      onChange={handleChange}
                      required
                    />
                  </div>
                  {/* Confirm Password */}
                  <div className="mb-3">
                    <input
                      type="password"
                      name="confirmPassword"
                      className="form-control form-control-lg rounded-pill"
                      placeholder="Confirm Password"
                      value={formData.confirmPassword}
                      onChange={handleChange}
                      required
                    />
                  </div>
                  {/* Terms and Conditions */}
                  <div className="mb-3 form-check">
                    <input
                      type="checkbox"
                      className="form-check-input"
                      id="terms"
                      required
                    />
                    <label
                      className="form-check-label"
                      htmlFor="terms"
                    >
                      I agree to the{' '}
                      <a href="#" className="text-decoration-none">
                        Terms and Conditions
                      </a>
                    </label>
                  </div>
                  {/* Register Button */}
                  <button
                    type="submit"
                    className="btn btn-dark btn-lg w-100 rounded-pill mb-3"
                  >
                    Register
                  </button>
                  <div className="text-center">
                    Already have an account?{' '}
                    <Link to="/login" className="text-decoration-none">
                      Login Here
                    </Link>
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

export default Register;
