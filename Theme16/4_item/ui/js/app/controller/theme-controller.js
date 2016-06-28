'use strict';

angular.module('app').controller('ThemeController',
	function($scope, $location, $rootScope, ThemeService) {

		/*pagination*/
		$scope.messages=[],
		$scope.filteredMessages = [],
  		$scope.currentPage = 1,
  		$scope.numPerPage = 10,
  		$scope.maxSize = 5;

  		$scope.$watch("currentPage + numPerPage", function() {
    	var begin = (($scope.currentPage - 1) * $scope.numPerPage),
    	end = begin + $scope.numPerPage;

    	$scope.filteredMessages = $scope.messages.slice(begin, end);
  		});



			ThemeService.getMessageCurrentTheme()
			.then(function(res){
				if (!res.data) {
					alert("Server error");
				} else {
					if (!res.data.object) {
						alert(res.data.message);
					} else {
						$scope.messages = res.data.object;
					}
				}
			})
			.catch(function(res){
				alert("Server error");
				console.log('Error', res.data);
			});


			$scope.newMessage = {
				text: '',
				date : new Date(),
				author : $rootScope.currentUser,
				rubric : null,
				theme : null
			};

			$scope.createMessage = function(newMessage) {

			ThemeService.createMessage(newMessage)
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
			});
};

			$scope.deleteMessage = function(message) {

			ThemeService.deleteMessage(message)
			.then(function(res){
				if (!res.data) {
					alert("Server error");
				} else {
					if (!res.data.object) {
						alert(res.data.message);
					} else {
						alert(res.data.object);
					}
				}
			})
			.catch(function(res){
				alert("Server error");
			});
};

		});
