angular.module('app').service('ApplicationService', function($http) {

	this.getRubric = function(id) {
		return $http.get('/webservice/rubric/' + id);
	};

	this.getrubrics = function() {
		return $http.get('/webservice/rubrics');
	};

});
