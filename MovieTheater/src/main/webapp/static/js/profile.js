var profile = angular.module('HomePage');
profile.controller('NavigationPanel', function ($scope, $http){
	'use strict';
	$scope.LogOut = function(){
		//$http.defaults.headers.get["Content-Type"] = "application/x-www-form-urlencoded";
		$http.get('logout').then(function(response){
			location.reload();
		})
	};
	$scope.MainPage=function(){
		$http.get('mainpage').then(function(response){
			console.log("mainpage");
			console.log(response.data)
			location.href = response.data;
		})
	}
});

/*profile.controller('NavigationPanel', function ($scope, $http){
	'use strict';
	$scope.MainPage=function(){
		#http.get('mainpage').then(function(response){
			location.reload();
		})
	}
})*/