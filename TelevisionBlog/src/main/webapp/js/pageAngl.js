/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


angular.module("root", [])
        .controller("index", ["$scope",
            function ($scope) {
                $scope.$watch("title", function(newString) {
                   $scope.url = newString.replace(/[\s]/g, '-').toLowerCase(); 
                });
            }]);
