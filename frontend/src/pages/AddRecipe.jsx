import React from 'react'
import { Link } from 'react-router-dom'

function AddRecipe() {
  return (
    <div className='relative flex justify-center min-h-screen overflow-hidden'>
      <div className='w-full p-6 m-auto bg-white rounded-md shadow-md lg:max-w-xl'>
        <h1 className='text-3xl font-semibold text-center text-secondary'>
          Add New Recipe
        </h1>
        <form className='mt-6'>
          <div className='mb-2'>
            <label
              for='name'
              className='block text-sm font-semibold text-gray-800'
            >
              Recipe Name
            </label>
            <input
              type='text'
              className='block w-full px-4 py-2 mt-2 text-secondary bg-white border rounded-md focus:border-purple-400 focus:ring-purple-300 focus:outline-none focus:ring focus:ring-opacity-40'
            />
          </div>
          <div className='mb-2'>
            <label
              for='ingredients'
              className='block text-sm font-semibold text-gray-800'
            >
              Ingredients
            </label>
            <input
              type='text'
              className='block w-full px-4 py-2 mt-2 text-secondary bg-white border rounded-md focus:border-purple-400 focus:ring-purple-300 focus:outline-none focus:ring focus:ring-opacity-40'
            />
          </div>
          <div className='mb-2'>
            <label
              for='steps'
              className='block text-sm font-semibold text-gray-800'
            >
              Steps
            </label>
            <input
              type='text'
              className='block w-full px-4 py-2 mt-2 text-secondary bg-white border rounded-md focus:border-purple-400 focus:ring-purple-300 focus:outline-none focus:ring focus:ring-opacity-40'
            />
          </div>
          <div className='mb-2'>
            <label
              for='time'
              className='block text-sm font-semibold text-gray-800'
            >
              Estimated Time
            </label>
            <input
              type='text'
              className='block w-full px-4 py-2 mt-2 text-secondary bg-white border rounded-md focus:border-purple-400 focus:ring-purple-300 focus:outline-none focus:ring focus:ring-opacity-40'
            />
          </div>
          <div className='mb-2'>
            <label
              for='type'
              className='block text-sm font-semibold text-gray-800'
            >
              Food Type
            </label>
            <input
              type='text'
              className='block w-full px-4 py-2 mt-2 text-secondary bg-white border rounded-md focus:border-purple-400 focus:ring-purple-300 focus:outline-none focus:ring focus:ring-opacity-40'
            />
          </div>
          <div className='mb-2'>
            <label
              for='photo'
              className='block text-sm font-semibold text-gray-800'
            >
              Upload Photo
            </label>
            <input
              type='file'
              className='file-input file-input-bordered file-input-secondary w-full max-w-xs'
            />
          </div>
          <div className='mt-6'>
            <Link
              to='/'
              className='font-medium text-purple-600 hover:underline'
            >
              <button className='w-full px-4 py-2 tracking-wide text-white transition-colors duration-200 transform bg-secondary rounded-md hover:bg-purple-600 focus:outline-none focus:bg-purple-600'>
                Submit
              </button>
            </Link>
          </div>
        </form>

        <p className='mt-8 text-xs font-light text-center text-gray-700'>
          {' '}
          Don't have an account?{' '}
        </p>
      </div>
    </div>
  )
}

export default AddRecipe
