/*global angular,_*/

angular.module('battleship')
  .controller('MatchListCtrl', ['$scope', 'MatchListService', 'localStorageService',
    function ($scope, MatchListService, localStorageService) {

      $scope.getMatches = function () {
        return MatchListService.getMatches();
      };

      $scope.createMatch = function () {
        MatchListService.createMatch();
      };

      localStorageService.bind($scope, 'matches');
    }]);