import React, { useState } from 'react';
import './PrivacySettings.css';

const PrivacySetting = () => {
    const [settings, setSettings] = useState({
        profileVisibility: 'Public',
        emailNotifications: true,
        twoFactorAuth: false,
    });

    const handleToggle = (key) => {
        setSettings((prevSettings) => ({
            ...prevSettings,
            [key]: !prevSettings[key],
        }));
    };

    const handleVisibilityChange = (event) => {
        setSettings((prevSettings) => ({
            ...prevSettings,
            profileVisibility: event.target.value,
        }));
    };

    return (
        <div className="privacy-settings-container">
            <h2 className="mb-4">Privacy & Settings</h2>

            <div className="settings-group mb-3">
                <label className="form-label">Profile Visibility</label>
                <select
                    className="form-select"
                    value={settings.profileVisibility}
                    onChange={handleVisibilityChange}
                >
                    <option value="Public">Public</option>
                    <option value="Private">Private</option>
                    <option value="Friends Only">Friends Only</option>
                </select>
            </div>

            <div className="settings-group mb-3">
                <label className="form-label">Email Notifications</label>
                <div className="form-check">
                    <input
                        type="checkbox"
                        className="form-check-input"
                        id="emailNotifications"
                        checked={settings.emailNotifications}
                        onChange={() => handleToggle('emailNotifications')}
                    />
                    <label className="form-check-label" htmlFor="emailNotifications">
                        Enable Email Notifications
                    </label>
                </div>
            </div>

            <div className="settings-group mb-3">
                <label className="form-label">Two-Factor Authentication</label>
                <div className="form-check">
                    <input
                        type="checkbox"
                        className="form-check-input"
                        id="twoFactorAuth"
                        checked={settings.twoFactorAuth}
                        onChange={() => handleToggle('twoFactorAuth')}
                    />
                    <label className="form-check-label" htmlFor="twoFactorAuth">
                        Enable Two-Factor Authentication
                    </label>
                </div>
            </div>

            <button className="btn btn-primary">Save Changes</button>
        </div>
    );
};

export default PrivacySetting;
