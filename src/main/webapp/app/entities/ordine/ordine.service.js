(function() {
    'use strict';
    angular
        .module('sagrabeApp')
        .factory('Ordine', Ordine);

    Ordine.$inject = ['$resource', 'DateUtils'];

    function Ordine ($resource, DateUtils) {
        var resourceUrl =  'api/ordines/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.dataOrdine = DateUtils.convertDateTimeFromServer(data.dataOrdine);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
