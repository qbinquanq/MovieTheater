function newRegistration($scope, $http){
	$scope.newRegis={};
	$scope.submitRegistration = function(newRegis, $event){
		console.log(newRegis);
		console.log("registration="+JSON.stringify($scope.newRegis))
		$http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
		$http.post("register", "account="+JSON.stringify($scope.newRegis)).then(alert("You created account succesfully")).then(location.reload());
	}
}