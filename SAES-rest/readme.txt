#REST API COMMANDS EXAMPLE
#
#there are three users pre-created, with id's: 1-3

#LIST USERS
curl -i -X GET http://localhost:8080/pa165/rest/users/list

#GET USER
curl -i -X GET http://localhost:8080/pa165/rest/users/3

#CREATE USER
curl -X POST -i -H "Content-Type: application/json" --data '{"username":"Peter","password":"heslo","sex":"MALE","weight":82,"age":36}' http://localhost:8080/pa165/rest/users/create

#UPDATE USER
curl -X PUT -i -H "Content-Type: application/json" --data '{"id":2,"username":"erik","password":"erik","sex":"MALE","weight":76,"age":25}' http://localhost:8080/pa165/rest/users/update

#CHECK IF USER IS ADMIN
curl -i -X GET http://localhost:8080/pa165/rest/users/admin/1

#DELETE USER
curl -i -X DELETE http://localhost:8080/pa165/rest/users/2