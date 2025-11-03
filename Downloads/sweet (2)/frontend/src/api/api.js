import axios from "axios";

const API = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
});

export const registerUser = (data) => API.post("/auth/register", data);
export const loginUser = (data) => API.post("/auth/login", data);

export const getSweets = (token) =>
  API.get("/sweets", {
    headers: { Authorization: `Bearer ${token}` },
  });

export const purchaseSweet = (id, token) =>
  API.post(
    `/sweets/${id}/purchase`,
    {},
    {
      headers: { Authorization: `Bearer ${token}` },
    }
  );
