# 📝 Spring Boot стартер для логирования HTTP-запросов

Легковесный Spring Boot стартер, который обеспечивает функциональность логирования HTTP-запросов и ответов с
использованием AOP (Аспектно-ориентированного программирования).

## ✨ Возможности

- **Простое логирование HTTP**: Автоматически логирует все запросы и ответы к REST-контроллерам
- **Метрики производительности**: Отслеживает и логирует время выполнения запросов
- **Настраиваемость**: Легко включать/отключать и регулировать уровни логирования через свойства приложения
- **Минимальные зависимости**: Построен на системе стартеров Spring Boot с минимальными внешними зависимостями

## 🔧 Требования

- Java 23+
- Spring Boot 3.4.x
- Spring Web MVC

## 📦 Установка

### Публикация стартера в локальный репозиторий

Перед использованием стартера необходимо опубликовать его в локальный Maven репозиторий (.m2):

```bash
# Перейдите в директорию проекта стартера
cd path/to/my_logging

# Для Gradle
./gradlew publishToMavenLocal

# Для Maven
mvn clean install
```

### Подключение стартера в проект

После публикации добавьте зависимость в ваш Maven-проект:

```xml

<dependency>
    <groupId>org.vnsemkin</groupId>
    <artifactId>my_logging</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

Или для проектов на Gradle:

```groovy
implementation 'org.vnsemkin:my_logging:0.0.1-SNAPSHOT'
```

Убедитесь, что в вашем файле конфигурации Gradle или Maven указан локальный репозиторий:

```groovy
// Для Gradle
repositories {
    mavenLocal()
    mavenCentral()
}
```

```xml
<!-- Для Maven -->
<repositories>
    <repository>
        <id>local-maven-repo</id>
        <url>file:///${user.home}/.m2/repository</url>
    </repository>
</repositories>
```

## ⚙️ Настройка

Стартер включает разумные настройки по умолчанию, но может быть настроен через файл `application.yml` или
`application.properties`:

```yaml
# application.yml
logging:
  http:
    enabled: true         # Включить/отключить логирование HTTP (по умолчанию: true)
    level: INFO           # Уровень логирования: INFO, DEBUG, WARN, ERROR (по умолчанию: INFO)
```

Или через properties:

```properties
# application.properties
logging.http.enabled=true
logging.http.level=INFO
```

## 🔍 Как это работает

Этот стартер использует Spring AOP для перехвата всех методов в классах, аннотированных `@RestController`. Для каждого
запроса:

1. Детали запроса (метод, URL) логируются перед выполнением
2. Измеряется время выполнения
3. Статус ответа и время выполнения логируются после завершения

Пример вывода логов:

```
2025-03-06 10:15:23.456 INFO  [HTTP REQUEST] GET /api/v1/hello
2025-03-06 10:15:23.789 INFO  [HTTP RESPONSE] GET /api/v1/hello - Status: 200, Time: 333 ms
```

## 📚 Примеры использования

### 🔰 Базовое использование

Просто добавьте зависимость в ваш проект, и логирование HTTP будет включено по умолчанию для всех REST-контроллеров.

### 🔄 Расширенная настройка

Чтобы использовать другой уровень логирования:

```yaml
logging:
  http:
    level: DEBUG
  level: DEBUG  # Более детальное логирование
```

Чтобы отключить логирование HTTP:

```yaml
logging:
  http:
    enabled: false  # Полностью отключить логирование
```

## 📜 Лицензия

Этот проект распространяется под лицензией MIT — см. файл LICENSE для подробностей.

## 👨‍💻 Автор

[Владимир Семкин](https://github.com/vnsemkin)