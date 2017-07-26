(function() {
    'use strict';
    angular
        .module('sagrabeApp')
        .factory('Sagra', Sagra);

    Sagra.$inject = ['$resource'];

    function Sagra ($resource) {
        var resourceUrl =  'api/sagras/:id';

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
