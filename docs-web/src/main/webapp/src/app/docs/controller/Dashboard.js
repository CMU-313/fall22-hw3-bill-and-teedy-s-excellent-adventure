'use strict';

/**
 * Dashboard controller.
 */
angular.module('docs').controller('Dashboard', function ($scope, $rootScope, $timeout, $state, Restangular, $q, $filter, $uibModal) {
  /**
   * Scope variables.
   */
  $scope.reviews = [];
  $scope.total = 0;
  $scope.averageGPA = 0;
  $scope.averageEffort = 0;
  $scope.averageExperience = 0;
  $scope.averageSkill = 0;

  /**
   * Comments?
   */

  /**
   * Load new documents page.
   */
  $scope.loadReviews = function() {
    Restangular.one('review/list').get().then(function(data) {
      $scope.reviews = JSON.parse(data.reviews);
      $scope.total = data.total;
    });
  };
  $scope.loadReviews();

  /**
   * Average the GPA scores
   */
  $scope.calculateAverageGPA = function() {
    var sum = 0
    for (var review of $scope.reviews) {
      sum += parseInt(review["GPAScore"]);
    }
    $scope.averageGPA = ($scope.total == 0 ? 0 : (sum / $scope.total));
  };

  /**
   * Average the Effort scores
   */
   $scope.calculateAverageEffort = function() {
    var sum = 0
    for (var review of $scope.reviews) {
      sum += parseInt(review["effortScore"]);
    }
    $scope.averageEffort = ($scope.total == 0 ? 0 : (sum / $scope.total));
  };

  /**
   * Average the Experience scores
   */
   $scope.calculateAverageExperience = function() {
    var sum = 0
    for (var review of $scope.reviews) {
      sum += parseInt(review["experienceScore"]);
    }
    $scope.averageExperience = ($scope.total == 0 ? 0 : (sum / $scope.total));
  };

  /**
   * Average the Skill scores
   */
   $scope.calculateAverageSkill = function() {
    var sum = 0
    for (var review of $scope.reviews) {
      sum += parseInt(review["skillScore"]);
    }
    $scope.averageSkill = ($scope.total == 0 ? 0 : (sum / $scope.total));
  };

  $scope.calculateAverageGPA();
  console.log($scope.averageGPA);
  $scope.averageGPA = 3.7;
  $scope.averageEffort = 4.6;
  $scope.averageExperience = 3.2;
  $scope.averageSkill = 4.1;

});


