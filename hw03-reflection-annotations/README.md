# Домашнее задание по теме "Reflection, annotations".
## Свой тестовый фреймворк
**Цель**: научиться работать с reflection и аннотациями,
понять принцип работы фреймворка junit.


**Описание/Пошаговая инструкция выполнения домашнего задания**:
* Написать свой тестовый фреймворк.
* Поддержать свои аннотации `@Test`, `@Before`, `@After`.
* Запускать вызовом статического метода с именем класса с тестами.

Т.е. надо сделать:

- [x] создать три аннотации - `@Test`, `@Before`, `@After`.
- [x] Создать класс-тест, в котором будут методы, отмеченные аннотациями.
- [x] Создать "запускалку теста". На вход она должна получать имя класса с тестами, в котором следует найти и запустить методы отмеченные аннотациями пункта 1.
- [x] Алгоритм запуска должен быть следующий:
  * метод(ы) Before
  * текущий метод Test
  * метод(ы) After
  
  для каждой такой "тройки" надо создать СВОЙ объект класса-теста.
- [x] Исключение в одном тесте не должно прерывать весь процесс тестирования.
- [x] На основании возникших во время тестирования исключений вывести статистику выполнения тестов (сколько прошло успешно, сколько упало, сколько было всего)
- [x] "Запускалка теста" не должна иметь состояние, но при этом весь функционал должен быть разбит на приватные методы.
Надо придумать, как передавать информацию между методами.