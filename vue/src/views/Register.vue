<template>
  <div id="register" class="text-center">

    <div id="grid-container">

    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
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
      <label for="confirmPassword" class="sr-only">Confirm Password</label>
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        v-model="user.confirmPassword"
        required
      />
      <router-link id="account-link" :to="{ name: 'login' }">Have an account?</router-link>
      <button id="button" class="btn btn-lg btn-primary btn-block" type="submit">
        Create Account
      </button>
    </form>

    </div>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
#grid-container{
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100vh;
  background-image: url('../assets/Images/nyc-dark-background.jpg');
  background-size: cover;
  background-position: center;
}

.form-register{
  display: grid;
  width: 80%;
  background-color: rgba(255, 255, 255, 0.5);
  max-width: 400px;
  padding: 2rem;
  border-radius: 5px;
  box-sizing: border-box;
}

label{
  font-weight: 500;
  padding: 10px 0;
}

input{
  padding: 0.5rem 1rem;
  border-radius: .5em;
  border: none;
}

#button, #account-link{
  padding: 10px 0;
}

#button {
  background: rgb(247, 94, 83);
  font-family: inherit;
  font-weight: 500;
  border: none;
  border-radius: .5rem;
}

#confirmPassword{
  padding: 0.5rem 1rem;
}

h1{
  justify-self: center;
  text-align: center;
}

#account-link{
  color: rgb(27, 46, 129);
  text-align: center;
  font-weight: bold;
}




</style>
