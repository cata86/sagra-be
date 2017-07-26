'use strict';

describe('Controller Tests', function() {

    describe('TavoloAccomodato Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockTavoloAccomodato, MockSerata, MockTavoloReale;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockTavoloAccomodato = jasmine.createSpy('MockTavoloAccomodato');
            MockSerata = jasmine.createSpy('MockSerata');
            MockTavoloReale = jasmine.createSpy('MockTavoloReale');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'TavoloAccomodato': MockTavoloAccomodato,
                'Serata': MockSerata,
                'TavoloReale': MockTavoloReale
            };
            createController = function() {
                $injector.get('$controller')("TavoloAccomodatoDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'sagrabeApp:tavoloAccomodatoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
