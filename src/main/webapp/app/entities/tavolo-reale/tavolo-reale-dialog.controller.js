(function() {
    'use strict';

    angular
        .module('sagrabeApp')
        .controller('TavoloRealeDialogController', TavoloRealeDialogController);

    TavoloRealeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'TavoloReale', 'Sagra'];

    function TavoloRealeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, TavoloReale, Sagra) {
        var vm = this;

        vm.tavoloReale = entity;
        vm.clear = clear;
        vm.save = save;
        vm.sagras = Sagra.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.tavoloReale.id !== null) {
                TavoloReale.update(vm.tavoloReale, onSaveSuccess, onSaveError);
            } else {
                TavoloReale.save(vm.tavoloReale, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('sagrabeApp:tavoloRealeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
