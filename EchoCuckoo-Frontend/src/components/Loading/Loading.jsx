// Loading.js
import React, { useEffect } from 'react';
import './Loading.css'


const Loading = () => {
    useEffect(() => {
        window.scroll(0, 0); // Cuộn lên đầu mỗi khi route thay đổi
      }, []);
    return (
        <div className="d-flex justify-content-center align-items-center vh-100 bg-white">
            <div className="text-center">
               
                <div className="spinner-border text-danger" role="status">
                    <span className="visually-hidden">Loading...</span>
                </div>
            
                <p className="text-white mt-3 fs-5 fw-semibold">Loading...</p>
            </div>
        </div>

    );
};

export default Loading;
