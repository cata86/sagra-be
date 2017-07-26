(function() {
    'use strict';

    angular
        .module('sagrabeApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('serata', {
            parent: 'entity',
            url: '/serata',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sagrabeApp.serata.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/serata/seratas.html',
                    controller: 'SerataController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('serata');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('serata-detail', {
            parent: 'serata',
            url: '/serata/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sagrabeApp.serata.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/serata/serata-detail.html',
                    controller: 'SerataDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('serata');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Serata', function($stateParams, Serata) {
                    return Serata.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'serata',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('serata-detail.edit', {
            parent: 'serata-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/serata/serata-dialog.html',
                    controller: 'SerataDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Serata', function(Serata) {
                            return Serata.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('serata.new', {
            parent: 'serata',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/serata/serata-dialog.html',
                    controller: 'SerataDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                codice: null,
                                descrizione: null,
                                data: null,
                                dataApertura: null,
                                dataChiusura: null,
                                personaApertura: null,
                                personaChiusura: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('serata', null, { reload: 'serata' });
                }, function() {
                    $state.go('serata');
                });
            }]
        })
        .state('serata.edit', {
            parent: 'serata',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/serata/serata-dialog.html',
                    controller: 'SerataDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Serata', function(Serata) {
                            return Serata.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('serata', null, { reload: 'serata' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('serata.delete', {
            parent: 'serata',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/serata/serata-delete-dialog.html',
                    controller: 'SerataDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Serata', function(Serata) {
                            return Serata.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('serata', null, { reload: 'serata' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
