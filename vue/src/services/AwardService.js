import axios from 'axios';

export default {

getAllAwards(){
    return axios.get('/awards')
},

getAwardById(){
    return axios.get('/user/awards')
}


}