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