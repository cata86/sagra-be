(function() {
    'use strict';

    angular
        .module('sagrabeApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('pietanza-categoria', {
            parent: 'entity',
            url: '/pietanza-categoria',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sagrabeApp.pietanzaCategoria.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/pietanza-categoria/pietanza-categorias.html',
                    controller: 'PietanzaCategoriaController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('pietanzaCategoria');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('pietanza-categoria-detail', {
            parent: 'pietanza-categoria',
            url: '/pietanza-categoria/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sagrabeApp.pietanzaCategoria.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/pietanza-categoria/pietanza-categoria-detail.html',
                    controller: 'PietanzaCategoriaDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('pietanzaCategoria');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'PietanzaCategoria', function($stateParams, PietanzaCategoria) {
                    return PietanzaCategoria.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'pietanza-categoria',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('pietanza-categoria-detail.edit', {
            parent: 'pietanza-categoria-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pietanza-categoria/pietanza-categoria-dialog.html',
                    controller: 'PietanzaCategoriaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PietanzaCategoria', function(PietanzaCategoria) {
                            return PietanzaCategoria.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('pietanza-categoria.new', {
            parent: 'pietanza-categoria',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pietanza-categoria/pietanza-categoria-dialog.html',
                    controller: 'PietanzaCategoriaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                codice: null,
                                descrizione: null,
                                descrizioneBreve: null,
                                nomeStampante: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('pietanza-categoria', null, { reload: 'pietanza-categoria' });
                }, function() {
                    $state.go('pietanza-categoria');
                });
            }]
        })
        .state('pietanza-categoria.edit', {
            parent: 'pietanza-categoria',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pietanza-categoria/pietanza-categoria-dialog.html',
                    controller: 'PietanzaCategoriaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PietanzaCategoria', function(PietanzaCategoria) {
                            return PietanzaCategoria.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('pietanza-categoria', null, { reload: 'pietanza-categoria' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('pietanza-categoria.delete', {
            parent: 'pietanza-categoria',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pietanza-categoria/pietanza-categoria-delete-dialog.html',
                    controller: 'PietanzaCategoriaDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['PietanzaCategoria', function(PietanzaCategoria) {
                            return PietanzaCategoria.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('pietanza-categoria', null, { reload: 'pietanza-categoria' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
