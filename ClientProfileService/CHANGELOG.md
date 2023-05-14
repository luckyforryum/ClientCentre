## Reference Documentation

модуль ClientProfileCommon - модуль который содержит в себе сущности для использования во всех микросервисах для достижения
консистентности при маштабировании и взаимодействии

### Изменения в МКС ClientProfileCommon
* [14.05.2022]
  add documentation, добавлен интерфейс IndividualService который реализует работу для использования в IndividualController
 метод которого создает пользователя и назначет ему поля имя и фамилию, и при вызове метода 
GET http://localhost:8080/individual/getClient в ответ получаем 
{
  "name": "Ilia",
  "surname": "Test"
  }
