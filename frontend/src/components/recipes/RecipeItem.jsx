import { Link } from 'react-router-dom'
import PropTypes from 'prop-types'

function RecipeItem({ recipe }) {
  return (
    <div className='card shadow-md compact side bg-base-100'>
      <div className='flex-row items-center space-x-4 card-body'>
        <div>
          <div className='avatar'>
            <div className='rounded-full shadow w-14 h-14'>
              <img src={recipe.image} alt='Profile' />
            </div>
          </div>
        </div>
        <div>
          <h2 className='card-title'>{recipe.title}</h2>
          <Link
            className='text-base-content text-opacity-40'
            to={`/recipe/${recipe.title}`}
          >
            You have {recipe.usedIngredientCount} ingredients
          </Link>
        </div>
      </div>
    </div>
  )
}

RecipeItem.propTypes = {
  recipe: PropTypes.object.isRequired,
}

export default RecipeItem