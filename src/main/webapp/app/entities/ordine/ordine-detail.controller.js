(function() {
    'use strict';

    angular
        .module('sagrabeApp')
        .controller('OrdineDetailController', OrdineDetailController);

    OrdineDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Ordine', 'TavoloAccomodato'];

    function OrdineDetailController($scope, $rootScope, $stateParams, previousState, entity, Ordine, TavoloAccomodato) {
        var vm = this;

        vm.ordine = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('sagrabeApp:ordineUpdate', function(event, result) {
            vm.ordine = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
