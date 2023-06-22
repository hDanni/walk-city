<template>
  <div id="app">
    <!-- <div id="app-container-grid"> -->

    <div id="nav">
      <div id="logo-container">
        <img
          id="logo-img"
          src="./assets/Images/walk-nyc-logo.png"
          alt="Walk New York City Logo"
        />
      </div>

      <div id="nav-links-container">
        <router-link class="links" v-bind:to="{ name: 'home' }"
          >Home</router-link
        >&nbsp;|&nbsp;
        <!-- <router-link
        @click="wipeAwards()"
          class="links"
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token"
          >Logout</router-link
        > -->
        <a
        @click="wipeAwards()"
          class="links"
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token"
          >Logout</a
        >
        <router-link
          class="links"
          v-bind:to="{ name: 'login' }"
          v-if="!$store.state.token"
          >Log In</router-link
        >
        <section class="p-menu1">
          <nav id="navbar" class="navigation" role="navigation">
            <input id="toggle1" type="checkbox" />
            <label class="hamburger1" for="toggle1">
              <div class="top"></div>
              <div class="meat"></div>
              <div class="bottom"></div>
            </label>

            <nav class="menu1" id="account-dropdown">
              <a
                class="links"
                @click="checkedInlocations()"
                v-bind:to="{ name: 'history' }"
                >History</a
              >
              <router-link
                class="links"
                @click="getUserAwards()"
                v-bind:to="{ name: 'awards' }"
                >Awards</router-link
              >
              <router-link class="links" v-bind:to="{ name: 'aboutus' }"
                >About Us</router-link
              >
              <router-link class="links" v-bind:to="{ name: 'faq' }"
                >FAQ</router-link
              >
            </nav>
          </nav>
        </section>

        <!-- <select href="#" id="account-icon" @click.prevent="accountActive = !accountActive" @blur="accountActive = false">
          <option value="">Account</option>
          <option @click="console.log('logout')" value="">Logout</option>
        </select> -->
        <!-- <img src="./assets/Icons/account-icon.png" alt=""/> -->
        <!-- <account-dropdown id="account-dropdown" v-if="accountActive" /> -->
      </div>
    </div>

    <div id="search-container">
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
    </div>

    <router-view />

    <!-- </div> -->
  </div>
</template>

<script>
// import AccountDropdown from "./components/AccountDropdown.vue";
/* import attractionService from "./services/AttractionService"; */

export default {
  components: {
    // AccountDropdown,
  },
  data() {
    return {
      accountActive: false,
      searchTerm: "",
    };
  },
  methods: {
    searchLocations() {
      // this.$store.dispatch("searchByTerm", {searchTerm: this.searchTerm})
      /*         let userCoor = this.$store.state.userCoor;
        this.searchTerm = this.$store.state.searchTerm;
        attractionService.getAttractionBySearch(this.searchTerm, userCoor.userLat, userCoor.userLong)
        .then(response => {
            let result = response.data;
            this.$store.commit('STORE_LOCATION_SEARCH', result.businesses);
        }); */

      /* TODO upon refresh searchTerm in store is not persistent */
      this.$store.commit("SET_SEARCH_TERM", this.searchTerm);
      this.$router.push({
        name: "search",
        params: { searchTerm: this.searchTerm },
      });
      this.$store.dispatch("searchLocations");

      this.$store.dispatch("getCheckInHistory");
    },
    checkedInlocations() {
      // return this.$store.state.checkedInlocations;
      if (this.$route.name != "history") {
        if (this.$store.state.token) {
          this.$store.dispatch("getCheckInHistory");
          this.$router.push({ name: "history" });
        } else {
          this.$router.push({ name: "login" });
        }
      }
    },
    getUserAwards() {
      if (this.$store.state.token) {
        this.$store.dispatch("getCheckInHistory");
        this.$router.push({ name: "history" });
      } else {
        this.$router.push({ name: "login" });
      }
    },
    wipeAwards(){
      this.$router.push({name : 'logout'})
      this.$store.commit("RESET_CHECKEDIN_LOGOUT");
      this.$store.commit("RESET_BADGELIST_ON_LOGOUT");
    }
  },
};
</script>

<style>
/* navigation bar */
#nav {
  background-color: rgb(0, 0, 0);
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem 2rem;
}

#logo-container {
  display: flex;
  align-items: center;
  border: red;
}

#logo-img {
  width: 110px;
}

#nav-links-container {
  display: flex;
  align-items: center;
}

#nav-links-container {
  justify-content: flex-end;
  flex-grow: 1;
}

#account-icon {
  background: url("./assets/Icons/account-icon.png") no-repeat center;
  width: 50px;
  height: 50px;
  border: none;
  outline: none;
}

#account-icon img {
  display: block;
}

#account-dropdown {
  position: absolute;
  display: none;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 1rem;
  padding: 0 0.5rem;
  /*   width: 100px;
  height: 100px; */
  background: black;
  /* border: 2px solid white; */
  border-radius: 0.5rem;
  /*   top: 100px; */
}

#account-dropdown {
  display: flex;
}

.links {
  color: rgb(226, 175, 8);
  text-decoration: none;
  font-weight: bold;
  margin-left: 10px;
}

.links:hover {
  text-decoration: underline;
  color: rgb(247, 94, 83);
}
/* TEST */
/* CITE: https://codepen.io/ngochuynh/pen/yKrvVZ */

.p-menu1 {
  height: 100%;
}

/* Hamburger */

.hamburger1 {
  height: 40px;
  margin: 10px;
  display: -ms-grid;
  display: grid;
  grid-template-rows: repeat(3, 1fr);
  justify-items: center;
  z-index: 120;
}

.hamburger1 div {
  background-color: rgb(226, 175, 8);
  position: relative;
  width: 40px;
  height: 5px;
  margin-top: 7px;
  -webkit-transition: all 0.2s ease-in-out;
  transition: all 0.2s ease-in-out;
}

#toggle1 {
  display: none;
}

#toggle1:checked + .hamburger1 .top {
  -webkit-transform: rotate(-45deg);
  transform: rotate(-45deg);
  margin-top: 22.5px;
}

#toggle1:checked + .hamburger1 .meat {
  -webkit-transform: rotate(45deg);
  transform: rotate(45deg);
  margin-top: -5px;
}

#toggle1:checked + .hamburger1 .bottom {
  -webkit-transform: scale(0);
  transform: scale(0);
}

#toggle1:checked ~ .menu1 {
  height: 200px;
}

/* Menu */
.menu1 {
  width: 100%;
  background-color: #02173f;
  margin: 0;
  display: -ms-grid;
  display: grid;
  grid-template-rows: 1fr repeat(4, 0.5fr);
  grid-row-gap: 25px;
  padding: 0;
  list-style: none;
  clear: both;
  width: auto;
  text-align: center;
  height: 0px;
  overflow: hidden;
  transition: height 0.4s ease;
  z-index: 120;
  -webkit-transition: all 0.3s ease;
  transition: all 0.3s ease;
}

.menu1 a:first-child {
  margin-top: 40px;
}

.menu1 a:last-child {
  margin-bottom: 40px;
}

.link1 {
  width: 100%;
  margin: 0;
  padding: 10px 0;
  font: 700 20px "Oswald", sans-serif;
}

.link1:hover {
  background-color: #fff;
  color: rgb(61, 61, 61);
  -webkit-transition: all 0.3s ease;
  transition: all 0.3s ease;
}

#search-container {
  display: flex;
  align-items: center;
  padding: 0 1rem;
}

#search {
  display: block;
  width: 90%;
  margin: 0.5rem auto;
  border: none;
  height: 30px;
  border-radius: 3px;
  font-family: "Montserrat", sans-serif;
}

.bi-geo-alt-fill {
  color: rgb(226, 175, 8);
}
</style>
