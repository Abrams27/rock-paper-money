<template>
  <v-container fill-height>
    <v-layout align-center justify-center>
      <v-flex xs12 sm8 md4>
        <v-card class="elevation-12">
          <v-toolbar color="blue-grey">
            <v-toolbar-title>Login</v-toolbar-title>
          </v-toolbar>
          <v-card-text>
            <v-form ref="form" v-model="valid" lazy-validation>
              <v-text-field
                  name="username"
                  label="Username"
                  type="username"
                  v-model="user.username"
                  :rules="usernameRules"
                  required
              >
              </v-text-field>
              <v-text-field
                  name="password"
                  label="Password"
                  type="password"
                  required
                  v-model="user.password"
                  :rules="passwordRules"
              >
              </v-text-field>
            </v-form>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                color="blue-grey"
                :disabled="!valid && !loading"
                @click="submit"
            >Login
            </v-btn
            >
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>
<script>
import User from "@/models/user";

export default {
  name: "LoginView",
  data() {
    return {
      valid: false,
      user: new User("", ""),
      loading: false,
      usernameRules: [
        v => !!v || "Username is required",
      ],
      passwordRules: [
        v => !!v || "Password is required",
      ],
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
  },
  created() {
    if (this.loggedIn) {
      this.$router.push("/player");
    }
  },
  methods: {
    async submit() {
      this.loading = true;
      if (this.$refs.form.validate()) {
        await this.$store.dispatch("auth/login", this.user).then(
            () => {
              this.$router.push("/player");
            },
            error => {
              this.loading = false;
              console.log(error);
            },
        );
      } else {
        this.loading = false;
      }
    },
  },
};
</script>

<style scoped></style>
