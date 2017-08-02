(function() {
    'use strict';

    angular
        .module('sagrabeApp')
        .controller('OrdineDialogController', OrdineDialogController);

    OrdineDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Ordine', 'TavoloAccomodato'];

    function OrdineDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Ordine, TavoloAccomodato) {
        var vm = this;

        vm.ordine = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.tavoloaccomodatoes = TavoloAccomodato.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.ordine.id !== null) {
                Ordine.update(vm.ordine, onSaveSuccess, onSaveError);
            } else {
                Ordine.save(vm.ordine, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('sagrabeApp:ordineUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.dataOrdine = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
