import { takeLatest, put, delay, cancel, fork, race, take } from 'redux-saga/effects';

// Worker Saga: Handle delayed increment
function* delayedIncrement() {
  try {
    // Race between delay and the cancel action
    const { timeout, cancelAction } = yield race({
      timeout: delay(3000), // Wait 3 seconds
      cancelAction: take('CANCEL_DELAY'), // Listen for cancel action
    });

    // If timeout happens, increment happens
    if (timeout) {
      yield put({ type: 'INCREMENT' });
      yield put({ type: 'STOP_DELAY' }); 
    }

    // If cancel action is triggered, immediately stop delay and increment
    if (cancelAction) {
      yield put({ type: 'INCREMENT' });
      yield put({ type: 'CANCEL_DELAY' }); 
    }
  } catch (error) {
    yield put({ type: 'CANCEL_DELAY' });
  }
}

// Watcher Saga: Listens for DELAYED_INCREMENT action
function* watchDelayedIncrement() {
  let task;
  yield takeLatest('DELAYED_INCREMENT', function* () {
    if (task) {
      yield cancel(task); // Cancel any running task
    }
    task = yield fork(delayedIncrement); // Start a new delayed increment task
  });
}

// Root Saga
export default function* rootSaga() {
  yield watchDelayedIncrement();
}
