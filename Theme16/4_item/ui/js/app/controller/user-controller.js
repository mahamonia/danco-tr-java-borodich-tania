'use strict';

angular.module('app').controller('UserController',
	function($scope, $location, $rootScope, UserService, MessageService, FriendsService, EventService) {

var user = $rootScope.currentUser;

		UserService.getUserProfile(user)
			.then(function(res){
				if (!res.data) {
					alert("Server error");
				} else {
					if (!res.data.object) {
						alert(res.data.message);
					} else {
						$rootScope.currentUser = res.data.object;
						$location.path('/profile');
					}
				}
			})
			.catch(function(res){
				alert("Server error");
				console.log('Error', res.data);
			});

			$scope.changeProfile = function(user, adress, childrenList) {
				UserService.updateAdress(adress);
				UserService.updateChildren(children);
				$scope.user.profile.adress = adress;
				$scope.user.profile.childrenList = childrenList;
				UserService.changeProfile(user)
				.then(function(res){
					if (!res.data) {
						alert("Server error");
					} else {
						if (!res.data.object) {
							alert(res.data.message);
						} else {
							$rootScope.currentUser = res.data.object;
						}
					}
				}
				)
				.catch(function(res){
				alert("Server error");
				console.log('Error', res.data);
				});
		
				};


			$scope.amountNewMessage = function() {
				MessageService.amountNewMessage().then(function(res){
				if (!res.data) {
					alert("Server error");
				} else {
					if (!res.data.object) {
						alert(res.data.message);
					} else {
						$rootScope.amountNewMessage = res.data.object;
					}
				}
			})
			.catch(function(res){
				alert("Server error");
				console.log('Error', res.data);
			});
			};

			$scope.amountNewFriends = function() {
				FriendService.amountNewFriends().then(function(res){
				if (!res.data) {
					alert("Server error");
				} else {
					if (!res.data.object) {
						alert(res.data.message);
					} else {
						$rootScope.amountNewFriends = res.data.object;
					}
				}
			})
			.catch(function(res){
				alert("Server error");
				console.log('Error', res.data);
			});
			};

			$scope.getEventList = function(user) {
				
			EventService.getUserEvents(user)
			.then(function(res){
					if (!res.data) {
						alert("Server error");
					} else {
						if (!res.data.object) {
							alert(res.data.message);
						} else {
							$scope.eventList = res.data.object;
						}
					}
				}
				)
			.catch(function(res){
				alert("Server error");
				console.log('Error', res.data);
			});
		};

			$scope.createEvent = function (newEvent) {
			EventService.createEvent(newEvent)
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

			$scope.deleteEvent = function (event) {
			EventService.deleteEvent(event)
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
		}

			$scope.getListChildren = function() {
				
			UserService.getListChildren()
				.then(function(res){
				if (!res.data) {
					alert("Server error");
				} else {
					if (!res.data.object) {
						alert(res.data.message);
					} else {
						$scope.childrenList = res.data.object;
					}
				}
			})
			.catch(function(res){
				alert("Server error");
				console.log('Error', res.data);
			});
		}
		});
