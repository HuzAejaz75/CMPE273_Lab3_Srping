function groupmembers(state =[], action){
   switch(action.type){
       case 'MEMBER_DATA':
       return {
        ...state[0],members1:action.info
    }
    break;
    default:
    return state;
    break;
   }
   
}
export default groupmembers;