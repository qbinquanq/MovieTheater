var profile = angular.module('HomePage',[]);
	profile.controller('fill',function($scope,$http){
	$http.post("home/all").then(function(response){
		console.log(response);
		$scope.MOVIES=response.data;

});
});


	profile.controller('filling',function($scope,$http){
		$http.post("home/all").then(function(response){
			$scope.fillForm = function(index){
				alert(index)
			}
			
		});
	});
	
	profile.controller('autoFill',function($scope,$http){
		$http.post("info/movies").then(function(response){
				$scope.SHOWTIMES=response.data;
		});
	});
	
	profile.controller('autoFillHalls',function($scope,$http){
		$http.post("info/halls").then(function(response){
			console.log(response);
			$scope.HALLS=response.data;
		});
	});
	

/*	profile.controller('autoFill',function($scope){
		$scope.updateVar = function (event){
			$scope.getMovies = angular.element(event.target).text();
		};
	});*/