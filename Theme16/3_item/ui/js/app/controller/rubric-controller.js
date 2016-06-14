angular.module('app').controller('RubricController',function($scope, $location, $rootScope, ApplicationService,
				RubricService) {

			ApplicationService.getrubrics().success(function(data) {
				$scope.items = data;
			});

			$scope.$watch(function() {
				$location.path();
			}, function() {
				activateMenuItem();
			}, function() {
				activateTheme();
			});

			$scope.$on('$routeChangeSuccess', function() {
				activateMenuItem();
				activateTheme();
			});
			$rootScope.currentRubric = {
				id : '',
				name : ''

			};

			$scope.selectMenu = function(elem) {
				$scope.activeMenu = $scope.activeMenu == elem.item.name ? '': elem.item.name;
				$rootScope.currentRubric = elem.item;
				RubricService.getThemeCurrentRubric(elem.item)
				.success(function(data) {
							$rootScope.themes = data;
						})
				.error(function(data, status) {
					console.log('Error', status, data);

				});
				RubricService.getThemeRubricCurrentUser($rootScope.currentUser,	elem.item)
				.success(function(data) {
					$rootScope.themesCurrentUser = data;
				});
			};
			function activateMenuItem() {
				var currentPath = $location.path();
			};

			$scope.selectTheme = function(elem, $event) {
				$scope.activeTheme = $scope.activeTheme == elem.theme.name ? '' : elem.theme.name;
			};

			function activateTheme() {
				var currentPath = $location.path();
			};

			$scope.newTheme = {
				name : '',
				date : new Date(),
				author : $rootScope.currentUser,
				rubric : null
			};

			$scope.createTheme = function(newTheme) {
				$scope.newTheme.rubric = $rootScope.currentRubric;
				RubricService.createTheme($scope.newTheme).then(
						function(response) {
							return response.data;
						})
			};
		});
