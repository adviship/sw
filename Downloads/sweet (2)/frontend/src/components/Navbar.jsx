import React from "react";
import { Link, useNavigate } from "react-router-dom";

export default function Navbar({ token, setToken }) {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("token");
    setToken(null);
    navigate("/login");
  };

  return (
    <nav className="flex justify-between items-center bg-rose-500 text-white p-4 shadow-md rounded-b-2xl">
      <h1 className="text-xl font-bold">🍬 Sweet Shop</h1>
      <div>
        {token ? (
          <button
            onClick={handleLogout}
            className="bg-white text-rose-600 px-3 py-1 rounded-lg font-semibold"
          >
            Logout
          </button>
        ) : (
          <Link
            to="/login"
            className="bg-white text-rose-600 px-3 py-1 rounded-lg font-semibold"
          >
            Login
          </Link>
        )}
      </div>
    </nav>
  );
}
