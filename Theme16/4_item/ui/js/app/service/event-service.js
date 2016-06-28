angular.module('app').service('EventService',  function ($http, $rootScope) {

	this.getEventList = function getEventList(user){
		return $http
        .get('/webservice/events'+ user)       
	}

	this.createEvent = function createEvent(newEvent) {
		return $http
        .put('/webservice/event'+ newEvemt) 
	}

	this.deleteEvent = function deleteEvent(event) {
		return $http
        .delete('/webservice/event'+ event.id) 
	}

});
