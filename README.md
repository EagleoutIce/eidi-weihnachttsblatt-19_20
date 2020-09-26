[![made-with-latex](https://img.shields.io/badge/Made%20with-LaTeX-1f425f.svg)](https://www.latex-project.org/) ![Compile the document](https://github.com/EagleoutIce/eidi-weihnachttsblatt-19_20/workflows/Compile%20the%20document/badge.svg)

# Weihnachtsblatt EidI 2019/2020

Lösungscode und -material zu den freiwilligen Weihnachtsaufgaben für die Veranstaltung "Einführung in die Informatik" an der Universität Ulm im Wintersemester 2019/2020.

## Disclaimer

Ich, Florian Sihler, trete jegliche Verantwortung für die Funktionalität sowie Qualität der in diesem Repository verorteten Dokumente ab und stelle den trauten Leser dieser Zeilen somit in die vom Internet gewohnte Selbstverantwortung. Sollte es zu etwaigen Problemen bei der Ausführung oder Verwendung diesiger Dokumente kommen, so bitte ich um eine (direkte) Kontaktaufnahme.

## Das PDF-Kompilat

Eine hoffentlich stets aktuelle PDF-Variante des Lösungsblattes findet sich hier auf dem [hier](https://media.githubusercontent.com/media/EagleoutIce/eidi-weihnachttsblatt-19_20/gh-pages/weihnachtsblatt-lsg.pdf) oder [hier](https://github.com/EagleoutIce/eidi-weihnachttsblatt-19_20/blob/gh-pages/weihnachtsblatt-lsg.pdf).

Wem hier Fehler sowohl syntaktischer, semantischer als auch katastrophaler wie unbedeutender Natur auffallen sollen, der darf diese gern selbst korrigieren, oder mir auch mittels einer Email an florian.sihler@uni-ulm.de mitteilen. Bitte in einem solchen Fall 'Fehler im Weihnachtsblatt' oder vergleichbares dem Nachrichtentitel anfügen.

## Die Quellcodes

Feinsäuberlich unsortiert gliedern sich die Aufgabenlösungen entsprechend ihres Schwierigkeitsgrades in die Order [easy/](easy/) und [middle/](middle/) auf (*und dann auch 'hard', wenn ich dazu komme*). Notiert sind sie nach dem Schema: `A<Sektion><Aufgabenummer>_<Bezeichner>.java` wobei der Bezeichner der Hauptklasse jeweils dem Titel (oder zumindest dem Keyword im Titel) entspricht.

So lässt sich die TicTacToe-Lösung zum Beispiel mittels:

```bash
javac A36*.java
```

ausführbar machen und mittels

```bash
java A36_TicTacToe
```

ausführen. Natürlich spricht auch nichts gegen eine explizite Angabe, oder die Dateien zu verschieben. Um ehrlich zu sein, basiert diese Anordnung lediglich auf einer einfachen eingliederung in das LaTeX-Dokument.

## Das LaTeX-Dokument

Um das Dokument kompilieren zu können sind weitere Pakete vonnöten die sich von meiner Feder entstammt zu bezeichnen mögen und im Rahmen des Softwaregrundprojektes entstehen/entstanden sind.
Die Kompilierung sollte mit [sltx](https://github.com/EagleoutIce/sltx) problemlos möglich sein.

Alle nicht auf CTAN-verzeichneten Pakete finden sich in der [sopra-collection](https://github.com/EagleoutIce/sopra-collection).

*Ach ja: Der LaTeX-Code ist in Eile entstanden und sollte deswegen nicht als Maß für einen guten Programmierstil herangezogen werden.*
