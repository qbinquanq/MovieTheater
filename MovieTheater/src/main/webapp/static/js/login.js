function newLoginService($scope, $http){
	$scope.newLogin={};
	$scope.login = function(newLogin, $event){
		console.log(newLogin);
		console.log("registration="+JSON.stringify($scope.newLogin))
		$http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
		$http.post("login", "account="+JSON.stringify($scope.newLogin)).then(alert("You login account succesfully"));
	}
}