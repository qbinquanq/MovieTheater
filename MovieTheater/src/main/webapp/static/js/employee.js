var employee = angular.module('WalkIn', []);

employee.controller('fill', function($scope, $http) {
	$http.post("employee/movieinfo").then(function(response) {
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
	$scope.getWalk = function(x){
		var amo = (+x.am + +x.walkTot + +x.onlineTot);
		console.log(amo);
		if(amo>x.hall.hCapacity){
			alert("You let too many people in.");
		}else{
			$http.post("employee/"+x.am+"/"+x.infoId).then(function onSuccess(response){
				alert("You successfully put in foot traffic.");
				location.reload();
			});
		}
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