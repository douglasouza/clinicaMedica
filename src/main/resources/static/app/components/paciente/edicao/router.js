var clinicaMed = angular.module('clinicaMed');

clinicaMed.config(function ($stateProvider) {

        $stateProvider.state('paciente.novo', {
            url: '/novo',
            templateUrl: './app/components/paciente/edicao/view.html',
            controller: 'pacienteEdicaoController',
            acesso: {
                loginRequerido: true,
                usuariosAutorizados: ['ADMINISTRADOR', 'RECEPCIONISTA']
            }
        });

        $stateProvider.state('paciente.edicao', {
            url: '/editar/:id',
            templateUrl: './app/components/paciente/edicao/view.html',
            controller: 'pacienteEdicaoController',
            acesso: {
                loginRequerido: true,
                usuariosAutorizados: ['ADMINISTRADOR', 'RECEPCIONISTA']
            }
        });

    }
);