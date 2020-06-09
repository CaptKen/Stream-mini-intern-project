(function () {
  "use strict";

  angular
    .module("RoutingApp", ["ui.router"])
    .controller("ShoppingListShowController", ShoppingListShowController)
    .controller("LoadData", LoadData)
    .controller("myApi", myApi)
    .service("ShoppingListService", ShoppingListService);
  angular.module("RoutingApp").config(RoutesConfig);
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
        controller:"LoadData"
      })
  }
  LoadData.$inject = ["$http","$rootScope"];
  function LoadData($http, $rootScope) {
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
  myApi.$inject = ["$http", "$rootScope"];
  function myApi($http, $rootScope) {
    document.getElementById("input-file").addEventListener("change", getFile);
    function getFile(event) {
      const input = event.target;
      if ("files" in input && input.files.length > 0) {
        var arr;
        placeFileContent(
          document.getElementById("content-target"),
          input.files[0]
        );
      }
    }

    function placeFileContent(target, file) {
      readFileContent(file)
        .then((content) => {
          target.value = content.split("|");
        })
        .catch((error) => console.log(error));
    }

    function readFileContent(file) {
      const reader = new FileReader();
      return new Promise((resolve, reject) => {
        reader.onload = (event) => resolve(event.target.result);
        reader.onerror = (error) => reject(error);
        reader.readAsText(file);
      });
    }
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
    document.getElementById("myBtn").addEventListener("click", sendIt);
    function sendIt() {
      var endpoint = "http://localhost:8080/create";
      $.ajax({
        type: "POST",
        url: endpoint,
        data: $("#myForm").serializeArray(),
        success: function () {
          alert("Upload success");
          location.replace("http://localhost:3000/index.html#!/tab2");
        },
        error: function (){
          alert("กรอกข้อมูลให้ครบ");
        }
      });
      
      
    }
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
