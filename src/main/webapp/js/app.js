/**
 * author jary
 * since Oct. 11, 2014
 */

'use strict';

var app = angular.module('Stream2Grid', []).

    config(['$routeProvider', function ($routeProvider) {
      $routeProvider.
        when('/', {
          templateUrl: 'pages/index.html',
          controller: HomeCtrl ,
          activetab: 'projects'
        }).
        when('/project/:projectId', {
          templateUrl: function (params) {
              return 'pages/' + params.projectId + '.html';
          },
          controller: ProjectCtrl,
          activetab: 'projects'
        }).
        when('/about', {
          templateUrl: 'pages/about.html',
          controller: AboutCtrl,
          activetab: 'about'
        }).
        otherwise({ redirectTo: '/' });
    }]).run(
        ['$rootScope', '$http', '$browser', '$timeout', "$route",
        function ($scope, $http, $browser, $timeout, $route) {

        $scope.$on("$routeChangeSuccess", function (scope, next, current) {
          $scope.part = $route.current.activetab;
        });

        //// onclick event handlers
        //$scope.showForm = function () {
        //  $('.contactRow').slideToggle();
        //};
        //$scope.closeForm = function () {
        //  $('.contactRow').slideUp();
        //};
  }]);

app.config(['$locationProvider', function($location) {
    $location.hashPrefix('!');
}]);

