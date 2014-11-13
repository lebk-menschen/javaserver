/*global angular*/

angular.module('battleship', ['ui.router'])
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
