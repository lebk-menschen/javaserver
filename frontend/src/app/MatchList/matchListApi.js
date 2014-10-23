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