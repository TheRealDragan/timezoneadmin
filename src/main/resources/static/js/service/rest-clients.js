app.factory('RESTClient', function($resource, $rootScope){
    return $resource(
        '/timezoneadmin/:param1/:param2/:param3',
        {},
        {
            register: {
                method: 'POST',
                params: {
                    param1: 'registration',
                    param2: 'register-user'
                },
                isArray: false
            }, 
            login: {
                method: 'POST',
                params: {
                    param1: 'login',
                    param2: 'token'
                },
                isArray: false
            },
            addUser: {
                method: 'POST',
                params: {
                    param1: 'admin',
                    param2: 'users'
                },
                isArray: false
            },
            addTimezone: {
                method: 'POST',
                params: {
                    param1: 'admin',
                    param2: 'timezones'
                },
                isArray: false
            },
            loadUsers: {
                method: 'GET',
                params: {
                    param1: 'admin',
                    param2: 'users'
                },
                isArray: true
            },
            deleteUser: {
                method: 'DELETE',
                params: {
                    param1: 'admin',
                    param2:'users'
                },
                isArray: false
            },  currentGmtMilis: {
                method: 'GET',
                params: {
                    param1: 'time',
                    param2:'current-gmt-milis'
                },
                isArray: true
            },
            loadTimezones: {
                method: 'GET',
                params: {
                    param1: 'admin',
                    param2: 'timezones'
                },
                isArray: true
            }
        }
    );
});