import React, { useEffect, useRef, useState } from 'react';
import config from "../../data/config.json";

function MaintainCategories() {
  const [categories, setCategories] = useState([]);
  const categoryRef = useRef();

  useEffect(() => {
    fetch(config.backendUrl + "/categories")
      .then(res => res.json())
      .then(json => setCategories(json || []));
  }, [categoryRef]);

  const add = () => {
    const newCategory = {
      "name": categoryRef.current.value
    };

    fetch(config.backendUrl + "/category/add", {
      method: "POST",
      body: JSON.stringify(newCategory),
      headers: { "Content-Type": "application/json" }
    })
      .then(res => res.json())
      .then(json => setCategories(json));
  }

  const deleteCategory = (id) => {
    fetch(config.backendUrl + "/category/delete/" + id, {
      method: "DELETE"

    })
      .then(res => res.json())
      .then(json => setCategories(json));
  }

  return (
    <div>
      {categories.length === 0 && <div>No categories!</div>}
      <label>Category</label><br />
      <input ref={categoryRef} type="text" /><br />

      <button onClick={add}>Add</button> <br />

      {categories.map((element, index) =>
        <div key={index}>
          {element.name}
          <button onClick={() => deleteCategory(element.id)}>x</button>
        </div>)}
    </div>
  )
}

export default MaintainCategories