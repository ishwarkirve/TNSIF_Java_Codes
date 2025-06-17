import React from 'react'
import './Footer.css';
import { assets } from '../../assets/assets';

const Footer = () => {
    return (
        <div className='footer' id="footer">
            <div className="footer_content">
                <div className="footer_content_left">
                    <img src={assets.logo} alt="" />
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Rem animi similique explicabo porro molestiae, voluptatum, quis eius fugit natus impedit ipsam, dicta omnis error temporibus dolorum dignissimos dolore! Quidem magni deserunt rerum quae, est eaque nam molestiae necessitatibus inventore! Dolor similique quae, in natus ipsum vitae numquam eius sed ea?</p>
                    <div className="footer_social_icons">
                        <img src={assets.facebook_icon} alt="" />
                        <img src={assets.twitter_icon} alt="" />
                        <img src={assets.linkedin_icon} alt="" />
                    </div>
                </div>
                <div className="footer_content_center">
                    <h2>Company</h2>
                    <ul>
                        <li>Home</li>
                        <li>About Us</li>
                        <li>Delivery</li>
                        <li>Privacy Policy</li>
                    </ul>
                </div>
                <div className="footer_content_right">
                    <h2>Get In Touch</h2>
                    <ul>
                        <li>+91 1234567890</li>
                        <li>example@gmail.com</li>
                    </ul>
                </div>
            </div>
            <hr />
            <p className="footer_copyright">Copyright 2025 &copy; HangerHop.com -All Rights Reserved.</p>
        </div>
    )
}

export default Footer
