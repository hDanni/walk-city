<template>
  <div id="login" class="text-center">
    <div id="grid-container">
      <form class="form-signin" @submit.prevent="login">
        <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
        <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
          Invalid username and password!
        </div>
        <div
          class="alert alert-success"
          role="alert"
          v-if="this.$route.query.registration"
        >
          Thank you for registering, please sign in.
        </div>
        <label for="username" class="sr-only">Username</label>
        <input
          type="text"
          id="username"
          class="form-control"
          v-model="user.username"
          required
          autofocus
        />
        <label for="password" class="sr-only">Password</label>
        <input
          type="password"
          id="password"
          class="form-control"
          v-model="user.password"
          required
        />
        <router-link id="account-link" :to="{ name: 'register' }"
          >Need an account?</router-link
        >
        <button id="button" type="submit">Sign in</button>
      </form>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            // this.$router.push("/");
            // this.$router.push({name: 'home', redirect: {name: 'nearby'}});
            this.$store.dispatch("getCheckInHistory");
            this.$router.push(this.$route.query.redirect || "/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style scoped>
#grid-container {
  display: flex;
  align-items: center;
  justify-content: center;

  height: 100vh;
  background-size: cover;
  background-position: center;
  background-image: url("../assets/Images/nyc-dark-background.jpg");
}

.form-signin {
  display: grid;
  height: 400px;
  width: 80%;
  background-color: rgba(255, 255, 255, 0.5);
  max-width: 400px;
  padding: 2rem;
  border-radius: 5px;
  box-sizing: border-box;
}

h1 {
  text-align: center;
}

label {
  font-weight: 500;
  padding: 10px 0;
}

#button,
#account-link {
  padding: 10px 0;
}

#button {
  background: rgb(247, 94, 83);
  font-family: inherit;
  font-weight: 500;
  border: none;
  border-radius: 0.5rem;
}

input {
  padding: 0.5rem 1rem;
  border-radius: 0.5em;
  border: none;
}

#account-link {
  color: rgb(27, 46, 129);
  text-align: center;
  font-weight: bold;
}
</style>
