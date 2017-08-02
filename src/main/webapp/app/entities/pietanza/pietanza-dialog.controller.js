(function() {
    'use strict';

    angular
        .module('sagrabeApp')
        .controller('PietanzaDialogController', PietanzaDialogController);

    PietanzaDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Pietanza', 'PietanzaCategoria'];

    function PietanzaDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Pietanza, PietanzaCategoria) {
        var vm = this;

        vm.pietanza = entity;
        vm.clear = clear;
        vm.save = save;
        vm.pietanzacategorias = PietanzaCategoria.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.pietanza.id !== null) {
                Pietanza.update(vm.pietanza, onSaveSuccess, onSaveError);
            } else {
                Pietanza.save(vm.pietanza, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('sagrabeApp:pietanzaUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
