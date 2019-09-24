app.controller('UsersController', function($scope, $rootScope, $location, $uibModal, RESTClient) {
	
	 $scope.users = [];	 
	 
	 function init() {
	    	 RESTClient.loadUsers(
		        		function(response) {
		        			$scope.users = response;
		        		},
		        		function(error) {	        
		        		}
		        	);
	    }
	    init();  
    
    $scope.openUserDetails = function(id) {
    	$location.url("add-user?userId="+id);
    }
    
    $scope.openUserDeletionModal = function(id) {
    	$rootScope.selectedUserId=id;
		var modalInstance = $uibModal.open({
            ariaLabelledBy: 'modal-title',
            ariaDescribedBy: 'modal-body',
            templateUrl: 'views/deletion-modal.html',
            controller: 'RegistrationModalController',
            controllerAs: '$ctrl',
            size: 'lg',
        });

        modalInstance.result.then(function () { init()
        }, function () {
        });
	}
});