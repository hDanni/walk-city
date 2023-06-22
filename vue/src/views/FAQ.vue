<template>
  <div id="background">
    <div id="title-container">
      <h1>Frequently Asked Questions</h1>
    </div>

    <ol>
      <li>What is Walk New York City, and how does it work?</li>
      <p>Walk New York City is an app used to explore the city.</p>

      <li>What are the features of Walk New York City app?</li>
      <p>
        Features include viewing places you previously visited, getting
        directions, and earning awards.
      </p>
      <li>What can we expect from Walk New York City in the future?</li>
      <p>Upcoming features include recommending locations to be added</p>

      <li>How can I use Walk New York City app to explore the city?</li>
      <p>You can use it by creating an account and start visiting locations.</p>

      <li>Can I earn rewards or badges by using Walk New York City app?</li>
      <p>
        Visit a location, click "check in" and start earning badges. Once you
        get a certain amount, you will get an award.
      </p>

      <li>What is the "Excelsior" badge, and how can I earn it?</li>
      <p>
        The Excelsior badge is earned through visiting and checking into all our
        featured locations for New York City
      </p>
      <li>
        Is Walk New York City app available for free download, or is there a fee
        to use it?
      </li>
      <p>
        It is not available for download yet but it is free, you just need to
        make an account.
      </p>

      <li>
        Does Walk New York City app provide additional information or resources
        about the attractions and locations it features?
      </li>
      <p>This is a feature we are hoping to implement in the nearby future!</p>
      <li>
        Can I customize my Walk New York City app experience to fit my
        preferences and interests?
      </li>
      <p>Ask our devs.</p>
      <li>
        What other cities or regions does Walk New York City app cover, if any?
      </li>
      <p>For the time being, just New York City.</p>
    </ol>
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

ol {
  padding: 3rem;
}

li {
  color: #e2af08;
  font-weight: bold;
}

p {
  color: white;
}

#background {
  background-image: linear-gradient(180deg, #120e0b, #002d56, #005278);
  min-height: 100%;
  height: 60%;
  overflow-y: auto;
}
::-webkit-scrollbar {
  width: 0;
}
</style>