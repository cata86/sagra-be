(function() {
    'use strict';
    angular
        .module('sagrabeApp')
        .factory('TavoloReale', TavoloReale);

    TavoloReale.$inject = ['$resource'];

    function TavoloReale ($resource) {
        var resourceUrl =  'api/tavolo-reales/:id';

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
