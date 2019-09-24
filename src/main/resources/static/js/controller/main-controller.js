app.controller('MainController', function($scope, $rootScope, $cookies, $location) {

    $scope.nijeLogin = false;

    $rootScope.$on('$routeChangeStart', function(event, next, current) {
        if (!next) {
            return;
        }

        if (next.$$route && next.$$route.originalPath !== "/login") {
            $scope.nijeLogin = true;
            return;
        }

        $scope.nijeLogin = false;
    });

    $scope.logout = function() {
        $cookies.remove('jwt');
        $cookies.remove('role');
        $rootScope.admin_users = false;
		$rootScope.admin_timezones=false;
        $location.path('home');

    }

});