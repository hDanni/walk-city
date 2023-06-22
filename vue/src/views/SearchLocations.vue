<template>
  <div id="background">
    <div>
      <search-location
        v-for="location in $store.state.locationSearch"
        v-bind:key="location.id"
        :searchLocation="location"
      ></search-location>
    </div>
  </div>
</template>

<script>
import SearchLocation from "../components/SearchLocation.vue";

export default {
  components: {
    SearchLocation,
  },
};
</script>

<style>
#background{
  background-image: linear-gradient(180deg, #120e0b, #002d56, #005278);
  min-height: 100%;
  height: 60%;
  overflow-y: auto;
  margin: 0.5rem 0;
}
</style>