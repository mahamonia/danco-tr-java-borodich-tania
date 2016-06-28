angular.module('app').service('FriendsService',  function ($http, $rootScope) {


	this.amountNewFriends = function amountNewFriends(){
		return $http
        .post('/webservice/friend/amountNewFriends'+ $rootScope.currentUser.id)       
	}

	this.getFriendsCurrentUser = function getFriendsCurrentUser() {
		return $http
        .post('/webservice/friendsUser/'+ $rootScope.currentUser.id)  
	}

	this.addNewFriend = function addNewFriend(friend) {
		return $http
        .post('/webservice/friend/add'+ $rootScope.currentUser.id+'/'+friend.id) 
	}

	this.deleteFriend = function deleteFriend(friendUser) {
		return $http
        .delete('/webservice/friend/delete'+ $rootScope.currentUser.id+'/'+friendUser.id) 
	}

});
