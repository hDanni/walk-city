<template>
  <div id="background">
    <div id="info-location-detail">
      <h1 id="name">{{ location.name }}</h1>
      <button
        id="get-directions"
        @click="
          getDirectionsByDriving();
          toggleMapStatus();
        "
      >
        Get Directions
      </button>

      <button
        id="check-in"
        @click="checkIn()"
        :disabled="
          location.id ==
          this.$store.state.checkedInLocations.some((loc) => {
            return loc.businessId == location.id;
          })
        "
      >
        Check In
      </button>
      <h2 id="categories">{{ location.categories[0].title }}</h2>
      <p id="address">
        {{ location.location.display_address[0] }}
        {{ location.location.display_address[1] }}
      </p>
      <div id="schedule-container" v-if="location.hours">
        <p>Days and Hours:</p>
        <div
          id="schedule"
          v-for="(schedule, index) in location.hours[0].open"
          :key="index"
        >
          <p>
            {{ dayFormatter(schedule.day) }}:
            {{ timeFormatter(schedule.start.toString()) }} -
            {{ timeFormatter(schedule.end.toString()) }}
          </p>
        </div>
      </div>
      <div id="map-direction" v-show="this.toggleMap">
        <h3 id="direction-title">Directions</h3>
        <ol>
          <li v-for="steps in this.$store.state.directions" :key="steps.id">
            {{ steps.maneuver.instruction }}
          </li>
        </ol>
        <img
          id="map-img"
          :src="
            this.generateMap(
              this.$store.state.userCoor.userLong,
              this.$store.state.userCoor.userLat,
              this.location.coordinates.longitude,
              this.location.coordinates.latitude,
              this.$store.state.geometry
            )
          "
          alt=""
        />
      </div>
      <p id="phone">Phone Number: {{ location.phone }}</p>
      <div id="social-media">
        <!-- <a :href="location.url">Yelp Page</a> -->
      </div>

      <div id="img-container">
        <img
          id="location-detail-img"
          v-bind:src="photo"
          alt=""
          v-for="photo in location.photos"
          v-bind:key="photo"
        />
      </div>
    </div>
  </div>
</template>

<script>
import attractionService from "../services/AttractionService";
export default {
  props: ["location"],

  data() {
    return {
      toggleMap: false,
      directions: [],
      dbAttraction: {
        name: "",
        businessId: "",
        address: "",
        latitude: 0,
        longitude: 0,
        category: "",
        description: "",
        imageUrl: "",
      },
      testPath:
        "https://api.mapbox.com/styles/v1/mapbox/streets-v12/static/pin-s-a+9ed4bd({{this.$store.state.userCoor.userLong}}, {{this.$store.state.userCoor.userLat}}),pin-s-b+000({{this.location.coordinates.longitude}},{{this.location.coordinates.latitude}}),path-5+f44-0.5({{this.$store.state.geometry}})/auto/500x300?access_token=pk.eyJ1Ijoiamd0MyIsImEiOiJjbGdmNWJtcGIwdTVvM2xwanV4b2kyN2hrIn0.MvFZWiRnKRRdgM1uPDPagw",
      actualPath:
        "https://api.mapbox.com/styles/v1/mapbox/streets-v12/static/pin-s-a+9ed4bd(-122.46589,37.77343),pin-s-b+000(-122.42816,37.75965),path-5+f44-0.5(%7DrpeFxbnjVsFwdAvr@cHgFor@jEmAlFmEMwM_FuItCkOi@wc@bg@wBSgM)/auto/500x300?access_token=pk.eyJ1Ijoiamd0MyIsImEiOiJjbGdmNWJtcGIwdTVvM2xwanV4b2kyN2hrIn0.MvFZWiRnKRRdgM1uPDPagw",
    };
  },
  methods: {
    toggleMapStatus() {
      this.toggleMap = !this.toggleMap;
    },
    generateMap(userStart, userEnd, destStart, destEnd, geo) {
      return (
        "https://api.mapbox.com/styles/v1/mapbox/streets-v12/static/pin-s-a+9ed4bd(" +
        userStart +
        "," +
        userEnd +
        "),pin-s-b+000(" +
        destStart +
        "," +
        destEnd +
        "),path-5+f44-0.5(" +
        geo +
        ")/auto/500x300?access_token=pk.eyJ1Ijoiamd0MyIsImEiOiJjbGdmNWJtcGIwdTVvM2xwanV4b2kyN2hrIn0.MvFZWiRnKRRdgM1uPDPagw"
      );
    },
    assignDbAttraction() {
      // this.dbAttraction = this.location;
      this.dbAttraction.name = this.location.name;
      this.dbAttraction.businessId = this.location.id;
      this.dbAttraction.address =
        this.location.location.display_address[0] +
        ", " +
        this.location.location.display_address[1];
      this.dbAttraction.latitude = this.location.coordinates.latitude;
      this.dbAttraction.longitude = this.location.coordinates.longitude;
      this.dbAttraction.category = this.location.categories[0].title;
      this.dbAttraction.imageUrl = this.location.image_url;
    },

    timeFormatter(string) {
      let hours24 = parseInt(string.substring(0, 2));
      let hours = ((hours24 + 11) % 12) + 1;
      let amPm = hours24 > 11 ? "PM" : "AM";
      let minutes = string.substring(2);

      return hours + ":" + minutes + amPm;
    },
    dayFormatter(day) {
      if (day == 0) {
        return "Monday";
      } else if (day == 1) {
        return "Tuesday";
      } else if (day == 2) {
        return "Wednesday";
      } else if (day == 3) {
        return "Thursday";
      } else if (day == 4) {
        return "Friday";
      } else if (day == 5) {
        return "Saturday";
      } else if (day == 6) {
        return "Sunday";
      }
    },
    // getDirections(locationId, lat, long) {
    //   attractionService
    //     .getDirectionsByDriving(
    //       /*           this.location.id,
    //       this.$store.state.userCoor.userLat,
    //       this.$store.state.userCoor.userLong */
    //       locationId,
    //       lat,
    //       long
    //     )
    //     .then((response) => {
    //       this.directions = response.data;
    //     });
    // },
    getDirectionsByDriving() {
      this.$store.commit("SET_CURRENT_ID", this.location.id);
      this.$store.dispatch("getDirectionsByDriving");
      this.$store.dispatch("getGeometryByDriving");
    },
    checkIn() {
      this.assignDbAttraction();
      attractionService
        .checkInAtLocation(this.dbAttraction)
        .then((response) => {
          console.log(response.status);
        });
      this.$store.dispatch("getCheckInHistory");
      this.$router.push({ name: "locations" });
    },
  },
  created() {
    /* getBusinessDetails(){
      attractionService.getAttraction(this.location.id).then(
        response => {
          this.locationObj = response.data;
        }
      );
    } */
    /* getDirections() {
      attractionService
        .getDirectionsByDriving(
          this.location.id,
          this.$store.state.userCoor.userLat,
          this.$store.state.userCoor.userLong
        )
        .then(response => {
          this.directions = response.data;
        });
    }, */
  },
};
</script>

<style scoped>
#background {
  background-image: linear-gradient(180deg, #120e0b, #002d56, #005278);
  min-height: 100%;
  height: 60%;
  overflow-y: auto;
}

* >>> #info-location-detail {
  color: white;
  display: grid;
  gap: 1rem;
  grid-template-columns: 3fr min-content min-content;
  grid-template-areas:
    "name name check-in"
    "name name direction"
    "cat cat cat"
    "add add add"
    "schedule schedule schedule"
    "phone phone phone"
    "sm sm sm"
    "image image image"
    "map map map";
  padding: 2rem;
}

#name {
  grid-area: name;
  color: #e2af08;
}

#check-in,
#get-directions {
  font-family: inherit;
  font-weight: 600;
  background-color: #e2af08;
  border: 4px double #cccccc;
  border-radius: 10px;
  text-align: center;
  justify-self: center;
}

#check-in {
  display: block;
  grid-area: check-in;
  justify-self: center;
  align-self: flex-end;
}

#get-directions {
  align-self: flex-start;
  grid-area: direction;
}

#categories {
  margin: 0;
  grid-area: cat;
}

#address {
  margin: 0;
  grid-area: add;
}

#schedule-container {
  grid-area: schedule;
  margin: 0;
}

#schedule > p {
  margin: 0;
}

#phone {
  margin: 0;
  grid-area: phone;
}

#social-media {
  grid-area: sm;
  max-width: 100vw;
  max-height: 100vh;
}

#social-media h3 {
  word-wrap: break-word;
}

#img-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 1rem;
  grid-area: image;
}

#img-container #location-detail-img {
  height: auto;
  width: 100%;
  border-radius: 0.5rem;
}

h2 {
  font-size: inherit;
}

#direction-title {
  text-align: center;
}

#map-direction {
  grid-area: map;
}

#map-img {
  width: 100%;
  height: auto;
  border-radius: 0.5rem;
}
</style>