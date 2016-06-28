angular.module('app').controller('RubricController',function($scope, $location, $rootScope, RubricService) {

			$scope.currentRubric = {
				id : '',
				name : ''

			};
			
			$scope.themes = {};

		$scope.filteredThemss = [],
  		$scope.currentPage = 1,
  		$scope.numPerPage = 4,
  		$scope.maxSize = 3;

  		$scope.$watch("currentPage + numPerPage", function() {
    	var begin = (($scope.currentPage - 1) * $scope.numPerPage),
    	end = begin + $scope.numPerPage;

    	$scope.filteredThemss = $scope.themes.slice(begin, end);
  		});


			RubricService.getrubrics()
			.then(function(res){
				if (!res.data) {
					alert("Server error");
				} else {
					if (!res.data.object) {
						alert(res.data.message);
					} else {
						$scope.items = res.data.object;
					}
				}
			})
			.catch(function(res){
				alert("Server error");
				console.log('Error', res.data);
			});


			$scope.selectMenu = function(elem) {
			$scope.activeMenu = $scope.activeMenu == elem.item.name ? '': elem.item.name;
			$scope.currentRubric = elem.item;
			RubricService.getThemeCurrentRubric(elem.item)
			.then(function(res){
				if (!res.data) {
					alert("Server error");
				} else {
					if (!res.data.object) {
						alert(res.data.message);
					} else {
						console.log("thems", res.data.object);
						$scope.themes = res.data.object;
					}
				}
			})
			.catch(function(res){
				alert("Server error");
				console.log('Error', res.data);
			});
		
};

			$scope.selectTheme = function(elem, $event) {
			$scope.activeTheme = $scope.activeTheme == elem.theme.name ? '' : elem.theme.name;
			};

			
			$scope.newTheme = {
				name : '',
				date : new Date(),
				author : $rootScope.currentUser,
				rubric : null
			};

			$scope.createTheme = function(newTheme) {
				$scope.newTheme.rubric = $rootScope.currentRubric;
				RubricService.createTheme($scope.newTheme).then(function(res){
				if (!res.data) {
					alert("Server error");
				} else {
					if (!res.data.object) {
						alert(res.data.message);
					} else {
						alert(res.data.message);
					}
				}
			})
			.catch(function(res){
				alert("Server error");
				console.log('Error', res.data);
			});
};
			$scope.delete = function (theme) {
			RubricService.delete(theme)
				.then(function(res){
				if (!res.data) {
					alert("Server error");
				} else {
					if (!res.data.object) {
						alert(res.data.message);
					} else {
						alert(res.data.message);
					}
				}
			})
			.catch(function(res){
				alert("Server error");
				console.log('Error', res.data);
			});
		};
	});