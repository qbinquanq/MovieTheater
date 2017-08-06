function fill($scope, $http){
	$http.post("home/all").then(function(response){
		console.log(response);
		$scope.MOVIES=response.data;
	});
};



