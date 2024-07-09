import React, { useState, useEffect } from 'react';
import axios from 'axios';
import TaskList from '../components/TaskList';
import TaskFilter from '../components/TaskFilter';

const TaskListPage = () => {
  const [tasks, setTasks] = useState([]);
  const [filter, setFilter] = useState('ALL');

  const fetchTasks = async () => {
    try {
      const response = await axios.get('http://localhost:8082/tasks');
      setTasks(response.data);
    } catch (error) {
      console.error('Error fetching tasks:', error);
    }
  };

  useEffect(() => {
    fetchTasks();
  }, []);

  const filteredTasks = tasks.filter((task) => {
    if (filter === 'ALL') return true;
    return task.status === filter;
  });

  return (
    <div className="task-list-container">
      <TaskList tasks={filteredTasks} fetchTasks={fetchTasks} />
    </div>
  );
};

export default TaskListPage;
