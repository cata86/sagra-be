(function() {
    'use strict';
    angular
        .module('sagrabeApp')
        .factory('Pietanza', Pietanza);

    Pietanza.$inject = ['$resource'];

    function Pietanza ($resource) {
        var resourceUrl =  'api/pietanzas/:id';

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
