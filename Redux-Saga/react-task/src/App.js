import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { increment, delayedIncrement, cancelDelayedIncrement } from './redux/actions';

function App() {
  const dispatch = useDispatch();
  const { count, loading, message } = useSelector((state) => state);

  return (
    <div>
      <h1>Counter: {count}</h1>
      <button onClick={() => dispatch(increment())}>Increment Immediately</button>
      <button onClick={() => dispatch(delayedIncrement())}>Increment after 3 seconds</button>
      <button onClick={() => dispatch(cancelDelayedIncrement())}>Cancel Delayed Increment</button>

      {loading && <p>{message}</p>}
    </div>
  );
}

export default App;
