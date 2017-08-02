(function() {
    'use strict';

    angular
        .module('sagrabeApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('pietanza-ordinata', {
            parent: 'entity',
            url: '/pietanza-ordinata?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sagrabeApp.pietanzaOrdinata.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/pietanza-ordinata/pietanza-ordinatas.html',
                    controller: 'PietanzaOrdinataController',
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
                    $translatePartialLoader.addPart('pietanzaOrdinata');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('pietanza-ordinata-detail', {
            parent: 'pietanza-ordinata',
            url: '/pietanza-ordinata/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sagrabeApp.pietanzaOrdinata.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/pietanza-ordinata/pietanza-ordinata-detail.html',
                    controller: 'PietanzaOrdinataDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('pietanzaOrdinata');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'PietanzaOrdinata', function($stateParams, PietanzaOrdinata) {
                    return PietanzaOrdinata.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'pietanza-ordinata',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('pietanza-ordinata-detail.edit', {
            parent: 'pietanza-ordinata-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pietanza-ordinata/pietanza-ordinata-dialog.html',
                    controller: 'PietanzaOrdinataDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PietanzaOrdinata', function(PietanzaOrdinata) {
                            return PietanzaOrdinata.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('pietanza-ordinata.new', {
            parent: 'pietanza-ordinata',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pietanza-ordinata/pietanza-ordinata-dialog.html',
                    controller: 'PietanzaOrdinataDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                numeroSequenza: null,
                                quantita: null,
                                note: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('pietanza-ordinata', null, { reload: 'pietanza-ordinata' });
                }, function() {
                    $state.go('pietanza-ordinata');
                });
            }]
        })
        .state('pietanza-ordinata.edit', {
            parent: 'pietanza-ordinata',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pietanza-ordinata/pietanza-ordinata-dialog.html',
                    controller: 'PietanzaOrdinataDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PietanzaOrdinata', function(PietanzaOrdinata) {
                            return PietanzaOrdinata.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('pietanza-ordinata', null, { reload: 'pietanza-ordinata' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('pietanza-ordinata.delete', {
            parent: 'pietanza-ordinata',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pietanza-ordinata/pietanza-ordinata-delete-dialog.html',
                    controller: 'PietanzaOrdinataDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['PietanzaOrdinata', function(PietanzaOrdinata) {
                            return PietanzaOrdinata.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('pietanza-ordinata', null, { reload: 'pietanza-ordinata' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
