EXAMEN MINIM 1 DSA
YERALD MONTES PARIONA

He realizado las comprobaciones JUNIT la ejecución del servicio REST y las operaciones con Swagger, donde tanto en los tests y el servico REST funciona todo correctamente, pero en el Swagger aunque me funcionen 3 operaciones que son consultar todas las operaciones de un alumno y de un instituto, además de la del POST. Las otras 2 ( el ranking y el procesar no me funcionan porque aun no sé exactamente porque pero el intellij que usa MOXy no puede convertir de Java a JSON mis ArrayList, incluso he probado de usar un String pero tampoco. Entonces he intentado crear una clase java llamada Ranking dentro de models para usar el xmlRootElement pero tampoco me funciona. En un principio el resto que lo he probado si me funciona, solo tengo ese problema de los array, donde mirando un poco por internet me recomienda modificar el pom.xml pero tengo que verlo mejor para entenderlo.





En mi segunda versión de la entrega MINIM1 he conseguido resolver el problema de las listas que el MOXy no traducia de Java a JSON, aunque sinceramente no creo que sea la forma mas eficiente, porque ya que no se creaba el JSON he decidido hacer un pequeño codigo que creaba el JSON, pero bueno el problema esta resuelto y funciona correctamente. Después el problema que tenia de procesar las operaciones fue un error que tuve al introducir los parametros del POST entonces no podia procesar las operaciones correctamente el PUT, pero a nivel de codigo si que funcionaba. El resto como en la primera versión funciona.

Quiero aclarar que la clase Ranking que he creado y enviado en la primera version fue un intento de solucionar de alguna manera el problema que tuve con la traducción JSON.
