var profile = angular.module('HomePage',[]);
	profile.controller('fill',function($scope,$http){
	$http.post("home/all").then(function(response){
		console.log(response);
		$scope.MOVIES=response.data;
		$scope.fillForm = function(x){
			console.log(x);
			//console.log($scope.MOVIES[0].mTitle);
			$scope.mTitle = x.mTitle;
			$scope.releaseDate = x.releaseDate;
			$scope.genre = x.genre;
			$scope.mLength = x.mLength;
		}

	});
});


	/*profile.controller('filling',function($scope,$http){
		$scope.fillForm = function(count){
			console.log(count);
			console.log($scope.MOVIES[0].mTitle);
			$scope.mTitle = $scope.MOVIES[count].mTitle;
			$scope.releaseDate = $scope.MOVIES[count].releaseDate;
			$scope.genre = $scope.MOVIES[count].genre;
			$scope.mLength = $scope.MOVIES[count].mLength;
		}
		$http.post("home/all").then(function(response){
			$scope.myVar= response.data;
			console.log($scope.myVar);
				//$scope.myVar= response.data.mTitle;
			
			
		});
		
	});
	*/
	
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
	

	profile.controller('transAmt/save',function($scope,$http){
		
	});
/*	profile.controller('autoFill',function($scope){
		$scope.updateVar = function (event){
			$scope.getMovies = angular.element(event.target).text();
		};
	});*/
