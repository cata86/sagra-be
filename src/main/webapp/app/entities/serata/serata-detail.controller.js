(function() {
    'use strict';

    angular
        .module('sagrabeApp')
        .controller('SerataDetailController', SerataDetailController);

    SerataDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Serata', 'Sagra'];

    function SerataDetailController($scope, $rootScope, $stateParams, previousState, entity, Serata, Sagra) {
        var vm = this;

        vm.serata = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('sagrabeApp:serataUpdate', function(event, result) {
            vm.serata = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
