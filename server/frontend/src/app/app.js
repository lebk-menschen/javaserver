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
