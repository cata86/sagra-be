(function() {
    'use strict';

    angular
        .module('sagrabeApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('sagra', {
            parent: 'entity',
            url: '/sagra',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sagrabeApp.sagra.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/sagra/sagras.html',
                    controller: 'SagraController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('sagra');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('sagra-detail', {
            parent: 'sagra',
            url: '/sagra/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sagrabeApp.sagra.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/sagra/sagra-detail.html',
                    controller: 'SagraDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('sagra');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Sagra', function($stateParams, Sagra) {
                    return Sagra.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'sagra',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('sagra-detail.edit', {
            parent: 'sagra-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/sagra/sagra-dialog.html',
                    controller: 'SagraDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Sagra', function(Sagra) {
                            return Sagra.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('sagra.new', {
            parent: 'sagra',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/sagra/sagra-dialog.html',
                    controller: 'SagraDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                nome: null,
                                logo: null,
                                logoContentType: null,
                                indirizzo: null,
                                piva: null,
                                testataScontrino: null,
                                footerScontrino: null,
                                sequenzeAbilitate: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('sagra', null, { reload: 'sagra' });
                }, function() {
                    $state.go('sagra');
                });
            }]
        })
        .state('sagra.edit', {
            parent: 'sagra',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/sagra/sagra-dialog.html',
                    controller: 'SagraDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Sagra', function(Sagra) {
                            return Sagra.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('sagra', null, { reload: 'sagra' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('sagra.delete', {
            parent: 'sagra',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/sagra/sagra-delete-dialog.html',
                    controller: 'SagraDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Sagra', function(Sagra) {
                            return Sagra.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('sagra', null, { reload: 'sagra' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
