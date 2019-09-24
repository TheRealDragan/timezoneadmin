app.controller('HomeController', function ($scope, $rootScope, $cookies, $location, $uibModal,  RESTClient) {
	
	$scope.username = '';
	$scope.password='';
	$scope.admintimezones='false';
	$scope.adminusers='false';
	
	$scope.login = function() {
 		var credentials =  {
	            username: $scope.username,
	            password: $scope.password
	        };
		 RESTClient.login(
	        		{}, 
	        		credentials, 
	        		function(response) {
	        			 $cookies.put("jwt", response.token);
	        			 $cookies.put("role", response.role);
	        			 
	        			 switch(response.role) {
	                     case 'SUPER_ADMIN': {
	                    	 $location.path('users');
	                    	 $rootScope.admin_users = true;
	                 		 $rootScope.admin_timezones=true;
	                         break;
	                     }
	                     case 'REGULAR': {
	                    	 $location.path('timezones');
	                    	 $rootScope.admin_users = false;
	                 		 $rootScope.admin_timezones=true;
	                         break;
	                     }
	                     case 'ADMIN': {
	                    	 $location.path('users');
	                    	 $rootScope.admin_users = true;
	                 		 $rootScope.admin_timezones=false;
	                         break;
	                     }
	                     default: {
	                         alert('Unknown user role');
	                     }
	        		} 
	        		},
	        		function(error) {	  
	        			if (error && error.message.indexOf('baddata')>0) {
	        				alert("The provided credentials are incorrect");
	        			} else {
	        				alert("An error has occurred. If the problem persists, contact the system administrator")
	        			}
	        			
	        		}
	        	);
	}
	
	$scope.openRegistrationModal = function() {
		var modalInstance = $uibModal.open({
            ariaLabelledBy: 'modal-title',
            ariaDescribedBy: 'modal-body',
            templateUrl: 'views/registration.html',
            controller: 'RegistrationModalController',
            controllerAs: '$ctrl',
            size: 'lg',
        });

        modalInstance.result.then(function () {
        }, function () {
        });
	}
});