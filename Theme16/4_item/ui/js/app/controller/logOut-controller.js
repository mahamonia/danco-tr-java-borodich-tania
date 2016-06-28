angular.module('app').controller('LogOutController', function($scope, $rootScope, $location, AuthService, CONSTANT) {

   $scope.logOut =  function logOut() {
                
    AuthService.logOut()
    .then(function (res) {
    if(!res.data) 
      {alert(CONSTANT.MESSAGE_SERVER);}
    else{
      if(!res.data.object){
        alert(res.data.message);
      }else{
       $rootScope.currentUser=null;
       $rootScope.isAuthenticated=false;
       $location.path('/login');
        }
    }
       })
       
       .catch(function (res) {
    	   alert(CONSTANT.MESSAGE_SERVER);
           console.log('Error', res.status, res.data);
        });
   }; 
});

