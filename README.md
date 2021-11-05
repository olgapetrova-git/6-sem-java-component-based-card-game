*Please find German version below*
# (EN) Mau-Mau card game
Study project at the University of Applied Sciences Berlin (HTW Berlin).       
My bachelor study program was Business Computing (Wirtschaftsinformatik B.Sc).       

6. Semester, August-September 2020.    
The project was carried out as part of the course Components-based development. The project assignment was completed in 6 weeks with a grade of 1.0.        

This is an implementation of [Mau-Mau card game](https://en.wikipedia.org/wiki/Mau-Mau_(card_game)) for a one real player and up to 3 virtual players.     

The project is a Java application developed using the following technologies:
- Component Architecture
- Maven
- DI with Spring Context
- JPA persistence with Hibernate and H2
- Test cases with JUnit and Easy-Mock
- Logging library log4j
- MVC pattern
- Error handling
## Documentation
The Java application is accompanied by technical documentation that includes a detailed description, a component diagram, a data schema, interface documentation as a formatted JavaDoc, interface images, a description of the frameworks and runtime environment used, and a presentation of the application examples. 

This documentation file named *Datenverarbeitungskonzept.pdf* (Data processing concept) is written in German and located in *resources* folder of the project. Diagrams are also available in the *resources* folder as well as in the documentation.
*h2-latest.jar* is in the *resources* folder. JavaDoc html files are located under the *doc* folder.
## How to Install
Clone the project using `git clone https://github.com/olgapetrova-git/6-sem-java-component-based-card-game.git`

To re-build the project, use *BUILD ALL configuration_management [clean install]* run configuration  in Maven tab in *configuration_management* section. It builds the project in the following order:
1. card_management
2. player_management
3. virtual_player_management
4. real_player_managemet
5. rules_management
6. game_management
7. view_management
8. configuration_management
## How to Use
Option 1: Run .jar file *view_management-1.0-SNAPSHOT-jar-with-dependencies* which is located in folder mau_mau\view_management\target

Option 2: To start the game from IDE use *htwberlin.mau_mau.configuration_management.runner.ApplicationRunner* class in *configuration_management* project.
## Author
Olga Petrova
- GitHub: [@olgapetrova-git](https://github.com/olgapetrova-git)
- LinkedIn: [Olga Petrova](https://www.linkedin.com/in/olga-petrova-berlin/)
## License  
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)  
Do not use and hand in this project as your own assignment in the studies.
# (DE) Mau-Mau-Kartenspiel
Studienprojekt während des Studiums an der HTW Berlin (Hochschule für Technik und Wirtschaft Berlin).
Studiengang Wirtschaftsinformatik B.Sc.

6. Semester, August-September 2020.     
Das Projekt wurde im Rahmen der Lehrveranstaltung Komponentenbasierte Entwicklung durchgeführt. Der Projektauftrag wurde in 6 Wochen mit der Note 1,0 abgeschlossen.     

Dies ist eine Implementierung des [Mau-Mau Kartenspiels](https://de.wikipedia.org/wiki/Mau-Mau_(Kartenspiel)) für einen realen Spieler und bis zu 3 virtuelle Spieler.    

Das Projekt ist eine Java-Anwendung, die unter Einsatz der folgenden Technologien entwickelt wurde:
- Komponentenarchitektur
- Maven Tool
- DI mit Spring Context
- JPA-Persistenz mit Hibernate und H2
- Testfälle mit JUnit und Easy-Mock
- Logging library log4j
- MVC-Pattern
- Fehlerbehandlung
## Dokumentation
Die Java-Anwendung wurde von einer technischen Dokumentation begleitet, die eine detaillierte Beschreibung, ein Komponentendiagramm, ein Datenschema, eine Schnittstellendokumentation als formatiertes JavaDoc, Oberflächenbilder, eine Beschreibung der verwendeten Frameworks und Ablaufumgebung sowie eine Darstellung der Anwendungsbeispiele enthielt.

Diese Dokumentationsdatei mit dem Namen *Datenverarbeitungskonzept.pdf* ist in deutscher Sprache verfasst und befindet sich im  im Ordner *"resources"* des Projekts. Diagramme sind ebenfalls im Ressourcenordner und in der Dokumentation verfügbar.
*h2-latest.jar* befindet sich im Ordner *"resources"*. JavaDoc html-Dateien sind im Ordner *"doc"* zu finden.
## Installation
Klonen Sie das Projekt mit `git clone https://github.com/olgapetrova-git/6-sem-java-component-based-card-game.git`

Um das Projekt neu zu erstellen, verwenden Sie *BUILD ALL configuration_management [clean install]* und führen Sie die Konfiguration auf der Registerkarte Maven im Unterprojekt *configuration_management* aus. Es baut das Projekt in der folgenden Reihenfolge:
1. card_management
2. player_management
3. virtual_player_management
4. real_player_managemet
5. rules_management
6. game_management
7. view_management
8. configuration_management
## Verwendungsweise
Option 1: Starten Sie die .jar-Datei *view_management-1.0-SNAPSHOT-jar-with-dependencies*, die sich im Ordner mau_mau\view_management\target befindet.

Option 2: Um das Spiel aus der IDE zu starten, verwenden Sie die Klasse *htwberlin.mau_mau.configuration_management.runner.ApplicationRunner* im Projekt *configuration_management*.
## Autor
Olga Petrova
- GitHub: [@olgapetrova-git](https://github.com/olgapetrova-git)
- LinkedIn: [Olga Petrova](https://www.linkedin.com/in/olga-petrova-berlin/)
## Lizenz
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)  
Verwenden und/oder reichen Sie dieses Projekt nicht als Ihre eigene Aufgabe im Studium ein. 
