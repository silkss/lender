# Сборка #
** mvn compile war:war **

# Развертывание #
Для развертывания использовал **Tomcat11**.
Запускаем службу **Tomcat11**
В *localhost:8080/manager/html* добавляем lender.war

# Endpoints #

***/lender*** - главная страница.
***/lender/customer/all*** - список всех пользователей.
***/lender/customer/search*** - поиск по пользователям.
***/lender/request/create*** - создать заявку на кредит.
***/lender/request/approved*** - список всех одобренныех заявок.
***/lender/loan/contract/signed*** - список всех **подписанных** контрактов.
***/lender/loan/contract/all*** - список **всех** контратов.

# Проблемы #
1. Нет GUI для поиска пользователей. Поиск работает только через ручной ввод запроса. Например: */lender/customer/search?name=Петя*
2. Нет проверки на дублирующее создание пользователя.
3. Нет стилей.

# БД #
Для БД использовал контейнер postgresql в Docker.
Для развертывания контейнера: **docker run --name [<enter_container-name>] -p 5432:5432 -e POSTGRES_PASSWORD=[<enter_password>] -d postgres**