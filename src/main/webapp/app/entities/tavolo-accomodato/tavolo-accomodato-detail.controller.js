(function() {
    'use strict';

    angular
        .module('sagrabeApp')
        .controller('TavoloAccomodatoDetailController', TavoloAccomodatoDetailController);

    TavoloAccomodatoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'TavoloAccomodato', 'Serata', 'TavoloReale'];

    function TavoloAccomodatoDetailController($scope, $rootScope, $stateParams, previousState, entity, TavoloAccomodato, Serata, TavoloReale) {
        var vm = this;

        vm.tavoloAccomodato = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('sagrabeApp:tavoloAccomodatoUpdate', function(event, result) {
            vm.tavoloAccomodato = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
