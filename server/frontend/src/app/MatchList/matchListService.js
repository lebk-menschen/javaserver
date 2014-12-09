/*global angular*/

angular.module('battleship')
  .service('MatchListService', ['localStorageService', 'apiService',
    function (localStorageService, apiService) {

      var that = this;

      this.matches = [];


      var getMatches = function () {
        return localStorageService.get('matches');
      };

      var updateMatches = function () {
        that.matches = getMatches();
      };


      this.addMatch = function (newMatch) {
        var tmpMatches = getMatches();
        tmpMatches.push(newMatch);
        localStorageService.set('matches', tmpMatches);

        updateMatches();
      };

      this.createMatch = function () {
        apiService.getNewMatchToken()
          .then(function (newMatch) {
            that.addMatch(newMatch);
          });
      };


      function init() {
        var tmpMatches = localStorageService.get('matches');
        if (tmpMatches === null || tmpMatches.length === 0) {
          localStorageService.set('matches', []);
        }

        updateMatches();
      }

      init();

    }]);