(function() {
    'use strict';

    angular
        .module('sagrabeApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('tavolo-reale', {
            parent: 'entity',
            url: '/tavolo-reale',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sagrabeApp.tavoloReale.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tavolo-reale/tavolo-reales.html',
                    controller: 'TavoloRealeController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('tavoloReale');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('tavolo-reale-detail', {
            parent: 'tavolo-reale',
            url: '/tavolo-reale/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sagrabeApp.tavoloReale.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tavolo-reale/tavolo-reale-detail.html',
                    controller: 'TavoloRealeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('tavoloReale');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'TavoloReale', function($stateParams, TavoloReale) {
                    return TavoloReale.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'tavolo-reale',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('tavolo-reale-detail.edit', {
            parent: 'tavolo-reale-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tavolo-reale/tavolo-reale-dialog.html',
                    controller: 'TavoloRealeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['TavoloReale', function(TavoloReale) {
                            return TavoloReale.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tavolo-reale.new', {
            parent: 'tavolo-reale',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tavolo-reale/tavolo-reale-dialog.html',
                    controller: 'TavoloRealeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                codice: null,
                                descrizione: null,
                                postiMax: null,
                                postiOccupati: null,
                                asporto: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('tavolo-reale', null, { reload: 'tavolo-reale' });
                }, function() {
                    $state.go('tavolo-reale');
                });
            }]
        })
        .state('tavolo-reale.edit', {
            parent: 'tavolo-reale',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tavolo-reale/tavolo-reale-dialog.html',
                    controller: 'TavoloRealeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['TavoloReale', function(TavoloReale) {
                            return TavoloReale.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tavolo-reale', null, { reload: 'tavolo-reale' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tavolo-reale.delete', {
            parent: 'tavolo-reale',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tavolo-reale/tavolo-reale-delete-dialog.html',
                    controller: 'TavoloRealeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['TavoloReale', function(TavoloReale) {
                            return TavoloReale.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tavolo-reale', null, { reload: 'tavolo-reale' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
