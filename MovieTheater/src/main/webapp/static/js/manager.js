var manager = angular.module('Manager', []);
manager.controller('NavigationPanel', function ($scope, $http){
	'use strict';
	$scope.HomePage=function(){
		$http.get('home').then(function(response){
			console.log('home');
			console.log(response.data)
			location.href = "home";
		})
	};
	
	
$scope.LogOut=function(){
	$http.get('logout').then(function(response){
		console.log('logout');
		console.log(response.data)
		location.reload();
	})
  }
});
