var app = angular.module('timezoneadmin', [ 'ngRoute', 'ngResource',
		'ui.bootstrap', 'ngCookies' ]);

app.run(function($rootScope, $cookies, $location) {
	var token = $cookies.get('jwt');
	var role = $cookies.get('role');

	if (token) {
		$location.path(role === 'ADMIN' ? 'users' : 'timezones');
		$rootScope.admin_users = role!=='REGULAR';
		$rootScope.admin_timezones=role!=='ADMIN';
	} else {
		$location.path('home');
		$rootScope.admin_users = false;
		$rootScope.admin_timezones=false;
	}

	$rootScope.$on('$routeChangeStart', function(event, next, current) {
		token = $cookies.get('jwt');
		if (!token && next && next.$$route
				&& next.$$route.originalPath !== "/login") {
			$location.path('home');
		}
	});
});

app.factory('tokenExpiredInterceptor', function($q) {
	return {
		'responseError' : function(rejection) {
			if (rejection && rejection.data && rejection.data.message && rejection.data.message.indexOf('JWT') > 0) {
				alert("Please login again");
			} else {
				return $q.reject(rejection);
			}
		}
	};
});

app.config([ '$httpProvider', function($httpProvider) {
	$httpProvider.interceptors.push('tokenExpiredInterceptor');
} ]);

app.factory('httpRequestInterceptor', function($cookies) {
	return {
		request : function(config) {
			
			var jwt = 'Token '+$cookies.get('jwt');
			
			config.headers = {
				'Authorisation' : jwt,
				'Content-type' : "application/json"
			}
			return config;
		}
	};
});

app.config(function($httpProvider) {
	$httpProvider.interceptors.push('httpRequestInterceptor');
});

app.filter('datum', function($filter) {
	return function(ulaz) {
		return $filter('date')(ulaz, 'yyyy-MM-dd hh:mm:ss');
	};
});