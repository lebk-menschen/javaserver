/*global angular*/

angular.module('battleship', ['ui.router', 'ngMaterial', 'LocalStorageModule'])
  .config(['$stateProvider', '$urlRouterProvider', 'localStorageServiceProvider',
    function ($stateProvider, $urlRouterProvider, localStorageServiceProvider) {

      $urlRouterProvider.otherwise("/login");

      $stateProvider
        .state('matchList', {
          url: '/matches',
          templateUrl: '/tpl/views/matchList.html',
          controller: 'MatchListCtrl'
        })
        .state('match', {
          url: '/match/:matchId',
          templateUrl: '/tpl/views/match.html',
          controller: 'MatchCtrl'
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
  .service('authApi', ['$q', '$http', '$window',
    function ($q, $http) {

      this.login = function (userName, password) {
        var deferred = $q.defer();

        $http.post('/api/login', {
          userName: userName,
          password: password
        })
          .success(function (result) {
            console.log(result);
            // var userInfo = {
            //   accessToken: result.accessToken,
            //   userName: result.userName
            // };

            // $window.sessionStorage.userInfo = JSON.stringify(userInfo);

            deferred.resolve(result);
          })
          .error(function (error) {
            deferred.reject(error);
          });

        return deferred.promise;
      };
    }])
  .factory('authHttpResponseInterceptor', ['$q',
    function ($q) {
      return {
        response: function (res) {
          if (res.status === 401) {
            console.log('Response 401');
          }

          return res || $q.when(res);
        },
        responseError: function (res) {
          if (res.status === 401) {
            console.log('Response Error 401', res);
          }
        }
      };
    }]);
  // .config(['$httpProvider',
  //   function ($httpProvider) {
  //     $httpProvider.interceptors.push('authHttpResponseInterceptor');
  //   }]);
angular.module('battleship')
  .controller('MatchCtrl', ['$scope',
    function ($scope) {

      $scope.game = {
        fields: {
          player: {},
          opponent: {}
        }
      };

      $scope.columns = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10'];
      $scope.rows = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'];

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
/*global angular*/

angular.module('battleship')
  .service('matchApi', ['$q', '$http',
    function ($q, $http) {

    }]);
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
  .service('MatchListService', ['localStorageService', '$http',
    function (localStorageService, $http) {

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
        $http.get('/api/create')
          .success(function (newMatch) {
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