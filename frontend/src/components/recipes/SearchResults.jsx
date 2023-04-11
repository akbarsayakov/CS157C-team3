import { useEffect, useState } from 'react'

function SearchResults() {
  const [recipes, setRecipes] = useState([])
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    handleSubmit()
  }, [])

  const handleSubmit = async () => {
    const response = await fetch(
      `https://api.spoonacular.com/recipes/findByIngredients?apiKey=${process.env.REACT_APP_API_KEY}&ingredients=apples,+flour,+sugar&number=3`
    )
    const data = await response.json()
    console.log(data)
    setRecipes(data)
  }

  return (
    <div className='grid grid-cols-1 gap-8 xl:grid-cols-4 lg:grid:cols-3 md:grid-cols-2'>
      {recipes.map((recipe) => (
        <h3>{recipe.title}</h3>
      ))}
    </div>
  )
}

export default SearchResults
