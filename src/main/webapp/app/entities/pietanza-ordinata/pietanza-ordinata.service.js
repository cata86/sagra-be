(function() {
    'use strict';
    angular
        .module('sagrabeApp')
        .factory('PietanzaOrdinata', PietanzaOrdinata);

    PietanzaOrdinata.$inject = ['$resource'];

    function PietanzaOrdinata ($resource) {
        var resourceUrl =  'api/pietanza-ordinatas/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
