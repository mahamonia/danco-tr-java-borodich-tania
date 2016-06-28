angular.module('app').service('ApplicationService', function($http) {

	this.getRubric = function(id) {
		return $http.get('/webservice/rubric/' + id);
	};

	this.getRubrics = function() {
		return $http.get('/webservice/rubrics');
	};

});
