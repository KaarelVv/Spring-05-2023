import { Route, Routes } from "react-router-dom";
import "./App.css";
import HomePage from "./pages/global/HomePage";
import Cart from "./pages/global/Cart";
import Shops from "./pages/global/Shops";
import SingleProduct from "./pages/global/SingleProduct";
import AdminHome from "./pages/admin/AdminHome";
import AddProduct from "./pages/admin/AddProduct";
import EditProduct from "./pages/admin/EditProduct";
import MaintainProducts from "./pages/admin/MaintainProducts";
import MaintainCategories from "./pages/admin/MaintainCategories";
import MaintainShops from "./pages/admin/MaintainShops";
import Order from "./pages/global/Order";
import NavigationBar from "./components/NavigationBar";
import Login from "./pages/auth/Login";
import Signup from "./pages/auth/Signup";
import { useContext } from "react";
import { AuthContext } from "./store/AuthContext";
import Profile from "./pages/auth/Profile";
import CheckPayment from "./pages/global/CheckPayment";
import MaintainCarouselPictures from "./pages/admin/MaintainCarouselPictures";
import Persons from "./pages/admin/Persons";
import AllOrders from "./pages/admin/AllOrders";

function App() {
  const { loggedInUser } = useContext(AuthContext);

  return (
    <div className="App">
      <NavigationBar />

      <Routes>
        <Route path="" element={<HomePage />} />
        <Route path="cart" element={<Cart />} />
        <Route path="shops" element={<Shops />} />
        <Route path="product/:id" element={<SingleProduct />} />
        <Route path="payment" element={<CheckPayment />} />

        {loggedInUser.email && (
          <>
            <Route path="profile" element={<Profile />} />
            <Route path="order" element={<Order />} />
          </>
        )}

        {loggedInUser.admin && (
          <>

            <Route path="admin" element={<AdminHome />} />
            <Route path="admin/add-product" element={<AddProduct />} />
            <Route path="admin/edit-product/:id" element={<EditProduct />} />
            <Route
              path="admin/maintain-products"
              element={<MaintainProducts />}
            />
            <Route
              path="admin/maintain-categories"
              element={<MaintainCategories />}
            />
            <Route
              path="admin/maintain-carousel"
              element={<MaintainCarouselPictures />}
            />
            <Route path="admin/maintain-shops" element={<MaintainShops />} />
            <Route path="admin/persons" element={<Persons />} />
            <Route path="admin/all-orders" element={<AllOrders />} />
          </>
        )}
        <Route path="login" element={<Login />} />
        <Route path="signup" element={<Signup />} />
        <Route path="*" element={<div>404 Not Found</div>} />
      </Routes>
    </div>
  );
}

export default App;
