var profile = angular.module('HomePage',[]);
	profile.controller('fill',function($scope,$http){
	$http.get("home/all").then(function(response){
		console.log(response);
		for(var object of response.data){
			date = new Date(parseInt(object.releaseDate));
			object.releaseDate = date;
			}
		$scope.MOVIES=response.data;
		$scope.fillForm = function(x){
			console.log(x);
			//console.log($scope.MOVIES[0].mTitle);
			$scope.mTitle = x.mTitle;
			$scope.releaseDate = x.releaseDate;
			$scope.genre = x.genre;
			$scope.mLength = x.mLength;
			$scope.movieHall =[];
			for(var a of $scope.MOVIEINFO){
				var now = new Date();
				var at = new Date(parseInt(a.showtime.showtime));
				if(x.movieId == a.movie.movieId && now<at){
					$scope.movieHall.push({movieId: a.movie.movieId, hCapacity:a.hall.hCapacity, hCost:a.hall.hCost, showtime:a.showtime.showtime, time:at})
				}
			}
			$scope.getReq = function(){
				console.log($scope.selectedHall);
				var string = $scope.selectedHall.replace(/,/g, "").replace(/:/g, "");
				console.log(string);
				var arr = string.split(" ");
				console.log(arr);
				console.log($scope.movieHall);
				console.log($scope.movieHall.length);
				console.log("err");
				
				for(var i = 0; i<$scope.movieHall.length;i++){
					if(arr[2]==$scope.movieHall[i].hCapacity && arr[5]==$scope.movieHall[i].showtime){
						console.log($scope.movieHall[i]);
						for(var ck of $scope.MOVIEINFO){
							if($scope.movieHall[i].movieId == ck.movie.movieId && $scope.movieHall[i].hCapacity == ck.hall.hCapacity && $scope.movieHall[i].showtime == ck.showtime.showtime){
								console.log(ck);
								var infoId = ck.infoId;
								var onlineTot = ck.onlineTot;
								var walkTot = ck.walkTot;
								console.log(infoId);
								if((onlineTot+walkTot)>$scope.movieHall[i].hCapacity){
									alert("Cannot buy ticket, hall booked to max capacity.");
								}
								else{
								$http.post("buytickets/"+infoId).then(function onSuccess(response){
									alert("You purchased the ticket successfully. You can check it in your profile now.");
									location.reload();
								});
								}
								break;
							}
						}
						break;
					}
				}
			}
			//console.log($scope.selectedHall);
		}
		

	});
	$http.get("movieinfo/all").then(function(response){
		for(var object of response.data){
			date = new Date(parseInt(object.movie.releaseDate));
			object.movie.releaseDate = date;
			}
		$scope.MOVIEINFO = response.data;
		console.log($scope.MOVIEINFO);
	})
	
});
	/*$http.get("info/movies").then(function(response){
		$scope.SHOWTIMES=response.data;
		console.log("showtime: " + response)
	});*/
/*	$scope.changeShowtime = function(){
		console.log($scope.selectedData)
		console.log($scope.MOVIEINFO);
		$scope.filteredHalls = $scope.MOVIEINFO;
		console.log($scope.selectedData);
		console.log($scope.HALLS)
		for(var a of $scope.filteredHalls){
			//if($scope.seletedData == a.showtime.showtime){
				console.log(a.hall.hCapacity);
			if($scope.selectedData == a.showtime.showtime){
				console.log(a.showtime.showtime)
				console.log("hello");
			}
		}
		
		$scope.filerHALLS.splice(1,1);
			
		
	}*/
	/*$http.get("info/halls").then(function(response){
		console.log(response);
		$scope.HALLS=response.data;
		$scope.filerHALLS = $scope.HALLS;
	});*/
	/*$scope.changeHall = function(){
		console.log($scope.selectedHall)
		console.log($scope.MOVIEINFO);
	}*/



	

/*	profile.controller('autoFill',function($scope){
		$scope.updateVar = function (event){
			$scope.getMovies = angular.element(event.target).text();
		};
	});*/
