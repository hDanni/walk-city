import axios from 'axios';

export default {

    // Database
    // Featured List
    getAttractions() {
        return axios.get('/attraction');
    },

    // Yelp API
    // Info about location
    getAttraction(id) {
        return axios.get(`/attraction/detail/${id}`);
    },

    // Yelp API
    // Locations based on coordinates
    // getNearbyLocations(lat, long) {
    //     return axios.get(`/view?latitude=${lat}&longitude=${long}`);
    // }
    // MOVED TO STORE ACTION TO SHARE NEARBY LOC ACROSS COMP AND HAVE REACTIVITY

    getAttractionBySearch(searchTerm, lat, long) {
        return axios.get('/attractions/search', {
            params: {
                term: searchTerm,
                latitude: lat,
                longitude: long
            }
        })
    },
    getDirectionsByDriving(businessId, lat, long) {
        return axios.get(`/attraction/detail/${businessId}/directions/driving`, {
            params: {
                userLong: lat,
                userLat: long
            }
        })
    },
    checkInAtLocation(location){
        location.description = '';
        return axios.post('/checkin/create', location)
    },
    getCheckInHistory(){
        return axios.get(`/checkin/history`)
    }
}