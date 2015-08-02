# proxyapi
Client-API für Stanbol, die dem Entwickler eine einfache Schnitstelle für die Anreicherung der SUchergebnissen 
zur Verfügung stellt.

## Kurze Einleitung
Um diese Bibliothek in deinem Projekt benutzen zu können, muss du die entsprechende mvn-Abhängigkeit 
 in dein Projekthinzufügen:

```
<dependency>
    <groupId>de.unidue</groupId>
    <artifactId>proxyapi</artifactId>
</dependency>
```

* Um die Verbindung zum Stanbol aufzubauen, muss man eine Instanz von _EnhancementClient_ erzeugen.
* Um die Suchergebnisse (die man z.B. vom Bing kriegt), muss man eine Map von URL der gefundenen Webseite auf den Text desd Snippets
an den _EnhancementClient_ übergeben.
* In der Auflistung _EnhancementEngine_ findet man die Liste von verfügbaren Engine-Ketten.
* Als Antwort liefert die Bibliothek einen _EnhancementResult_, wo jeder Webseite eine Liste von _Ontologien_ zugewiesen wird.
* Das Beispiel findest du in dem Package ``de.unidue.proxyapi.examples``