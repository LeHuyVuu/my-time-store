import React, { useState } from 'react';
import './UserProfile.css';
import AccountOverview from '../../components/_UserProfile/Account Overview/AccountOverview';
import Orders from '../../components/_UserProfile/Orders/Order';
import PrivacySetting from '../../components/_UserProfile/Privacy and Settings/PrivacySetting';
import HelpSupport from '../../components/_UserProfile/Help Support/HelpSupport';

const UserProfile = () => {
    const [activeSection, setActiveSection] = useState('Account Overview');

    const handleMenuClick = (section) => {
        setActiveSection(section);
    };

    const renderContent = () => {
        switch (activeSection) {
            case 'Account Overview':
                return <AccountOverview />;
            case 'Orders':
                return <Orders/>
            case 'Privacy & Settings':
                return <PrivacySetting/>
            case 'Help & Support':
                return <HelpSupport/>
            default:
                return <AccountOverview />;
        }
    };

    return (
        <div className="container-fluid user-profile mb-5">
            <div className="row">
                {/* Sidebar */}
                <div className="col-md-3 sidebar bg-white shadow-sm">
                    <div className="profile-summary text-center py-4">
                        {/* <img
                            src="https://th.bing.com/th?id=OIP.Q6kV_vvF28sS-LDducZ-HQAAAA&w=200&h=212&c=8&rs=1&qlt=90&o=6&dpr=1.2&pid=3.1&rm=2"
                            alt="User"
                            className="profile-avatar rounded-circle"
                        />
                        <h3 className="username mt-3">John Doe</h3>
                        <button className="btn btn-primary mt-3">Edit Profile</button> */}
                    </div>
                    <ul className="menu list-group mt-4">
                        {['Account Overview', 'Orders', 'Privacy & Settings', 'Help & Support'].map((item) => (
                            <li
                                key={item}
                                className={`menu-item list-group-item ${activeSection === item ? 'active' : ''}`}
                                onClick={() => handleMenuClick(item)}
                            >
                                {item}
                            </li>
                        ))}
                    </ul>
                </div>
                <div className="col-md-9 main-content">
                    {renderContent()}
                </div>
            </div>
        </div>
    );
};

export default UserProfile;
