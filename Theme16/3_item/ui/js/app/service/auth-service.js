angular.module('app').service('AuthService',  function ($http, Session, $rootScope) {


this.isAuthenticated = function isAuthenticated(){
  return !!Session.userLogin;
}
this.SignIn = function SignIn(user){

  return $http
        .post('/webservice/login/'+ user.login)       
}

this.logout = function logout() {
      return
         $rootScope.currentUser = null;
       }

this.getCurrentUser = function getCurrentUser(auth) {
   return $http
      .post('/webservice/user/'+ auth.login)     

}
});
















  /*
	 * return {
	 * 
	 * isAuthenticated: function () { return !!Session.userLogin; },
	 * 
	 * SignIn: function (user) { return $http .post('/webservice/login/'+
	 * user.login) .then(function (res) { this.auth = res.data; console.log('2
	 * this.auth ',this.auth); if (user.password === this.auth.password) {
	 * console.log('3 true Authentication'); // $rootScope.currentUser=res.data;
	 *  // console.log('4 $rootScope.currentUser', $rootScope.currentUser);
	 * this.getCurrentUser(this.auth); console.log('4 current user'); } else {
	 * console.log('33 false Authentication')} }) .catch(function (res) {
	 * console.log('Error', res.status, res.data); }); },
	 * 
	 * getCurrentUser: function(user){ return $http .post('/webservice/user/'+
	 * user.login) .then(function(res){ $rootScope.currentUser=res.data;
	 * console.log('из запрова currentUser',$rootScope.currentUser);
	 * Session.create(res.data.id, res.data.login);
	 * $rootScope.isAuthenticated=true;
	 *  })
	 *  },
	 * 
	 * 
	 * };}])
	 */
