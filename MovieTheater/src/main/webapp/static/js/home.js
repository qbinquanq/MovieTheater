function fill($scope, $http){
	$http.post("home/all").success(function(response){
		console.log(response);
		$scope.movies=response.data
	});
};

