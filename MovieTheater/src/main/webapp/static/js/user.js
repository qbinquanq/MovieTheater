var app = angular.module('User', []);
app.controller('NavigationPanel', function ($scope, $http){
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
};

});

app.controller('userFil',function($scope,$http){
	$http.post("transactions/all").then(function(response){
		console.log(response);
		$scope.TRANSACTIONS=response.data;

	});
	
/*	$scope.deleteTrans=function(response){
		var response= $http({
			method:'post',
			url:'/trans/remove'
		});
		return response;
		}*/


	$scope.refund=function(x){
		var ref =1;
		$http.post("applyRefund/"+ref+"/"+x.transId).then(function onSuccess(response){
			location.reload()
		
		});
	};
});


