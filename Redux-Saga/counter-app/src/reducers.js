const initialState = {
    count: 0,
    loading: false,
    message: ''
  };
  
  const counterReducer = (state = initialState, action) => {
    switch (action.type) {
      case 'INCREMENT':
        return { ...state, count: state.count + 1 };
      case 'DELAYED_INCREMENT':
        return { ...state, loading: true, message: 'Please wait...' };
      case 'STOP_DELAY':
        return { ...state, loading: false, message: '' };
      case 'CANCEL_DELAY':
        return { ...state, loading: false, message: 'Operation canceled!' };
      default:
        return state;
    }
  };
  
  export default counterReducer;
  