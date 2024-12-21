import { Link } from 'react-router-dom';
import './Register.css';
import { useEffect } from 'react';
const Register = () => {
    useEffect(() => {
        window.scroll(0, 0); 
    }, [location]);
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
                <form>
                  {/* Full Name */}
                  <div className="mb-3">
                    <input
                      type="text"
                      className="form-control form-control-lg rounded-pill"
                      placeholder="Full Name"
                      required
                    />
                  </div>
                  {/* Email */}
                  <div className="mb-3">
                    <input
                      type="email"
                      className="form-control form-control-lg rounded-pill"
                      placeholder="Email Address"
                      required
                    />
                  </div>
                  {/* Password */}
                  <div className="mb-3">
                    <input
                      type="password"
                      className="form-control form-control-lg rounded-pill"
                      placeholder="Password"
                      required
                    />
                  </div>
                  {/* Confirm Password */}
                  <div className="mb-3">
                    <input
                      type="password"
                      className="form-control form-control-lg rounded-pill"
                      placeholder="Confirm Password"
                      required
                    />
                  </div>
                  {/* Terms and Conditions */}
                  <div className="mb-3 form-check">
                    <input
                      type="checkbox"
                      className="form-check-input"
                      id="terms"
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
