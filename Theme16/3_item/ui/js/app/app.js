var app = angular.module('app', [ 'ngRoute' ]);

app.config(function($routeProvider) {

	$routeProvider.when('/rubric/id1', {
		controller : 'RubricController',
		templateUrl : 'views/shared/rubrics/currentRubric.html'
	}).when('/rubric/id2', {
		controller : 'RubricController',
		templateUrl : 'views/shared/rubrics/currentRubric.html'
	}).when('/rubric/id3', {
		controller : 'RubricController',
		templateUrl : 'views/shared/rubrics/currentRubric.html'
	}).when('/rubric/id4', {
		controller : 'RubricController',
		templateUrl : 'views/shared/rubrics/currentRubric.html'
	}).when('/rubric/id5', {
		controller : 'RubricController',
		templateUrl : 'views/shared/rubrics/currentRubric.html'
	}).otherwise({
		redirectTo : '/'
	});
});

app.run(function($rootScope, AuthService) {

	$rootScope.$on('$stateChangeStart', function(event, next) {
		event.preventDefault();
	})
});

// $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
