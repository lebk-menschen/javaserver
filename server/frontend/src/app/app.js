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
