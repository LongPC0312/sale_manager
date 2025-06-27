import axios from "axios";
import requestNewAccessToken from "./requestRefreshToken";

const api = axios.create({ baseURL: "http://localhost:8080" });

/* ① request-interceptor: tự gắn token mỗi lần gửi */
api.interceptors.request.use((config) => {
  const token = localStorage.getItem("accessToken");
  if (!config.url.includes("/api/refreshtoken")) {   
    config.headers.Authorization = "Bearer " + token;
  }  
  return config;
},
    (error) => {
    console.error("Lỗi trước khi gửi request:", error);
    return Promise.reject(error);
  });

/* ② response-interceptor: tự refresh khi 401 */
api.interceptors.response.use(
  (res) => res,
  async (err) => {
    const original = err.config;
    if ((err.response?.status === 401 || err.response?.status === 403) && !original._retry) {
      original._retry = true;
      const newToken = await requestNewAccessToken();
      if (newToken) {
        original.headers.Authorization = "Bearer " + newToken;
        return api(original);  
      } else {
        localStorage.clear();
        window.location.href = "/login";
      }
    }
    return Promise.reject(err);
  }
);

export default api;
