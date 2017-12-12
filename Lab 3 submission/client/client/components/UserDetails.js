import React from 'react';
import {Link} from 'react-router';
import CSSTransitionGroup from 'react-addons-css-transition-group';
import localstorage from 'local-storage';
import filedetails from '../data/fetch_files';
import './App.css';
import Leftpanel from './Leftpanel'
const  UserDetails = React.createClass({

      
    render(){
        
       
       
            return(
                <div>
                   <Leftpanel/>
                   <div className="col-md-4">
                   <input type="text" className="formfo" placeholder="Add your workplace here" /><br/>
                   <input type="text" className="formfo" placeholder="Add your educational details here"/><br/>
                   <input type="text" className="formfo" placeholder="Add your contact info here"/><br/>
                   <input type="text" className="formfo" placeholder="Add your Life events info here"/><br/>
                   <textarea className="formfo" placeholder="areas of interest" >

                    </textarea><br/>
                   <input className="btn btn-primary" type="submit" value="submit details"/>
                    <table className="table">
                    <thead>
                        <th> Details</th>
                        </thead>
                   
                    <tbody>
                        <tr>
                            <td>Name</td>
                            <td>Test User 3</td>
                         </tr>
                         <tr>
                            <td>Educational Details </td>
                            <td>San Jose State University</td>
                         </tr>
                         <tr>
                            <td>Contact info</td>
                            <td>12345</td>
                         </tr>
                         <tr>
                            <td>Life events</td>
                            <td>graduted from VTU </td>
                         </tr>
                         <tr>
                            <td>Interests</td>
                            <td>Music, Poetry</td>
                         </tr>
                    </tbody> 
                    </table>
                    </div>
                 </div>
                )
        
    }
});
export default  UserDetails;