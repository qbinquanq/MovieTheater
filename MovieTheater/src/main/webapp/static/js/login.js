function newLoginService($scope, $http){
	$scope.newLogin={};
	$scope.login = function(newLogin, $event){
		console.log(newLogin);
		console.log("registration="+JSON.stringify($scope.newLogin))
		$http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
		$http.post("loginthrough", "login="+JSON.stringify($scope.newLogin)).success(function (data, status, headers, config) {
            //$scope.PostDataResponse = data;
		  alert("You login account succesfully");
		  location.reload();
        })
		
	}
}