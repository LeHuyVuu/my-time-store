import React from 'react';
import './Order.css';

const Orders = () => {
    const orders = [
        {
            id: 'ORD123456',
            date: '2023-12-01',
            status: 'Delivered',
            total: '$150.00',
            depositPaid: true,
        },
        {
            id: 'ORD123457',
            date: '2023-11-28',
            status: 'Pending',
            total: '$90.00',
            depositPaid: false,
        },
        {
            id: 'ORD123458',
            date: '2023-11-15',
            status: 'Cancelled',
            total: '$45.00',
            depositPaid: true,
        },
    ];

    return (
        <div className="orders-container">
            <h2 className="mb-4">Your Orders</h2>
            <div className="table-responsive">
                <table className="table table-striped">
                    <thead>
                        <tr>
                            <th>Order ID</th>
                            <th>Order Date</th>
                            <th>Status</th>
                            <th>Total</th>
                            <th>Deposit Paid</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {orders.map((order) => (
                            <tr key={order.id}>
                                <td>{order.id}</td>
                                <td>{order.date}</td>
                                <td>
                                    <span
                                        className={`badge ${
                                            order.status === 'Delivered'
                                                ? 'bg-success'
                                                : order.status === 'Pending'
                                                ? 'bg-warning'
                                                : 'bg-danger'
                                        }`}
                                    >
                                        {order.status}
                                    </span>
                                </td>
                                <td>{order.total}</td>
                                <td>
                                    {order.depositPaid ? (
                                        <span className="badge bg-success">Yes</span>
                                    ) : (
                                        <span className="badge bg-danger">No</span>
                                    )}
                                </td>
                                <td>
                                    <button className="btn btn-sm btn-primary">View Details</button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default Orders;
