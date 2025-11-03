import React, { useState } from "react";
import API from "../api";

const AddSweetForm = ({ onAdd }) => {
  const [formData, setFormData] = useState({
    name: "",
    category: "",
    price: "",
    quantity: "",
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await API.post("/sweets", formData);
      alert("Sweet added successfully!");
      setFormData({ name: "", category: "", price: "", quantity: "" });
      onAdd(); // refresh sweet list
    } catch (error) {
      console.error(error);
      alert("Error adding sweet");
    }
  };

  return (
    <div className="add-sweet-form">
      <h3>Add New Sweet</h3>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="name"
          placeholder="Sweet Name"
          value={formData.name}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="category"
          placeholder="Category"
          value={formData.category}
          onChange={handleChange}
          required
        />
        <input
          type="number"
          name="price"
          placeholder="Price"
          value={formData.price}
          onChange={handleChange}
          required
        />
        <input
          type="number"
          name="quantity"
          placeholder="Quantity"
          value={formData.quantity}
          onChange={handleChange}
          required
        />
        <button type="submit">Add Sweet</button>
      </form>
    </div>
  );
};

export default AddSweetForm;
