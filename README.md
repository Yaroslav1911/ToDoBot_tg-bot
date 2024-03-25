  This is functionally complete code of tg-bot. In this bot you can add your goal, write a description for it, add deadline & reminder date. If you add reminder, ToDoBot will send you a message in chat whit your goal. It's still lack of some tests, and this bot won't work until I turn on it on my server. So after I finish with tests, I'll try to host this bot on some cloud service.
  
  I'll be glad if this code helps you in some way. So you can freely use it for your personal needs only, but not in commercial. 

  **************

  Це функціонально повний код tg-bot. У цьому боті ви можете додати свою ціль, написати її опис, додати крайній термін і дату нагадування. Якщо ви додасте нагадування, ToDoBot надішле вам повідомлення в чаті з вашою метою. Ще бракує деяких тестів, і цей бот не працюватиме, доки я не ввімкну його на своєму сервері. Тож після завершення тестів я спробую розмістити цього бота на якомусь хмарному сервісі.
  
  Я буду радий, якщо цей код допоможе вам у чомусь. Тож ви можете вільно використовувати його лише для особистих потреб, але не для комерційних.


I used this list of 'tools':
  - Spring Boot
  - telegram-api
  - PostgreSQL
  - JPA
  - flywaydb
  - Docker
With localization in two languages, English and Ukrainian.




If you want to implement full project you'll need the Dockerfile & application.yaml.

First, create 'docker' folder in your main project, and create a file with name 'Dockerfile' like on this pic:

![docker_creation](https://github.com/Yaroslav1911/ToDoBot_tg-bot/assets/145599115/8b545250-eedd-4059-980e-07379cd65115)

Then put this code in Docker file with yours creddentials:

![docker_file](https://github.com/Yaroslav1911/ToDoBot_tg-bot/assets/145599115/8b3c81cd-172a-4992-afdf-0317e4f95df9)

Second, create 'application.yaml' file in resources:

![application_creation](https://github.com/Yaroslav1911/ToDoBot_tg-bot/assets/145599115/efc4c9e5-f567-497f-837a-72fb5788299e)

'''
And put this code 
'''
