var app =angular.module("User",[]);

app.controller("userFil",function($scope,$http){
	$http.post("transactions/all").then(function(response){
		console.log(response);
		$scope.TRANSACTIONS=response.data;
	});
});