app.controller('RegistrationModalController', function ($scope, $http, $location, $rootScope, $uibModalInstance, RESTClient) {

    var $ctrl = this;
    $ctrl.jwtUser = {
    	username: '',
    	password: ''
    };
    
    $ctrl.userId = '';

    $ctrl.register = function() {
    	if ($ctrl.jwtUser.username==='' || $ctrl.jwtUser.password==='' 
    		|| $ctrl.jwtUser.username.length < 5 || $ctrl.jwtUser.password < 5 
    		|| $ctrl.jwtUser.username.length > 15 || $ctrl.jwtUser.password > 15 ) {
    		alert('Please enter a valid username and password.')
    		return;
    	}
        RESTClient.register(
        		{}, 
        		$ctrl.jwtUser, 
        		function(registeredUser) {
        			$ctrl.registrationSuccessful=true;
        			$ctrl.duplicateUsername=false;
        			$scope.$apply();
        		}, 
        		function(error) {
        			$ctrl.registrationSuccessful=false;
        			$ctrl.duplicateUsername=true;
        			$scope.$apply();
        		}
        	);
    };
    
    $ctrl.delete = function() {
    	 $http.delete('/timezoneadmin/admin/users/'+$rootScope.selectedUserId).
         then(function(response) {
        	 $rootScope.selectedUserId='';
        	 $location.path('users');
        	 $uibModalInstance.close('');
          });
    };
    
    $ctrl.deleteTimezone = function() {
   	 $http.delete('/timezoneadmin/admin/timezones/'+$rootScope.selectedTimezoneId).
        then(function(response) {
       	 $rootScope.selectedTimezoneId='';
       	 $location.path('timezones');
       	 $uibModalInstance.close('');
         });
   };

    $ctrl.ok = function () {
        $uibModalInstance.close('');
    };

    $ctrl.cancel = function () {
        $uibModalInstance.dismiss('');
    };
});