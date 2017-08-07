function NavigationPanel($scope, $http){
	'use strict';
	$scope.LogOut = function(){
		$http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
		$http.get('logout').then(function(response){
			location.reload();
		})
	}
}