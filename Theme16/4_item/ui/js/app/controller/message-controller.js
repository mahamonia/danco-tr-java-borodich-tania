angular.module('app').controller('MessageController',
	function($scope, $location, $rootScope, MessageService) {

			$scope.getMessagesCurrentUser = function() {
				MessageService.getMessagesCurrentUser()
				.then(function(res){
				if (!res.data) {
					alert("Server error");
				} else {
					if (!res.data.object) {
						alert(res.data.message);
					} else {
						$scope.messagesCurrentUser = res.data.object;
					}
				}
			})
				.catch(function(res){
				alert("Server error");
				console.log('Error', res.data);
			});
			};

			$scope.selectMessage = function (message){
				MessageService.getMessageList(message)
				.then(function(res){
				if (!res.data) {
					alert("Server error");
				} else {
					if (!res.data.object) {
						alert(res.data.message);
					} else {
						$scope.messagesList = res.data.object;
					}
				}
			})
				.catch(function(res){
				alert("Server error");
				console.log('Error', res.data);
			});
			};

			$scope.newMessage = {
				id:'',
				text:'',
				date: null,
				status:null,
				author:null,
				recipient:null

			}

			$scope.createNewMessage = function createNewMessage(newMessage) {
				MessageService.createNewMessage(newMessage)
				.then(function(res){
				if (!res.data) {
					alert("Server error");
				} else {
					if (!res.data.object) {
						alert(res.data.message);
					} else {
						$scope.messagesCurrentUser = res.data.object;
					}
				}
			})
				.catch(function(res){
				alert("Server error");
				console.log('Error', res.data);
			});
			};

			$scope.deleteMessage = function deleteMessage(message) {
				MessageService.deleteMessage(message)
				.then(function(res){
				if (!res.data) {
					alert("Server error");
				} else {
					if (!res.data.object) {
						alert(res.data.message);
					} else{alert(res.data.object);}
				}
			})
				.catch(function(res){
				alert("Server error");
				console.log('Error', res.data);
			});
			};


		});
