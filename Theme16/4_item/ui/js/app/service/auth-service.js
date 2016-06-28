angular.module('app').service('AuthService', function($http, $rootScope) {

	this.isAuthenticated = function isAuthenticated() {
		return !!sessionStorage.currentUser;
	}

	this.SignIn = function SignIn(user) {
		return $http.post('/webservice/login/' + user.id)
	}

	this.logOut = function logOut() {
		return $http.post('/webservice/logOut/' + $rootScope.currentUser.id)
	}

	this.getCurrentUser = function getCurrentUser() {
		console.log('2 get User - sessionStorage - ', sessionStorage);
		var userStorageData = sessionStorage.currentUser;
		if (userStorageData) {
			return JSON.parse(sessionStorage.currentUser);
		}
	}

	this.getUser = function getUser(user) {
		return $http.post('/webservice/user/' + user.id)
	}

	this.setCredentials = function setCredentials(data) {
		console.log('set cred - sessionStorage ', sessionStorage);
		sessionStorage.currentUser = JSON.stringify(data);
	}

	this.register = function register(newUser) {
		return $http.post('/webservice/user/register', newUser)
	}

});