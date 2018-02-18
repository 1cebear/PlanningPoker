# PlanningPoker
Задание.

Написать веб-приложение для оценки затрат (estimate) набора задачи (stories) командой (Planning Poker).



Объекты.

    Каждый пользователь имеет имя, email, роль (Ведущий или участник), состояние (активный/неактивный).

    Роли пользователя: ведущий (moderator), участник (team member).

    Карты в колоде имеют значения (story points) 0, 1/2, 1, 2, 3, 5, 8, 13, 20, 40, 100, ?, "Invalid", "Won't Fix", "Duplicate".

    Задача (story) описывается через название (summary), описание (description) и ссылку на баг-трекер (link).

    Панель задач - общий набор задач на единой панели, где показывается только название и сервисная информация.

    Панель участников - отображается имя, статус оценки (красный - ни одна задача не оценена, желтый - в процессе, зелёный - все задачи проэстимированы)

Поведение.

    Ведущий может добавлять/удалять участников покера планирования.

    Ведущий имеет возможность создать набор задач (stories) для оценки времени (story set).

    Ведущий может делать сеты наборы активными (только один может быть активным в какой-то момент времени) и деактивировать (откладывать).

    Ведущий имеет возможность запустить оценку в работу (все могут начать оценивать) и вскрыть карты (показать результаты всем).

    Когда ведущий делает набор активным, активные участники получают уведомление на почту со ссылкой на игру.

    Каждый участник видит задачи по одной, но может перейти вперед/назад по задачам, поставить оценку (никому не видна, кроме него) или написать комментарий (виден всем).

    Каждая задача имеет собственный простой чат (имя участника: текст), где каждый может писать и видеть сообщения других.

    Если в чате есть хотя бы одно сообщение, на панели задач отображается какой-либо значок (например, цветной кружок), показывающий, что для задачи есть непустой чат.

    Нажатие на название задачи в панели вызывает переход пользователя на эту задачу. Активная задача подсвечивается соответственно в панели цветом или как-либо ещё.

    В верхней части окна показывается вся колода карта. Участник простым нажанием выбирает оценку для задачи, при этом автоматически переходит к следующей задаче.

    Ведущий также может оценивать задачи, не видя оценок других, как и все.

    Когда участник выполнил все оценки для всех задач в активном наборе (любая выбранная карта является оценкой), его статус помечается зелёным, он всё ещё может участвовать в обсуждениях и менять оценки.

    Когда все активные участники выполнили оценку, все карты автоматически открываются, и для каждой задачи можно видеть, какую оценку поставил каждый участник (таблица или как-либо ещё).

    Если оценки всех участников совпадают или отличаются на одну ступень (для чисел), задача показывает итоговую оценку как максимальную из двух. Это правило не работает, если только один участник указал оценку выше, чем остальные.



Дополнительные требования к архитектуре приложения.

    Вся бизнес логика должна быть построена на Web-сервисах. Не должно быть прямых обращений со страниц к базе данных (все только через веб сервисы).

    Приложение должно быть устойчиво к ошибкам (корректный error handling).

    Использование хранимых процедур в данном задании не является обязательным.

    База данных может быть заменена на ту, которая использует компактную СУБД и может быть развёрнута без установки дополнительного ПО (например, SQLite).

    Язык приложения - английский. Базовые термины описаны, любые сообщения об ошибках и логи должны быть на английском в том числе.