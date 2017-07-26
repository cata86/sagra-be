(function() {
    'use strict';

    angular
        .module('sagrabeApp')
        .controller('TavoloAccomodatoDeleteController',TavoloAccomodatoDeleteController);

    TavoloAccomodatoDeleteController.$inject = ['$uibModalInstance', 'entity', 'TavoloAccomodato'];

    function TavoloAccomodatoDeleteController($uibModalInstance, entity, TavoloAccomodato) {
        var vm = this;

        vm.tavoloAccomodato = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            TavoloAccomodato.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
