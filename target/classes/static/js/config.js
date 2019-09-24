app
    .config(['$routeProvider', '$httpProvider', '$resourceProvider', function($routeProvider, $httpProvider, $resourceProvider) {

        $routeProvider
            .when('/home', {templateUrl: 'views/home.html'})
            .when('/users', {templateUrl: 'views/users.html'})
            .when('/timezones', {templateUrl: 'views/timezones.html'})
            .when('/add-user', {templateUrl: 'views/add-user.html'})
            .when('/add-timezone', {templateUrl: 'views/add-timezone.html'})
            .otherwise({ templateUrl: 'views/404.html'});

        $resourceProvider.defaults.actions = {
            get: {method: 'GET'},
            post: {method: 'POST'},
            update: {method: 'PUT'},
            delete: {method: 'DELETE'},
            query: {method: 'GET', isArray: true}
        };

        
    }]);