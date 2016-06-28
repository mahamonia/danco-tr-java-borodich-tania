var app = angular.module('app', [ 'ngRoute' , 'ui.bootstrap']);

app.constant('CONSTANT', {
	BASE_URL : 'http://localhost:8080/roditeli/#/',
	MESSAGE_SERVER : 'Server error, please try later',
	MESSAGE_ERROR : 'Invalid passvord'

});

app.config(function($routeProvider, $httpProvider) {

	$routeProvider.when('/login', {
		controller : 'LoginController',
		templateUrl : 'views/login.html',
	}).when('/register', {
		controller : 'RegisterController',
		templateUrl : 'views/register.html',
	}).when('/rubric/:id', {
		controller : 'RubricController',
		templateUrl : 'views/rubrics/currentRubric.html'
	}).when('/adress', {
		controller : 'MenuController',
		templateUrl : 'views/menu/adress.html'
	}).when('/message', {
		controller : 'MessageController',
		templateUrl : 'views/menu/message.html'
	}).when('/friends', {
		controller : 'FriendsController',
		templateUrl : 'views/menu/friends.html'
	}).when('/profile', {
		controller : 'UserController',
		templateUrl : 'views/menu/profile.html'
	}).when('/events', {
		controller : 'UserController',
		templateUrl : 'views/menu/events.html'
	}).when('/changeProfile', {
		controller : 'UserController',
		templateUrl : 'views/menu/changeProfile.html'
	}).when('/search', {
		controller : 'SearchController',
		templateUrl : 'views/menu/search.html'
	}).otherwise({
		redirectTo : '/'
	});

	$httpProvider.defaults.withCredentials = true;
    $httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];

});

app.run(function($rootScope, AuthService) {

	$rootScope.$on('$stateChangeStart', function(event, next) {
		event.preventDefault();
	})
});

