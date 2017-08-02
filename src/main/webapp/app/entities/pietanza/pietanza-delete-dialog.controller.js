(function() {
    'use strict';

    angular
        .module('sagrabeApp')
        .controller('PietanzaDeleteController',PietanzaDeleteController);

    PietanzaDeleteController.$inject = ['$uibModalInstance', 'entity', 'Pietanza'];

    function PietanzaDeleteController($uibModalInstance, entity, Pietanza) {
        var vm = this;

        vm.pietanza = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Pietanza.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
