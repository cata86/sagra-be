(function() {
    'use strict';
    angular
        .module('sagrabeApp')
        .factory('PietanzaCategoria', PietanzaCategoria);

    PietanzaCategoria.$inject = ['$resource'];

    function PietanzaCategoria ($resource) {
        var resourceUrl =  'api/pietanza-categorias/:id';

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
