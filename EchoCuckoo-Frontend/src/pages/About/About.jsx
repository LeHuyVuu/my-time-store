
import './About.css'
const About = () => {
   
    return (
        <div>

            <header className="header-section position-relative ">
                <div className="overlay"></div>
                <div className="container">
                    <h1 className="text-center header-title ">About Us</h1>
                </div>
            </header>
            <section className="about-section py-5">
                <div className="container">

                    <div className="row">

                        <div className="col-12 mb-4">
                            <div className="mission-box p-4">
                                <h2 className="section-heading">Our Mission</h2>
                                <div className="red-line mb-3"></div>
                                <p>To revolutionize the industry through innovation and excellence, providing unparalleled value to our customers while maintaining the highest standards of quality and service.</p>
                            </div>
                        </div>

                        <div className="col-12 mb-4">
                            <div className="vision-box p-4">
                                <h2 className="section-heading">Our Vision</h2>
                                <div className="red-line mb-3"></div>
                                <p>To become the global leader in our field, recognized for our commitment to sustainability, customer satisfaction, and technological advancement.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

        </div>
    )
}

export default About