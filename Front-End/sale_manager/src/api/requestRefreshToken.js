import axios from 'axios';

const requestNewAccessToken = async() =>{
    const refreshToken = localStorage.getItem("refreshToken");
    if(!refreshToken){
        console.log("Không tìm thấy refreshToken trong localStorage");
        return;
    }
    try{
        const response = await axios.post("http://localhost:8080/api/refreshtoken",{
            refreshToken: refreshToken,
        }) 
            const newAccessToken = response.data.accessToken;
            if (newAccessToken) {
                localStorage.setItem("accessToken", newAccessToken);
                localStorage.getItem("role");
                console.log("AccessToken đã được cập nhật.");
                return newAccessToken;
              } else {
                console.error("Không nhận được accessToken mới từ BE.");
                return null;
              }              
    }
    catch (error) {
              console.error("Lỗi khi gọi API refresh token:", error.response?.data?.message || error.message);
              return null;
            }   
}
export default requestNewAccessToken