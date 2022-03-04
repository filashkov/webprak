# Схема базы данных
![Image alt](./database_scheme.png)
# Схема навигации страниц
![Image alt](./navigation_scheme.png)
# Страницы приложения
### Главная страница
Шапка страницы
+ Кнопка для перехода на страницу регистрации или входа
+ Кнопка перехода на страницу информации о компании
+ Если пользователь авторизовался, то есть возможность перехода на страницу "личный кабинет"

Главная часть страницы
+ Каталог услуг, название вместе с описанием и ценами. Есть возможности фильтрации (например, отображение услуг в определённом интервале цен или только услуг, связанных с определённой областью, например, услуги с тегом "защита потребителей") и возможности настройки порядка отображения услуг в каталоге (по популярности / по цене | по убыванию / по возрастанию). Авторизованный клиент может заказать услугу, выбрав нужную в каталоге услуг и нажав кнопку `оформить`
  
Внизу главной страницы (footer)
+ Информация о контактах фирмы (ссылки на соцсети) и адрес фирмы

### Страница информации о компании
Описывает компанию, её устройство и историю, лицензию на ведение юридической деятельности и другие юридические аспекты.

### Страница регистрации / входа
Для **регистрации** содержатся поля `имя`, `контактный телефон`, `email`, `логин` (здесь есть возможность позволить клиенту не придумывать логин, а использовать в качестве логина номер контактного телефон или адрес электронной почты), `пароль` и поле `подтверждение пароля`. Далее кнопка `регистрация`.

Для **авторизации уже существующей учётной записи** есть два поля: `логин` - в качестве логина используется контактный телефон или email и `пароль`. Далее кнопка `авторизация`.

### Страница личный кабинет клиента
*Эта страница доступна только тем, кто авторизовался как клиент.*

Наверху поля, отображающие информацию о клиенте, которые сам клиент может редактировать. Есть кнопка `удалить аккаунт`. Далее чуть ниже идёт список незаконченных услуг, а ещё ниже список завершённых услуг данного клиента.

### Страница личный кабинет сотрудника
*Эта страница доступна только тем, кто авторизовался как сотрудник.*

Наверху поля, отображающие информацию о сотруднике, которые сам сотрудник может редактировать. Есть поля для создания клиентского аккаунта. Далее список активных услуг: то есть тех, которые оказывает данный сотрудник на данный момент. Активную услугу служащий может завершить. Внизу список завершённых услуг данным сотрудником.

### Страница услуги
*Эта страница доступна только тем, кто авторизовался как сотрудник.*

На этой страницы отображаются все услуги клиентов. Отображаются поля название услуги-договора, дата начала, имя клиента и логин, который заказал данный контракт и несколько строк из поля дополнительной информации. Есть возможность настройки параметров отображения: например, услуг, оказанных в определённый период времени, определёнными сотрудниками, определённым клиентам. Есть кнопка `добавить услугу`, к которой прилагаются поля: `логин клиента`, `участвующие сотрудники`, `тип услуги`. У сотрудника для каждой активной услуги есть кнопка `заняться услугой` или `перестать заниматься услугой`. Для активной (незавершённой) услуги у сотрудников есть возможность редактирования информации об услуги, например, её стоимости. Также у сотрудника есть кнопка `завершить услугу`, тогда услуга считается завершённой и отправляется в список завершённых услуг.

### Страница клиенты
*Эта страница доступна только тем, кто авторизовался как сотрудник.*

Отображает список всех клиентов и услуг, которые они заказывали. Отображется ФИО клиента, его контактный телефон, email и логин. Служающие могут просматривать любую информацию о любом клиенте (кроме пароля клиента).

### Панель управления администраторов
*Эта страница доступна только администраторам.*

Кнопка регистрации нового служающего: поля информации о сотруднике (имя, пароль, контактный телефон, email, логин и пароль).

Есть полная возможность редактирования любой информации о сотрудниках, клиентах, контрактах и услугах. Для этого на панели администратора есть возможность отобразить соответсвующие списки, есть возможности настройки параметров отображения и отредактировать соответствующие поля.

# Сценарии использования
Ниже приведены сценарии использования. Нашим сайтом будут пользоваться разные пользователи, их можно разделить на три категории, каждая категория имеет разные привелегии.

Администраторы базы имеют полный доступ ко всему, они могут просматривать / редактировать / удалять любую информацию о клиентах, сотрудниках и заказах.

Права клиентов и сотрудников фирмы планируется разграничивать с помощью учётных записей. Возможность создать аккаунт клиента есть у каждого, кто зайдёт на сайт. Возможность создать аккаунт сотрудника есть только у администратора.

### Примеры сценариев использования

**Для клиентов, зашедших на сайт**
+ Просмотр каталога услуг (не требует регистрации). Для этого просто заходим на главную страницу
+ Регистрация клиентом своего аккаунта. В шапке главной странице есть кнопка перехода на страницу регистрации или входа
+ Заказать новую юридическую услугу (требует регистрации). Для этого у зарегистрированных пользователей есть возможность выбрать услугу в каталоге услуг на главной странице и нажать кнопку `оформить`
+ Просмотреть информацию о всех предыдущих услугах своего акканута (требует регистрации). Для этого нужно перейти со главной странице в личный кабинет пользователя, у авторизированных пользователей в шапке главной страницы есть ссылка на страницу личного кабинета
+ Удаление клиентом своего аккаунта (требует регистрации). Для этого авторизированный клиент, зайдя в личный кабинет, нажимает кнопку `удалить аккаунт`

**Сотрудники**
+ Создание учётной записи клиента. Для этого сотрудник переходит в свой личный кабинет, по ссылке в шапке главной страницы
+ Добавление услуги к аккаунту клиента. Для этого сотрудник из личного кабинета переходит на страницу клиенты, выбирает нужного клиента и добавляет ему определённую услугу из каталога услуг с помошью соответствующей кнопки
+ Просмотр истории услуг любого клиента. Для этого сотрдуник заходит на страницу услуги и в поле для фильтрации отображения вводит логин клиента
+ Изменение информации своего аккаунта сотрудника. Такая вохможность есть в личной кабинете сотрудника.

**Администраторы базы**

У администраторов базы есть полный доступ ко всей информации, ниже приведены наиболее важные операции, которые должны будут делать администраторы базы.
+ Создание учётной записи для нового клиента / служающего / администратора. Для этого администратор заполняет соответствующие поля (имя, логин, пароль, тип привилегии)
+ Изменение информации о любом сотруднике (сюда входит любая информация, связанная с сотрудником, а также есть вохможность изменения пароля). На панели администратора есть возможность отобразить список все учётные записи, выюирает среди них только сотрудников и изменяет любое информационное поле о нём
+ Удаление информации о любом сотруднике. Администратор отображает список всех сотрудников и нажимает кнопку `удалить` напротив определённого сотрудника
+ Добавление / изменение / удаление информации о клиентах. Для этого администратор отображает список всех учётных записей и выбирает тех, которые имеют уровень привелегии клиента
+ Добавление / изменение / удаление списка договоров. Для этого администратор отображает список всех договоров и с помощью web-интерфейса редактирует соответствующие поля
+ Добавление / изменение / удаление услуги в каталоге услуг. Для этого администратор отображает список всех услуг и с помошью web-интерфейса редактирует соответствующие поля