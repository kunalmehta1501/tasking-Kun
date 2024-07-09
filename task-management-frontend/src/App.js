import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import TaskFormPage from './pages/TaskFormPage';
import TaskListPage from './pages/TaskListPage';
import './App.css'; // Ensure this import is present

const App = () => {
  return (
    <Router>
      <div>
        <nav className="navbar">
          <ul>
            <li>
              <Link to="/">Task Form</Link>
            </li>
            <li>
              <Link to="/tasks">Task List</Link>
            </li>
          </ul>
        </nav>
        <Routes>
          <Route path="/" element={<TaskFormPage apiUrl="http://localhost:8082" />} />
          <Route path="/tasks/*" element={<TaskListPage apiUrl="http://localhost:8082" />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;
