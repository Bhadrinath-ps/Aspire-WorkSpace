import { put, takeLatest, delay } from 'redux-saga/effects';

function* delayedIncrementSaga() {
  try {
    yield delay(3000); // 3 seconds delay
    yield put({ type: 'DELAYED_INCREMENT_SUCCESS' }); // Dispatch success after delay
  } catch (error) {
    console.error(error);
  }
}

function* cancelDelayedIncrementSaga() {
  yield put({ type: 'CANCEL_DELAYED_INCREMENT' });
}

export function* watchCounterActions() {
  yield takeLatest('DELAYED_INCREMENT', delayedIncrementSaga); // Start delayed increment on this action
  yield takeLatest('CANCEL_DELAYED_INCREMENT', cancelDelayedIncrementSaga); // Cancel operation on this action
}
