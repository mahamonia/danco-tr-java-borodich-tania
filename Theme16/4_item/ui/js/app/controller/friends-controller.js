'use strict';

angular.module('app').controller('FriendsController',
	function($scope, $location, $rootScope, UserService, MessageService, FriendsService) {

		$scope.addNewFriend = function(friend) {
				FriendsService.addNewFriend(friend)
				.then(function(res){
				if (!res.data) {
					alert("Server error");
				} else {
						alert(res.data.message);
					}
				})
				.catch(function(res){
				alert("Server error");
				console.log('Error', res.data);
				});
				};

		$scope.deleteFriend = function(friend) {
				FriendsService.deleteFriend(friend)
				.then(function(res){
				if (!res.data) {
					alert("Server error");
				} else {
						alert(res.data.message);
					}
				})
				.catch(function(res){
				alert("Server error");
				console.log('Error', res.data);
			});
			};

		});
