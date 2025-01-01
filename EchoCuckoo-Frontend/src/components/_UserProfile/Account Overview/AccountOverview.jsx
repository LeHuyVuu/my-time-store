import { useEffect, useState } from 'react';
import { toast } from 'react-toastify';
import { useNavigate } from 'react-router-dom';
import { getCurrentUser } from '../../../apis/User API/user';
import './AccountOverview.css';

const AccountOverview = () => {
    const [userInfo, setUserInfo] = useState(null);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchUserInfo = async () => {
            try {
                const response = await getCurrentUser(); // Gọi API lấy thông tin người dùng
                console.log('API Response:', response); // Log dữ liệu trả về từ API
                setUserInfo(response.data); // Lưu thông tin vào state
            } catch (err) {
                const errorMsg = err.response?.data?.message || 'Failed to fetch user information';
                setError(errorMsg);
                toast.error(errorMsg);

                if (err.response?.status === 401) {
                    navigate('/login');
                }
            }
        };

        fetchUserInfo();
    }, [navigate]);

    if (!userInfo) {
        return <div>Loading...</div>;
    }

    const { userId, username, email, fullName, customerResponse, image } = userInfo.data;
    const { phone, address } = customerResponse || {}; // Trích xuất thêm phone và address

    return (
        <div>
            <h2 className="my-4 text-center">Account Information</h2>
            <div className="row info-card bg-white shadow-sm p-4 rounded mx-auto">
                <div className="profile-summary text-center mb-4">
                    <img
                        src={image || 'https://static.vecteezy.com/system/resources/previews/021/548/095/original/default-profile-picture-avatar-user-avatar-icon-person-icon-head-icon-profile-picture-icons-default-anonymous-user-male-and-female-businessman-photo-placeholder-social-network-avatar-portrait-free-vector.jpg'}
                        alt="User"
                        className="profile-avatar rounded-circle"
                    />
                </div>
                <div className="col-md-6 info-item mb-4">
                    <h4>Full Name</h4>
                    <input
                        type="text"
                        name="fullName"
                        id="fullName"
                        placeholder="Enter your full name"
                        value={fullName || ''}
                    />
                </div>
                <div className="col-md-6 info-item mb-4">
                    <h4>Email</h4>
                    <input
                        type="email"
                        name="email"
                        id="email"
                        placeholder="Enter your email"
                        value={email || ''}
                    />
                </div>
                <div className="col-md-6 info-item mb-4">
                    <h4>Username</h4>
                    <input
                        type="text"
                        name="username"
                        id="username"
                        placeholder="Enter your username"
                        value={username || ''}
                    />
                </div>
                <div className="col-md-6 info-item mb-4">
                    <h4>User ID</h4>
                    <input
                        type="text"
                        name="userId"
                        id="userId"
                        value={userId || ''}
                        readOnly
                    />
                </div>
                <div className="col-md-6 info-item mb-4">
                    <h4>Phone</h4>
                    <input
                        type="text"
                        name="phone"
                        id="phone"
                        placeholder="Enter your phone number"
                        value={phone || ''}
                    />
                </div>
                <div className="col-md-6 info-item mb-4">
                    <h4>Address</h4>
                    <input
                        type="text"
                        name="address"
                        id="address"
                        placeholder="Enter your address"
                        value={address || ''}
                    />
                </div>
            </div>
            <div className='row'>
                <button className="btn btn-primary mt-4 d-block mx-auto w-25">Update Details</button>
                <button className="btn btn-primary bg-danger mt-4 d-block mx-auto w-25">Log out</button>
            </div>

        </div>
    );
};

export default AccountOverview;
