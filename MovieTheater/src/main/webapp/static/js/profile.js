var profile = angular.module('HomePage');
profile.controller('NavigationPanel', function ($scope, $http){
	'use strict';
	$scope.LogOut = function(){
		//$http.defaults.headers.get["Content-Type"] = "application/x-www-form-urlencoded";
		$http.get('logout').then(function(response){
			location.reload();
		})
	}
})
