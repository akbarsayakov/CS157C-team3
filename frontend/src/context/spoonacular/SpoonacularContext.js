import { useReducer, createContext } from 'react'
import spoonacularReducer from './SpoonacularReducer'
import axios from 'axios'

const api = axios.create({
  baseURL: `https://86d2-2601-646-8200-2960-911a-da24-3db3-7acb.ngrok-free.app`,
})

const SpoonacularContext = createContext()

const SPOONACULAR_API_KEY = process.env.REACT_APP_SPOONACULAR_API_KEY
const SPOONACULAR_URL = process.env.REACT_APP_SPOONACULAR_URL

export const SpoonacularProvider = ({ children }) => {
  const initialState = {
    recipes: [],
    recipe: {},
    loading: false,
  }

  const [state, dispatch] = useReducer(spoonacularReducer, initialState)

  const searchRecipes = async (text) => {
    setLoading()

    try {
      const response = await api.get(
        '/recipes?email=rahul.pillai03@gmail.com&name=&ingredients=&estimated_time=0&food_type='
      )
      dispatch({
        type: 'GET_RECIPES',
        payload: response.data,
      })
    } catch (err) {
      console.log('Error', err.response)
    }

    // const data = await response.json()
    // console.log(data, 'DATA')
  }

  // const getRecipe = async (name) => {
  //   setLoading()

  //   try {
  //     const response = await api.get(
  //       '/recipes?email=rahul.pillai03@gmail.com&name=&ingredients=&estimated_time=0&food_type='
  //     )
  //     dispatch({
  //       type: 'GET_RECIPES',
  //       payload: response.data,
  //     })
  //   } catch (err) {
  //     console.log('Error', err.response)
  //   }

  //   // const data = await response.json()
  //   // console.log(data, 'DATA')
  // }

  // Clear recipies from state
  const clearRecipes = () => dispatch({ type: 'CLEAR_RECIPES' })

  // Set loading
  const setLoading = () => dispatch({ type: 'SET_LOADING' })

  return (
    <SpoonacularContext.Provider
      value={{
        recipes: state.recipes,
        loading: state.loading,
        user: state.user,
        searchRecipes,
        clearRecipes,
      }}
    >
      {children}
    </SpoonacularContext.Provider>
  )
}

export default SpoonacularContext
