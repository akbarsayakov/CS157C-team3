import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import Navbar from './components/layout/Navbar'
import Footer from './components/layout/Footer'
import Alert from './components/layout/Alert'
import Home from './pages/Home'
import About from './pages/About'
import Login from './pages/Login'
import AddRecipe from './pages/AddRecipe'
import Signup from './pages/Signup'
import Recipe from './pages/Recipe'
import NotFound from './pages/NotFound'
import { SpoonacularProvider } from './context/spoonacular/SpoonacularContext'
import { AlertProvider } from './context/alert/AlertContext'

function App() {
  return (
    <SpoonacularProvider>
      <AlertProvider>
        <Router>
          <div className='flex flex-col justify-between h-screen'>
            <Navbar />

            <main className='container mx-auto px-3 pb-12'>
              <Alert />
              <Routes>
                <Route path='/' element={<Home />} />
                <Route path='/about' element={<About />} />
                <Route path='/login' element={<Login />} />
                <Route path='/signup' element={<Signup />} />
                <Route path='/add' element={<AddRecipe />} />
                <Route path='/recipe/:name' element={<Recipe />} />
                <Route path='/notfound' element={<NotFound />} />
                <Route path='/*' element={<NotFound />} />
              </Routes>
            </main>

            <Footer />
          </div>
        </Router>
      </AlertProvider>
    </SpoonacularProvider>
  )
}

export default App
