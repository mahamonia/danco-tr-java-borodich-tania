angular
		.module('app')
		.service('RubricService', function($http, $rootScope) {

	this.getRubric = function(id) {
		return $http.get('/webservice/rubric/' + id);
	};

	this.getRubrics = function() {
		return $http.get('/webservice/rubrics');
	};

	this.getThemeCurrentRubric = function getThemeCurrentRubric(rubric) {
		return $http.get('/webservice/themes/' + rubric.id)
	};

	this.getThemeRubricCurrentUser = function getThemeRubricCurrentUser(currentUser, rubric) {
		return $http.get('/webservice/user/themes/' + rubric.id + '/'+ currentUser.id)
	};

	this.createTheme = function createTheme(newTheme) {
		return $http.post('/webservice/theme/', newTheme)
	};

	this.deleteTheme = function deleteTheme(theme) {
		return $http.delete('/webservice/theme/', theme)
	};

		});
