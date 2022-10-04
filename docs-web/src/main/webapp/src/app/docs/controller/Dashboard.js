'use strict';

/**
 * Dashboard controller.
 */
angular.module('docs').controller('Dashboard', function ($scope, $rootScope, $timeout, $state, Restangular, $q, $filter, $uibModal) {
  /**
   * Scope variables.
   */
  $scope.reviews = [];
  $scope.averageGPA = 0;

  /**
   * Load new documents page.
   */
  $scope.loadReviews = function() {
    Restangular.one('dashboard/list').get().then(function(data) {
      $scope.reviews = dashboard.reviews;
    });
  };
  $scope.loadReviews();

  /**
   * Average the GPA scores
   */
  $scope.averageGPA = function() {
    var sum = 0
    for (var num of $scope.reviews) {
      sum += num;
    }
    $scope.averageGPA = sum / $scope.reviews.length;
  }
  $scope.calculateAverageGPA();
  console.log($scope.averageGPA);
});
