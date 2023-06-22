<template>
  <div id="main">
     <location-detail v-if="location" :location="location"></location-detail>
  </div>
</template>

<script>
import attractionService from '../services/AttractionService'
import LocationDetail from '../components/LocationDetail.vue'

export default {
    components: {
      LocationDetail
    },
    created(){

      let businessId = this.$route.params.locationId;
      console.log(businessId)
      attractionService.getAttraction(businessId).then(response => {
            this.location = response.data;
       })
       console.log(this.location)
    },
    data(){
      return {location: ""}
    }
}
</script>

<style scoped>


</style>