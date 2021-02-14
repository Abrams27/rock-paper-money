<template>
  <v-container fluid>
    <v-layout>
      <v-container>
        <v-row>
          <v-col>
            Użytkownik
          </v-col>
          <v-col>
            {{debtorHistory.questionerUsername}}
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            Dłużnik
          </v-col>
          <v-col>
            {{debtorHistory.debtorUsername}}
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            Bilans
          </v-col>
          <v-col>
            {{debtorHistory.totalAmount}}
          </v-col>
        </v-row>
        <v-row>
          <new-debt-dialog></new-debt-dialog>
        </v-row>
      </v-container>

    </v-layout>
    <v-data-table :headers="headers" :items="debtorHistory.debts">
      <template v-slot:item="row">
        <tr>
          <td>{{ row.item.amount }}</td>
          <td>{{ row.item.date }}</td>
        </tr>
      </template>
    </v-data-table>
  </v-container>
</template>
<script>
import DebtsService from "../services/debts.service";
import NewDebtDialog from "@/components/NewDebtDialog";

export default {
  name: "DebtorView",
  components: {
    NewDebtDialog,
  },
  data() {
    return {
      debtorHistory: {},
      headers: [
        { text: 'Kwota', value: 'Kwota' },
        { text: 'Data', value: 'Data' },
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

  created() {
    this.fetchData();

  },
  watch: {
    // call again the method if the route changes
    "$route": "fetchData",
  },
  methods: {
    fetchData() {
      if (!this.loggedIn) {
        this.$router.push("/login");
      } else {
        DebtsService.getDebtorHistory(this.currentUser, this.$route.params.username).then(
            successResponse => {
              this.debtorHistory = successResponse.data;
            },
            errorResponse => {
              console.log(errorResponse);
            },
        );
      }

    },
    onButtonClick(item) {
      this.$router.push("/login/"+item.username);
    },

  },
};
</script>

<style scoped>

</style>
