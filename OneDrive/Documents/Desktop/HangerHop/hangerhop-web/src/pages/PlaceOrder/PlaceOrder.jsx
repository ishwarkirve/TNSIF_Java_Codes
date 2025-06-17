import React, { useEffect } from 'react'
import './PlaceOrder.css';
import { useContext } from 'react';
import { StoreContext } from '../../components/Context/StoreContext';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom'
import axios from 'axios';

const PlaceOrder = () => {
  console.log("here");
  const {getTotalCartAmount,token,food_list,cartItems,url} = useContext(StoreContext);

  const [data,setData] = useState({
    firstName:"",
    lastName:"",
    email:"",
    street:"",
    city:"",
    state:"",
    zipcode:"",
    country:"",
    phone:""
  });

  const onChangeHandler = (evt)=>{
    const name = evt.target.name;
    const value = evt.target.value;
    setData(data=>({...data,[name]:value}))
  }

  const placeOrder = async(evt)=>{
    evt.preventDefault();
    let orderItems = [];
    food_list.map((item)=>{
      if(cartItems[item._id]>0){
        let itemInfo = item;
        itemInfo['quantity']=cartItems[item._id];
        orderItems.push(itemInfo);
      }
    })
    let orderData = {
      address : data,
      items : orderItems,
      amount : getTotalCartAmount()+2
    }
    let response = await axios.post(url+"/api/order/place",orderData,{headers:{token}});
    console.log(response.data);
    if(response.data.success){
      const {session_url} = response.data;
      window.location.replace(session_url);
    }else{
      alert("ERROR");
    }
  }

  const navigate = useNavigate();

  useEffect(()=>{
    if(!token){
      navigate('/cart')
    }else if(getTotalCartAmount() === 0){
      navigate('/cart')
    }
  },[token])

  return (
    <form onSubmit={placeOrder} className='place_order'>
      <div className='place_order_left'>
      <p className="title">Delivery Information</p>
      <div className="multi_fields">
        <input type="text" name='firstName' onChange={onChangeHandler} value={data.firstName} placeholder='First name' required/>
        <input type="text" name='lastName' onChange={onChangeHandler} value={data.lastName} placeholder='Last name' required/>
      </div>
      <input type="email" name='email' onChange={onChangeHandler} value={data.email} placeholder='Email address' required/>
      <input type="text" name='street' onChange={onChangeHandler} value={data.street} placeholder='Street' required/>
      <div className="multi_fields">
        <input type="text" name='city' onChange={onChangeHandler} value={data.city} placeholder='City' required/>
        <input type="text" name='state' onChange={onChangeHandler} value={data.state} placeholder='State' required/>
      </div>
      <div className="multi_fields">
        <input type="text" name='zipcode' onChange={onChangeHandler} value={data.zipcode} placeholder='Zip code' required/>
        <input type="text" name='country' onChange={onChangeHandler} value={data.country} placeholder='Country' required/>
      </div>
      <input type="text" name='phone' onChange={onChangeHandler} value={data.phone} placeholder='Phone' required/>
      </div>
      <div className='place_order_right'>
      <div className="cart_total">
          <h2>Cart Totals</h2>
          <div>
            <div className='cart_total_details'>
              <p>Subtotal</p>
              <p>${getTotalCartAmount()}</p>
            </div>
            <hr />
            <div className='cart_total_details'>
              <p>Delivery</p>
              <p>${getTotalCartAmount() > 0 ? 2:0}</p>
            </div>
            <hr />
            <div className='cart_total_details'>
              <b>Total</b>
              <b>${getTotalCartAmount()+(getTotalCartAmount() > 0 ? 2:0)}</b>
            </div>
            <hr />
          </div>
          <button type='submit' >Proceed to payment</button>
        </div>
      </div>
    </form>
  )
}

export default PlaceOrder
