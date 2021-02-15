<template>
  <v-container>
    <div v-if="!isGamePrepared">
      <v-btn @click="findGame(1)">Zagraj o 1 HRN</v-btn>

      <v-btn @click="findGame(2)">Zagraj o 2 HRN</v-btn>
      <v-btn @click="findGame(5)">Zagraj o 5 HRN</v-btn>
      <v-btn @click="findGame(10)">Zagraj o 10 HRN</v-btn>
      <v-btn @click="findGame(20)">Zagraj o 20 HRN</v-btn>
    </div>
    <div v-else>
      <v-btn @click="play('ROCK')"> Kamień</v-btn>
      <v-btn @click="play('PAPER')"> Papier</v-btn>
      <v-btn @click="play('SCISSORS')"> Nożyce</v-btn>
    </div>
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
// import KafkaProxyService from "../services/kafka.proxy.service";
import GameService from "../services/game.service";

export default {
  name: "GameView",
  data() {
    return {
      gameHistory: [],
      balance: null,
      currentGame: null,
      isGamePrepared: false,
      headers: [
        {text: 'Przeciwnik', value: 'Przeciwnik'},
        {text: 'Stawka', value: 'Stawka'},
        {text: 'Rezultat', value: 'Rezultat'},
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
    "currentGame": function () {
      this.isGamePrepared = this.currentGame!=null && this.currentGame.gameId != null;
      console.log("aktualna")
      console.log(this.currentGame)
      console.log(this.isGamePrepared);
    },
  },
  methods: {

    redirectToPlayer() {
      this.$router.push("/player");
    },

    async findGame(stake) {
      console.log(stake);
      await this.waitForNewGame();

      // KafkaProxyService.findGame(this.currentUser, stake)
      // .then(await this.waitForNewGame)
    },

    async waitForNewGame() {
      while (!this.isGamePrepared) {
        this.currentGame = (await GameService.newGame(this.currentUser)).data;
        console.log(this.currentGame);
        await new Promise(r => setTimeout(r, 500));
      }
    },

    play(handSign) {
      console.log(this.currentGame)
      GameService.enterGame(this.currentUser, handSign, this.currentGame.gameId).then(
          () => this.redirectToPlayer)
      this.currentGame = null;
    }

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
