angular.module('app').service('UserService', function($http, $rootScope) {

	this.changeProfile = function changeProfile(user) {
		return $http.put('/webservice/user/update' + user)
	}

	this.getUserProfile = function getUserProfile(user) {
		return $http.post('/webservice/userProfile/' + user.login)
	}

	this.updateUser = function updateUser(user) {
		return $http.put('/webservice/user/update' + user)
	}

	this.getListCildren = function getListCildren() {
		return $http.get('/webservice/children/' + $rootScope.currentUser)
	}

	this.updateAdress = function updateAdress(adress) {
		return $http.post('/webservice/adress/' + adress)
	}

	this.updateChildren = function updateChildren(childrenList) {
		return $http.post('/webservice/children/' + childrenList)
	}

});
