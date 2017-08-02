(function() {
    'use strict';

    angular
        .module('sagrabeApp')
        .controller('PietanzaOrdinataDetailController', PietanzaOrdinataDetailController);

    PietanzaOrdinataDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'PietanzaOrdinata', 'Ordine', 'Pietanza'];

    function PietanzaOrdinataDetailController($scope, $rootScope, $stateParams, previousState, entity, PietanzaOrdinata, Ordine, Pietanza) {
        var vm = this;

        vm.pietanzaOrdinata = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('sagrabeApp:pietanzaOrdinataUpdate', function(event, result) {
            vm.pietanzaOrdinata = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
