# rest-example


He conseguido que funcionen todas las operaciones de la parte 1, se puede ver en el test donde se comprueba test por test cada operacion.

De la parte 2, AvionService funciona perfectamente tanto el POST como el GET en  VueloService me faltaba añadir ejemplos de aviones para poder hacer addVuelo ya que llama a addAvion, y de ProductMaletas no esta revisado.

# Actualización entrega definitiva

Parte 1

- He ha añadido un Test para comprobar que si el identificador del avión es el mismo se modifiquen sus datos.

- Me he dado cuenta que de la manera que había planteado el enunciado no es del todo practica para un programa real debido a que no hay una estructura que almacene los vuelos dentro del objeto Avión, aunque para lo que nos pide el enunciado podría llegar a funcionar porque lo que hacemos es una comprobación unidireccional de vuelo el cual coge el id de avión y si no esta salta la excepción, voy a mejorarlo y voy a crear una relación bidireccional entre vuelo y avión.

Para hacerlo modifico la clase avión y le agrego la lista de vuelos. He agregado un test que comprueba que hay dentro avión existe vuelos.

-He añadido una nueva excepción AvionNotFoundException para poder lazar esta para los métodos donde no se encuentre el Avión

-Para facturar Maletas como el enunciado nos pide que la maleta tenga un id único por usuario estaba utilizando el propio id del usuario para identificar la maleta, me he dado cuenta de que no es muy funcional porque si el usuario quiere facturar mas de 1 maleta habría conflictos con el id, asi que para hacerlo mas real cada maleta tiene su propio ID único utilizo el random para asignar un id.

-He actualizado mi método getMaletasDeVuelo(String vueloId) para que lanze la excepción VueloNotFoundException

-Estaba mal gestionada el facturarMaletas ya que no se almacenaba directamente dentro de los vuelos sino que era un mapa por otro lado, entonces se ha agregado dentro de la clase vuelo la gestión de Maletas con un private Deque<Maleta> maletas;, por lo tanto para tener el código más limpio he echo los cambios debidos para que ya no se utilize     private HashMap<String, Deque<Maleta>> vuelosMaletas;
si no que se utilize la gestión de maletas a partir de vuelos.

-Me he asegurado con los test los cuales están pensados para comprobar todas las operaciones que nos pide el enunciado y todo funciona perfectamente.

Parte 2:

-Tanto el post como el get del AvionService funcionan perfectamente, si se modifica un avión es decir se modifica todo menos el id el avión se actualiza, en VueloService se puede hacer el get por id perfectamente pero no el post, y en maletas se puede hacer el post (se puede comprobar que funciona bien porque en los logs se ve como se añaden perfectamente),  pero no el get. 

He intentado de todo para poder solucionar el problema MessageBodyWriter not found for media type=application/json pero no lo he conseguido, segui el consejo de "Es recomana que totes les operacions retornin objectes de transferència i evitar 
cicles/relacions. " Por eso veras que he creado la clase MaletaDTO y algún que otro método DTO para crear una clase sin relaciones pero aun así me sigue apareciendo el mismo error he tenido problemas porque Jersey no puede encontrar un MessageBodyWriter adecuado para convertir el objeto ArrayList a JSON. Para comprobar esto veras que he dejado comentado el MaletaService para ver si era problema de mi lógica o a la hora de pasar datos y se ve claramente que el problema es de la conversión, también se puede comprobar con los logs donde todo funciona perfectamente. He intentado mirar vídeos para solucionar el problema y me decia que debia de añadir dependencias al pom.xml pero no acaba de entender bien a que se referia.

# Funcionamiento correcto de la API

Se que es fuera de tiempo, pero se me habia quedado en la mente el porque habia partes que no funcionavan así que me he puesto a ello y al final he conseguido que funcionen todos los servicios de la API correctamente se puede comprovar mediante el swagger.

