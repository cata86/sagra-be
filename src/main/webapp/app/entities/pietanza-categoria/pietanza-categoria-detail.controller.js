(function() {
    'use strict';

    angular
        .module('sagrabeApp')
        .controller('PietanzaCategoriaDetailController', PietanzaCategoriaDetailController);

    PietanzaCategoriaDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'PietanzaCategoria'];

    function PietanzaCategoriaDetailController($scope, $rootScope, $stateParams, previousState, entity, PietanzaCategoria) {
        var vm = this;

        vm.pietanzaCategoria = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('sagrabeApp:pietanzaCategoriaUpdate', function(event, result) {
            vm.pietanzaCategoria = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
