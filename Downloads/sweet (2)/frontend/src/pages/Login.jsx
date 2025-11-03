import React, { useState } from "react";
import { loginUser } from "../api/api";
import { useNavigate, Link } from "react-router-dom";

export default function Login({ setToken }) {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const res = await loginUser({ email, password });
      localStorage.setItem("token", res.data);
      setToken(res.data);
      navigate("/");
    } catch {
      alert("Invalid credentials");
    }
  };

  return (
    <div className="flex justify-center items-center h-[80vh]">
      <form
        onSubmit={handleLogin}
        className="bg-white p-8 rounded-xl shadow-xl w-80 flex flex-col gap-4"
      >
        <h2 className="text-center text-xl font-bold text-rose-500">
          Login to Sweet Shop
        </h2>
        <input
          type="email"
          placeholder="Email"
          className="border p-2 rounded-lg"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <input
          type="password"
          placeholder="Password"
          className="border p-2 rounded-lg"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button className="bg-rose-500 text-white py-2 rounded-lg hover:bg-rose-600">
          Login
        </button>
        <p className="text-sm text-center">
          Don’t have an account?{" "}
          <Link to="/register" className="text-rose-600 font-semibold">
            Register
          </Link>
        </p>
      </form>
    </div>
  );
}
