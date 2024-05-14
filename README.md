# О задании
Тестовое задание "Калькулятор отпускных" для NeoStudy курса по Java.
Используется Java 11 и Spring Boot 2.7.18. Система автоматической сборки Gradle.
API /calculate принимает среднюю зарплату сотрудника за год и количество дней отпуска. Пользователь получает ответ в виде json с количеством отпускных.
# API запрос
Запрос через curl или используя Postman:

[localhost:8080/calculate?averageSalary=100000&vacationDays=28](http://localhost:8080/calculate?averageSalary=100000&vacationDays=28 "localhost:8080/calculate?averageSalary=100000&vacationDays=28")
# Сборка
В директории проекта:

`gradle clean build`

Запуск:

`gradle bootRun`
# Docker
Создание образа:
`docker build . -t vacationCalculator`

Запуск контейнера:

`docker run -p 8080:8080 -d vacationCalculator`
