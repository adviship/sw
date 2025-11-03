import React from "react";

export default function SweetCard({ sweet, onPurchase }) {
  return (
    <div className="bg-white shadow-lg p-4 rounded-xl flex flex-col items-center hover:scale-105 transition">
      <h3 className="text-lg font-bold">{sweet.name}</h3>
      <p className="text-sm text-gray-600">{sweet.category}</p>
      <p className="font-semibold mt-2">₹{sweet.price}</p>
      <p className="text-sm text-gray-500">Stock: {sweet.quantity}</p>
      <button
        onClick={() => onPurchase(sweet._id)}
        disabled={sweet.quantity === 0}
        className={`mt-3 px-4 py-1 rounded-lg ${
          sweet.quantity === 0
            ? "bg-gray-300 cursor-not-allowed"
            : "bg-rose-500 hover:bg-rose-600 text-white"
        }`}
      >
        {sweet.quantity === 0 ? "Out of Stock" : "Purchase"}
      </button>
    </div>
  );
}
