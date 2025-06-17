import React, { useContext, useState } from 'react'
import './Navbar.css';
import { assets } from '../../assets/assets';
import { Link, useNavigate } from 'react-router-dom';
import { StoreContext } from '../Context/StoreContext';

const Navbar = ({ setShowLogin }) => {
    const [menu, setMenu] = useState("home");
    const { getTotalCartAmount,token,setToken } = useContext(StoreContext);
    const navigate = useNavigate();
    const logOut = ()=>{
        localStorage.removeItem("token");
        setToken("");
        navigate("/");
    }

    return (
        <div className='navbar'>
            <Link to="/"> <img src={assets.logo} alt="" className="logo" /></Link>
            <ul className="navbar_menu">
                <Link to='/' className={menu === "home" ? 'active' : ''} onClick={() => setMenu("home")}>Home</Link>
                <a href="#explore_menu" className={menu === "menu" ? 'active' : ''} onClick={() => setMenu("menu")}>Menu</a>
                <a href="#app_download" className={menu === "mobile_app" ? 'active' : ''} onClick={() => setMenu("mobile_app")}>Mobile App</a>
                <a href="#footer" className={menu === "contact_us" ? 'active' : ''} onClick={() => setMenu("contact_us")}>Contact Us</a>
            </ul>
            <div className="navbar_right">
                <img src={assets.search_icon} alt="" />
                <div className="navbar_search_icon">
                    <Link to='/cart'><img src={assets.basket_icon} alt="" /></Link>
                    { getTotalCartAmount() > 0 ? <div className="dot"></div> : <></> }
                </div>
                {!token ? <button onClick={() => { setShowLogin(true) }}>Sign In</button> : 
                    <div className="navbar_profile">
                        <img src={assets.profile_icon} alt="" />
                        <ul className='nav_profile_dropdown'>
                            <li onClick={()=>{navigate('/myorders')}}>
                                <img src={assets.bag_icon} alt="" />
                                <p>Orders</p>
                            </li>
                            <hr />
                            <li>
                                <img src={assets.logout_icon} alt="" />
                                <p onClick={logOut}>LogOut</p>
                            </li>
                        </ul>
                    </div>
                }
            </div>
        </div>
    )
}

export default Navbar
