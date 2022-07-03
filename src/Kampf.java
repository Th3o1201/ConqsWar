import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Kampf {

    public static int kampf (int SpielerLeben) {
        Scanner Kampf = new Scanner(System.in);

        int Schaden; //Schaden den der Spieler verursacht //
        String Gegner ; //Siehe Klasse "Name"
        int GegnerLeben ; //Gegner bekommt eine zufällige Lebensanzahl zwischen 20 und 100

        if(Bauen.HütteLevel != Bauen.MaxLevelHütte){ //Bedingung um mit Endboss zu kämpfen bzw wann der Endboss erscheint
            Gegner = Name.name();
            GegnerLeben = ThreadLocalRandom.current().nextInt(20,101);
            System.out.println("Du wirst von einem " + Gegner + " angegriffen, es hat " + GegnerLeben + " Leben, wie möchtest du angreifen? ");
        }else{
            Gegner = Endboss.theChosenOne; //Der Name des Gegners ist nicht mehr zufällig, sondern wird mit dem des Bosses überschrieben
            GegnerLeben = Endboss.LebensPunkte; //Selbes Prinzip wie bei Gegner, nur mit Leben
            System.out.println("Du wirst vom " +Gegner+ " angegriffen. Er hat " +GegnerLeben+ " Leben, wie möchtest du angreifen? ");
            System.out.println("Eine deiner Fähigkeiten fühlt sich mächtiger an, als je zuvor");
        }



        System.out.println();
        System.out.println("1 für Machtblitz");
        System.out.println("2 für Machtschub");
        System.out.println("3 für Machtnichts");
        System.out.println();
        System.out.println("Wie möchtest du handeln?");


        while ((GegnerLeben >= 1) && (SpielerLeben >= 1)) {
            String Angriff = Kampf.nextLine();

            switch (Angriff) { //Definiton der einzelnen Angriffe mit Schadenszahlen, welche der gegnerischen Lebensleiste abgezogen werden.

                case "1" -> {
                    Schaden = 20 + ((40 * Bauen.HütteLevel) / 10); //Desto höher das Level der Hütte, desto höher der Schaden des Spielers
                    GegnerLeben = GegnerLeben - Schaden;
                    System.out.println("Du hast " + Schaden + " Schaden gemacht!");
                    System.out.println("Gegnerische Leben: " + GegnerLeben);
                    System.out.println();
                    if (GegnerLeben >= 1){ //Der Gegner kann nur Angreifen, wenn er mindestens 1 Leben hat
                        int Dmg = 0; //Schaden wird in Klasse "GegnerSchaden" definiert.
                        int GegnerDmg = GegnerSchaden.schaden(Dmg); //Siehe Klasse "GegnerSchaden"
                        SpielerLeben = SpielerLeben - GegnerDmg; //Die Leben des Spielers werden bei erfolgreichem Angriff des Gegners reduziert
                        System.out.println("Der Gegner greift an und hat: " + GegnerDmg + " Schaden gemacht!");
                        System.out.println("Du hast noch " + SpielerLeben + " Leben!");
                    }
                }
                case "2" -> {
                    Schaden = 10 + ((20 * Bauen.HütteLevel) / 10);
                    GegnerLeben = GegnerLeben - Schaden;
                    System.out.println("Du hast " + Schaden + " Schaden gemacht!");
                    System.out.println("Gegnerische Leben " + GegnerLeben);
                    System.out.println();
                    if (GegnerLeben >= 1){
                        int Dmg = 0;
                        int GegnerDmg = GegnerSchaden.schaden(Dmg);
                        SpielerLeben = SpielerLeben - GegnerDmg;
                        System.out.println("Der Gegner greift an und hat: " + GegnerDmg + " Schaden gemacht!");
                        System.out.println("Du hast noch " + SpielerLeben + " Leben!");
                    }
                }
                case "3" -> {
                    int Dmg = 0;
                    if (Bauen.HütteLevel == Bauen.MaxLevelHütte){
                        Schaden = 99999; //Endgame belohnung um Endboss zu besiegen, allerdings "geheim"
                        GegnerLeben = GegnerLeben - Schaden;
                        System.out.println("Du hast dein wahres Potential entdeckt!");
                        System.out.println("Du hast " + Schaden + " Schaden gemacht!");
                        System.out.println("Gegnerische Leben " + GegnerLeben);
                        System.out.println();
                    }
                    if(GegnerLeben >= 1) {
                        int GegnerDmg;
                        if (Bauen.HütteLevel == Bauen.MaxLevelHütte){
                            GegnerDmg = GegnerSchaden.schaden(Dmg) / 10; //Spieler muss mit case 3 angreifen, da hier der Schaden des Endbosses minimiert wird und der Schaden des Spielers maximiert
                        }else {
                            GegnerDmg = GegnerSchaden.schaden(Dmg);
                            System.out.println("Du hast nichts gemacht");
                        }
                        SpielerLeben = SpielerLeben - GegnerDmg;
                        System.out.println();
                        System.out.println("Der Gegner greift an und hat: " + GegnerDmg + " Schaden gemacht!");
                        System.out.println("Du hast noch " + SpielerLeben + " Leben!");
                    }
                }
                case "69" -> {        //Cheatcode/Easteregg
                    System.out.println("(￣ー￣)");
                    System.out.println();
                    GegnerLeben = 0;
                    System.out.println("Gegnerische Leben: " + GegnerLeben);
                }
                default -> System.out.println("Antworte 1, 2 oder 3! ");
            }
            if (GegnerLeben <= 0){   //Beendigung der Schleife wird dem Spieler angezeigt, Gegner ist besiegt er erhält exp
                System.out.println("Der Gegner wurde besiegt! ");
                System.out.println();
                System.out.println("Du hast noch: " +SpielerLeben+ " Leben");
                System.out.println();

            } if (SpielerLeben <= 0){
                System.out.println("Du wurdest besiegt xDDDDDDDDD ");
            }
        }

        return SpielerLeben;}

}