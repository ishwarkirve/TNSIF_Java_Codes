import React, { useState } from 'react'
import './Add.css'
import { assets } from '../../assets/assets'
import axios from "axios";
import { toast } from 'react-toastify';


const Add = ({url}) => {


    const [image,setImage] = useState(false);
    const [data,setData] = useState({
        name:"",
        description:"",
        price:"",
        category:"Salad"
    });

    const onChangeHandler = (event)=>{
        const name = event.target.name;
        const value = event.target.value;
        setData(data=>({...data,[name]:value}))
    }

    const onSubmitHandler = async(event)=>{
        event.preventDefault();
        const formData = new FormData();
        formData.append("name",data.name);
        formData.append("description",data.description);
        formData.append("price",Number(data.price));
        formData.append("category",data.category);
        formData.append("image",image);

        const response = await axios.post(`${url}/api/food/add`,formData);
        if(response.data.success){
            setData({
                name:"",
                description:"",
                price:"",
                category:"Salad"
            });
            setImage(false);
            toast.success(response.data.message);
        }else{
            toast.error(response.data.message);
        }
    }

  return (
    <div className='add'>
        <form className='flex_col' onSubmit={onSubmitHandler}>
            <div className="add_img_upload flex_col">
                <p>Upload Image</p>
                <label htmlFor="image">
                    <img src={image?URL.createObjectURL(image):assets.upload_area} alt="" />
                </label>
                <input onChange={(e)=>{setImage(e.target.files[0])}} type="file" id="image" name="image" hidden required/>
            </div>
            <div className="add_product_name flex_col">
                <p>Product Name</p>
                <input onChange={onChangeHandler}  value={data.name} type="text" name='name' placeholder='Type here'/>
            </div>
            <div className="add_product_desc flex_col">
                <p>Product Description</p>
                <textarea onChange={onChangeHandler}  value={data.description} name="description" rows="6" placeholder='Write content here' required></textarea>
            </div>
            <div className="add_category_price">
                <div className="add_category flex_col">
                    <p>Product Category</p>
                    <select onChange={onChangeHandler}  value={data.category} name="category">
                        <option value="Salad">Salad</option>
                        <option value="Rolls">Rolls</option>
                        <option value="Deserts">Deserts</option>
                        <option value="Sandwich">Sandwich</option>
                        <option value="Cake">Cake</option>
                        <option value="Pure Veg">Pure Veg</option>
                        <option value="Pasta">Pasta</option>
                        <option value="Noodles">Noodles</option>
                    </select>
                </div>
                <div className="add_price flex_col">
                    <p>Product Price</p>
                    <input onChange={onChangeHandler}  value={data.price} type="number" name='price' placeholder='$20'/>
                </div>
            </div>
            <button type='submit' className='add_btn'>Add</button>
        </form>
      
    </div>
  )
}

export default Add
