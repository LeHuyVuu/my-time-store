
import { createRoot } from 'react-dom/client'
import './index.css'

import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import HomePage from './pages/HomePage.jsx'
import RootLayout from './pages/RootLayout.jsx'
import Carousel from './components/Carousel/Carousel.jsx'



const router = createBrowserRouter([
  {
    element: <RootLayout />,
    children: [
      {
        path: '/',
        element: <HomePage />
      },
      {
        path: '/movie/:id',
        element: <Carousel />
      },
      {
        // path: '/tv/:id',
        // element: <TVShowDetail />
      }
    ]
  }
])

createRoot(document.getElementById('root')).render(

  <RouterProvider router={router} />

)
