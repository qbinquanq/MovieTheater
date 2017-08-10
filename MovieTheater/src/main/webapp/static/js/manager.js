var manager = angular.module('Manager', []);
manager.controller('NavigationPanel', function ($scope, $http){
	'use strict';
	$scope.HomePage=function(){
		$http.get('home').then(function(response){
			console.log('home');
			console.log(response.data)
			location.href = "home";
		});
	};
	
	
$scope.LogOut=function(){
	$http.get('logout').then(function(response){
		console.log('logout');
		console.log(response.data)
		location.reload();
	});
}
});



manager.controller('report', function ($scope, $http){
$http.post("manager/movieinfo").then(function(response){
	console.log(response);
	for(var object of response.data){
        var date = new Date(parseInt(object.showtime.showtime));
        object.showtime.showtime = date;
        
	}

	$scope.MOVIEINFO=response.data;
	
	}); 
});




manager.controller('fill', function($scope, $http) {
    $http.post("manager/movieinfo").then(function(response) {
        for(var object of response.data){
        var date = new Date(parseInt(object.showtime.showtime));
        object.showtime.showtime = date;
        date = new Date(parseInt(object.movie.releaseDate));
        object.movie.releaseDate = date;
        $scope.show = object.showtime.showtime;
        }
        console.log(response.data);
        $scope.MOVIEINFO = response.data;
        $scope.now = new Date();
        
        
        
        
        
    });

});



