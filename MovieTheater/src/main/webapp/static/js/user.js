var user = angular.module("User",[]);	
user.controller('userInfo',function($scope,$http){
		$http.post("transactions/all").then(function(response){
			console.log(response);
			$scope.TRANSACTIONS=response.data;
		});
	});