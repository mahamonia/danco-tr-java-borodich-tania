angular.module('app').controller('MenuController', function($scope, $location) {
	
	// temp
	$scope.items = [ 
		{id : 1, name : 'For parent', href:'rubricForParent'},
		{id : 2, name : 'Wating for baby', href:''},
		{id : 3, name : 'Kindergarten', href:''},
		{id : 4, name : 'School', href:''},
		{id : 5, name : 'For children', href:''}
 ];
	
	$scope.$watch(function () {
	    $location.path();
	  });
	
	$scope.activeMenu = function (elem) {
	    $scope.activeMenu == elem.item.name ? '' : elem.item.name;
	  };
	
});
