'use strict';
angular.module('app').controller('ApplicationController', function($scope, $rootScope, $location, $http, 
				AuthService) {

			$rootScope.isAuthenticated = AuthService.isAuthenticated();

			if (sessionStorage.currentUser != undefined ) {
				$rootScope.currentUser=AuthService.getCurrentUser();			
			} else {$location.path('/login');}

		});