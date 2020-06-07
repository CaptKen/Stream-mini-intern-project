(function(){
    'use strict'
    angular.module('myApp',[]).controller('myCtrl',myCtrl)
    myCtrl.$inject = ['$scope']
    function myCtrl($scope){
        $scope.name = "Kazuya"
    }
})()