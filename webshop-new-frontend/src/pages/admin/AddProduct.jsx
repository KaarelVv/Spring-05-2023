import React, { useEffect, useState } from 'react'
import config from "../../data/config.json";
import { t } from 'i18next';
import { ToastContainer, toast } from 'react-toastify';
import { useRef } from 'react';
import { json } from 'react-router-dom';

function AddProduct() {


  const nameRef = useRef();
  const priceRef = useRef();
  const imageRef = useRef();
  const categoryRef = useRef();
  const descriptionRef = useRef();
  const activeRef = useRef();

 
  const [categories, setCategories] = useState([]);
  

  useEffect(() => {
    fetch(config.backendUrl + "/categories") //võtan kategooriad 
      .then(res => res.json())
      .then(json => setCategories(json))
  }, []);

  // useEffect(() => {
  //   fetch(config.backendUrl + "/product") //võtan tooted ??
      
  // }, []);

  function add() {

    if (nameRef.current.value === "") {
      toast("Name not filled");
      return;
    }
    if (priceRef.current.value === "") {
      toast("Price not filled");
      return;
    }
    if (categoryRef.current.value === "") {
      toast("Category not selected");
      return;
    }
    if (/^[0-9.]+$/.test(priceRef.current.value) === false) {
      toast("Price must contain only numbers");
      return;
    }
    if (descriptionRef.current.value === "") {
      toast("Description not filled");
      return;
    }
    const selectedCategory = categories.find(category => category.name === categoryRef.current.value);

    const addProduct = {

      "name": nameRef.current.value,
      "price": Number(priceRef.current.value),
      "image": imageRef.current.value,
      "category": selectedCategory,
      "description": descriptionRef.current.value,
      "active": activeRef.current.value.checked,
    }

    toast(t("product_added"));
    // TODO: BACKENDI PÄRING
    fetch(config.backendUrl + "/product/add",
      {
        method: "POST",
        body: JSON.stringify(addProduct),
        headers: { "Content-Type": "application/json" }
      })
     



    nameRef.current.value = "";
    priceRef.current.value = "";
    imageRef.current.value = "";
    categoryRef.current.value = "";
    descriptionRef.current.value = "";
    activeRef.current.checked = false;
  }



  return (
    <div>

      <label> {t("name")}:</label> <br />
      <input ref={nameRef} type="text" /> <br />
      <label> {t("price")}:</label><br />
      <input ref={priceRef} type="number" /> <br />
      <label> {t("image")}:</label><br />
      <input ref={imageRef} type="text" /><br />
      <label> {t("category")}:</label>
      <select ref={categoryRef}>
        <option value="">Vali kategooria!</option>
        {categories.map(category => <option key={category.name}>{category.name}</option>)}
      </select> <br />
      <label> {t("description")}:</label>
      <input ref={descriptionRef} type="text" /> <br />
      <label> {t("active")}:</label>
      <input ref={activeRef} type="checkbox" /> <br />
      <button onClick={add}>{t("add_product")}</button>

      <ToastContainer position='top-center'></ToastContainer>
    </div>
  )
}
export default AddProduct