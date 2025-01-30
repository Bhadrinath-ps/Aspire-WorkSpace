import React from 'react';
import { useDispatch, useSelector } from 'react-redux';

const Counter = () => {
  const dispatch = useDispatch();
  const { count, loading, message } = useSelector(state => state);

  const handleIncrement = () => {
    dispatch({ type: 'INCREMENT' });
  };

  const handleDelayedIncrement = () => {
    dispatch({ type: 'DELAYED_INCREMENT' });
  };

  const handleCancelDelay = () => {
    dispatch({ type: 'CANCEL_DELAY' });
  };

  return (
    <div>
      <h1>Counter: {count}</h1>
      <div>
        <button onClick={handleIncrement}>Increment Immediately</button>
        <button onClick={handleDelayedIncrement}>Increment After 3 Seconds</button>
        <button onClick={handleCancelDelay} disabled={!loading}>Cancel Delay</button>
      </div>
      {loading && <p>{message}</p>}
    </div>
  );
};

export default Counter;
