import React, { useState } from 'react';
import axios from 'axios';

const TaskModal = ({ task, onUpdate }) => {
  const [updatedTask, setUpdatedTask] = useState(task);
  const apiUrl = "http://localhost:8082";

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setUpdatedTask({ ...updatedTask, [name]: value });
  };

  const handleUpdate = () => {
    axios.put(`${apiUrl}/tasks/${updatedTask.id}`, updatedTask)
      .then(response => {
        onUpdate(response.data);
      })
      .catch(error => {
        console.error('Error updating task:', error);
      });
  };

  return (
    <div className="modal">
      <div className="modal-content">
        <h2>Edit Task</h2>
        <form>
          <label>Title:</label>
          <input type="text" name="title" value={updatedTask.title} onChange={handleInputChange} />
          <label>Description:</label>
          <textarea name="description" value={updatedTask.description} onChange={handleInputChange} />
          <label>Status:</label>
          <select name="status" value={updatedTask.status} onChange={handleInputChange}>
            <option value="TODO">To Do</option>
            <option value="IN_PROGRESS">In Progress</option>
            <option value="DONE">Done</option>
          </select>
          <button type="button" onClick={handleUpdate}>Update Task</button>
        </form>
      </div>
    </div>
  );
};

export default TaskModal;
