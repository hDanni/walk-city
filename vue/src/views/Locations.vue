<template>
  <div id="background">
    <div id="title-container">
      <h1>Locations</h1>
    </div>

    <!-- <div id="search-container">
      <input
        id="search"
        type="text"
        name="search-bar"
        v-model="searchTerm"
        placeholder="Search for Locations"
      />
      <a @click.prevent="searchLocations()"
        ><svg
          xmlns="http://www.w3.org/2000/svg"
          width="30"
          height="40"
          fill="currentColor"
          class="bi bi-geo-alt-fill"
          viewBox="-4 -5 18 25"
        >
          <path
            d="M8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10zm0-7a3 3 0 1 1 0-6 3 3 0 0 1 0 6z"
          />
        </svg>
      </a>
    </div> -->

    <!-- todo: version 1.1 -->
    <!-- <div id="suggested-search-bar">
      <div class="suggested-search-item">Restaurants</div>

      <div class="suggested-search-item">Parks</div>

      <div class="suggested-search-item">Museums</div>

      <div class="suggested-search-item">Nightlife</div>

      <div class="suggested-search-item">Shopping</div>
    </div> -->

    <div id="feature-location" @click="showFeature = !showFeature">
      <h2>Featured Locations</h2>
      <div id="search-container">
        <input
          id="search"
          type="text"
          name="search-bar"
          v-model="featuredSearchTerm"
          @click.stop=""
          placeholder="Filter Featured"
        />
        <!-- <button @click="showFeature = !showFeature"></button> -->
      </div>
      <img
        src="../assets/Icons/dropdown_arrow.png"
        alt=""
        :class="{ closed: !showFeature }"
      />
    </div>

    <div id="feature-location-list" v-if="showFeature">
      <div>
        <location
          class="location-item"
          v-for="location in filterFeatureLocations"
          v-bind:key="location.locationId"
          :location="location"
        />
      </div>
    </div>

    <div id="current-location" @click="showNearBy = !showNearBy">
      <h2>Nearby Locations</h2>
      <div id="search-container">
        <input
          id="search"
          type="text"
          name="search-bar"
          v-model="nearbySearchTerm"
          @click.stop=""
          placeholder="Filter Nearby"
        />
        <!-- <button @click="showNearBy = !showNearBy"></button> -->
      </div>
      <img
        src="../assets/Icons/dropdown_arrow.png"
        alt=""
        :class="{ closed: !showNearBy }"
      />

      <!-- <nearby-location
        class="location-item"
        v-for="nearbyLocation in filterNearbyLocations"
        v-bind:key="nearbyLocation.locationId"
        :nearbyLocation="nearbyLocation"
      /> -->
      <!-- <h2 v-if="!searchTerm">Nearby Locations</h2> -->
      <!-- <h2 v-if="!searchTerm">Nearby Locations</h2>
        <h2 v-else>Search Results</h2>
        <nearby-location v-if="!searchTerm" class="location-item" v-for="nearbyLocation in filterNearbyLocations" v-bind:key="nearbyLocation.locationId" :nearbyLocation="nearbyLocation"/>
        <nearby-location v-else class="location-item" v-for="searchResult in searchResults" v-bind:key="searchResult.locationId" :nearbyLocation="searchResult"/> -->
    </div>

    <div id="current-location-list" v-if="showNearBy">
      <div>
        <nearby-location
          class="location-item"
          v-for="nearbyLocation in filterNearbyLocations"
          v-bind:key="nearbyLocation.locationId"
          :nearbyLocation="nearbyLocation"
        />
      </div>
    </div>

    <!-- <div id="attraction-container-data" v-for="attraction in featuredAttractions" v-bind:key="attraction.id">
        <h1 id="name">{{attraction.name}}</h1>
        <h1 id="alias">{{attraction.alias}}</h1>
        <h1 id="phone">{{attraction.phone}}</h1>
    </div> -->
  </div>
</template>

<script>
import Location from "../components/Location.vue";
import attractionService from "../services/AttractionService";
import NearbyLocation from "../components/NearbyLocation.vue";

export default {
  components: {
    Location,
    NearbyLocation,
  },
  data() {
    return {
      featuredLocations: [],
      searchTerm: "",
      featuredSearchTerm: "",
      nearbySearchTerm: "",
      searchResults: [],
      showFeature: true,
      nearbyLocations: [],
      showNearBy: true,
    };
  },
  created() {
    if (this.storeLat == "" || this.storeLon == "") {
      this.$store.commit("STORE_COOR");
    }

    attractionService.getAttractions().then((response) => {
      this.featuredLocations = response.data;
    });

    this.$store.dispatch("updateNearbyLocations");

    this.$store.dispatch("getCheckInHistory");

    // attractionService.getAttraction('OUPyV2WKYETuprScXSAtuA').then(response => {
    //     this.attraction = response.data;
    //     console.log(this.attraction);
    // })
    // if(this.$store.state.userCoor.userLat && this.$store.state.userCoor.userLong ){

    // }

    console.log(this.storeLat);

    console.log(this.storeLon);
  },
  mounted() {
    //     this.$store.commit("STORE_COOR")
    //     let lat = this.$store.getters.getCoordinates.userLat;
    //     let long = this.$store.getters.getCoordinates.userLong;
    //     console.log(typeof lat)
    //     console.log(typeof long)
    //     console.log(lat.toString())
    //     console.log(long.toString())
    //      attractionService.getNearbyLocations(lat.toString(), long.toString()).then(response => {
    //         this.nearbyLocations = response.data;
    //           }).catch(e => { console.log(e.response)})
    //     console.log(this.$store.state.userCoor.userLong)
    //     console.log(this.$store.state.userCoor.userLat)
  },
  computed: {
    filterFeatureLocations() {
      return this.featuredLocations.filter((location) => {
        return location.name
          .toLowerCase()
          .includes(this.featuredSearchTerm.toLowerCase());
      });
    },
    filterNearbyLocations() {
      return this.$store.state.nearbyBusiness.filter((location) => {
        return location.name
          .toLowerCase()
          .includes(this.nearbySearchTerm.toLowerCase());
      });
    },

    nearbyBusiness() {
      return this.$store.state.nearbyBusiness;
    },
  },

  methods: {
    checkUserRole() {
      // let test = this.$store.getters.userRole();
      if (!this.$store.state.token) {
        this.$router.push({ name: "login", query: { redirect: "/locations" } });
      }
    },
    getNearbyLocations() {
      this.checkUserRole();
    },
    searchLocations() {
      // this.$store.dispatch("searchByTerm", {searchTerm: this.searchTerm})
      let userCoor = this.$store.state.userCoor;
      attractionService
        .getAttractionBySearch(
          this.searchTerm,
          userCoor.userLat,
          userCoor.userLong
        )
        .then((response) => {
          let result = response.data;
          this.$store.commit("STORE_LOCATION_SEARCH", result.businesses);
        });
      this.$router.push({ name: "search" });
    },
  },
};
</script>

<style scoped>
#title-container {
  background-image: url("../assets/Images/homepage-background.png");
  background-size: cover;
  background-position: center;
  height: 200px;
  display: flex;
  justify-content: center;
  align-items: center;

  flex-direction: column;
}

h1 {
  color: white;
  text-align: center;
}

h2 {
  color: rgb(226, 175, 8);
}

#feature-location-list,
#current-location-list {
  display: grid;
  grid-template-columns: repeat(auto-fit, min-content);
  padding: 0 1rem;
  padding-bottom: 20px;
  height: auto;
  overflow: auto;
}

#feature-location-list h2 {
  color: #e2af08;
}

#feature-location,
#current-location {
  display: flex;
  justify-content: space-between;
  padding: 1rem;
  align-items: center;
}

#location-item {
  height: min-content;
}

#background {
  background-image: linear-gradient(180deg, #120e0b, #002d56, #005278);
  min-height: 100%;
  height: 60%;
  overflow-y: auto;
}

#suggested-search-bar {
  display: flex;
  color: white;
  border: turquoise solid 1px;
  margin: 0.5rem 1rem;
  border-radius: 3px;
  overflow-x: scroll;
}

.suggested-search-item {
  background-color: rgb(70, 152, 230);
  padding: 8px;
  border-radius: 5px;
  margin: 10px 0px 10px 10px;
}

#search-container {
  display: flex;
  align-items: center;
  padding: 0 1rem;
}

#search {
  display: block;
  width: 150px;
  margin: 0.5rem auto;
  border: none;
  height: 30px;
  border-radius: 1.5rem;
  font-family: "Montserrat", sans-serif;
  text-align: center;
}

#search::placeholder {
  text-align: center;
}

#attraction-container-data {
  border: 1px solid rgb(70, 152, 230);
  background: rgba(29, 29, 29, 0.7);
  margin: 0rem 1rem 1rem;
  margin-bottom: 1rem;
  border-radius: 1rem;
}

.bi-geo-alt-fill {
  color: rgb(226, 175, 8);
}

.closed {
  transform: rotate(90deg);
}

::-webkit-scrollbar {
  width: 0;
}
</style>