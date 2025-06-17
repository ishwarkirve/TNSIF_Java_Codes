import React from 'react'
import './FoodDisplay.css';
import { StoreContext } from '../Context/StoreContext';
import { useContext } from 'react';
import FoodItem from '../FoodItem/FoodItem';

const FoodDisplay = ({category}) => {
    const {food_list} = useContext(StoreContext)
  return (
    <div className='food_display' id="food_display">
      <h2>Top dishes near you</h2>
      <div className="food_display_list">
        {food_list.map((item,index)=>{
          if(category === "All" || category === item.category){
              return <FoodItem key={index} id={item._id} name={item.name} description={item.description} image={item.image} price={item.price}/>
          }
          })}
      </div>
    </div>
  )
}

export default FoodDisplay
