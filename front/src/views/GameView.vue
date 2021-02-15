<template>
  <v-container>
    <div v-if="gameState==='choosingStake'">
      <v-btn @click="findGame(1)">Zagraj o 1 HRN</v-btn>
      <v-btn @click="findGame(2)">Zagraj o 2 HRN</v-btn>
      <v-btn @click="findGame(5)">Zagraj o 5 HRN</v-btn>
      <v-btn @click="findGame(10)">Zagraj o 10 HRN</v-btn>
      <v-btn @click="findGame(20)">Zagraj o 20 HRN</v-btn>
    </div>

    <div v-if="gameState==='waitingForOpponent'">
      Prosimy czekać, trwa wyszukiwanie przeciwnika.
    </div>

    <div v-if="gameState==='choosingSign'">
      <v-btn @click="play('ROCK')"> Kamień</v-btn>
      <v-btn @click="play('PAPER')"> Papier</v-btn>
      <v-btn @click="play('SCISSORS')"> Nożyce</v-btn>
    </div>
    <div v-if="gameState==='waitingForResults'">
      Prosimy czekać na ruch przeciwnika.
    </div>
    <div v-if="gameState==='showingResults'">
      Przeciwnik: {{ gameResults.opponentUsername }}
      Rezultat: {{ gameResults.gameResult }}
    </div>

  </v-container>
</template>
<script>
import KafkaProxyService from "../services/kafka.proxy.service";
import GameService from "../services/game.service";

export default {
  name: "GameView",
  data() {
    return {
      gameHistory: [],
      balance: null,
      currentGame: null,
      gameState: "choosingStake",
      gameStatus: null,
      gameResults: null,
      headers: [
        {text: 'Przeciwnik', value: 'Przeciwnik'},
        {text: 'Stawka', value: 'Stawka'},
        {text: 'Rezultat', value: 'Rezultat'},
      ],
    };
  },

  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
    currentUser() {
      return this.$store.state.auth.user;
    },
  },

  watch: {
    "currentGame": function () {
      if (this.currentGame != null && this.currentGame.gameId != null) {
        this.gameState = "choosingSign";
      }
    },

    "gameStatus": async function () {
      if(this.gameStatus === "ENDED"){
        this.gameResults = (await GameService.getGameResult(this.currentUser, this.currentGame.gameId)).data;
        this.gameState = "showingResults";
      }
    }
  },

  methods: {
    async findGame(stake) {
      this.gameState = "waitingForOpponent";
      await KafkaProxyService.findGame(this.currentUser, stake)
      .then(await this.waitForNewGame)
    },

    async waitForNewGame() {
      while (this.gameState === "waitingForOpponent") {
        this.currentGame = (await GameService.newGame(this.currentUser)).data;
        await new Promise(r => setTimeout(r, 500));
      }
    },

    async waitForResults() {
      while (this.gameState === "waitingForResults"){
        await new Promise(r => setTimeout(r, 500));
        this.gameStatus = (await GameService.getGameStatus(this.currentGame.gameId)).data;
      }
    },

    play(handSign) {
      this.gameState = "waitingForResults"
      GameService.enterGame(this.currentUser, handSign, this.currentGame.gameId).then(
          () => this.waitForResults())
    }

  },
};
</script>

<style scoped>

</style>
