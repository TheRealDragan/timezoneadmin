app.controller('TimezonesController', function($scope, $rootScope,$uibModal, $routeParams,
		RESTClient, $http, $location) {

	$scope.timezones = [];
	$scope.lastFetchedMilis ='';
	$scope.timezoneName='';
	$scope.fetchedServertime='';
	$scope.filteredTimezones=[];
	function init() {
		RESTClient.loadTimezones(function(response) {
			$scope.timezones = response;
			$scope.filteredTimezones = response;
			for (var i = 0; i < $scope.timezones.length; i++) {
				var timezone = $scope.timezones[i];
				$scope.timezones[i].readableOffset ='';
				var hours = Math.floor(timezone.gmtOffsetInMinutes/60); 
				timezone.readableOffset = hours + 'H';
				var minutes = timezone.gmtOffsetInMinutes - hours * 60;
				if (minutes >0) {
					timezone.readableOffset += ' '+minutes+'M';
				}
			}
			if (!$scope.fetchedServertime) {
				$scope.loadServerGmtMilis();
			}
		}, function(error) {
		});
	}
	init();
	
	$scope.findAllTimezones = function() {
		init();
	}
	
	$scope.openTimezoneDetails = function(id) {
    	$location.url("add-timezone?timezoneId="+id);
    }
	
	$scope.findTimezone = function (timezoneName) {
		$scope.filteredTimezones=[];
		for (var i = 0; i < $scope.timezones.length; i++) {
			if ($scope.timezones[i].name.toUpperCase()===timezoneName.toUpperCase()) {
				$scope.filteredTimezones.push($scope.timezones[i]);
			}
		}		
	}

	$scope.loadServerGmtMilis = function() {
		{
			RESTClient.currentGmtMilis(function(response) {
				$scope.lastFetchedMilis = response[0];
				for (var i = 0; i < $scope.timezones.length; i++) {
					$scope.timezones[i].currentTime = new Date($scope.lastFetchedMilis
							+ $scope.timezones[i].gmtOffsetInMinutes * 60000)
							.toLocaleTimeString();
				}
				$scope.startAdjustingTime();
				$scope.fetchedServertime = true;
			}, function(error) {
			});
		}
	}

	$scope.startAdjustingTime = function() {
		setInterval(adjustTimeTimer, 1000);
	}

	function adjustTimeTimer() {
		if ($scope.lastFetchedMilis && $scope.timezones && $scope.timezones.length) {
			$scope.lastFetchedMilis+=1000;
			for (var i = 0; i < $scope.timezones.length; i++) {
				$scope.timezones[i].currentTime = new Date($scope.lastFetchedMilis
				+ $scope.timezones[i].gmtOffsetInMinutes * 60000)
				.toLocaleTimeString();
			}
		}	
		$scope.$apply();
	}
	
	 $scope.openTimezoneDeletionModal = function(id) {
	    	$rootScope.selectedTimezoneId=id;
			var modalInstance = $uibModal.open({
	            ariaLabelledBy: 'modal-title',
	            ariaDescribedBy: 'modal-body',
	            templateUrl: 'views/timezone-deletion-modal.html',
	            controller: 'RegistrationModalController',
	            controllerAs: '$ctrl',
	            size: 'lg',
	        });

	        modalInstance.result.then(function () { init()
	        }, function () {
	        });
		}

});