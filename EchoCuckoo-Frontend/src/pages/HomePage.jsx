import Carousel from "../components/Carousel/Carousel"
import FeatureCollections from "../components/FeartureCollections/FeatureCollections"
import NewArrival from "../components/New Arrival/NewArrival"

const HomePage = () => {
  return (
    <div>
        <Carousel/>
        <NewArrival/>
        <FeatureCollections/>
    </div>
  )
}

export default HomePage