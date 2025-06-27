import React, { useState } from 'react';
import api from "../api/axiosInstance";
import { useNavigate } from 'react-router-dom';

const Admin = () => {
  const [formData, setFormData] = useState({
    username: "",
    password: "",
    role:"",
  });
  const [confirmPass, setConfirmPass] =useState("");
  const [isLoading, setIsLoading] = useState(false);
  const [text, setText] = useState("");
  const [success, setSuccess] = useState(false);
  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (name === "confirmPass") {
      setConfirmPass(value); // xử lý riêng confirmPass
    } else {
      setFormData({ ...formData, [name]: value });
    }
  };
  

  const registerAccount = async (e) => {
    e.preventDefault();
    const name = localStorage.getItem("username") || formData.username;
    setText("Xin chào " + name + ", quản trị viên");

    if(!formData.username.trim()){
      setMessage("Tài khoản không được bỏ trống");
      return;
    }
    if(!formData.password.trim()){
      setMessage("Mật khẩu không được bỏ trống");
      return;
    }
    if(formData.password != confirmPass){
      setMessage("Mật khẩu xác nhận không khớp");
      return;
    }
    if(!formData.role.trim()){
      setMessage("Vai trò không được bỏ trống");
      return;
    }
  

    try {
      setIsLoading(true);
      const token = localStorage.getItem('accessToken');
      const response = await api.post('/taikhoan/admin/dangky', formData, {
        headers: { Authorization: "Bearer " + token },
      });
      if(response.data.success){
        setSuccess(response.data.message);
        setMessage(response.data.message);
      }  
    } catch (error) {
      
      setMessage(error.response?.data?.message);
      console.error('Error fetching admin message:', error);
    } finally {
      setIsLoading(false);
      
    }
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-100 to-purple-200 flex items-center justify-center px-4">
      <div className="bg-white p-8 rounded-2xl shadow-lg w-full max-w-md">
        <h1 className="text-2xl font-bold text-center mb-4 text-gray-800">
          {text || "Đăng ký quản trị viên"}
        </h1>

        <form onSubmit={registerAccount} className="space-y-4">
          <input
            type="text"
            name="username"
            placeholder="Tên đăng nhập"
            value={formData.username}
            onChange={handleChange}
            className="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-400"
          />
          <input
            type="password"
            name="password"
            placeholder="Mật khẩu"
            value={formData.password}
            onChange={handleChange}
            className="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-purple-400"
          />
          <input
            type="password"
            name="confirmPass"
            placeholder="Xác nhận lại mật khẩu"
            value= {confirmPass}
            onChange={handleChange}
            className="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-purple-400"
          />
          <select
            name="role"
            value={formData.role}
            onChange={handleChange}
            className="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-400"
          >
            <option value="" disabled>-- Chọn vai trò --</option>
              <option value="manager">Sale Manager</option>
              <option value="customer">Customer</option>
              <option value="sale">Sale</option>
              <option value="warehouse">Warehouse</option>
              <option value="accountant">Accountant</option>
            </select>

          <button
            type="submit"
            disabled={isLoading}
            className="w-full bg-blue-600 text-white py-2 rounded-lg hover:bg-blue-700 transition"
          >
            {isLoading ? "Đang xử lý..." : "Đăng ký"}
          </button>
        </form>

        {message && (
          <p className={`mt-4 text-center ${success ? 'text-green-600' : 'text-red-600'}`}>
            {message}
          </p>
        )}
      </div>
    </div>
  );
};

export default Admin;