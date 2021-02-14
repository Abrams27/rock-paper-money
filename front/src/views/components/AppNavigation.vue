<template>
    <v-app-bar color="blue-grey darken-3" dark app>
      <v-btn to="/debtors">Debtors6</v-btn>
<!--      <v-btn v-if="!loggedIn" to="/debtors">Debtors</v-btn>-->
      <v-spacer></v-spacer>
      <v-btn to="/login">Login</v-btn>
      <v-btn v-if="!loggedIn" to="/loginf">Login1</v-btn>
      <v-btn @click="handleLogout">Logout2</v-btn>
      <v-btn v-if="!loggedIn" @click="handleLogout">Logout3</v-btn>
      <v-btn to="/register">Register4</v-btn>
      <v-btn v-if="!loggedIn" to="/register">Register5</v-btn>
    </v-app-bar>
</template>

<script>
export default {
  name: "AppNavigation",
  computed: {
    loggedIn() {
      return true;
      // return this.$store.state.auth.status.loggedIn;
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
