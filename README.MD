Веб сервис управления телеграм ботом.
##
Spring Boot, SpringMVC, Spring Security, Hibernate, JUnit, PostgreSQL, H2, Maven, Tomcat
##
Склонировать репозиторий git clone https://github.com/RabyAliaksandr/bot.git
##
Собрать проект с помощью maven mvn clean install
##
Запустить приложение с помощью mvn spring-boot:run
##
имя телеграм бота: 'City_InfoBot'
##
токен: '1277942386: AAHMyDv_vcjfDBjWidIMkpx4QGYVpSDTE-0'
##
после создания и подключения базы данных (скрипт прлагается), необходимо создать пользователя.
неавторизованный пользователь не сможет воспользоваться сервисом.
тестирование производилось с помощью Postman.
примеры:
##
регистрация пользователя
метод Post, http://localhost:8080/auth/signin
{   
    "name":"Admin",
    "surname":"Admin",
    "login":"Admin",
    "password":"gfhjkm",
    "email":"email@email.com",
    "role":"ADMIN"
}
##
вход в систему
метод Post http://localhost:8080/auth/signin
{   
    "usernameOrEmail":"",
    "password":""
}

##
добавление города
метод Post http://localhost:8080/city
{   
    "name":"",
    "description":{
        "description":""
    }
}

##
изменение города
метод Put
http://localhost:8080/city
{   
    "id":,
    "name":"",
    "description":{
        "id":,
        "description":""
    }
}
##
удаление города
метод Post
http://localhost:8080/city/id
где id - id удаляемого города