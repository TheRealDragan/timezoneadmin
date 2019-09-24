 app.controller('UserController', function($scope,  $routeParams, RESTClient, $http, $filter, $location) {
	
    $scope.userId = $routeParams.userId;
    $scope.newUser = !$routeParams.userId;
	$scope.roles= ['REGULAR', 'ADMIN', 'SUPER_ADMIN']; 	
	
	$scope.user = {
	       username: '',
	       password: ''
	    };
	
	$scope.addUser = function() {
		if ($scope.newUser) {
        RESTClient.addUser(
        		{}, 
        		$scope.user, 
        		function(user) {
        			$location.path('users');
        		}, 
        		function(error) {
        			if (error && error.message && error.message.indexOf('already') && error.message.indexOf('exists')) {
        				alert("The user already exists");
        			} else {
        				alert("An error has occurred. If the problem persists, contact the system administrator");
        			}
        		}
        	);
		} else {
			$http.put('/timezoneadmin/admin/users/'+$scope.userId, $scope.user).
	         then(function(response) {
	        	 $location.path('users');
	          }, function(error) {
      			if (error && error.message && error.message.indexOf('already') && error.message.indexOf('exists')) {
    				alert("The username you are trying to set is taken");
    			} else {
    				alert("An error has occurred. If the problem persists, contact the system administrator");
    			}
    		});
		}
    };
	 
	 function init() {
		 if ($scope.userId) {
	            RESTClient.loadUsers().$promise.then(function(users) {
	                var user = $filter('filter')(users, {id: $scope.userId});
	                if (user.length){
	                    $scope.user = user[0];
	                    $scope.newUser = false;
	                }	             
	            }, function(error) {
	  				alert("An error has occurred. If the problem persists, contact the system administrator");
		          });
	        }
	    }
	    init();

});