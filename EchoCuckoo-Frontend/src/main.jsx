
import { createRoot } from 'react-dom/client'
import './index.css'

import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import HomePage from './pages/HomePage/HomePage.jsx'
import RootLayout from './pages/RootLayout.jsx'
import Carousel from './components/_HomePage/Carousel/Carousel.jsx'
import ListProduct from './pages/ListProduct/ListProduct.jsx'
import About from './pages/About/About.jsx'
import Contact from './pages/Contact/Contact.jsx'
import Login from './pages/Login/Login.jsx'
import CartList from './pages/CartList/CartList.jsx'
import Register from './pages/Register/Register.jsx'
import ProductDetail from './pages/ProductDetail/ProductDetail.jsx'
import { ToastProvider } from './context/ToastProvider.jsx'
import UserProfile from './pages/UserProfile/UserProfile.jsx'



const router = createBrowserRouter([
  {
    element: <RootLayout />,
    children: [
      {
        path: '/',
        element: <HomePage />
      },
      {
        element: <Carousel />
      },
      {
        path: '/shop',
        element: <ListProduct />
      }
      ,
      {
        path: '/about',
        element: <About />
      },
      {
        path: '/contact',
        element: <Contact />
      }
      ,
      {
        path: '/login',
        element: <Login />
      }
      ,
      {
        path: '/cart',
        element: <CartList />
      }
      ,
      {
        path: '/product-detail/:id',
        element: <ProductDetail />
      },
      {
        path: '/profile',
        element: <UserProfile />
      }
    ]
  },
  {
    path: '/register',
    element: <Register />
  },


]);

createRoot(document.getElementById('root')).render(
  <ToastProvider>
    <RouterProvider router={router} />
  </ToastProvider>


)
