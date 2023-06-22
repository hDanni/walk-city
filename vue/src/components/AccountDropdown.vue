<template>
    <div id="account-dropdown-container">
      <router-link class="dropdown-links" v-bind:to="{name: 'account'}">Account</router-link>
      <router-link class="dropdown-links" v-bind:to="{ name: 'logout' }">Logout</router-link>
    </div>
</template>

<script>
export default {};
</script>

<style scoped>
.dropdown-links {
    color: white;
    text-decoration: none;
}
</style>