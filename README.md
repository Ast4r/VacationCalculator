# О задании
Тестовое задание "Калькулятор отпускных" для NeoStudy курса по Java.
Используется Java 11 и Spring Boot 2.7.18. Система автоматической сборки Gradle.
API /calculate принимает среднюю зарплату сотрудника за год и количество дней отпуска. Пользователь получает ответ в виде json с количеством отпускных.
Можно указать дополнительный аргумент даты начала отпуска. Тогда отпускные будут посчитаны с учетом официальных праздников. Формат даты DD.MM.YYYY.
# API запрос
Запрос через curl или используя Postman для подсчета без учета даты начала отпуска.

[localhost:8080/calculate?averageSalary=100000&vacationDays=28](http://localhost:8080/calculate?averageSalary=100000&vacationDays=28 "localhost:8080/calculate?averageSalary=100000&vacationDays=28")

Запрос через curl или используя Postman для подсчета с учетом даты начала отпуска.

[localhost:8080/calculate?averageSalary=100000&vacationDays=28&startOfVacation=01.01.2024](http://localhost:8080/calculate?averageSalary=100000&vacationDays=28&startOfVacation=01.01.2024 "localhost:8080/calculate?averageSalary=100000&vacationDays=28&startOfVacation=01.01.2024")
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
