import React from 'react';
import {Link} from 'react-router';
import CSSTransitionGroup from 'react-addons-css-transition-group';
import localstorage from 'local-storage';
import filedetails from '../data/fetch_files';
import './App.css';
const  FilesList = React.createClass({


    renderList(file){
     
           return(
               <li className="list-group-item oneRow" style={{"display":"flex",    "flexDirection":"row","height":"60px","borderLeft":"1px solid transparent","borderRight":"1px solid transparent","width":"750px","fontColor":"#3d464d","fontSize":"15px","lineHeight":"24px"}} key={file.FileId+' '+file.FILENAME}>{file.FILENAME} <div className={(file.STARRED)?"starred":"unstarred"} onClick={this.props.toggleStar.bind(null,file.FileId)}> </div></li>
           )
       
        },
      
    render(){
        
      
       
            return(
                <div className="col-md-2 ">
                    <div  style={{"width":"550px"}}> 
                        <ul>
                       
                            
                             {this.props.filedetails.filedata.map(this.renderList)}
                              
                           
                        </ul>
                     </div>
                 </div>
                )
        
    }
});
export default  FilesList;




      