/*global angular*/

angular.module('battleship', ['ui.router', 'ngMaterial', 'LocalStorageModule'])
  .config(['$stateProvider', '$urlRouterProvider', 'localStorageServiceProvider',
    function ($stateProvider, $urlRouterProvider, localStorageServiceProvider) {

      $urlRouterProvider.otherwise("/");

      $stateProvider
        .state('default', {
          url: '/',
          views: {
            sidebar: {
              templateUrl:  '/tpl/matchList.html',
              controller:   'MatchListCtrl'
            },
            content: {
              templateUrl:  '/tpl/match.html',
              controller:   'MatchCtrl'
            }
          },
        });

      localStorageServiceProvider
        .setPrefix('bs');
    }])

  .run(['$rootScope', '$stateParams',
    function ($rootScope, $stateParams) {
      $rootScope.$on('$stateChangeSuccess', function () {
        console.log('state changed', $stateParams);

        $rootScope.stateParams = $stateParams;
      });
    }]);

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
/*global angular,_*/

angular.module('battleship')
  .controller('MatchCtrl', ['$scope',
    function ($scope) {

      $scope.game = {
        fields: {
          player: {},
          opponent: {}
        }
      };

      $scope.columns  = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10'];
      $scope.rows     = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'];

      var getField = function () {
        var space = {
          isShip: false,
          isHit: false
        };

        var field = _.object(_.map($scope.rows, function (row) {
          var columns = _.object(_.map($scope.columns, function (column) {
            var copy = _.clone(space);

            copy.row = row;
            copy.col = column;

            return [column, copy];
          }));

          return [row, columns];
        }));

        return field;
      };

      var init = function () {
        $scope.game.fields.player = getField();
        $scope.game.fields.opponent = getField();
      };

      init();
    }
  ]);

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
angular.module('battleship')
  .directive('field', function () {
    return {
      restrict: 'EA',
      replace: true,
      templateUrl: '/tpl/partials/field.html'
    };
  });
angular.module('battleship')
  .directive('fieldItem', function () {
    return {
      restrict: 'EA',
      replace: true,
      templateUrl: '/tpl/partials/fieldItem.html',
      scope: {
        field: '=fieldData'
      }
    };
  });
angular.module('battleship')
  .service('MatchList', [function () {

  }]);