'use strict';
angular.module('app').controller('ApplicationController', function($scope, $rootScope, $location, $http, ApplicationService,
				AuthService) {

			$rootScope.isAuthenticated = AuthService.isAuthenticated();

		});