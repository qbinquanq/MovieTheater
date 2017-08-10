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

    var now = new Date();
	$scope.MOVIEINFO=response.data;
	
	}); 
});

manager.controller('refund',function($scope,$http){
	$http.post("refund/all").then(function(response){
		console.log(response);
		$scope.TRANSACTIONS=response.data;
	});
});

manager.controller('switchHalls',function($scope,$http){
	$http.post("manager/movieinfo").then(function(response){
		console.log(response);
		$scope.MOVIEINFO=response.data;
		$scope.switches=function(x){
			$scope.changeMovie= x.movie.mTitle;
		}
	});
});

manager.controller('showMovie',function($scope,$http){
		
		$scope.switches=function(x){
			$scope.movie = x.movie.mTitle;
			$http.post("save/hall",{
				'Movie':$scope.movie,
				'Hall':$scope.hall_time,
				'Showtime':$scope.show
			}).success(function(data,status,headers,config){
				alert("updated Movie");
			});
		}

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




