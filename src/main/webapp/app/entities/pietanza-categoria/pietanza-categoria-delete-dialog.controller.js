(function() {
    'use strict';

    angular
        .module('sagrabeApp')
        .controller('PietanzaCategoriaDeleteController',PietanzaCategoriaDeleteController);

    PietanzaCategoriaDeleteController.$inject = ['$uibModalInstance', 'entity', 'PietanzaCategoria'];

    function PietanzaCategoriaDeleteController($uibModalInstance, entity, PietanzaCategoria) {
        var vm = this;

        vm.pietanzaCategoria = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            PietanzaCategoria.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
