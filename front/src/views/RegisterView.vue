<template>
  <v-container fill-height>
    <v-layout align-center justify-center>
      <v-flex xs12 sm8 md4>
        <v-card class="elevation-12">
          <v-toolbar color="blue-grey">
            <v-toolbar-title>Zarejestruj</v-toolbar-title>
          </v-toolbar>
          <v-card-text>
            <v-form ref="form" v-model="valid" lazy-validation>
              <v-text-field
                  name="username"
                  label="Nazwa użytkownika"
                  type="username"
                  v-model="user.username"
                  :rules="usernameRules"
                  required
              >
              </v-text-field>
              <v-text-field
                  name="password"
                  label="Hasło"
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
                :disabled="!valid"
                @click="submit"
            >Zarejestruj
            </v-btn
            >
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>
<script>
import User from '../models/user.js'

export default {
  name: "RegisterView",
  data() {
    return {
      valid: false,
      user: new User("", ""),
      usernameRules: [
        v => !!v || "Username is required",
      ],
      passwordRules: [
        v => !!v || "Password is required",
      ],
    };
  },
  methods: {
    submit() {
      if (this.$refs.form.validate()) {
        this.$store.dispatch("auth/register", this.user).then(
            () => {
              this.$router.push("/login");
            },
            error => {
              console.log(error);
            },
        );
      }
    },
  },
};
</script>

<style scoped></style>
