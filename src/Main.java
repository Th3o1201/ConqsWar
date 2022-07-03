import java.util.Random;
import java.util.Scanner;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Main {
    static int SpielerLeben = 115; //Die SpielerLeben werden für alle Klassen zugänglich gemacht
    static int materialHolz = 555550; //        ""

    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException { //wird für Musik benötigt, falls der Pfad nicht erkannt wird, wird ein Fehler angezeigt

//Die Klasse "Geschichte" wird mithilfe einer for Schleife stück für stück ausgelesen, der Spieler kann kontrollieren, wann er die nächste Zeile lesen möchte
        boolean storyContinue = true;

        String[] story = Story.Geschichte();

        for (int i = 0; i < story.length; i++) {
            if (storyContinue) {
                System.out.println();
                System.out.println(story[i]); //Story wird an entsprechender Stelle im Array ausgelesen, der erste Teil wird immer ausgegeben
                System.out.println();
                System.out.println("Schreibe 'weiter' um fortzufahren! ");
            }
            Scanner scanner = new Scanner(System.in);
            String storyEingabe = scanner.nextLine();

            if (storyEingabe.equals("weiter")) //Nur wenn der Spieler "weiter" schreibt, soll die for Schleife weiter ausgeführt werden
                storyContinue = true;
            else {
                System.out.println();
                System.out.println("Schreibe 'weiter'!");
                i--; // Bei falscher Eingabe bleibt der Text an derselben Stelle, wo er das letzte Mal richtig ausgeführt wurde. Also beispielsweise die Eingabe ist "Hallo" statt "weiter", so würde der Text nicht ausgegeben werden, aber das "i" würde weiter hoch zählen
                storyContinue = false; //Die if-Bedingung wird übersprungen und direkt zum scanner gegangen, dadurch wird verhindert, dass derselbe Text immer wieder angezeigt wird, bei falscher Eingabe
            }
        }

        SpielerLeben = Kampf.kampf(SpielerLeben); //Nach Story erster Kampf
//Kurze Erklärung des Prinzips des Spiels
        System.out.println();
        System.out.println("Du hast nun die Auswahlmöglichkeit zwischen insgesamt 3 Optionen. ");
        System.out.println("Du hast aber niemals die Möglichkeit zwischen allen zu wählen.");
        System.out.println("Du brauchst dich auch nicht selbst zu heilen, bei jedem Durchlauf hast du eine 4% Chance deine Leben vollständig zu regenerieren");
//Erste Chance muss außerhalb der while Schleife sein, da ansonsten nach jeder Eingabe, egal ob richtig oder falsch, eine neue Chance generiert wird
        double AuswahlChance;
        Random r = new Random();
        AuswahlChance = r.nextDouble(); //Randomizer selbes Prinzip wie bei Klasse "GegnerSchaden"

        //Initialisiere die Upgrade-Liste
        Bauen.initialisieren();

        //Schleife für das Spiel selbst, wenn der Spieler stirbt, ist das Spiel automatisch vorbei
        while (SpielerLeben >= 1) {
//Bedingung um mit dem Endboss zu kämpfen
            if(Bauen.HütteLevel == Bauen.MaxLevelHütte){
                //Endboss Story
                boolean storyWeiter = true;
                String[] storyBoss = Story.boss(); //Klasse "StoryBoss", Methode "boss" wird aufgerufen

                for (int i = 0; i < storyBoss.length; i++) {
                    if (storyWeiter) {
                        System.out.println();
                        System.out.println(storyBoss[i]);
                        System.out.println();
                        System.out.println("Schreibe 'weiter' um fortzufahren! ");
                    }
                    Scanner scannerBoss = new Scanner(System.in);
                    String storyBossEingabe = scannerBoss.nextLine();

                    if (storyBossEingabe.equals("weiter"))
                        storyWeiter = true;
                    else {
                        System.out.println();
                        System.out.println("Schreibe 'weiter'!");
                        i--;
                        storyWeiter = false;
                    }
                }
                //Endboss Musik
                SoundPlayer.thePath = "src/Full-Song_-Bury-the-Light-Vergils-battle-theme-from-Devil-May-Cry-5-Special-Edition.aiff"; //Die Klasse "SoundPlayer" wird aufgerufen und der Pfad des gewünschten Liedes wird hier hinterlegt.
                SoundPlayer LiedSpielen = new SoundPlayer();
                LiedSpielen.play();//Lied wird abgespielt

                //Endboss Kampf
                Kampf.kampf(SpielerLeben);
                //Endboss Story nach Kampf
                System.out.println("Du hast den stärksten besiegt, dein neues Ego bringt dich dazu, die gesamte Galaxie versklaven zu wollen. Du Tötest jeden der dir in den weg kommt");
                System.out.println();
                System.out.println("Deine Tyrannei kennt keine Grenzen und du bist das geworden, was du niemals werden wolltest, ein Sith...");
                break;//breche die Schleife ab, da das Spiel beendet ist.
            }

//Spieler soll nicht immer dasselbe auswählen können, sorgt für etwas Abwechslung, da auch kämpfen im Prinzip nicht nötig ist, wird er hiermit dazu gezwungen. Mithilfe eines Randomizer wird dies umgesetzt.
            if (AuswahlChance < 0.24) {
                System.out.println();
                System.out.println("Du hast die Möglichkeit zu kämpfen oder zu sammeln!");
                System.out.println("Entscheide dich, indem du ´kämpfen´ oder ´sammeln´ schreibst");
                System.out.println();

                Scanner s = new Scanner(System.in);
                String Entscheidung = s.nextLine();

                switch (Entscheidung) {
                    case "kämpfen" ->{
                        SpielerLeben = Kampf.kampf(SpielerLeben); //Möglichkeit um zu kämpfen, die Spielerleben werden hier ebenfalls gespeichert, welche in der Klasse "Kampf" definiert werden
                        AuswahlChance = r.nextDouble();} //Neue Chance wird nur bei korrekter Eingabe definiert

                    case "sammeln" -> {
                        materialHolz = materialHolz + Materialien.sammeln(); //Möglichkeit Holz zu sammeln, die gesamte Menge des Holzes des Spielers wird hier ebenfalls gespeichert bzw. immer überschrieben, wenn + oder - gemacht wird
                        AuswahlChance = r.nextDouble();}

                    default -> System.out.println("Schreibe ´kämpfen´ oder ´sammeln´");
                }
            }
            else if (AuswahlChance < 0.48) {
                System.out.println();
                System.out.println("Du hast die Möglichkeit zu bauen oder zu sammeln!");
                System.out.println("Entscheide dich, indem du ´bauen´ oder ´sammeln´ schreibst");
                System.out.println();

                Scanner s = new Scanner(System.in);
                String Entscheidung = s.nextLine();

                switch (Entscheidung){
                    case "sammeln" -> {
                        materialHolz = materialHolz + Materialien.sammeln();
                        AuswahlChance = r.nextDouble();}

                    case "bauen" -> {
                        materialHolz = Bauen.bauen(materialHolz);
                        AuswahlChance = r.nextDouble();}
                }
            }
            else if (AuswahlChance < 0.72) {
                System.out.println();
                System.out.println("Du hast die Möglichkeit zu kämpfen oder zu bauen!");
                System.out.println("Entscheide dich, indem du ´kämpfen´ oder ´bauen´ schreibst");
                System.out.println();

                Scanner s = new Scanner(System.in);
                String Entscheidung = s.nextLine();

                switch (Entscheidung){
                    case "kämpfen" -> {
                        SpielerLeben = Kampf.kampf(SpielerLeben);
                        AuswahlChance = r.nextDouble();}

                    case "bauen" -> {
                        materialHolz = Bauen.bauen(materialHolz);
                        AuswahlChance = r.nextDouble();}
                }
            }
            else if (AuswahlChance < 0.96) {
                System.out.println();
                System.out.println("Du hast nur die Möglichkeit zu kämpfen!");
                System.out.println("Schreibe ´kämpfen´ um fortzufahren");
                System.out.println();

                Scanner s = new Scanner(System.in);
                String Entscheidung = s.nextLine();

                if (Entscheidung.equals("kämpfen")) {
                    SpielerLeben = Kampf.kampf(SpielerLeben);
                    AuswahlChance = r.nextDouble();
                } else {
                    System.out.println("Du musst kämpfen!");
                }
            }
            else {
                SpielerLeben = 115;
                System.out.println();
                System.out.println("Du hast eine Pause eingelegt und hast deine Leben wieder regeneriert.");
                System.out.println("Du hast nun wieder " +SpielerLeben+ " Leben!");
                System.out.println();
                AuswahlChance = r.nextDouble();
            }
        }
    }
}