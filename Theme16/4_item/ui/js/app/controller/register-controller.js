'use strict';

app.controller('RegisterController', function ($scope, $http, AuthService, CONSTANT) {

		$scope.newUser = {
				status:'',
				profile:'',
				authen:'',
				date: ''
			};
		
        $scope.register = function (newUser) {
         
        AuthService.register(newUser)
                .then(function (res) {
                	if(!res.data) 
                	{alert(CONSTANT.MESSAGE_SERVER);}
                	else{
                		if(!res.data.object){
                			alert(res.data.message);
                		}else{
                			alert("Registration successful");
                			$location.path('/login');
                		}
                		}
                }
                )
                .catch(function (res) {
                	alert(CONSTANT.MESSAGE_SERVER);
                	console.log('Error', res.status, res.data);
                });
        }; 
    });