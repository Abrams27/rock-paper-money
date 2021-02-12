<template>
  <v-app-bar color="blue-grey darken-3" dark app>
    <v-btn v-if="loggedIn" to="/debtors">Debtors</v-btn>
    <v-spacer></v-spacer>
    <v-btn v-if="!loggedIn" to="/login">Login</v-btn>
    <v-btn v-if="loggedIn" @click="handleLogout">Logout</v-btn>
    <v-btn v-if="!loggedIn" to="/register">Register</v-btn>
  </v-app-bar>
</template>

<script>
export default {
  name: "AppNavigation",
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
  },
  methods: {
    handleLogout() {
      this.$store.dispatch("auth/logout", this.user).then(
          () => {
            this.$router.push("/login");
          },
          error => {
            console.log(error);
          },
      );
    },
  },
};
</script>

<style scoped>
</style>
