# PARTE TEORICA

### Arquitecturas de UI: MVP, MVVM y MVI

#### MVVM

##### ¿En qué consiste esta arquitectura?
Model View ViewModel (MVVM) es un patrón de diseño que tiene los siguientes componentes:

- **Model:** componente que permite la gestión de datos, es decir donde se define los tipos de datos para poder editarlos, guardarlos o recuperarlos desde la memoria del dispositivo o una base de datos. Suministra datos a ViewModel quien le emite eventos para poder editar los datos.
- **View:** componente que permite la visualización de información, es decir muestra la información a través de la interfaz gráfica y gestiona las interacciones con el usuario mediante la implementación de una actividad o fragmento (Android).
- **ViewModel:** componente que permite suministrar enlaces a la vista para pasarle eventos al modelo y trasladar la información almacenada en el modelo a la vista, de tal manera que, si la vista actualiza un valor en la interfaz gráfica, el modelo se actualiza automáticamente. El ViewModel se mantiene en memoria mientras el objeto al que pertenece no haya finalizado, lo que permite responder correctamente al ciclo de vida de la aplicación.

##### ¿Cuáles son sus ventajas?
- La lógica de negocio está desacoplada de la interfaz de usuario.
- El mantenimiento de las aplicaciones es simplificado y mas fácil para realizar pruebas.
- Los componentes pueden ser reutilizados. 

##### ¿Qué inconvenientes tiene?
- La distribución de componentes nos obliga a la creación y mantenimiento de un mayor número de ficheros.
- La curva de aprendizaje para nuevos desarrolladores es un poco superior a los otros modelos que son más simples.
- Se debe adaptar a una estructura predefinida, lo cual puede incrementar la complejidad de la aplicación.

#### MVP

##### ¿En qué consiste esta arquitectura?
Model View Presenter (MVP) es un patrón de diseño muy similar al MVVM, tiene los siguientes componentes: 

- **Model:** es donde se lleva a cabo la lógica de negocio.
- **View:** compuesta por las actividades y controles que forman parte de la interfaz de usuario.
- **Presenter:** escucha los eventos que se producen en la vista y ejecuta las acciones necesarias a través del modelo.

##### ¿Cuáles son sus ventajas?
- El código de las vistas es relativamente sencillo, no se asignan muchas tareas a esta capa por lo que cumple con su objetivo de sencillez y rapidez.
- La vista y el modelo pueden cambiar y ser muy diferentes sin que eso afecte demasiado a la aplicación

##### ¿Qué inconvenientes tiene?
- Requiere una gran cantidad de código que no vale la pena en aplicaciones fáciles y simples.
- Posee dependencia con el componente "Presentador".
- Consume bastante memoria por que depende de los atributos.
- El mapeo entre propiedades y controles puede llegar a ser tedioso.

#### MVI

##### ¿En qué consiste esta arquitectura?
 Model View Intent (MVI) es un patrón de diseño que tiene los siguientes componentes:
 
- **Model:** componente de gestión de datos y además representa los diferentes estados de la aplicación.
- **View:** componente de visualización y se encarga de actualizar la información en pantalla a través de la interfaz y en función del estado del Modelo.
- **Intent:** componente de intención de un usuario para realizar una acción, lo cual puede provocar un cambio en el estado del modelo y de la interfaz.

##### ¿Cuáles son sus ventajas?
- Se enfoca principalmente en los estados, por lo que es fácil de depurar, ya que conocemos el estado del objeto cuando ocurrió el error.
- Garantiza la seguridad de los subprocesos, ya que los objetos de estado son inmutables.
- Está más desacoplado ya que cada componente cumple con su propia responsabilidad.

##### ¿Qué inconvenientes tiene?
- Conduce a una gran cantidad de código repetitivo, ya que tenemos que mantener un estado para cada acción del usuario.
- Tiene que crear muchos objetos para todos los estados, lo que hace que sea demasiado costoso para la gestión de la memoria de la aplicación.

---

### Testing

#### ¿Qué tipo de tests se deberían incluir en cada parte de la pirámide de test? Pon ejemplos de librerías de testing para cada una de las partes. 

- **Pruebas de nivel inferior:** está conformada por pruebas unitarias estas son menos costosas y si tuvieramos que priorizar el esfuerzo para escribir pruebas debería ser en este bloque. Las librerías que se pueden utilizar para este tipo de test son Junit o Mockito.
- **Pruebas de nivel intermedio:** esta conformada por pruebas de integración entre componentes y las diferentes capas de nuestra aplicación, la priorización en esfuerzo es media, lo cual implica que el desarrollo de estas pruebas cuenta con una menor prioridad que los unit tests. Las librerías que se pueden utilizar para este tipo de test son Intents de Espresso o Truth
- **Pruebas de nivel superior:** esta conformada por pruebas de UI, el desarrollo de pruebas de UI guarda consigo cierta complejidad dependiendo de la aplicación que estemos probando. Proporcionalmente hablando, en muchos casos la cantidad de pruebas será menor que la de las otras capas. Las librerías que se pueden utilizar para este tipo de test son Espresso o Robotium.

#### ¿Por qué los desarrolladores deben centrarse sobre todo en los Unit Tests?
Los desarrolladores se deben centrarse en las pruebas unitarias puesto que con estos test se puede verificar el comportamiento de las funciones y la lógica de nuestras unidades de programación, además son fáciles de entender, de escribir y son más rápidos para ejecutarlos.

---

### Inyección de dependencias

#### Explica en qué consiste y por qué nos ayuda a mejorar nuestro código.
Cuando se establece un patrón de diseño o arquitectura, uno de los primeros aspectos a revisar son las responsabilidades de cada entidad. Cada una debe realizar una serie de tareas específicas y nada mas. Lo mismo pasa con sus dependencias: debe saber cómo usarlas, no como generarlas. El paradigma de inyección de dependencias señala que en lugar de que cada Clase sea responsable de buscar sus dependencias, otra entidad será la encargada de proporcionarlas. Nos ayuda a mejorar el código puesto que se puede reutilizarlo, nos proporicona facilidad para la refactorización y pruebas.

#### Explica cómo se hace para aplicar inyección de dependencias de forma manual a un proyecto (sin utilizar librerías externas).
La inyección de dependencias se la puede realizar pasando el objeto en el constructor de la clase, o con una función pública para setear el valor y usando una variable tipo lateinit, para lo cual se debe crear los objetos que necesitemos fuera de la clase, y pasarlos como parámetro.
