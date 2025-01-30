import { createStore, applyMiddleware } from 'redux';
import createSagaMiddleware from 'redux-saga';
import counterReducer from './reducer';
import { watchCounterActions } from './sagas';

const sagaMiddleware = createSagaMiddleware();

const store = createStore(counterReducer, applyMiddleware(sagaMiddleware));

sagaMiddleware.run(watchCounterActions);

export default store;
