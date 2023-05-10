# Readme для тайм-трекера

## Описание общей концепции таймера
В приложении существуют пользователи. Пользователь может создавать группы таймеров, в которых может создавать таймеры.

Таймер при создании имеет статус 'default' и не считает время.
Таймер может иметь ещё три состояния (помимо 'default'): run, stop, pause.

В состоянии 'run' таймер отсчитывает время.

В состоянии 'pause' таймер не отсчитывает время, но считается время паузы. После запуска из паузы таймер стартует
со значения до паузы.

В состоянии 'stop' таймер не считает время и не считает время простоя. Из этого состояния таймер нельзя поставить
на паузу или запустить.

Активен (в статусе 'run') может быть только один таймер. При запуске другого таймера активный ставится на паузу.

## Описание эндпоинтов контроллеров
Дополнительные данные запросов передаются в параметрах запроса
### UserController
Создание пользователя

    @PostMapping
    public ResponseEntity createUser(@RequestParam String email, @RequestParam String password)

Получение пользователя
    
    @GetMapping
    public ResponseEntity getUser(@RequestParam Long id)

### GroupController
Создание группы таймеров

    @GetMapping    
    public ResponseEntity createGroup(@RequestParam String title, @RequestParam Long userID)

Получение одной группы по ID

    @GetMapping
    public ResponseEntity getOneGroup(@RequestParam Long groupID)

Удаление группы по ID

    @DeleteMapping
    public ResponseEntity deleteGroup(@RequestParam Long groupID)  

Изменение названыя группы по ID

     @PatchMapping
    public ResponseEntity changeGroupTitle(@RequestParam Long groupID, @RequestParam String newTitle)

Получение всех групп по ID пользователя

     @GetMapping("/getAll")
    public ResponseEntity getGroupsByUser(@RequestParam Long userID)

### TimerController
Создание нового таймера
    
    @PostMapping
    public ResponseEntity createTimer(@RequestParam String title, @RequestParam Long groupID)

Получение таймера по ID

    @GetMapping
    public ResponseEntity getOneTimer(@RequestParam Long timerID)

Получение всех таймеров по ID группы

    @GetMapping("/getAll")
    public ResponseEntity getTimersByGroup(@RequestParam Long groupID)

Удаление таймера по ID

    @DeleteMapping
    public ResponseEntity deleteTimer(@RequestParam Long timerID)

Удаление всех таймеров группы по ID группы

    @DeleteMapping("/deleteAll")
    public ResponseEntity deleteTimersByGroup(@RequestParam Long groupID)


Изменение названия таймера по ID таймера

    @PatchMapping("/changeTitle")
    public ResponseEntity changeTimerTitle(@RequestParam Long timerID, @RequestParam String newTitle)

Изменение статуса таймера по ID

    @PatchMapping("/changeStatus")
    public ResponseEntity changeTimerStatus(@RequestParam Long timerID, @RequestParam String newStatus)

Создание отчета в формате txt по всей группе в формате <название таймера>: <значение>

    @GetMapping("/report")
    public ResponseEntity saveReport(@RequestParam Long groupID)

Изменение статуса всех таймеров группы (на 'pause' или 'stop'). Статусы 'default' и 'stop' остаются неизменными

    @PatchMapping("/pauseAll")
    public ResponseEntity pauseOrStopThisGroup(@RequestParam Long groupID, @RequestParam String newStatus) {