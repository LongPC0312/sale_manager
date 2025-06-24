import { useState } from 'react'
import {Routes, Route} from 'react-router-dom'
import Login from './pages/login'
import Admin from './pages/admin'

function App() {
  const [count, setCount] = useState(0)

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-100 to-purple-200 flex flex-col items-center justify-center text-gray-800 font-sans">
      <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/admin" element={<Admin />} />
          <Route path="/" element={<Login />} />
      </Routes>
         
    </div>
  )
}

export default App
