(function() {
    'use strict';

    angular
        .module('sagrabeApp')
        .controller('PietanzaDetailController', PietanzaDetailController);

    PietanzaDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Pietanza', 'PietanzaCategoria'];

    function PietanzaDetailController($scope, $rootScope, $stateParams, previousState, entity, Pietanza, PietanzaCategoria) {
        var vm = this;

        vm.pietanza = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('sagrabeApp:pietanzaUpdate', function(event, result) {
            vm.pietanza = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
