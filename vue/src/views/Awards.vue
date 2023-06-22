<template>
  <div id="background">
    <div id="title-container">
      <h1>Awards</h1>
    </div>

    <div
      v-for="badge in $store.state.badgeList"
      v-bind:key="badge.id"
      class="badges"
    >
      <h2 class="badge-title" v-bind:class="[badge.achieved?'earned-award':'unearned-award']">{{ badge.name }}</h2>
      <img
        class="badge-img"
        v-bind:class="[badge.achieved?'earned-award':'unearned-award']"
        v-bind:src="badge.image"
        :title="badge.description"
        alt="badge-icon"
      />
      <p class="badge-desc" v-bind:class="[badge.achieved?'earned-award':'unearned-award']">{{ badge.description }}</p>
      <!-- <button @click="museumBadge()">test</button> -->
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    

      // let a = this.$store.state.checkedInLocations.filter((location) => {
      //   if (location.featured) {
      //     return location.category == "Museum";
      //   }
      // });
      // if (a.length >= 5){
      //   this.$store.commit('SET_ACHIEVED', 'Museum');
      // }
    },
    created() {
      this.$store.dispatch('museumBadge');
      this.$store.dispatch('parkBadge');
      this.$store.dispatch('nightlifeBadge');
      this.$store.dispatch('shoppingBadge');
      this.$store.dispatch('restaurantsBadge');
      this.$store.dispatch('excelsiorBadge');
  },
};
</script>

<style scoped>
.unearned-award {
  opacity: 0.25;
}

.earned-award {
  opacity: 1;
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

.badges {
  display: grid;
  grid-template-columns: min-content 1fr;
  grid-template-areas: 
  "img title"
  "img desc";
  column-gap: 1rem;
  margin: 1rem;
  padding: .5rem;
  background: rgba(29, 29, 29, 0.7);
  border: 1px solid rgb(70, 152, 230);
  border-radius: 1rem;
}

h1 {
  color: white;
  text-align: center;
}

.badge-title {
  color: rgb(226, 175, 8);
  grid-area: title;
}

.badge-desc {
  color: #e2af08;
  font-weight: bold;
  grid-area: desc;
}

#background {
  background-image: linear-gradient(180deg, #120e0b, #002d56, #005278);
  min-height: 100%;
  height: 60%;
  overflow-y: auto;
}

#img-container {
  display: flex;
  justify-content: center;
}

.badge-img {
  height: 100px;
  width: 100px;
  /* margin-bottom: 5rem; */
  align-self: center;
  grid-area: img;
}

#about-us-desc {
  padding: 1rem;
}
::-webkit-scrollbar {
  width: 0;
}
</style>