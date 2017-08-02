(function() {
    'use strict';

    angular
        .module('sagrabeApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('ordine', {
            parent: 'entity',
            url: '/ordine?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sagrabeApp.ordine.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/ordine/ordines.html',
                    controller: 'OrdineController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('ordine');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('ordine-detail', {
            parent: 'ordine',
            url: '/ordine/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sagrabeApp.ordine.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/ordine/ordine-detail.html',
                    controller: 'OrdineDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('ordine');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Ordine', function($stateParams, Ordine) {
                    return Ordine.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'ordine',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('ordine-detail.edit', {
            parent: 'ordine-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ordine/ordine-dialog.html',
                    controller: 'OrdineDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Ordine', function(Ordine) {
                            return Ordine.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('ordine.new', {
            parent: 'ordine',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ordine/ordine-dialog.html',
                    controller: 'OrdineDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                personaOrdine: null,
                                dataOrdine: null,
                                numeroCoperti: null,
                                totale: null,
                                quotaPersona: null,
                                asporto: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('ordine', null, { reload: 'ordine' });
                }, function() {
                    $state.go('ordine');
                });
            }]
        })
        .state('ordine.edit', {
            parent: 'ordine',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ordine/ordine-dialog.html',
                    controller: 'OrdineDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Ordine', function(Ordine) {
                            return Ordine.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('ordine', null, { reload: 'ordine' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('ordine.delete', {
            parent: 'ordine',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ordine/ordine-delete-dialog.html',
                    controller: 'OrdineDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Ordine', function(Ordine) {
                            return Ordine.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('ordine', null, { reload: 'ordine' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
