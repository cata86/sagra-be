(function() {
    'use strict';

    angular
        .module('sagrabeApp')
        .controller('SerataDialogController', SerataDialogController);

    SerataDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Serata', 'Sagra'];

    function SerataDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Serata, Sagra) {
        var vm = this;

        vm.serata = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
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
            if (vm.serata.id !== null) {
                Serata.update(vm.serata, onSaveSuccess, onSaveError);
            } else {
                Serata.save(vm.serata, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('sagrabeApp:serataUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.data = false;
        vm.datePickerOpenStatus.dataApertura = false;
        vm.datePickerOpenStatus.dataChiusura = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
