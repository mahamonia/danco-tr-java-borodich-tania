angular.module('app').controller('LoginController', function($scope, $rootScope, AuthService, UserService, CONSTANT) {

	$scope.user = { //form
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
    if(!res.data) 
      {alert(CONSTANT.MESSAGE_SERVER);}
    else{
      if(!res.data.object){
        alert(res.data.message);
      }else{
       this.auth = res.data.object;
       if (user.password !== this.auth.password) {
        console.log($scope.auth);
        alert(CONSTANT.MESSAGE_ERROR)}
        else{
          console.log('auth -true');         
       $rootScope.currentUser=AuthService.getUser(user);
       AuthService.setCredentials($rootScope.currentUser);
       
       $rootScope.isAuthenticated=true;
        }
      }
    }
       })
	.catch(function (res) {
	alert(CONSTANT.MESSAGE_SERVER);
	console.log('Error', res.status, res.data);
        });
   }; 
});