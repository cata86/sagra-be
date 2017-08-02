(function() {
    'use strict';

    angular
        .module('sagrabeApp')
        .controller('PietanzaOrdinataDeleteController',PietanzaOrdinataDeleteController);

    PietanzaOrdinataDeleteController.$inject = ['$uibModalInstance', 'entity', 'PietanzaOrdinata'];

    function PietanzaOrdinataDeleteController($uibModalInstance, entity, PietanzaOrdinata) {
        var vm = this;

        vm.pietanzaOrdinata = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            PietanzaOrdinata.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
