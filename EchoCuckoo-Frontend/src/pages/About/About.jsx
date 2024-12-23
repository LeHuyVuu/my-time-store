import './About.css';

const About = () => {
    return (
        <div>
            <header className="header-section position-relative">
                <div className="overlay"></div>
                <div className="container">
                    <h1 className="text-center header-title">Về Chúng Tôi</h1>
                </div>
            </header>
            <section className="about-section py-5">
                <div className="container">
                    <div className="row">
                        <div className="col-12 mb-4">
                            <div className="mission-box p-4">
                                <h2 className="section-heading">Sứ Mệnh Của Chúng Tôi</h2>
                                <div className="red-line mb-3"></div>
                                <p>Cung cấp các sản phẩm và dịch vụ chất lượng cao, mang lại cuộc sống hạnh phúc và tiện nghi cho mọi người, đồng thời cam kết cải tiến liên tục để đáp ứng nhu cầu của khách hàng.</p>
                            </div>
                        </div>
                        <div className="col-12 mb-4">
                            <div className="vision-box p-4">
                                <h2 className="section-heading">Tầm Nhìn Của Chúng Tôi</h2>
                                <div className="red-line mb-3"></div>
                                <p>Trở thành thương hiệu hàng đầu trong lĩnh vực cung cấp các sản phẩm gia dụng thông minh, được khách hàng tin tưởng và yêu mến trên toàn cầu.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    );
};

export default About;
