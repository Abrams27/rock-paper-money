<template>
  <v-container>
    <v-btn @click="findGame(1)">Zagraj o 1 HRN</v-btn>
    <v-btn @click="findGame(2)">Zagraj o 2 HRN</v-btn>
    <v-btn @click="findGame(5)">Zagraj o 5 HRN</v-btn>
    <v-btn @click="findGame(10)">Zagraj o 10 HRN</v-btn>
    <v-btn @click="findGame(20)">Zagraj o 20 HRN</v-btn>
  </v-container>
<!--  <v-container fluid>-->
<!--    <v-layout>-->
<!--      <v-btn v-if="loggedIn" to="/game">Zagraj</v-btn>-->
<!--    </v-layout>-->
<!--    <v-data-table :headers="headers" :items="gameHistory">-->
<!--      <template v-slot:item="row">-->
<!--        <tr>-->
<!--          <td>{{ row.item.opponentsUsername }}</td>-->
<!--          <td>{{ row.item.stake }}</td>-->
<!--          <td>{{ row.item.userGameResult }}</td>-->
<!--        </tr>-->
<!--      </template>-->
<!--    </v-data-table>-->
<!--  </v-container>-->
</template>
<script>
import KafkaProxyService from "../services/kafka.proxy.service";
// import GameService from "../services/game.service";

export default {
  name: "GameView",
  data() {
    return {
      gameHistory: [],
      balance: null,
      currentGame: null,
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
    // this.fetchData();

  },
  watch: {
    "$route": "fetchData",
  },
  methods: {
    findGame(stake) {
      KafkaProxyService.findGame(this.currentUser.username, stake).then(

      )
      console.log("Szukam gry o " + stake);
    },

    // fetchData() {
    //   if (!this.loggedIn) {
    //     this.$router.push("/login");
    //   } else {
    //     UserService.getGameHistory(this.currentUser).then(
    //         successResponse => {
    //           this.gameHistory = successResponse.data.gameHistory;
    //         },
    //         errorResponse => {
    //           console.log(errorResponse);
    //         },
    //     );
    //   }
    //
    // },
    // onButtonClick(item) {
    //   this.$router.push("/debtor/"+item.debtorUsername);
    // },

  },
};
</script>

<style scoped>

</style>
