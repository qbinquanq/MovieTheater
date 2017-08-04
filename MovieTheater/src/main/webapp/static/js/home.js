
function Hello($scope,$http){
	$http.get('home/all').then(function(response){
		console.log(response);
	});
};