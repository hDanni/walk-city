import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if (currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    userCoor: {
      userLat: '',
      userLong: ''
    },
    /*     locationList: [
          {
            id: 1,
            name: 'The Statue of Libery',
            image: '',
            days: 'Sun - Sat',
            hours: '9:00 am - 3:30 pm',
            description: 'the Statue of Liberty is an iconic symbol of freedom and democracy. ',
            category: 'Landmark',
          },
          {
            id: 2,
            name: 'The Metropolitan Museum of Art',
            image: '',
            days: 'Tues - Sun',
            hours: '10:00 am - 5:00 pm',
            description: 'the Met is a must-see attraction for art lovers.',
            category: 'Attraction',
          },
          {
            id: 3,
            name: 'Central Park',
            image: '',
            days: 'Sun - Sat',
            hours: '6:00 am - 1:00 am',
            description: 'Visitors can take a stroll through the park\'s many gardens and paths, rent a bike, or enjoy a picnic with friends and family.',
            category: 'Park',
          },
          {
            id: 4,
            name: 'The Empire State Building',
            image: '',
            days: 'Sun - Sat',
            hours: '8:00 am - 2:00 am',
            description: 'Visitors can take an elevator to the top for stunning views of the city skyline.',
            category: 'Landmark',
          },
          {
            id: 5,
            name: 'The 9/11 Memorial and Museum',
            image: '',
            days: 'Sun - Sat',
            hours: '9:00 am - 8:00 pm',
            description: 'The 9/11 Memorial and Museum pays tribute to the victims of the September 11th terrorist attacks. ',
            category: 'Landmark',
          },
        ], */
    nearbyBusiness: [],
    locationSearch: [],
    checkedInLocations: [],
    directions: [],
    geometry: '',
    searchTerm: '',
    currentLocationId: '',
    badgeList: [
      {
        id: 1,
        image: require('../assets/Badges/curator-badge.png'),
        name: 'Curator',
        description: 'Visit all featured museums in NYC',
        category: 'Museum',
        achieved: false,
      },
      {
        id: 2,
        image: require('../assets/Badges/nature-lover-badge.png'),
        name: 'Nature Lover',
        description: 'Visit all featured parks in NYC',
        category: 'Park',
        achieved: false,
      },
      {
        id: 3,
        image: require('../assets/Badges/party-animal-badge.png'),
        name: 'Party Animal',
        description: 'Visit all featured nightlife attractions in NYC',
        category: 'Nightlife',
        achieved: false,
      },
      {
        id: 4,
        image: require('../assets/Badges/shopaholic-badge.png'),
        name: 'Shopaholic',
        description: 'Visit all featured shopping sites in NYC',
        category: 'Shopping',
        achieved: false,
      },
      {
        id: 5,
        image: require('../assets/Badges/foodie-badge.png'),
        name: 'Foodie',
        description: 'Visit all featured restaurants in NYC',
        category: 'Restaurants',
        achieved: false,
      },
      {
        id: 6,
        image: require('../assets/Badges/excelsior-badge.png'),
        name: 'Excelsior',
        description: 'Visit all featured locations in NYC',
        category: '',
        achieved: false,
      }
    ]
  },
  getters: {
    userRole() {
      return currentUser.authorities[0].name;
    }
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user', JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    STORE_COOR(state) {
      navigator.geolocation.getCurrentPosition(postion => {

        let userLat = postion.coords.latitude;
        let userLong = postion.coords.longitude;

        state.userCoor.userLat = userLat;
        state.userCoor.userLong = userLong;
      })
    },
    STORE_LOCATION_SEARCH(state, locations) {
      state.locationSearch = locations;
    },
    SET_SEARCH_TERM(state, term) {
      state.searchTerm = term;
    },
    SET_CHECKIN_HISTORY(state, checkedInLocations) {
      state.checkedInLocations = checkedInLocations;
    },
    SET_CURRENT_ID(state, id){
      state.currentLocationId = id;
    },
    SET_ACHIEVED(state, category){
      let badge = state.badgeList.find(badge => {
        return badge.category == category
      });
      console.log(badge);
      badge.achieved = true;
    },
    RESET_BADGELIST_ON_LOGOUT(state){
      state.badgeList.forEach(badge => {
        return badge.achieved = false;
      });
    },
    RESET_CHECKEDIN_LOGOUT(state){
      state.checkedInLocations = [];
    }
  },
  actions: {

    async updateNearbyLocations() {

      // Only call for current location and nearby locations if token exists
      // if (this.state.token) {
      navigator.geolocation.getCurrentPosition(postion => {

        let userLat = postion.coords.latitude
        let userLong = postion.coords.longitude

        this.state.userCoor.userLat = userLat
        this.state.userCoor.userLong = userLong

        axios.get('/view', {
          params: { latitude: this.state.userCoor.userLat, longitude: this.state.userCoor.userLong }
        }).then(response => {
          this.state.nearbyBusiness = response.data.businesses;

        })
      });
      // }
    },
    async searchLocations() {

      // Only call for current location and nearby locations if token exists
      // if (this.state.token) {
      navigator.geolocation.getCurrentPosition(postion => {

        let userLat = postion.coords.latitude
        let userLong = postion.coords.longitude

        this.state.userCoor.userLat = userLat
        this.state.userCoor.userLong = userLong

        axios.get('/attractions/search', {
          params: {
            term: this.state.searchTerm,
            latitude: this.state.userCoor.userLat,
            longitude: this.state.userCoor.userLong
          }
        }).then(response => {
          this.state.locationSearch = response.data.businesses
        })
      });
      // }
    },
    async getCheckInHistory() {
      axios.get(`/checkin/history`).then(
        (response) => {
          this.state.checkedInLocations = response.data;
        }
      );
    },
    async getUserAwards() {
      axios.get(`/user/awards`).then(
        (response) => {
          this.state.badgeList = response.data;
        }
      );
    },
    async getDirectionsByDriving() {
      navigator.geolocation.getCurrentPosition(postion => {

        let userLat = postion.coords.latitude
        let userLong = postion.coords.longitude

        this.state.userCoor.userLat = userLat
        this.state.userCoor.userLong = userLong

        /* console.log(businessId); */

        axios.get(`/attraction/detail/${this.state.currentLocationId}/directions/driving`, {
          params: {
            userLong: this.state.userCoor.userLong,
            userLat: this.state.userCoor.userLat
          }
        }).then(response => {
          this.state.directions = response.data
        })
      });

    },
    async getGeometryByDriving(){
      navigator.geolocation.getCurrentPosition(postion => {

        let userLat = postion.coords.latitude
        let userLong = postion.coords.longitude

        this.state.userCoor.userLat = userLat
        this.state.userCoor.userLong = userLong

        /* console.log(businessId); */

        axios.get(`/attraction/detail/${this.state.currentLocationId}/geometry/driving`, {
          params: {
            userLong: this.state.userCoor.userLong,
            userLat: this.state.userCoor.userLat
          }
        }).then(response => {
          this.state.geometry = response.data
        })
      });
    },

   async museumBadge() {
      let a = this.state.checkedInLocations.filter((location) => {
        if (location.featured) {
          return location.category == "Museum";
        }
      });
      if (a.length >= 5){
        let badge = this.state.badgeList.find(badge => {
          return badge.category == 'Museum'
        });
        badge.achieved = true;
      }
    },

    async parkBadge() {
      let a = this.state.checkedInLocations.filter((location) => {
        if (location.featured) {
          return location.category == "Park";
        }
      });
      if (a.length >= 5){
        let badge = this.state.badgeList.find(badge => {
          return badge.category == 'Park'
        });
        badge.achieved = true;
      }
    },

    async nightlifeBadge() {
      let a = this.state.checkedInLocations.filter((location) => {
        if (location.featured) {
          return location.category == "Nightlife";
        }
      });
      if (a.length >= 5){
        let badge = this.state.badgeList.find(badge => {
          return badge.category == 'Nightlife'
        });
        badge.achieved = true;
      }
    },

    async shoppingBadge() {
      let a = this.state.checkedInLocations.filter((location) => {
        if (location.featured) {
          return location.category == "Shopping";
        }
      });
      if (a.length >= 5){
        let badge = this.state.badgeList.find(badge => {
          return badge.category == 'Shopping'
        });
        badge.achieved = true;
      }
    },

    async restaurantsBadge() {
      let a = this.state.checkedInLocations.filter((location) => {
        if (location.featured) {
          return location.category == "Restaurants";
        }
      });

      if (a.length >= 5){
        let badge = this.state.badgeList.find(badge => {
          return badge.category == 'Restaurants'
        });
        badge.achieved = true;
      }
    },
    async excelsiorBadge() {
      let counter = 1;

      for(let i = 0; i < this.state.badgeList.length-1; i ++){
        if(this.state.badgeList[i].achieved == true){
          counter++;
        }
        
      }
      if(counter == 6){
        this.state.badgeList[5].achieved = true;
      }
    },

    /* searchByTerm(searchTerm) {
      console.log(searchTerm);
    } */
  }
})
