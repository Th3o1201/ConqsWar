import java.util.Random;

public class Materialien {
    public static int sammeln() {

        Random Menge = new Random();
        double Chance;

        Chance = Menge.nextDouble(); //Zufällige Chance eine gewisse Menge Holz zu bekommen (Selbes Prinzip wie bei "GegnerSchaden" Klasse)


        int Holz;
        if (Chance < 0.01) { //Geringe Chance viel Holz zu finden
            Holz = 100;

        } else if (Chance < 0.4) {
            Holz = 5;

        } else if (Chance < 0.7) {
            Holz = 3;

        } else if (Chance < 0.8) { //Chance beim Sammeln angegriffen zu werden
            System.out.println();
            System.out.println("Du wirst beim sammeln angegriffen und kannst deshalb kein Holz sammeln");
            System.out.println();
            Main.SpielerLeben = Kampf.kampf(Main.SpielerLeben);
            Holz = 0;
        } else {
            Holz = 2;
        }
        int GesamtHolz = Holz + Main.materialHolz;
        System.out.println("Du  hast " + Holz + " Holz gesammelt");
        System.out.println("Du hast nun insgesamt " +GesamtHolz+ " Holz");

        return Holz;
    }

}