/*global angular*/

angular.module('battleship')
  .controller('MatchListCtrl', ['$scope', 'MatchList',
    function ($scope, MatchList) {
      var numbers = _.range(10);

      var matches = _.map(numbers, function (matchId) {
        var ret = {
          name: 'Match Nr.' + (matchId + 1),
          opponent: 'Andreas',
          id: matchId,
          userTurn: (matchId % 3 === 0)
        };

        return ret;
      });

      $scope.matches = matches;
    }]);