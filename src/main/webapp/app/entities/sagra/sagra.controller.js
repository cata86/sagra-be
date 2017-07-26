(function() {
    'use strict';

    angular
        .module('sagrabeApp')
        .controller('SagraController', SagraController);

    SagraController.$inject = ['DataUtils', 'Sagra'];

    function SagraController(DataUtils, Sagra) {

        var vm = this;

        vm.sagras = [];
        vm.openFile = DataUtils.openFile;
        vm.byteSize = DataUtils.byteSize;

        loadAll();

        function loadAll() {
            Sagra.query(function(result) {
                vm.sagras = result;
                vm.searchQuery = null;
            });
        }
    }
})();
