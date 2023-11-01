# SOPRJ4 Sagrada - Group R (Semester 2)
The first group project of the Bachelor's program in Computer Science. In a group of 7 students, a board game was recreated in Java, and the GUI was built using JavaFX.

De repository van de Sagrada applicatie die wordt ontwikkeld door SOPRJ4 Groep R.

## Contributors

Imke Looman - studentnr. 2168114  
Mandy Hoang - studentnr. 2168063  
Freek van Rijn - studentnr. 2161343  
Dani de Rooij - studentnr. 2170545  
Joram Kwetters - studentnr. 2171055  
Janique van den Oever - studentnr. 2174527  
Jasper van der Veen - studentnr. 2165932

## Afspraken

Hieronder staan alle afspraken die gemaakt zijn gecategoriseerd per onderdeel.

### Gebruik van branches
- Er wordt niet direct gecommit naar de branches `main` of `develop`
  - Wijzigingen aan de branches `main` en `develop` worden doorgevoerd via pull requests.
- Branch `main` wordt enkel gebruikt als omgeving voor stabiele versies van de applicatie.
- Branch `develop` wordt gebruikt als omgeving voor versies die nog in ontwikkeling zijn.
- Alle nieuwe features die gemaakt worden worden gebranched vanuit de `develop` branch. 
- Feature branch namen beginnen met `feat/`. Voorbeeld: `feat/database-connection`.
  - Naamgeving is herleidbaar naar de issue waaraan wordt gewerkt.
- Wanneer de branch `develop` klaar is voor een nieuwe versie wordt er een pull request gemaakt worden naar de `main` branch.

### Commits
- Commits hebben een beschrijvende titel en omschrijving van de wijzigingen.
- Commits worden regelmatig gemaakt bij het werken aan een feature.
- Commits bevatten geen grote wijzigingen. Er wordt een commit gemaakt als een onderdeel binnen een feature af is. 
  - Kleinere commits zijn over het algemeen beter te begrijpen en te controleren bij een pull request.
  - Kleinere commits zijn specifieker te beschrijven.

### Pull requests en reviews
- De auteur van de pull request voegt een duidelijke titel en beschrijving van de wijzigingen toe.
- De auteur koppelt de issue die betrekking heeft op de pull request.
- De auteur van de pull request wijst twee mensen aan voor een review.
  - De auteur tagt deze mensen in zijn comment en informeert hen persoonlijk via WhatsApp over de pull request.
- Een pull request wordt door minimaal twee mensen gereviewed alvorens deze gemerged wordt.
- Een reviewer controleert een pull request zo spoedig mogelijk, uiterlijk binnen 2 werkdagen na aanwijzing.
  - De reviewer controleert of de code naar de juiste branch wordt gepulled. Dus geen `feat/` branch naar `main`!
  - De reviewer controleert of de code voldoet aan de programmeerstandaarden.
  - De reviewer controleert of de code juist gedocumenteerd is middels comments.
  - De reviewer controleert of de code voldoet aan de requirements / opdracht.
  - De reviewer controleert of er een issue is gelinkt aan de pull request.
  - De reviewer controleert of het pull request een beschrijvende titel en omschrijving heeft.
  - De reviewer controleert of er geen conflicten ontstaan met andere code.
- De reviewer geeft zijn akkoord via een comment.
- Reviews worden eerlijk verdeeld zodat iedereen ongeveer evenveel pull requests reviewt.
- Als beide reviewers akkoord zijn, merged de auteur de changes en wordt de feature branch verwijderd.

### Issues
- Ontwikkelactiviteiten worden vastgelegd in issues.
- Aan issues worden labels gekoppeld die de issue beschrijven. Voorbeeld: 'enhancement' bij een nieuwe functie of 'bug' bij een bug.
- Issues hebben een beschrijvende titel en een beschrijving van de taken die moeten worden uitgevoerd.
- Bij toewijzing van een taak wordt de issue geassigned aan de persoon.
- De issue wordt geclosed wanneer de pull request gemerged is met `develop`.

### Libraries en Ontwikkelomgeving
- De applicatie wordt ontwikkeld op het besturingssysteem Windows 10.
- De Java JDK versie 1.8.0_281 wordt gebruikt bij de ontwikkeling van de applicatie.
- Eclipse IDE versie 2020-06 (4.16.0) wordt gebruikt als IDE.
- Library MySQL Connector/J versie 5.1.45 wordt gebruikt voor de database connectie (https://downloads.mysql.com/archives/c-j/)
- Er wordt geen gebruik gemaakt van externe libraries anders dan die van de database.
- De repositories worden enkel gebruikt om broncode, documentatie, handleidingen en create-scripts voor de database op te slaan.

### Java Code
- De applicatie wordt volgens MVC opgebouwd.
- De code is efficient en vertoont geen herhalende stukken code.
- Namen van packages, klassen, methoden en variabelen zijn beschrijvend en voldoen aan de Java naming convention.
- Niet-final instance variabelen worden niet geïnitialiseerd bij declaratie, maar in de constructor.
- Een methode of constructor is niet groter dan 50 regels. 
  - Er wordt indien nodig gebruik gemaakt van hulpmethoden.
- Klassen zijn niet groter dan 600 regels.
- Boven elke methode wordt een comment geplaatst met de beschrijving van de werking van de functie.
  - Er wordt enkel een comment geplaatst als het nut van een methode niet in één opslag duidelijk is.
- Alle code (inclusief comments) is geschreven in het Engels.
- Indien mogelijk, wordt er gebruik gemaakt van slimme enumerations.

### Algemene afspraken
- Iedereen zet zich actief in om het einddoel te behalen.
- Iedereen houdt zich aan de afspraken zoals gesteld in het README.md document en de opgestelde samenwerkingsafspraken in de OneDrive map.
- Het stelselmatig niet naleven van de afspraken in dit document wordt bestraft met een gele kaart.
- Workbreakdown met schattingen worden wekelijks geüpdatet.
- Taakverdeling wordt wekelijks geüpdatet.
- Burndownchart wordt wekelijks geüpdatet.
- Iedereen registreert zijn eigen gemaakte uren met beschrijving van uitgevoerde taak.
