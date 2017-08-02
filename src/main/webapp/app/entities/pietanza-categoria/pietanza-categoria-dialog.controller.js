(function() {
    'use strict';

    angular
        .module('sagrabeApp')
        .controller('PietanzaCategoriaDialogController', PietanzaCategoriaDialogController);

    PietanzaCategoriaDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'PietanzaCategoria'];

    function PietanzaCategoriaDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, PietanzaCategoria) {
        var vm = this;

        vm.pietanzaCategoria = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.pietanzaCategoria.id !== null) {
                PietanzaCategoria.update(vm.pietanzaCategoria, onSaveSuccess, onSaveError);
            } else {
                PietanzaCategoria.save(vm.pietanzaCategoria, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('sagrabeApp:pietanzaCategoriaUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
