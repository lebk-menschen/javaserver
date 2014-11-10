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