(function() {
    'use strict';

    angular
        .module('sagrabeApp')
        .controller('SerataDeleteController',SerataDeleteController);

    SerataDeleteController.$inject = ['$uibModalInstance', 'entity', 'Serata'];

    function SerataDeleteController($uibModalInstance, entity, Serata) {
        var vm = this;

        vm.serata = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Serata.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
