# My Recipes App

Este proyecto es una aplicación de recetas escrita en Kotlin para Android. La aplicación permite a los usuarios obtener una lista de recetas, relizar una búsqueda por el nombre de la receta, 
ir a los detalles de la receta y visualizar una descripción general de la misma así como sus ingredientes y los pasos a seguir.
Está diseñada siguiendo los principios de Clean Architecture y SOLID, y utiliza varias tecnologías y bibliotecas importantes para proporcionar una experiencia robusta y escalable.

|                                    Home                                     |                                     Search                                      |
|:---------------------------------------------------------------------------:|:-------------------------------------------------------------------------------:|
| ![Home Screen](https://github.com/awsalcedo/MyRecipes/blob/master/home.png) | ![Search Screen](https://github.com/awsalcedo/MyRecipes/blob/master/search.png) |

|                                     Detail                                      |                                    Map                                    |
|:-------------------------------------------------------------------------------:|:-------------------------------------------------------------------------:|
| ![Detail Screen](https://github.com/awsalcedo/MyRecipes/blob/master/detail.png) | ![Map Screen](https://github.com/awsalcedo/MyRecipes/blob/master/map.png) |

## Características principales

- Clean Architecture: El proyecto está estructurado siguiendo el patrón de Clean Architecture, lo que facilita la separación de responsabilidades y mejora la mantenibilidad del código.
- SOLID: Los principios SOLID (Single Responsibility, Open-Closed, Liskov Substitution, Interface Segregation, Dependency Inversion) se aplican en el diseño de la aplicación para promover un código limpio, modular y extensible.
- Inyección de Dependencias - Dagger-Hilt: Se utiliza Dagger-Hilt para realizar la inyección de dependencias, lo que simplifica la gestión de las dependencias y permite una mejor escalabilidad y prueba unitaria del código.
- Jetpack Compose: La interfaz de usuario se desarrolla utilizando Jetpack Compose, el moderno toolkit de UI de Android que facilita la creación de interfaces de usuario flexibles y dinámicas.
- Room: Se utiliza Room, la biblioteca de persistencia de Android, para almacenar los datos de los recetas en una base de datos local y permitir un acceso rápido y eficiente a ellos.
- Retrofit: Se utiliza Retrofit para realizar las llamadas a una API remota y obtener datos relacionados con los hábitos, lo que permite una sincronización eficiente y actualizada de la información.
- Unit Test: Se incluyen pruebas unitarias para verificar el correcto funcionamiento de los componentes clave de la aplicación y garantizar la calidad del código.
- UI Test: Se proporcionan pruebas de interfaz de usuario para verificar que la aplicación se comporte correctamente y proporcione una experiencia de usuario fluida.
- Offline-First: La aplicación está diseñada siguiendo el enfoque "Offline-First", lo que significa que la funcionalidad principal está disponible incluso cuando el dispositivo está sin conexión a Internet. Los datos se sincronizan automáticamente una vez que la conexión está disponible utilizando WorkManager y la API remota.

## Capas de Clean Architecture

Es un enfoque de diseño de software que promueve la creación de sistemas escalables, mantenibles y testeables, al tiempo que facilita la separación de preocupaciones y la independencia de frameworks. Se basa en el principio de dividir un sistema en capas bien definidas y con dependencias unidireccionales, lo que permite un alto grado de desacoplamiento y flexibilidad.
La estructura del proyecto se ha dividido en tres capas:

### Presentation
Responsabilidad: La capa de Presentación es la interfaz de usuario de la aplicación y se encarga de la lógica relacionada con la presentación de datos y la interacción con el usuario.
Componentes: Composables, ViewModel en arquitecturas MVVM.

### Domain
Responsabilidad: La capa de Dominio contiene la lógica empresarial o de negocio de la aplicación, independiente de cualquier tecnología específica.
Componentes: Casos de Uso (Use Cases o Interactors), Interfaces de Repositorio (Repository Interfaces), Modelos de Datos (Data Models).

### Data
Responsabilidad: La capa de Datos se encarga de acceder y manipular los datos de la aplicación, ya sea desde una base de datos local, servicios web remotos u otras fuentes de datos.
Componentes: Implementaciones de Repositorio (Repository Implementations), Fuentes de Datos (Data Sources), Servicios Web (Web Services), Bases de Datos (Databases), Mapeadores (Mappers) para convertir entre modelos de datos y entidades.

## Third-party library

## Dagger Hilt

Dagger Hilt es un framework de inyección de dependencias diseñado para simplificar y agilizar el desarrollo de aplicaciones Android utilizando Kotlin. 
Desarrollado por Google y basado en Dagger, Hilt proporciona una manera elegante y eficiente de gestionar la inyección de dependencias en aplicaciones Android, facilitando la escritura de código limpio, mantenible y fácilmente testeable.
Hilt proporciona una forma estándar de utilizar la inyección de dependencias en su aplicación al proporcionar contenedores para cada clase de Android en su proyecto y administrar sus ciclos de vida automáticamente. Hilt se basa en la popular biblioteca de inyección de dependencias Dagger y se beneficia de la corrección en tiempo de compilación, el rendimiento en tiempo de ejecución, la escalabilidad y la compatibilidad con Android Studio que proporciona.

## Retrofit

Retrofit es un cliente REST de tipo seguro para Android que proporciona un marco potente para autenticar e interactuar con API y enviar solicitudes de red con OkHttp.
Esta biblioteca facilita bastante la descarga de datos JSON o XML desde una API web. Una vez que se descargan los datos, se analizan en una clase de datos que debe definirse para cada "recurso" en la respuesta.

## MockK
MockK es un framework de prueba para Kotlin que proporciona capacidades de creación y manipulación de objetos simulados (mocks) y objetos simulados parciales (spies) para facilitar las pruebas unitarias y de integración. 
Permite crear mocks y definir su comportamiento de una manera concisa y expresiva utilizando una sintaxis fluida en Kotlin.

Es altamente integrable con los marcos de prueba más populares, como JUnit y es ampliamente utilizado en el desarrollo de aplicaciones Kotlin para escribir pruebas efectivas y mantenibles.


## Uso
* Abrir el proyecto en Android Studio y clonar el repositorio del proyecto desde GitHub: https://github.com/awsalcedo/MyRecipes.git
* Agregar al archivo local.properties la clave GM_API_KEY=AIzaSyCbsaHSqk4leo8lIgoapjf-GEjhhQc3Qns para asegurar la clave de Google Maps, clave proporcionada temporalmente.
* Ir al menú Build de Android Studio, luego seleccionar la opción del submenú Rebuild Project para volver a generar el BuildConfig.
* Ejecutar la aplicación.