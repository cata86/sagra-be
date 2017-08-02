(function() {
    'use strict';

    angular
        .module('sagrabeApp')
        .controller('OrdineDeleteController',OrdineDeleteController);

    OrdineDeleteController.$inject = ['$uibModalInstance', 'entity', 'Ordine'];

    function OrdineDeleteController($uibModalInstance, entity, Ordine) {
        var vm = this;

        vm.ordine = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Ordine.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
