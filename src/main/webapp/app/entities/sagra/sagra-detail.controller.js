(function() {
    'use strict';

    angular
        .module('sagrabeApp')
        .controller('SagraDetailController', SagraDetailController);

    SagraDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'Sagra'];

    function SagraDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, Sagra) {
        var vm = this;

        vm.sagra = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('sagrabeApp:sagraUpdate', function(event, result) {
            vm.sagra = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
