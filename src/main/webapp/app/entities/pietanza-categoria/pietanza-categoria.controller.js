(function() {
    'use strict';

    angular
        .module('sagrabeApp')
        .controller('PietanzaCategoriaController', PietanzaCategoriaController);

    PietanzaCategoriaController.$inject = ['PietanzaCategoria'];

    function PietanzaCategoriaController(PietanzaCategoria) {

        var vm = this;

        vm.pietanzaCategorias = [];

        loadAll();

        function loadAll() {
            PietanzaCategoria.query(function(result) {
                vm.pietanzaCategorias = result;
                vm.searchQuery = null;
            });
        }
    }
})();
