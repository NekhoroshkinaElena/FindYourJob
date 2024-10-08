# Приложение для поиска вакансий

(Дипломный проект Яндекс.Практикума по курсу "Android-разработчик")

Дипломный проект представляет собой небольшое приложение для поиска работы,
использующее [API сервиса HeadHunter](https://github.com/hhru/api). Приложение предоставляет следующую функциональность:

- Поиск вакансий
- Указание фильтров для поиска
- Просмотр деталей отдельной вакансии
- Добавление вакансий в список "Избранного"

## Используемый стек: 
* MVVM
* Coroutines
* SQLite (Room)
* Retrofit
* Jetpack Navigation
* Koin
* Glide

## Главный экран - экран поиска вакансий

На этом экране пользователь может искать вакансии по любому непустому набору слов поискового запроса. Результаты поиска
представляют собой список, содержащий краткую информацию о вакансиях.

![image](https://github.com/user-attachments/assets/dc7beae2-f81b-4873-a807-32a87a32e0bb)


### Особенности экрана

- По умолчанию, поиск происходит по всей доступной базе вакансий без учёта региона, отрасли компании и уровня зарплаты и
  валюты.

## Фильтрация - набор экранов фильтров поиска

Используя настройки фильтра, пользователь может уточнить некоторые параметры поиска, который осуществляется на экране
"Поиск". Фильтр позволяет указать:

- Место работы - регион, населённый пункт, указанный в вакансии как рабочая локация.
- Отрасль - сфера деятельности организации, разместившей вакансию.
- Уровень зарплаты - уровень ЗП, соответствующий указанному в вакансии.
- Возможность скрывать вакансии, для которых не указана ЗП.

![image](https://github.com/user-attachments/assets/3087c78e-709a-4978-b41a-a9a50c4a69b1)


### Особенности экранов

- Параметры фильтра не являются обязательными - пользователь может уточнить любой параметр из предложенных, а может не
  указывать ничего.

## Экран просмотра деталей вакансии

Нажав на элемент списка найденных вакансий (аналогично в списке избранного), пользователь попадает на
экран с подробным описанием вакансии. Помимо уровня ЗП, требуемого опыта и графика работы пользователь может на этом
экране увидеть:

- Информацию о работодателе
- Подробное описание вакансии
- Перечень требуемых ключевых навыков

 ![image](https://github.com/user-attachments/assets/2530315c-a3ed-486d-853e-24dd29cef5b0)


## Экран избранных вакансий

Пользователь может добавлять вакансии в "Избранное", чтобы иметь возможность быстро вернуться к заинтересовавшему его
предложению. Добавить вакансию в "избранное" (или удалить из "избранного") можно на экране "Вакансия". Все вакансии,
добавленные в закладки, можно увидеть на отдельном экране в приложении.

![image](https://github.com/user-attachments/assets/39ca592f-7d4c-485e-973a-c2b1f85cfeeb)


## Экран информации о команде разработчиков

![image](https://github.com/user-attachments/assets/221ae23a-a819-48e4-a0d1-77f1c893a17d)


