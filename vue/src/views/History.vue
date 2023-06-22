<template>
  <div id="history-container">
    <div id="title-container">
      <h1>Check-In</h1>
    </div>
    <div id="search-container">
      <input
        id="search"
        type="text"
        name="search-bar"
        v-model="checkInSearchTerm"
        @click.stop=""
        placeholder="Filter Check In"
      />
      <!-- <button @click="showFeature = !showFeature"></button> -->
    </div>
    <checked-in-location
      v-for="checked in filterFeatureLocations"
      v-bind:key="checked.id"
      :checkedInLocations="checked"
    ></checked-in-location>
  </div>
</template>

<script>
/* import attractionService from "../services/AttractionService" */
import CheckedInLocation from "../components/CheckedInLocation.vue";

export default {
  data(){
    return {
      checkedInLocationFilter: [],
      checkInSearchTerm: ''
    }
  },
  computed: {
    filterFeatureLocations() {
      return this.$store.state.checkedInLocations.filter((location) => {
        return location.name
          .toLowerCase()
          .includes(this.checkInSearchTerm.toLowerCase());
      });
        // checkedInlocations(){
    //   return this.$store.state.checkedInlocations;
    // }
    }},
  components: {
    CheckedInLocation,
  },
  created() {
    /*     this.$store.commit("SET_CHECKIN_HISTORY",)
    attractionService.getCheckInHistory().then(
      (response) => {
      }
    ); */
    // this.$store.dispatch("getCheckInHistory");
  },
};
</script>

<style scoped>
#history-container {
  background-image: linear-gradient(180deg, #120e0b, #002d56, #005278);
  min-height: 100%;
  height: 60%;
  overflow-y: auto;
}

h1 {
  color: white;
  text-align: center;
}

#search {
  border-radius: 1.5rem;
  text-align: center;
}

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
::-webkit-scrollbar {
  width: 0;
}
</style>