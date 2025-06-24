import { configureStore } from '@reduxjs/toolkit';
import userReducer from './features/user/userSlice.js';

export const store = configureStore({
  reducer: {
    user: userReducer,
    // thêm các reducer khác ở đây nếu có
  },
});
