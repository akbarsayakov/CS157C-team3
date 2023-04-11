import { useReducer, createContext } from 'react'
import spoonacularReducer from './SpoonacularReducer'

const SpoonacularContext = createContext()

const SPOONACULAR_API_KEY = process.env.REACT_APP_SPOONACULAR_API_KEY
const SPOONACULAR_URL = process.env.REACT_APP_SPOONACULAR_URL

export const SpoonacularProvider = ({ children }) => {
  const initialState = {
    recipes: [],
    loading: false,
  }

  const [state, dispatch] = useReducer(spoonacularReducer, initialState)

  const searchRecipes = async (text) => {
    setLoading()

    const params = new URLSearchParams({
      ingredients: text,
    })

    const response = await fetch(
      `${SPOONACULAR_URL}?apiKey=${SPOONACULAR_API_KEY}&${params}&number=3`
    )
    const data = await response.json()
    console.log(data, 'DATA')

    dispatch({
      type: 'GET_RECIPES',
      payload: data,
    })
  }

  // Set loading
  const setLoading = () => dispatch({ type: 'SET_LOADING' })

  return (
    <SpoonacularContext.Provider
      value={{ recipes: state.recipes, loading: state.loading, searchRecipes }}
    >
      {children}
    </SpoonacularContext.Provider>
  )
}

export default SpoonacularContext
