(function() {
    'use strict';

    angular
        .module('sagrabeApp')
        .controller('TavoloRealeDetailController', TavoloRealeDetailController);

    TavoloRealeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'TavoloReale', 'Sagra'];

    function TavoloRealeDetailController($scope, $rootScope, $stateParams, previousState, entity, TavoloReale, Sagra) {
        var vm = this;

        vm.tavoloReale = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('sagrabeApp:tavoloRealeUpdate', function(event, result) {
            vm.tavoloReale = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
