'use strict';

describe('Controller Tests', function() {

    describe('PietanzaOrdinata Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockPietanzaOrdinata, MockOrdine, MockPietanza;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockPietanzaOrdinata = jasmine.createSpy('MockPietanzaOrdinata');
            MockOrdine = jasmine.createSpy('MockOrdine');
            MockPietanza = jasmine.createSpy('MockPietanza');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'PietanzaOrdinata': MockPietanzaOrdinata,
                'Ordine': MockOrdine,
                'Pietanza': MockPietanza
            };
            createController = function() {
                $injector.get('$controller')("PietanzaOrdinataDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'sagrabeApp:pietanzaOrdinataUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
