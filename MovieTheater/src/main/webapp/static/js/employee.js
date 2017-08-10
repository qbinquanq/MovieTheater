var employee = angular.module('WalkIn', []);

employee.controller('fill', function($scope, $http) {
	$http.post("employee/movieinfo").then(function(response) {
		$http.post("employee/walkin").then(function(walkresp){
			$scope.WALKIN = walkresp.data;
			$http.post("employee/id").then(function(empresp){
				$scope.EMP = empresp.data;
			})
		})
		for(var object of response.data){
		var date = new Date(parseInt(object.showtime.showtime));
		object.showtime.showtime = date;
		date = new Date(parseInt(object.movie.releaseDate));
		object.movie.releaseDate = date;
		$scope.now = new Date();
		}
		console.log(response.data);
		$scope.MOVIEINFO = response.data;
	
	})
})
employee.controller('WalkIn', function ($scope, $http){
	"use strict";
	$scope.newWalkIn={};
	$scope.submitAmt = function(newWalkIn, $event){
		console.log(newWalkIn);
		console.log("employee/walkinamt="+JSON.stringify($scope.newWalkIn))
		$http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
		$http.post("employee/walkinamt", "amount="+JSON.stringify($scope.newWalkIn)).then(function onSuccess(response){
			alert("You submitted foot traffic succesfully.");
			location.reload();
		});
	}
})

employee.controller('NavigationPanel', function($scope, $http) {
	'use strict';
	$scope.LogOut = function() {
		$http.get('logout').then(function(response) {
			location.reload();
		})
	};
	$scope.PageHome = function() {
		$http.get('home').then(function(response) {
			console.log("home");
			console.log(response.data);
			location.href = "home";
		})
	}
})