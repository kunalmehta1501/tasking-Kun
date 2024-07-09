import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const TaskForm = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [status, setStatus] = useState('TODO'); // Default status set to TODO
  const navigate = useNavigate();
  const apiUrl="http://localhost:8082"
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
        debugger;
      await axios.post(`${apiUrl}/tasks`, { title, description, status });
      navigate('/tasks');
    } catch (error) {
      console.error('Error creating task:', error);
    }
  };

  return (
    <div className="container form-container">
      <h2>Create a New Task</h2>
      <form onSubmit={handleSubmit}>
        <label>Title</label>
        <input 
          type="text" 
          value={title} 
          onChange={(e) => setTitle(e.target.value)} 
          required 
        />
        <label>Description</label>
        <textarea 
          value={description} 
          onChange={(e) => setDescription(e.target.value)} 
        ></textarea>
        <label>Status</label>
        <select 
          value={status} 
          onChange={(e) => setStatus(e.target.value)}
        >
          <option value="TODO">To Do</option>
          <option value="IN_PROGRESS">In Progress</option>
          <option value="DONE">Done</option>
        </select>
        <button type="submit">Create Task</button>
      </form>
    </div>
  );
};

export default TaskForm;
