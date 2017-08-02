(function() {
    'use strict';

    angular
        .module('sagrabeApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('pietanza', {
            parent: 'entity',
            url: '/pietanza',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sagrabeApp.pietanza.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/pietanza/pietanzas.html',
                    controller: 'PietanzaController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('pietanza');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('pietanza-detail', {
            parent: 'pietanza',
            url: '/pietanza/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sagrabeApp.pietanza.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/pietanza/pietanza-detail.html',
                    controller: 'PietanzaDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('pietanza');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Pietanza', function($stateParams, Pietanza) {
                    return Pietanza.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'pietanza',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('pietanza-detail.edit', {
            parent: 'pietanza-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pietanza/pietanza-dialog.html',
                    controller: 'PietanzaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Pietanza', function(Pietanza) {
                            return Pietanza.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('pietanza.new', {
            parent: 'pietanza',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pietanza/pietanza-dialog.html',
                    controller: 'PietanzaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                codice: null,
                                nome: null,
                                descrizione: null,
                                prezzo: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('pietanza', null, { reload: 'pietanza' });
                }, function() {
                    $state.go('pietanza');
                });
            }]
        })
        .state('pietanza.edit', {
            parent: 'pietanza',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pietanza/pietanza-dialog.html',
                    controller: 'PietanzaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Pietanza', function(Pietanza) {
                            return Pietanza.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('pietanza', null, { reload: 'pietanza' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('pietanza.delete', {
            parent: 'pietanza',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pietanza/pietanza-delete-dialog.html',
                    controller: 'PietanzaDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Pietanza', function(Pietanza) {
                            return Pietanza.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('pietanza', null, { reload: 'pietanza' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
