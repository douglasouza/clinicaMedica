var clinicaMed = angular.module('clinicaMed');

clinicaMed.config(function ($stateProvider) {

    $stateProvider.state('login', {
        url: '/login',
        templateUrl: './app/components/login/view.html',
        controller: 'loginController',
        acesso: {
            loginRequerido: false
        }
    });
});