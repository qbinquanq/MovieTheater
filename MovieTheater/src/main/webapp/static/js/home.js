var profile = angular.module('HomePage',[]);
	profile.controller('fill',function($scope,$http){
	$http.post("home/all").then(function(response){
		console.log(response);
		$scope.MOVIES=response.data;

});
});

	profile.controller('autoFill',function($scope,$http){
		$http.post("info/movies").then(function(response){
			console.log(response);
		});
	});