(function() {
    'use strict';

    angular
        .module('sagrabeApp')
        .controller('SagraDialogController', SagraDialogController);

    SagraDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'Sagra'];

    function SagraDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, Sagra) {
        var vm = this;

        vm.sagra = entity;
        vm.clear = clear;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.sagra.id !== null) {
                Sagra.update(vm.sagra, onSaveSuccess, onSaveError);
            } else {
                Sagra.save(vm.sagra, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('sagrabeApp:sagraUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setLogo = function ($file, sagra) {
            if ($file && $file.$error === 'pattern') {
                return;
            }
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        sagra.logo = base64Data;
                        sagra.logoContentType = $file.type;
                    });
                });
            }
        };

    }
})();
