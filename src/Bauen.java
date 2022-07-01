import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Bauen {
    static int MaxLevelHütte = 5;
    static int HütteLevel = 0; //Wert muss zugänglich für Klasse "Kampf" sein
    private static List<Integer> materialBenötigt = new LinkedList<>(); //Der Wert kann nur in dieser Klasse verändert werden

    public static void initialisieren(){
        //Erstelle eine Liste der benötigten Materialien
        materialBenötigt.add(20);
        materialBenötigt.add(35);
        materialBenötigt.add(50);
        materialBenötigt.add(65);
        materialBenötigt.add(80);
    }

    public static int bauen (int MaterialHolz){ //Zeigt wie viel Material ich noch habe
        String Verbesserung;  //Gebäude welches verbessert werden soll
        int FehlendeMaterialien;
        System.out.println();
        System.out.println("Du kannst deine Upgrade Möglichkeiten hier mit 4, 5 und 6 einsehen!");
        System.out.println();
        System.out.println("4 für benötigte Materialien");
        System.out.println("5 für deine Materialien im Besitz");
        System.out.println("6 um ein Gebäude zu verbessern");
        System.out.println("7 zum beenden.");

        boolean WeiterMachen = true;

        while(WeiterMachen) { //Schleife, da Spieler ansonsten wieder in Main landet nach einer Auswahl
            Scanner bauen = new Scanner(System.in);
            Verbesserung = bauen.nextLine();
            switch (Verbesserung) {
                case "4" -> {
                    if (materialBenötigt.isEmpty()){
                        System.out.println("Du hast schon das höchste Level erreicht! ヽ(´▽`)/ ");
                    }else {
                        System.out.println("Du benötigst insgesamt " + materialBenötigt.get(0) + " Materialien für dein nächstes Upgrade");
                    }
                }
                case "5" -> {
                    System.out.println("Du hast: " + MaterialHolz + " Materialien");
                }
                case "6" -> {
                    if (materialBenötigt.isEmpty()) //Überprüfung, ob das Level der Hütte bereits maximiert ist, also die Liste leer ist
                        System.out.println("Du hast schon das höchste Level erreicht! ヽ(´▽`)/ ");

                    else if (MaterialHolz >= materialBenötigt.get(0)) { //Bedingung um zu Upgraden, liest immer den ersten Eintrag der Liste aus
                        MaterialHolz = MaterialHolz - materialBenötigt.get(0); //Holz wird verbraucht
                        HütteLevel++; //Level der Hütte steigt
                        System.out.println("Du hast die Hütte mit " + materialBenötigt.get(0) + " Materialien verbessert.");
                        System.out.println("Du hast noch " + MaterialHolz + " Holz übrig.");
                        System.out.println("Deine Hütte hat nun das Level: " + HütteLevel);
                        System.out.println("Schreibe 4, 5, 6 oder 7 um weiter zu machen");
                        materialBenötigt.remove(0); //Die nächste Menge an Holz rückt nach und die vorherige Bedingung für ein Upgrade wird "gelöscht". Bsp.: Die Menge von 20 ist erreicht, also muss die Menge von 35 den Index 0 haben.
                    } else {
                        FehlendeMaterialien = materialBenötigt.get(0) - MaterialHolz;
                        System.out.println("Du hast zu wenige Ressourcen");
                        System.out.println("Du benötigst noch: " + FehlendeMaterialien + " Materialien");
                    }
                }
                case "7" -> { //Um aus der Schleife raus zu kommen
                    WeiterMachen = false;
                }
                default -> { //Bei falscher Eingabe erneuter Hinweis was geschrieben werden muss
                    System.out.println("Antworte 4, 5, 6 oder 7!");
                }
            }

        }
        return MaterialHolz;
    }
}