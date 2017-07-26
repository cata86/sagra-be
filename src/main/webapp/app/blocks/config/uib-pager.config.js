(function () {
    'use strict';

    angular
        .module('sagrabeApp')
        .config(pagerConfig);

    pagerConfig.$inject = ['uibPagerConfig', 'paginationConstants'];

    function pagerConfig(uibPagerConfig, paginationConstants) {
        uibPagerConfig.itemsPerPage = paginationConstants.itemsPerPage;
        uibPagerConfig.previousText = '«';
        uibPagerConfig.nextText = '»';
    }
})();
