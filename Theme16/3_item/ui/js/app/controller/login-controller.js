angular.module('app').controller('LoginController', function($scope, $rootScope, AuthService) {

	$scope.user = {
    login: '',
    password: ''
	};
	
	$scope.auth = {
	login :'',
    password:''
	};

  $scope.SignIn = function (user) {
	  AuthService.SignIn(user)
	  
	.then(function (res) {
        this.auth = res.data;
        
       if (user.password === this.auth.password) {
       $rootScope.currentUser=AuthService.getCurrentUser(this.auth)
       		.then(function(res){
       $rootScope.currentUser=res.data;
       Session.create(res.data.id, res.data.login);
       $rootScope.isAuthenticated=true;
        })
        	.catch(function(res){console.log('Error ', res.data)});
        }
          else { console.log('false Authentication')}
       })
       
	.catch(function (res) {
           console.log('Error', res.status, res.data);
        });
   }; 
});