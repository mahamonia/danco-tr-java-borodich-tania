angular
		.module('app')
		.service('RubricService', function($http, Session, $rootScope) {

			this.getThemeCurrentRubric = function getThemeCurrentRubric(rubric) {
						return $http.get('/webservice/themes/' + rubric.id)
					};
			this.getThemeRubricCurrentUser = function getThemeRubricCurrentUser(currentUser, rubric) {

						return $http.get('/webservice/user/themes/' + rubric.id + '/'+ currentUser.id)
					};

			this.createTheme = function createTheme(newTheme) {
						return $http.post('/webservice/theme/', newTheme)
					};
				});
