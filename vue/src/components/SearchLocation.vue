<template>
  <div id="loc-comp-background">
      <router-link :to="{name: 'locationDetail', params: {locationId: searchLocation.id} }" >

      <div id="loc-container" :class="{'checked-in': filterCheckIn(searchLocation.id)}">
          <h1 class="loc-name">{{searchLocation.name}}</h1>
          <!-- <h2 class="loc-hours">{{location.days}} {{location.hours}}</h2> -->
          <h2 class="loc-hours">{{searchLocation.location.display_address[0] + ", " + searchLocation.location.display_address[1]}}</h2>
          <img class="loc-img" v-bind:src="searchLocation.image_url" alt="">
          <h3 class="loc-cat">{{searchLocation.categories[0].title}}</h3>
          <p class="loc-desc">{{searchLocation.description}}</p>
    
      </div>
      </router-link>
  </div>
</template>

<script>
export default {
    props: ['searchLocation'],
    methods: {
        filterCheckIn(testId) {
            return this.$store.state.checkedInLocations.some(location => {
                return location.businessId == testId;
            })
        }
    }
}
</script>

<style scoped>
#loc-comp-background{
    margin: 0 1rem;
}

#loc-container {
    background:rgba(29, 29, 29, 0.7);
    border: 1px solid rgb(70, 152, 230);
    color: white;
    display: grid;
    grid-template-columns: min-content 3fr;
    grid-template-areas:
    "img name" 
    "img category"
    "img hours"
    "img desc";
    padding: 1rem;
    margin: 0.5rem 0;
    border-radius: 1rem;
    column-gap: 1rem;
}

.loc-name {
    grid-area: name;
    font-size: 15px;
    justify-self: flex-start;
    margin: 0;
    color: rgb(226, 175, 8); 
}

.loc-hours {
    grid-area: hours;
    font-size: 10px;
    text-align:left;
}

.loc-img {
    grid-area: img;
    /* height: auto; */
    aspect-ratio: 1 / 1;
    min-width: 100px;
    max-width: 100%;
    border-radius: 5px;
    justify-self: center;
}

.loc-cat {
    grid-area: category;
    font-size: 13px;
    justify-self: flex-start;
    margin: .5rem 0 0 0;
}

.loc-desc {
    grid-area: desc;
    font-size: 13px;
    margin: 0;
}

.checked-in {
  border: 1px solid rgb(226, 175, 8) !important;
  background: rgba(226, 175, 8, 0.137) !important;
}
</style>