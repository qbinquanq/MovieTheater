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
});
app.controller("userFil",function($scope,$http){
	"use strict";
	$scope.newDelete={};
	$scope.newRefund={};
	$scope.deleteTrans=function(){
		$http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
	$http.post("/trans/remove","deleted="+JSON.stringify($scope.newLogin)).then(function onSuccess(response){
		$scope.status="Transaction Deleted Successfully";
		location.reload()
	},function onError(response){
		$scope.status="That did not delete successfully";
		console.log("failed");
	});
};

$scope.refund=function(){
	$http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
	$http.post("/applyRefund/apply","Refunded="+JSON.stringify($scope.newRefund)).then(function onSuccess(response){
		$scope.status="Refund Sent Successfully";
		location.reload()
	},function onError(response){
		$scope.status="The Refund was not successful";
		console.log("Error in Refunding");
	});
};
});

app.controller('apply-username',function($scope,$http){
	$http.get("loginthrough").then(function(response){
		$scope.ACCOUNTS=response.data;
	});
});