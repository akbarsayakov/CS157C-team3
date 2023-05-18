import { useContext } from 'react'
import Spinner from '../layout/Spinner'
import RecipeItem from './RecipeItem'
import SpoonacularContext from '../../context/spoonacular/SpoonacularContext'

function SearchResults() {
  const { recipes, loading } = useContext(SpoonacularContext)

  if (!loading) {
    return (
      <div className='grid grid-cols-1 gap-8 xl:grid-cols-4 lg:grid:cols-3 md:grid-cols-2'>
        {recipes.map((recipe, index) => (
          <RecipeItem key={index} recipe={recipe} />
        ))}
      </div>
    )
  } else {
    return <Spinner />
  }
}

export default SearchResults
