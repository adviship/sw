import React, { useEffect, useState } from "react";
import API from "../api";
import SearchBar from "../components/SearchBar";
import AddSweetForm from "../components/AddSweetForm";

const Dashboard = () => {
  const [sweets, setSweets] = useState([]);
  const [isAdmin, setIsAdmin] = useState(false);

  const fetchSweets = async () => {
    const res = await API.get("/sweets");
    setSweets(res.data);
  };

  const handleSearch = async (query) => {
    const params = new URLSearchParams(query);
    const res = await API.get(`/sweets/search?${params.toString()}`);
    setSweets(res.data);
  };

  useEffect(() => {
    fetchSweets();

    // Decode token if stored to check admin role
    const token = localStorage.getItem("token");
    if (token) {
      const payload = JSON.parse(atob(token.split(".")[1]));
      setIsAdmin(payload.role === "ADMIN");
    }
  }, []);

  return (
    <div className="dashboard">
      <h2>Sweet Shop</h2>
      <SearchBar onSearch={handleSearch} />

      {isAdmin && <AddSweetForm onAdd={fetchSweets} />}

      <div className="sweet-list">
        {sweets.map((sweet) => (
          <div key={sweet.id} className="sweet-card">
            <h3>{sweet.name}</h3>
            <p>Category: {sweet.category}</p>
            <p>Price: ₹{sweet.price}</p>
            <p>In Stock: {sweet.quantity}</p>
            <button
              disabled={sweet.quantity <= 0}
              onClick={async () => {
                await API.post(`/sweets/${sweet.id}/purchase`);
                fetchSweets();
              }}
            >
              {sweet.quantity <= 0 ? "Out of Stock" : "Purchase"}
            </button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Dashboard;
