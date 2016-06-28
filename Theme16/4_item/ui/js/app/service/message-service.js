angular.module('app').service('MessageService',  function ($http, $rootScope) {


	this.getMessagesCurrentUser = function getMessagesCurrentUser() {
	return $http
        .post('/webservice/messageUser/'+ $rootScope.currentUser.id)  
	}

	this.getMessageList = function getMessageList(message) {
	return $http
        .post('/webservice/messageUser/'+ $rootScope.currentUser.id+'/dialog/'+message.id)  
	}

	this.createMessage = function createMessage(newMessage) {
	return $http
        .post('/webservice/messageUser/'+ newMessage)
	}

	this.deleteMessage = function deleteMessage(message) {
	return $http
        .delete('/webservice/messageUser/'+ message)
	}

	this.amountNewMessage = function amountNewMessage() {
	return $http
        .get('/webservice/messageUser/amountNewMessage' + $rootScope.currentUser)
	}



});
