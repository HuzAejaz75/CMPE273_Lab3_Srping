import React from 'react';
import {Link} from 'react-router';
import Photo from './Photo';

const  Leftpanel = React.createClass({
    render(){

    return(
        <div className="left-panel" style={{"backgroundColor":"#F7F9FB","height":"800px","width":"228px","marginRight":"20px","float":"left"}}>
          
          <img src={require('../images/dropbox_logo2.png')} style={{"height":"42px","width":"45px","marginLeft":"30px","marginTop":"30px"}}/>
          <Link className="button" to={`FileDashBoard`}>
            <div className="menu"> Home</div>
          </Link>
          <Link className="button" to={`FileDashBoard/Groups`}>
            <div className="menu" style={{ "margin-top":"30px"}}> Groups</div>
          </Link>
          <Link className="button" to={`UserDetails`}>
            <div className="menu" style={{ "margin-top":"30px"}}> User Details</div>
          </Link>
        </div>
            
        )
    }
    


});
export default  Leftpanel;