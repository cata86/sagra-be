(function() {
    'use strict';

    angular
        .module('sagrabeApp')
        .controller('TavoloRealeDeleteController',TavoloRealeDeleteController);

    TavoloRealeDeleteController.$inject = ['$uibModalInstance', 'entity', 'TavoloReale'];

    function TavoloRealeDeleteController($uibModalInstance, entity, TavoloReale) {
        var vm = this;

        vm.tavoloReale = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            TavoloReale.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
