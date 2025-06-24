import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Admin = () => {
  const [message, setMessage] = useState('');

  useEffect(() => {
    const welcomeAdmin = async () => {
      try {
        const token = localStorage.getItem('accessToken');
        const response = await axios.get('http://localhost:8080/taikhoan/admin', {
          headers: { Authorization: `Bearer ${token}` },
        });
        setMessage('Xin chào quản trị viên');
      } catch (error) {
        console.error('Error fetching admin message:', error);
        console.log(token);
        setMessage('Lỗi khi tải thông báo');
      }
    };
    welcomeAdmin();
  }, []);

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-100 to-purple-200 flex items-center justify-center">
      <h1 className="text-3xl font-bold text-gray-800">{message}</h1>
    </div>
  );
};

export default Admin;