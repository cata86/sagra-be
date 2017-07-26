(function() {
    'use strict';

    angular
        .module('sagrabeApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('tavolo-accomodato', {
            parent: 'entity',
            url: '/tavolo-accomodato?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sagrabeApp.tavoloAccomodato.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tavolo-accomodato/tavolo-accomodatoes.html',
                    controller: 'TavoloAccomodatoController',
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
                    $translatePartialLoader.addPart('tavoloAccomodato');
                    $translatePartialLoader.addPart('tavoloStato');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('tavolo-accomodato-detail', {
            parent: 'tavolo-accomodato',
            url: '/tavolo-accomodato/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sagrabeApp.tavoloAccomodato.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tavolo-accomodato/tavolo-accomodato-detail.html',
                    controller: 'TavoloAccomodatoDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('tavoloAccomodato');
                    $translatePartialLoader.addPart('tavoloStato');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'TavoloAccomodato', function($stateParams, TavoloAccomodato) {
                    return TavoloAccomodato.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'tavolo-accomodato',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('tavolo-accomodato-detail.edit', {
            parent: 'tavolo-accomodato-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tavolo-accomodato/tavolo-accomodato-dialog.html',
                    controller: 'TavoloAccomodatoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['TavoloAccomodato', function(TavoloAccomodato) {
                            return TavoloAccomodato.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tavolo-accomodato.new', {
            parent: 'tavolo-accomodato',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tavolo-accomodato/tavolo-accomodato-dialog.html',
                    controller: 'TavoloAccomodatoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                codice: null,
                                descrizione: null,
                                numCoperti: null,
                                stato: null,
                                accomodatoOrario: null,
                                accomodatoPersona: null,
                                inOrdinazioneOrario: null,
                                inOrdinazionePersona: null,
                                ordinazioneOrario: null,
                                ordinazionePersona: null,
                                liberatoOrario: null,
                                liberatoPersona: null,
                                asporto: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('tavolo-accomodato', null, { reload: 'tavolo-accomodato' });
                }, function() {
                    $state.go('tavolo-accomodato');
                });
            }]
        })
        .state('tavolo-accomodato.edit', {
            parent: 'tavolo-accomodato',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tavolo-accomodato/tavolo-accomodato-dialog.html',
                    controller: 'TavoloAccomodatoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['TavoloAccomodato', function(TavoloAccomodato) {
                            return TavoloAccomodato.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tavolo-accomodato', null, { reload: 'tavolo-accomodato' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tavolo-accomodato.delete', {
            parent: 'tavolo-accomodato',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tavolo-accomodato/tavolo-accomodato-delete-dialog.html',
                    controller: 'TavoloAccomodatoDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['TavoloAccomodato', function(TavoloAccomodato) {
                            return TavoloAccomodato.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tavolo-accomodato', null, { reload: 'tavolo-accomodato' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
