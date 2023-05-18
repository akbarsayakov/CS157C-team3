import React, {useState} from "react"

export const Upload = (props) => {
    const [image, setImage ] = useState('');
    const [recipeName, setRecipeName] = useState('');
    const [list, setList ] = useState('');
    const [steps, setSteps] = useState('');   

    const handleSubmit = (e) => {
        e.preventDefault();
    }
    
    return (
        <form onSubmit={handleSubmit} class="upload">
            <label htmlFor="image">Image</label>
            <input value={image} onChange={(e)=>setImage(e.target.value)} type="file" id="image" name="image"></input>

            <label htmlFor="recipeName">Enter name of recipe</label>
            <input value={recipeName} onChange={(e)=>setRecipeName(e.target.value)} type="text" id="recipeName" name="recipeName"></input>

            <label htmlFor="list">List of ingredients: </label>
            <input value={list} onChange={(e)=>setList(e.target.value)} type="text" id="list" name="list"></input>

            <label htmlFor="steps">Steps: </label>
            <input value={steps} onChange={(e)=>setSteps(e.target.value)} type="text" id="steps" name="steps"></input>

            <button onClick={() => props.onFormSwitch('upload')} type="submit"> Submit Recipe </button>
        </form>
    )
}