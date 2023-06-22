<template>
  <div class="home">

    <div id="home-grid">
      <div id="title-img">
        <h1 id="title">Explore New York</h1>
        <button class="home-btns" id="title-btn"><router-link id="title-link" :to="{name: 'locations'}">View Locations</router-link></button>
      </div>

      <div id="info-section">
        <p>Explore the hidden gems of your favorite city with Walk New York City! This tourist app is your key to discovering the most exciting attractions in town. 
          Not only can you search and view nearby locations, but you can also earn badges by checking in at various spots across the region. Want to prove your devotion to the city? 
          Earn the ultimate "Excelsior" badge by visiting every single location. 
          Get ready to embark on an adventure like no other!</p>
      </div>

      <!--Discover the most exciting attractions in town-->
      <!--View nearby hidden gems-->
      <!--Earn badges by checking in at various spots-->
      <!--Become the "Defender of the Land" by visiting every location-->

      <h2 id="post-img-title">Here are some featured attractions</h2>

      <div id="post-img-section">
        <div id="museum-img">
          <h3>Museums</h3>
        </div>
        <div id="restaurants-img">
          <h3>Restaurants</h3>
        </div>
        <div id="nightlife-img">
          <h3>Nightlife</h3>
        </div>
        <div id="parks-img">
          <h3>Parks</h3>
        </div>
        <div id="shopping-img">
          <h3>Shopping</h3>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
export default {
  name: "home",
  created() {
    this.$store.dispatch("getCheckInHistory");
  }
};
</script>

<style scoped>
#home-grid {
  display: flex;
  flex-direction: column;
}

#title-img {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  grid-area: title-img;
  height: 350px;
  background-image: url("../assets/Images/homepage-background.png");
  background-size: cover;
  background-position: center;
}

#title {
  font-size: 34px;
  color: white;
  text-align: center;
  text-shadow: 1px 0 20px black;
}

#title-btn {
  background: rgb(226, 175, 8);
  color: black;
  font-family: inherit;
  font-size: 16px;
  font-weight: 500;
  padding: 0.5em 1em;
  border: none;
  border-radius: 0.5em;
}

#title-link {
  color: black;
  text-decoration: none;
}

#info-section {
  box-sizing: border-box;
  grid-area: login;
  /* background-color: white; */
  background: black;
  color: white;
  padding: 1rem 2rem;
  margin: 2rem;
  border-radius: 1rem;
  border: 5px solid white;
}

#post-img-title {
  color: white;
  text-align: center;
}

#post-img-section {
  grid-area: post-img;
  display: grid;
  gap: 5rem;
  grid-template-columns: repeat(auto-fit, 300px);
  justify-content: center;
  margin: 0 3rem;
  padding-bottom: 40px;
}

.info-img {
  display: flex;
  justify-content: center;
  align-items: flex-end;
  background: url('../assets/Images/the-vessel.jpg');
  background-size: cover;
  background-position: center;
  /* width: 200px; */
  height: 200px;
  border-radius: 1rem;
  overflow: hidden;
}

#museum-img{
  background: url('../assets/Images/nyc-museum.webp');
  display: flex;
  justify-content: center;
  align-items: flex-end;
  background-size: cover;
  background-position: center;
  /* width: 200px; */
  height: 200px;
  border-radius: 1rem;
  overflow: hidden;
}

#museum-img h3{
  background: rgba(0, 0, 0, 0.616);
  color: white;
  width: 100%;
  text-align: center;
  margin: 0;
  padding: 1rem;
}

#restaurants-img{
  background: url('../assets/Images/nyc-restaurant.jpg');
  display: flex;
  justify-content: center;
  align-items: flex-end;
  background-size: cover;
  background-position: center;
  /* width: 200px; */
  height: 200px;
  border-radius: 1rem;
  overflow: hidden;
}

#restaurants-img h3{
  background: rgba(0, 0, 0, 0.616);
  color: white;
  width: 100%;
  text-align: center;
  margin: 0;
  padding: 1rem;
}

#nightlife-img{
  background: url('../assets/Images/nyc-nightlife.jpg');
  display: flex;
  justify-content: center;
  align-items: flex-end;
  background-size: cover;
  background-position: center;
  /* width: 200px; */
  height: 200px;
  border-radius: 1rem;
  overflow: hidden;
}

#nightlife-img h3{
  background: rgba(0, 0, 0, 0.616);
  color: white;
  width: 100%;
  text-align: center;
  margin: 0;
  padding: 1rem;
}

#parks-img{
  background: url('../assets/Images/parks-nyc.webp');
  display: flex;
  justify-content: center;
  align-items: flex-end;
  background-size: cover;
  background-position: center;
  /* width: 200px; */
  height: 200px;
  border-radius: 1rem;
  overflow: hidden;
}

#parks-img h3{
  background: rgba(0, 0, 0, 0.616);
  color: white;
  width: 100%;
  text-align: center;
  margin: 0;
  padding: 1rem;
}

#shopping-img{
  background: url('../assets/Images/nyc-shopping.jpg');
  display: flex;
  justify-content: center;
  align-items: flex-end;
  background-size: cover;
  background-position: center;
  /* width: 200px; */
  height: 200px;
  border-radius: 1rem;
  overflow: hidden;
}

#shopping-img h3{
  background: rgba(0, 0, 0, 0.616);
  color: white;
  width: 100%;
  text-align: center;
  margin: 0;
  padding: 1rem;
}

.info-img h3 {
  background: rgba(0, 0, 0, 0.616);
  color: white;
  width: 100%;
  text-align: center;
  margin: 0;
  padding: 1rem;
}




/* #info-section, #post-img-section, #post-img-title{
  background-color: #fe6357;
  background-image: linear-gradient(0deg, #fe6357 0%, #c6a49e 50%, #485769 100%);
} */
/* p {
  border: cornflowerblue solid 3px;
} */
</style>
