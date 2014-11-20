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