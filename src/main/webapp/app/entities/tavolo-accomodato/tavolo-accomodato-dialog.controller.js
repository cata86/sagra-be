(function() {
    'use strict';

    angular
        .module('sagrabeApp')
        .controller('TavoloAccomodatoDialogController', TavoloAccomodatoDialogController);

    TavoloAccomodatoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'TavoloAccomodato', 'Serata', 'TavoloReale'];

    function TavoloAccomodatoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, TavoloAccomodato, Serata, TavoloReale) {
        var vm = this;

        vm.tavoloAccomodato = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.seratas = Serata.query();
        vm.tavoloreales = TavoloReale.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.tavoloAccomodato.id !== null) {
                TavoloAccomodato.update(vm.tavoloAccomodato, onSaveSuccess, onSaveError);
            } else {
                TavoloAccomodato.save(vm.tavoloAccomodato, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('sagrabeApp:tavoloAccomodatoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.accomodatoOrario = false;
        vm.datePickerOpenStatus.inOrdinazioneOrario = false;
        vm.datePickerOpenStatus.ordinazioneOrario = false;
        vm.datePickerOpenStatus.liberatoOrario = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
