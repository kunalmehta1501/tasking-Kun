import React, { useState, useEffect } from 'react';
import axios from 'axios';
import TaskModal from './TaskModal';

const TaskList = () => {
  const [tasks, setTasks] = useState([]);
  const [filter, setFilter] = useState('All');
  const [selectedTask, setSelectedTask] = useState(null);
  const apiUrl = "http://localhost:8082";

  useEffect(() => {
    const fetchTasks = async () => {
      try {
        const response = await axios.get(`${apiUrl}/tasks`);
        setTasks(response.data);
      } catch (error) {
        console.error('Error fetching tasks:', error);
      }
    };
    fetchTasks();
  }, [apiUrl]);

  const handleStatusChange = (taskId, status) => {
    axios.put(`${apiUrl}/tasks/${taskId}`, { status })
      .then(response => {
        setTasks(tasks.map(task => task.id === taskId ? response.data : task));
      })
      .catch(error => {
        console.error('Error updating task status:', error);
      });
  };

  const handleDelete = (taskId) => {
    axios.delete(`${apiUrl}/tasks/${taskId}`)
      .then(() => {
        setTasks(tasks.filter(task => task.id !== taskId));
      })
      .catch(error => {
        console.error('Error deleting task:', error);
      });
  };

  const handleEditTask = (task) => {
    setSelectedTask(task);
  };

  const handleUpdateTask = (updatedTask) => {
    setTasks(tasks.map(task => task.id === updatedTask.id ? updatedTask : task));
    setSelectedTask(null);
  };

  const filteredTasks = filter === 'All' ? tasks : tasks.filter(task => task.status === filter);

  return (
    <div className="container tasks-container">
      <h2>Task List</h2>
      <div className="filter-container">
        <label>Filter by Status:</label>
        <select value={filter} onChange={(e) => setFilter(e.target.value)}>
          <option value="All">All</option>
          <option value="TODO">To Do</option>
          <option value="IN_PROGRESS">In Progress</option>
          <option value="DONE">Done</option>
        </select>
      </div>
      <ul className="tasks-list">
        {filteredTasks.map(task => (
          <li key={task.id} className="task-item">
            <div className="task-title">
              <strong>{task.title}</strong>
              <p>{task.description}</p>
              <p>Status: {task.status}</p>
            </div>
            <div className="task-actions">
              <button className="edit" onClick={() => handleEditTask(task)}>Edit</button>
              <button className="delete" onClick={() => handleDelete(task.id)}>Delete</button>
            </div>
          </li>
        ))}
      </ul>
      {selectedTask && (
        <TaskModal task={selectedTask} onUpdate={handleUpdateTask} />
      )}
    </div>
  );
};

export default TaskList;
