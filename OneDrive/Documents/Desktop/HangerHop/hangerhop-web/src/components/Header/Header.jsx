import React from 'react'
import './Header.css';

const Header = () => {
  return (
    <div className='header'>
      <div className="header_content">
        <h2>Order Your Favourite Food Here</h2>
        <p>Choose from a diverse menu featuring a delectable array of dishes. crafted with the finest ingredients and culinary expertise. Our mission is to satisfy your cravings and elevate your dining experience, one delicious meal at a time.</p>
        <a href="#explore_menu"><button>View Menu</button></a>
      </div>
    </div>
  )
}

export default Header
