import React from 'react';
import './HelpSupport.css';

const HelpSupport = () => {
    const faqs = [
        {
            question: 'How do I reset my password?',
            answer: 'To reset your password, go to the login page and click on "Forgot Password". Follow the instructions sent to your email.',
        },
        {
            question: 'How can I contact customer support?',
            answer: 'You can contact customer support through our contact form, email, or call our support hotline available on the Contact Us page.',
        },
        {
            question: 'Where can I track my orders?',
            answer: 'You can track your orders in the "Orders" section of your profile page.',
        },
    ];

    return (
        <div className="help-support-container">
            <h2 className="mb-4">Help & Support</h2>

            <div className="faqs">
                {faqs.map((faq, index) => (
                    <div key={index} className="faq-item mb-3">
                        <h5 className="faq-question">{faq.question}</h5>
                        <p className="faq-answer">{faq.answer}</p>
                    </div>
                ))}
            </div>

            <div className="contact-support mt-4">
                <h5>Need more help?</h5>
                <p>
                    If you can't find the answer you're looking for, please contact our support team for further assistance.
                </p>
                <button className="btn btn-primary w-50">Contact Support</button>
            </div>
        </div>
    );
};

export default HelpSupport;
