(function () {
  "use strict";

  angular
    .module("RoutingApp", ["ui.router"])
    .controller("ShoppingListShowController", ShoppingListShowController)
    .controller("ShoppingListAddController", ShoppingListAddController)
    .controller("myApi", myApi)
    .service("ShoppingListService", ShoppingListService);
  angular.module("RoutingApp").config(RoutesConfig);
  myApi.$inject = ["$http", "$rootScope"];
  function myApi($http, $rootScope) {
    $http.get("http://localhost:8080/product").then(function (response) {
      $rootScope.product = response.data;
    });
    $http.get("http://localhost:8080/company").then(function (response) {
      $rootScope.company = response.data;
    });
    $http.get("http://localhost:8080/showingOrder").then(function (response) {
      $rootScope.order = response.data;
    });
    $http.get("http://localhost:8080/order").then(function (response) {
      $rootScope.allOrder = response.data;
    });
  }
  RoutesConfig.$inject = ["$stateProvider", "$urlRouterProvider"];
  function RoutesConfig($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("/tab1");

    $stateProvider
      .state("tab1", {
        url: "/tab1",
        templateUrl: "/tab1.html",
      })
      .state("tab2", {
        url: "/tab2",
        templateUrl: "/tab2.html",
      })
      .state("detail", {
        url: "/detail",
        templateUrl: "/detail.html",
      });
  }

  ShoppingListAddController.$inject = ["ShoppingListService"];
  function ShoppingListAddController(ShoppingListService) {
    var itemAdder = this;
    itemAdder.itemName = "";
    itemAdder.itemQuantity = "";
    itemAdder.addItem = function () {
      ShoppingListService.addItem(itemAdder.itemName, itemAdder.itemQuantity);
    };
  }

  ShoppingListShowController.$inject = ["ShoppingListService"];
  function ShoppingListShowController(ShoppingListService) {
    var showList = this;
    showList.items = ShoppingListService.getItems();
  }

  function ShoppingListService() {
    var service = this;
    var items = [];
    service.addItem = function (name, quantity) {
      var item = {
        name: name,
        quantity: quantity,
      };
      items.push(item);
    };
    service.getItems = function () {
      return items;
    };
  }
})();
