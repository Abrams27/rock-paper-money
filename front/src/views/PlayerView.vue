<template>
  <v-container fluid>
    <v-layout>
      <new-debtor-dialog></new-debtor-dialog>
    </v-layout>
    <v-data-table :headers="headers" :items="gameHistory">
      <template v-slot:item="row">
        <tr>
          <td>{{ row.item.opponentsUsername }}</td>
          <td>{{ row.item.stake }}</td>
          <td>{{ row.item.userGameResult }}</td>
        </tr>
      </template>
    </v-data-table>
  </v-container>
</template>
<script>
import UserService from "../services/user.service";
import NewDebtorDialog from "@/components/NewDebtorDialog";

export default {
  name: "PlayerView",
  components: {
    NewDebtorDialog,
  },
  data() {
    return {
      gameHistory: [],
      balance: null,
      headers: [
        { text: 'Przeciwnik', value: 'Przeciwnik' },
        { text: 'Stawka', value: 'Stawka' },
        { text: 'Rezultat', value: 'Rezultat' },
      ],
    };
  },

  computed: {
    loggedIn() {
      // return true;
      return this.$store.state.auth.status.loggedIn;
    },
    currentUser() {
      return this.$store.state.auth.user;
    },
  },

  created() {
    this.fetchData();

  },
  watch: {
    "$route": "fetchData",
  },
  methods: {
    fetchData() {
      if (!this.loggedIn) {
        this.$router.push("/login");
      } else {
        UserService.getGameHistory(this.currentUser).then(
            successResponse => {
              this.gameHistory = successResponse.data.gameHistory;
            },
            errorResponse => {
              console.log(errorResponse);
            },
        );
      }

    },
    // onButtonClick(item) {
    //   this.$router.push("/debtor/"+item.debtorUsername);
    // },

  },
};
</script>

<style scoped>

</style>
