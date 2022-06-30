import java.util.Random;

public class GegnerSchaden {
    public static int schaden (int GS){ //Schaden wird definiert

        if(Bauen.HütteLevel == Bauen.MaxLevelHütte){
            return Endboss.attackPoints;
        }
        Random ChanceGegner = new Random();
        int Schaden;
        double SchadenChance;

        SchadenChance = ChanceGegner.nextDouble(); //Kann wegen double nicht > 1, oder < 0 sein

        if (SchadenChance < 0.1) { //In den Verzweigungen werden verschiedene Chancen definiert mit entsprechendem Schaden. Geringere Chance viel Schaden zu kassieren für einfaches Vorankommen im Tutorial
            Schaden = 10;
            GS = Schaden;

        } else if (SchadenChance < 0.4) {
            Schaden = 5;
            GS = Schaden;


        } else if (SchadenChance < 0.7) {
            Schaden = 3;
            GS = Schaden;

        } else {
            Schaden = 0;
            GS = Schaden;
        }
        return GS;
    }
}