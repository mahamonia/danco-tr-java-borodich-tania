angular.module('app').service('Session', function() {
	this.create = function(sessionId, userLogin) {
		this.id = sessionId;
		this.userLogin = userLogin;
	};
	this.destroy = function() {
		this.id = null;
		this.userLogin = null;
	};
	return this;
})