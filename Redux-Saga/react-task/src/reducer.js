const initialState = {
    count: 0,
    loading: false,
    message: '',
  };
  
  const counterReducer = (state = initialState, action) => {
    switch (action.type) {
      case 'INCREMENT':
        return {
          ...state,
          count: state.count + 1,
        };
      case 'DELAYED_INCREMENT':
        return {
          ...state,
          loading: true,
          message: 'Waiting for delayed increment...',
        };
      case 'CANCEL_DELAYED_INCREMENT':
        return {
          ...state,
          loading: false,
          message: 'Delayed increment cancelled.',
        };
      case 'DELAYED_INCREMENT_SUCCESS':
        return {
          ...state,
          count: state.count + 1,
          loading: false,
          message: 'Delayed increment completed.',
        };
      default:
        return state;
    }
  };
  
  export default counterReducer;

  