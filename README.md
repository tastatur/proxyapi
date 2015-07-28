# proxyapi
Proxy-API für den Stanbol

* *Server* wird benutzt, um die Verbindung zum Stanbol aufzubauen. Dabei sollen Serveraddresse und Enginename übergeben werden

* *Request* wird benutzt, um die Anfrage zu senden. *Request* beinhaltet die Liste von Tupels *(URL,Abstract)*. 
*URL* ist die Addresse der gefundenen Webseite, und *Abstract* ist der Text des Snipplets.

* Als Ausgabewert bekommt man das Object *Answer*, der die Liste von Tupels *(URL, List<Ontology>)* beinhaltet.
*URL* ist die entsprechende URL der gefundenen Webseite (so wird der Entwickler die Ontologien später anordnen könnte),
und *List<Ontology>* ist die Liste von gefundenen Ontologien