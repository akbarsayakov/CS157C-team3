import signupImage from '../assets/signup.jpg'
import { Link } from 'react-router-dom'

const Signup = (props) => {
  return (
    <div className='relative flex justify-center min-h-screen overflow-hidden'>
      <div className='w-full p-6 m-auto bg-white rounded-md shadow-md lg:max-w-xl'>
        <img src={signupImage} />
      </div>
      <div className='w-full p-6 m-auto bg-white rounded-md shadow-md lg:max-w-xl'>
        <h1 className='text-3xl font-semibold text-center text-secondary underline'>
          Sign up
        </h1>
        <form className='mt-6'>
          <div className='mb-2'>
            <label
              for='email'
              className='block text-sm font-semibold text-gray-800'
            >
              Email
            </label>
            <input
              type='email'
              className='block w-full px-4 py-2 mt-2 text-secondary bg-white border rounded-md focus:border-purple-400 focus:ring-purple-300 focus:outline-none focus:ring focus:ring-opacity-40'
            />
          </div>
          <div className='mb-2'>
            <label
              for='password'
              className='block text-sm font-semibold text-gray-800'
            >
              Password
            </label>
            <input
              type='password'
              className='block w-full px-4 py-2 mt-2 text-secondary bg-white border rounded-md focus:border-purple-400 focus:ring-purple-300 focus:outline-none focus:ring focus:ring-opacity-40'
            />
          </div>
          <a href='#' className='text-xs text-purple-600 hover:underline'>
            Forget Password?
          </a>
          <div className='mt-6'>
            <button className='w-full px-4 py-2 tracking-wide text-white transition-colors duration-200 transform bg-secondary rounded-md hover:bg-purple-600 focus:outline-none focus:bg-purple-600'>
              Sign up
            </button>
          </div>
        </form>

        <p className='mt-8 text-xs font-light text-center text-gray-700'>
          {' '}
          Already have an account?{' '}
          <Link
            to='/login'
            className='font-medium text-purple-600 hover:underline'
          >
            Sign up
          </Link>
        </p>
      </div>
    </div>
  )
}

export default Signup
