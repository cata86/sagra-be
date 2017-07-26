(function() {
    'use strict';
    angular
        .module('sagrabeApp')
        .factory('Serata', Serata);

    Serata.$inject = ['$resource', 'DateUtils'];

    function Serata ($resource, DateUtils) {
        var resourceUrl =  'api/seratas/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.data = DateUtils.convertLocalDateFromServer(data.data);
                        data.dataApertura = DateUtils.convertDateTimeFromServer(data.dataApertura);
                        data.dataChiusura = DateUtils.convertDateTimeFromServer(data.dataChiusura);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.data = DateUtils.convertLocalDateToServer(copy.data);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.data = DateUtils.convertLocalDateToServer(copy.data);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
