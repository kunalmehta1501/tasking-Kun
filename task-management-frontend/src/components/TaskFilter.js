import React from 'react';

const TaskFilter = ({ filter, setFilter }) => {
  return (
    <div className="task-filter">
      <label htmlFor="status-filter">Filter by status:</label>
      <select
        id="status-filter"
        value={filter}
        onChange={(e) => setFilter(e.target.value)}
      >
        <option value="ALL">All</option>
        <option value="TODO">To Do</option>
        <option value="IN_PROGRESS">In Progress</option>
        <option value="DONE">Done</option>
      </select>
    </div>
  );
};

export default TaskFilter;
