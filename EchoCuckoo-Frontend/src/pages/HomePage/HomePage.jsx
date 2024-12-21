import Carousel from "../../components/_HomePage/Carousel/Carousel"
import FeatureCollections from "../../components/_HomePage/FeartureCollections/FeatureCollections"
import NewArrival from "../../components/_HomePage/New Arrival/NewArrival"

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