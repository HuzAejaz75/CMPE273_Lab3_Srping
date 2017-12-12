function groupdata(state =[], action){
    switch(action.type){
        case 'GET_GROUPS':
        return {
            ...state[0],data:action.data,size:action.size
        }
        break;
        default:
        return state
        break;

    }
   
   
 }
 export default groupdata;