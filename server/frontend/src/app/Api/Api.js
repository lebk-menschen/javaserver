/*global angular*/

angular.module('battleship')
  .service('apiService', ['$q', '$http',
    function ($q, $http) {
      var that = this;

      var matches = {};

      this.matchLoading = false;

      this.getMatchDetails = function (playerToken) {
        var match = matches[playerToken];

        if (!match) {
          match = {};
        }

        that.matchLoading = true;

        $http.get('/api/matches')
          .success(function (response) {
            match = response;
          })
          .finally(function () {
            that.matchLoading = false;
          });

        return match;
      };

      this.getNewMatchToken = function () {
        var deferred = $q.defer();


        $http.get('/api/create')
          .success(function (playerToken) {
            deferred.resolve(playerToken);
          });

        return deferred.promise;
      };
    }]);