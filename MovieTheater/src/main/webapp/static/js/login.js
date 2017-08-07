var items = angular.module('movietheater',[]);
items.controller('newLoginService', function($scope, $http){
	"use strict";
	$scope.newLogin={};
	$scope.login = function(newLogin, $event){
		console.log(newLogin);
		console.log("registration="+JSON.stringify($scope.newLogin))
		$http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
		$http.post("loginthrough", "login="+JSON.stringify($scope.newLogin)).then(function onSuccess(response){
			console.log("successPost");
			//console.log(response.data.success);
			console.log(response.data == 'OK');
			console.log(response.data == 'BAD_REQUEST');
			if(response.data == 'OK'){
				alert("You login successfully.");
				location.reload()
			}
			else {
				alert("Your username or password is not correct.")
			}
			//console.log(response.data);
			//console.log(response.status);
			//console.log(response.statusText);
		}, function onError(response){
			console.log("failed");
		});
	}
})
		//success(function (data, status, headers, config) {
            //$scope.PostDataResponse = data;
			//console.log(status);
			//console.log(data);
			//console.log(headers);
			//console.log(config);
		  /*if(data == "HttpStatus.OK"){
			  console.log(data);
			alert(data);
			alert(status);
			alert("you login successfully");
		  	location.href = "home.html";
		  }
		  else{
			  console.log(data);
			 alert(data);
			 alert(status)
		     alert("Your username or password is not correct");
		  }*/
       // });

items.controller('newRegistration', function ($scope, $http){
	"use strict";
	$scope.newRegis={};
	$scope.submitRegistration = function(newRegis, $event){
		console.log(newRegis);
		console.log("registration="+JSON.stringify($scope.newRegis))
		$http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
		$http.post("register", "account="+JSON.stringify($scope.newRegis)).then(function onSuccess(response){
			if(response.data == 'OK'){
				alert("You created account succesfully.");
				location.reload();
			}
			else {
				alert("Your username or password are not valid.")
			}
		})
	}})