angular.module('app').service('ThemeService', function($http) {

	this.createMessage = function createTheme(newMessage) {
		return $http.post('/webservice/theme/message/', newMessage)
	};

	this.getMessageCurrentTheme = 	function getMessageCurrentTheme(currentTheme) {
		return $http.post('/webservice/theme/'+ currentTheme.id+'/message')
	};		

	 this.deleteMessage = function deleteMessage(message) {
	 	return $http.delete('/webservice/theme/message/', message)
	 };

});