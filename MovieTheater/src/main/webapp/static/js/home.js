var app = angular.module('HomePage',[]);
	app.controller('fill',function($scope,$http){
	$http.post("home/all").then(function(response){
		console.log(response);
		$scope.MOVIES=response.data;

});
});

	app.controller('autoFill',function($scope,$http){
		$http.post("info/movies").then(function(response){
			console.log(response);
		});
	});