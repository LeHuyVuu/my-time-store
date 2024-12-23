import { useEffect, useState } from "react"
import Benefits from "../../components/_HomePage/Benefits/Benefits"
import Blog from "../../components/_HomePage/Blog/Blog"
import Carousel from "../../components/_HomePage/Carousel/Carousel"
import FeatureCollections from "../../components/_HomePage/FeartureCollections/FeatureCollections"
import NewArrival from "../../components/_HomePage/New Arrival/NewArrival"
import Loading from "../../components/Loading/Loading"

const HomePage = () => {
  useEffect(() => {
    window.scroll(0, 0); // Cuộn lên đầu mỗi khi route thay đổi
  }, []);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
      // Simulate data fetching
      const fetchData = async () => {
          // Simulate a delay
          await new Promise(resolve => setTimeout(resolve, 2000));
          setIsLoading(false);
      };

      fetchData();
  }, []);

  const scrollToTop = () => {
    window.scrollTo({
        top: 0,
        behavior: 'smooth' // 'auto' hoặc 'smooth' để cuộn mượt mà
    });
};
  if (isLoading) {
      return <Loading />;
  }

  return (
      <div>
          <Carousel />
          <NewArrival />
          <FeatureCollections />
          <Blog />
          <Benefits />
      </div>
  );
}

export default HomePage