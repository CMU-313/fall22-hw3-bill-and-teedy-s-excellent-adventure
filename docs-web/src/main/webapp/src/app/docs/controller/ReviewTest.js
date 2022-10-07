'use strict';

/**
 * Review controller.
 */
angular.module('docs').controller('ReviewTest', function ($scope, $rootScope, $timeout, $state, Restangular, $q, $filter, $uibModal) {
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
   * Testing Json File
   */

  const JS_OBJECT = {"total":"3",
               "reviews": [
                {"reviewId":"1", "GPAScore":"3", "effortScore":"4", "experienceScore":"5", "skillScore":"5"},
                {"reviewId":"2", "GPAScore":"4", "effortScore":"3", "experienceScore":"1", "skillScore":"3"},
                {"reviewId":"3", "GPAScore":"5", "effortScore":"1", "experienceScore":"2", "skillScore":"4"},
               ]};

  /**
   * Load new reviews page.
   */
  // $scope.loadReviews = function() {
  //   Restangular.one('review/list')
  //   .get()
  //   .then(function(response) {
  //     response = JS_OBJECT;
  //     console.log(response);
  //     $scope.reviews = response.reviews;
  //     $scope.total = response.total;
  //   });
  // };
  // $scope.loadReviews();
  const response = JS_OBJECT; 
  $scope.reviews = response.reviews;
  $scope.total = response.total;

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
  $scope.calculateAverageEffort();
  $scope.calculateAverageExperience();
  $scope.calculateAverageSkill();

  $scope.averageGPA =  $scope.averageGPA.toFixed(2);
  $scope.averageEffort = $scope.averageEffort.toFixed(2);
  $scope.averageExperience = $scope.averageExperience.toFixed(2);
  $scope.averageSkill = $scope.averageSkill.toFixed(2);
});