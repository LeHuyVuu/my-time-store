
import { createRoot } from 'react-dom/client'
import './index.css'

import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import HomePage from './pages/HomePage/HomePage.jsx'
import RootLayout from './pages/RootLayout.jsx'
import Carousel from './components/_HomePage/Carousel/Carousel.jsx'
import ListProduct from './pages/ListProduct/ListProduct.jsx'



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
    ]
  }
])

createRoot(document.getElementById('root')).render(

  <RouterProvider router={router} />

)
