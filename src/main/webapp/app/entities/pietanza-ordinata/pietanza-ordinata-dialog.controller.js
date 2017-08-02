(function() {
    'use strict';

    angular
        .module('sagrabeApp')
        .controller('PietanzaOrdinataDialogController', PietanzaOrdinataDialogController);

    PietanzaOrdinataDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'PietanzaOrdinata', 'Ordine', 'Pietanza'];

    function PietanzaOrdinataDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, PietanzaOrdinata, Ordine, Pietanza) {
        var vm = this;

        vm.pietanzaOrdinata = entity;
        vm.clear = clear;
        vm.save = save;
        vm.ordines = Ordine.query();
        vm.pietanzas = Pietanza.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.pietanzaOrdinata.id !== null) {
                PietanzaOrdinata.update(vm.pietanzaOrdinata, onSaveSuccess, onSaveError);
            } else {
                PietanzaOrdinata.save(vm.pietanzaOrdinata, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('sagrabeApp:pietanzaOrdinataUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
