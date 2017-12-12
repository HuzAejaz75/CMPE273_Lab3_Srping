import React from 'react';
import {Link,Redirect} from 'react-router';
import localstorage from 'local-storage';
import axios from 'axios';
import Leftpanel from './Leftpanel';
import FilesList from './FilesList'


//import Comments from './Comments';
const  FileDashBoard = React.createClass({
   
    handleSubmit(e){

        console.log(this.refs.fileup.files[0].name);
        const pl = new FormData();   
        pl.append('fileup', this.refs.fileup.files[0]);
        this.props.uploadFile(pl);
        var user = localstorage.get('user');
        this.getfilesnow()
        e.preventDefault();
        },
        getfilesnow(){
    
            console.log( localstorage.get('token'))
            let config = {
                headers: {
                  'Authorization': localstorage.get('token'),
                }
              }
            axios.get("http://localhost:8080/routes/getalldata",
            {params:{email:localstorage.get('userdata')}},config).then(
                (res)=>{
                    console.log(res)
                   localstorage.set('fileData',res.data);
                   this.props.getallfiles(res.data)
                });
        },
    filelist(){
        {this.props.getallfiles(email)}
        return(
            <div>
                 <strong>file</strong>   
            </div>
        );
    },
    handleLogout(e){
        console.log('logging out');
        localstorage.clear();
        this.props.history.push('/login');
        e.preventDefault();
    },
    
    render(){
        
       if(localstorage.get('userdata')!==null){
                var filedetails = this.props.filedetails;
            
       
            return(    
                        <div >   
                        
                            <Leftpanel/>
                            <div  style={{"width":"100%","height":"50px","marginBottom":"20px"}}>
                               <div style={{"float":"left","fontSize":"21px","color":"#1B2733","marginLeft":"30px"}}>Home</div>
                                <div className="btn btn-primary" style={{"marginLeft":"800px"}} onClick={this.handleLogout} >Logout</div>
                             </div>  

                            <div style={{"display":"flex", "flexDirection":"row"}}>
                              <FilesList {...this.props}  fileData = {filedetails} />
                                <div className="col-md-1 col-md-offset-5" style={{"float":"left","marginLeft":"650px"}} >
                                        <form ref="fileupload" onChange={this.handleSubmit} >
                                                <div id="fileupload" style={{"height":"20px", "width":"100px"}}>
                                                    <input  type="file" ref="fileup" name="fileup" id="myFile" style={{"opacity":"0.5"}} />
                                                    <input type="submit" value="Upload files" className="btn btn-primary" style={{"backgroundColor":"#1167fb","width":"180px"}}/>
                                                </div>
                                        </form>
                                    
                                </div>
                            </div>
                        </div>
                               
                                
                            
                )
            
     


        }
    }
});
export default  FileDashBoard;