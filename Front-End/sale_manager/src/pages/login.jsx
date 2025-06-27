import { useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import axios from "axios";

const Login = () => {
    const [formData, setFormData] = useState({
        username:"",
        password:"",
    });
    const [message, setMessage] = useState("");
    const [success, setSuccess] = useState(false);
    const [isLoading, setIsLoading] = useState(false);
    const navigate = useNavigate();
       
    const routeByRole = (role) => {
        if(role==="ROLE_ADMIN"){
          return "/admin";
        }      
        if(role==="ROLE_MANAGER"){
          return "/manager";
        }   
        if(role==="ROLE_CUSTOMER"){
          return "/customer";
        }   
        if(role==="ROLE_SALE"){
          return "/sale";
        }   
        if(role==="ROLE_WAREHOUSE"){
          return "/warehouse";
        }   
        if(role==="ROLE_ACCOUNTANT"){
          return "/accountant";
        }     
      
    };

        const handleLogin = async(e) =>{
            e.preventDefault();
            if(!formData.username.trim()){
                setMessage("Tên tài khoản không được bỏ trống");
                return;
            }
            if(!formData.password.trim()){
                setMessage("Mật khẩu không được bỏ trống");
                return;
            }
            setIsLoading(true);
            try{    
                const response = await axios.post( "http://localhost:8080/taikhoan/dangnhap", formData);
   
                if(response.data.success){
                  const rolePlayer = response.data.role[0].authority;
                  localStorage.setItem("accessToken", response.data.accessToken);
                  localStorage.setItem("refreshToken", response.data.refreshToken);
                  localStorage.setItem("username", response.data.username);
                  localStorage.setItem("role", rolePlayer);                  
                  navigate(routeByRole(rolePlayer));
                  setMessage(response.data.message);
                }
            }
            catch(error){
                setMessage(error.response?.data?.message || "Lỗi server");
                console.log("Lỗi kiểm tra đăng nhập:", error);
            }
            finally{
                setIsLoading(false);
            }
        }    
    
    return (
        <div className="max-w-md mx-auto mt-8">
      <h1 className="text-2xl font-bold text-gray-800 mb-4 text-center">Form Login</h1>
      <form onSubmit={handleLogin} className="p-6 bg-white rounded-lg shadow-md">
        <input
          className="w-full mb-4 p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
          type="text"
          value={formData.username}
          onChange={(e) => setFormData({ ...formData, username: e.target.value })}
          placeholder="Enter your username"
        />
        <input
          className="w-full mb-4 p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
          type="password"
          value={formData.password}
          onChange={(e) => setFormData({ ...formData, password: e.target.value })}
          placeholder="Enter your password"
        />
        {message && (
          <p className={`mb-4 ${success ? "text-green-500" : "text-red-500"}`}>
            {message}
          </p>
        )}

        <button
          type="submit"
          disabled={isLoading}
          className="w-full p-2 bg-blue-500 text-white rounded hover:bg-blue-600 disabled:bg-gray-400"
        >
          {isLoading ? "Loading..." : "Login"}
        </button>
      </form>
    </div>
    );
}
export default Login;