
import { useEffect } from 'react';
import Footer from '../components/Footer/Footer'
import Header from '../components/Header/Header'
import { Outlet, useLocation } from 'react-router-dom'

const RootLayout = () => {
  const location = useLocation();
  useEffect(() => {
    window.scroll(0, 0); // Cuộn lên đầu mỗi khi route thay đổi
  }, [location]);
  return (
    <div>
      <Header />
      <Outlet />
      <Footer />
    </div>
  )
}

export default RootLayout