/*global angular*/

angular.module('battleship', ['ui.router', 'ngMaterial'])
  .config(['$stateProvider', '$urlRouterProvider',
    function ($stateProvider, $urlRouterProvider) {

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
/*global angular*/

angular.module('battleship')
  .service('MatchList', ['$q', '$http',
    function ($q, $http) {

      this.createMatch = function () {
        var deferred = $q.defer();

        $http.get('/api/create')
          .success(function (response) {
            deferred.resolve(response);
          })
          .error(function (error) {
            deferred.reject(error);
          });

        return deferred.promise;
      };

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