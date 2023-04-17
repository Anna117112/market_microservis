angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8289/app/api/v1';

    // console.log(123);

$scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                name_part: $scope.filter ? $scope.filter.name_part : null,
                min_cost: $scope.filter ? $scope.filter.min_cost : null,
                max_cost: $scope.filter ? $scope.filter.max_cost : null
            }
        }).then(function (response) {
        // response.data - грузит всю страницу
            $scope.ProductsList = response.data.content;
        });
    };

$scope.showBasket = function (pageIndex = 1) {
        $http.get(contextPath + '/basket')

        .then(function (response) {
        // response.data - грузит всю страницу
            $scope.ProductBasket = response.data.content;
        });
    };

     $scope.addProduct = function (productId) {
            $http.get(contextPath + 'basket/add/' + productId)
                .then(function (response) {
             $scope.ProductBasket = response.data.content;

                });
        }
            $scope.deleteById = function (productId) {
                $http.get(contextPath + '/delete/' + productId)
                    .then(function (response) {
                        $scope.ProductBasket = response.data.content;
                    });
            }



          $scope.loadProducts();
        });

