 app.controller('TimezoneController', function($scope,  $routeParams, RESTClient, $http, $filter, $location) {
	
    $scope.timezoneId = $routeParams.timezoneId;
    $scope.newTimezone = !$routeParams.timezoneId;     
    
    $scope.possibleHours = [];
    $scope.possibleMinutes = [0, 15, 30, 45];    
    
    $scope.hours = '';
    $scope.minutes = '';    
    
    function init() {
    	 for (i =-12; i<15; i++) {
		    	$scope.possibleHours.push(i);
		    }
		 if ($scope.timezoneId) {
	            RESTClient.loadTimezones().$promise.then(function(timezones) {
	                var timezone = $filter('filter')(timezones, {id: $scope.timezoneId});
	                if (timezone.length){
	                    $scope.timezone = timezone[0];
	                    $scope.newTimezone = false;
	                }
	             
	            });
	        }
	    }
    init();
	
	$scope.timezone = {
	       name: '',
	       nameOfCity: '',
	       gmtOffsetInMinutes: ''
	    };
	
	$scope.addTimezone = function() {
		$scope.timezone.gmtOffsetInMinutes = $scope.hours * 60 + $scope.minutes;
		if ($scope.newTimezone) {
        RESTClient.addTimezone(
        		{}, 
        		$scope.timezone, 
        		function(timezone) {
        			$location.path('timezones');
        		}, 
        		function(error) {
        			if (error && error.message && error.message.indexOf('already') && error.message.indexOf('exists')) {
        				alert("The timezone you are trying to enter already exists");
        			} else {
        				alert("An error has occurred. If the problem persists, contact the system administrator");
        			}
        		}
        	);
		} else {
			$http.put('/timezoneadmin/admin/timezones/'+$scope.timezoneId, $scope.timezone).
	         then(function(response) {
	        	 $location.path('timezones');
	          }, function(error) {
	        	  if (error && error.message && error.message.indexOf('already') && error.message.indexOf('exists')) {
      				alert("The timezone you are trying to enter already exists");
      			} else {
      				alert("An error has occurred. If the problem persists, contact the system administrator");
      			}	          });
		}
    };
});