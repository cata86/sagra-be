(function() {
    'use strict';

    angular
        .module('sagrabeApp')
        .controller('SagraDeleteController',SagraDeleteController);

    SagraDeleteController.$inject = ['$uibModalInstance', 'entity', 'Sagra'];

    function SagraDeleteController($uibModalInstance, entity, Sagra) {
        var vm = this;

        vm.sagra = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Sagra.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
