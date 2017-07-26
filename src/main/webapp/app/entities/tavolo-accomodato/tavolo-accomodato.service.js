(function() {
    'use strict';
    angular
        .module('sagrabeApp')
        .factory('TavoloAccomodato', TavoloAccomodato);

    TavoloAccomodato.$inject = ['$resource', 'DateUtils'];

    function TavoloAccomodato ($resource, DateUtils) {
        var resourceUrl =  'api/tavolo-accomodatoes/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.accomodatoOrario = DateUtils.convertDateTimeFromServer(data.accomodatoOrario);
                        data.inOrdinazioneOrario = DateUtils.convertDateTimeFromServer(data.inOrdinazioneOrario);
                        data.ordinazioneOrario = DateUtils.convertDateTimeFromServer(data.ordinazioneOrario);
                        data.liberatoOrario = DateUtils.convertDateTimeFromServer(data.liberatoOrario);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
