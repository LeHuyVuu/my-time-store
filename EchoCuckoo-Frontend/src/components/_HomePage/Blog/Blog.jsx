import React from 'react';
import './Blog.css';

const Blog = () => {
    return (
        <div>
            {/* From the Blog Section */}
            <section className="from-the-blog py-5 bg-light">
                <div className="container text-center">
                    <h2 className="fw-bold mb-4">From The Blog</h2>
                    <div className="row row-cols-1 row-cols-md-3 g-3">
                        <div className="col">
                            <div className="card h-90 border-0 shadow-sm">
                                <img
                                    src="https://th.bing.com/th/id/OIP.p0C5zXwhhQ7DkbfTuGYeewHaE8?rs=1&pid=ImgDetMain"
                                    className="card-img-top img-fluid" alt="Blog 1"
                                />
                                <div className="card-body">
                                    <p className="text-muted mb-1 small">June 15, 2024 | 4 Comments</p>
                                    <h5 className="card-title fw-bold">The History and Evolution of Cuckoo Clocks</h5>
                                    <p className="text-muted small">Explore the rich history behind the iconic cuckoo clock and its journey through time.</p>
                                </div>
                            </div>
                        </div>
                        <div className="col">
                            <div className="card h-90 border-0 shadow-sm">
                                <img
                                    src="https://th.bing.com/th/id/OIP.DxX-Okl1J-EsQ5FDFmIPvgHaE8?w=900&h=600&rs=1&pid=ImgDetMain"
                                    className="card-img-top img-fluid" alt="Blog 2"
                                />
                                <div className="card-body">
                                    <p className="text-muted mb-1 small">July 10, 2024 | 3 Comments</p>
                                    <h5 className="card-title fw-bold">Top 10 Cuckoo Clock Designs for Your Home</h5>
                                    <p className="text-muted small">Discover the most popular cuckoo clock designs to enhance your interior décor.</p>
                                </div>
                            </div>
                        </div>
                        <div className="col">
                            <div className="card h-90 border-0 shadow-sm">
                                <img
                                    src="https://img.freepik.com/premium-photo/blog-text-notebook-with-keyboard-coffee_218381-37.jpg"
                                    className="card-img-top img-fluid" alt="Blog 3"
                                />
                                <div className="card-body">
                                    <p className="text-muted mb-1 small">August 5, 2024 | 5 Comments</p>
                                    <h5 className="card-title fw-bold">How to Maintain and Care for Your Cuckoo Clock</h5>
                                    <p className="text-muted small">Learn essential tips to keep your cuckoo clock in perfect working condition.</p>
                                </div>
                            </div>
                        </div>
                        <div className="col">
                            <div className="card h-90 border-0 shadow-sm">
                                <img
                                    src="https://th.bing.com/th/id/OIP.D5AMERFmUxPx15K-1m7t0wAAAA?w=474&h=315&rs=1&pid=ImgDetMain"
                                    className="card-img-top img-fluid" alt="Blog 4"
                                />
                                <div className="card-body">
                                    <p className="text-muted mb-1 small">September 12, 2024 | 2 Comments</p>
                                    <h5 className="card-title fw-bold">The Art of Crafting Handmade Cuckoo Clocks</h5>
                                    <p className="text-muted small">Delve into the meticulous craftsmanship involved in creating traditional cuckoo clocks.</p>
                                </div>
                            </div>
                        </div>
                        <div className="col">
                            <div className="card h-90 border-0 shadow-sm">
                                <img
                                    src="https://thumbs.dreamstime.com/z/image-nice-clock-time-out-29732582.jpg"
                                    className="card-img-top img-fluid" alt="Blog 5"
                                />
                                <div className="card-body">
                                    <p className="text-muted mb-1 small">October 20, 2024 | 6 Comments</p>
                                    <h5 className="card-title fw-bold">Modern Innovations in Cuckoo Clock Mechanisms</h5>
                                    <p className="text-muted small">Explore how contemporary technology is influencing the evolution of cuckoo clocks.</p>
                                </div>
                            </div>
                        </div>
                        <div className="col">
                            <div className="card h-90 border-0 shadow-sm">
                                <img
                                    src="https://c8.alamy.com/comp/TR717W/3d-computer-graphic-clock-with-moving-second-hand-lettering-hurry-up!-TR717W.jpg"
                                    className="card-img-top img-fluid" alt="Blog 6"
                                />
                                <div className="card-body">
                                    <p className="text-muted mb-1 small">November 8, 2024 | 4 Comments</p>
                                    <h5 className="card-title fw-bold">Collecting Antique Cuckoo Clocks: A Beginner's Guide</h5>
                                    <p className="text-muted small">Tips and advice for starting your own collection of antique cuckoo clocks.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    );
};

export default Blog;
