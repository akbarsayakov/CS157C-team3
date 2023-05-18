import { useContext, useEffect, useState } from 'react'
import { useParams, Link } from 'react-router-dom'
import SpoonacularContext from '../context/spoonacular/SpoonacularContext'
import { AiOutlineLike, AiFillClockCircle } from 'react-icons/ai'
import { MdSoupKitchen } from 'react-icons/md'

function Recipe() {
  const { recipes } = useContext(SpoonacularContext)
  const params = useParams()
  const [myObject, setMyObject] = useState(null)

  useEffect(() => {
    const recipeObject = (arr, str) => {
      for (let i = 0; i < arr.length; i++) {
        const recipe = arr[i]
        if (
          Object.values(recipe).some((val) => {
            if (typeof val === 'string' || Array.isArray(val)) {
              return val.includes(str)
            } else {
              return false
            }
          })
        ) {
          return recipe
        }
      }
      return null
    }
    setMyObject(recipeObject(recipes, params.name))
  }, [recipes, params.name])

  return (
    <div>
      {myObject && (
        <div className='w-full mx-auto lg:w-10/12'>
          <div className='mb-4'>
            <Link to='/' className='btn btn-ghost'>
              Back To Search
            </Link>
          </div>

          <div className='grid grid-cols-1 xl:grid-cols-3 lg:grid-cols-3 md:grid-cols-3 mb-8 md:gap-8'>
            <div className='custom-card-image mb-6 md:mb-0'>
              <div className='rounded-lg '>
                <figure>
                  <img className='rounded-lg ' src={myObject.photo} alt='' />
                </figure>
              </div>
            </div>

            <div className='col-span-2'>
              <div className='mb-6'>
                <h1 className='text-3xl card-title'>
                  {myObject.name}
                  <div className='ml-2 mr-1 badge badge-success'>
                    {myObject.food_type}
                  </div>
                </h1>
                <div className='mt-4 card-actions'>{myObject.ingredients}</div>
              </div>

              <div className='w-full rounded-lg shadow-md bg-base-100 stats'>
                {myObject.steps && (
                  <div className='stat'>
                    <div className='stat-title text-md'>Steps</div>
                    <div className='text-lg stat-value'>{myObject.steps}</div>
                  </div>
                )}
              </div>
            </div>
          </div>

          <div className='w-full py-5 mb-6 rounded-lg shadow-md bg-base-100 stats'>
            <div className='grid grid-cols-1 md:grid-cols-3'>
              <div className='stat'>
                <div className='stat-figure text-secondary'>
                  <AiFillClockCircle className='text-3xl md:text-5xl' />
                </div>
                <div className='stat-title pr-5'>Estimated Time</div>
                <div className='stat-value pr-5 text-3xl md:text-4xl'>
                  {myObject.estimated_time} mins
                </div>
              </div>

              <div className='stat'>
                <div className='stat-figure text-secondary'>
                  <MdSoupKitchen className='text-3xl md:text-5xl' />
                </div>
                <div className='stat-title pr-5'>
                  Number of ingredients you have
                </div>
                <div className='stat-value pr-5 text-3xl md:text-4xl'>{3}</div>
              </div>

              <div className='stat'>
                <div className='stat-figure text-secondary'>
                  <AiOutlineLike className='text-3xl md:text-5xl' />
                </div>
                <div className='stat-title pr-5'>Likes</div>
                <div className='stat-value pr-5 text-3xl md:text-4xl'>{5} </div>
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  )
}

export default Recipe
