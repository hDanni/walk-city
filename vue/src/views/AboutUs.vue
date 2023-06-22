<template>
  <div id="background">
    <div id="title-container">
      <h1>About Us</h1>
    </div>

    <div id="about-us-desc">
      <p>
        We are a team of passionate individuals who love to explore and discover
        the hidden gems of cities. Our mission is to make it easy and fun for
        people to experience and appreciate all the exciting attractions and
        locations that a city has to offer. We created Walk New York City app to
        provide a user-friendly and interactive platform for tourists and locals
        alike to navigate the city and uncover its hidden treasures. With our
        app, you can not only find nearby locations, but also earn badges and
        rewards for visiting different spots across the city. We are dedicated
        to enhancing your city adventure and helping you become a New Yorker.
        Join us on this journey of discovery and exploration!
      </p>
    </div>

    <div id="img-container">
      <img src="../assets/Images/nyc-aboutUs-img.jpg" alt="" />
    </div>
  </div>
</template>

<script>
export default {};
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

p {
  color: #e2af08;
  font-weight: bold;
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

img {
  height: 300px;
  width: 300px;
  margin-bottom: 5rem;
}

#about-us-desc {
  padding: 1rem;
}
</style>