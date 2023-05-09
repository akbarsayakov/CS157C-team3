import { useContext, useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import SpoonacularContext from '../context/spoonacular/SpoonacularContext'

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
    <>
      <h1>{myObject && <div>{myObject.ingredients}</div>}</h1>
      <h2>sdd</h2>
    </>
  )
}

export default Recipe
