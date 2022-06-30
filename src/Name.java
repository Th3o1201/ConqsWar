import java.util.Random;

public class Name {
    public static String name () {
        String [] Namen = {"Ewok", "Wookie", "Akk Hund", "JarJar Bings", "Rey (Cringe) Skywalker", "Asyyyriak", "Bergruutfa", "Asohka Tahno"}; //Array mit Auswahl von Namen für Gegner.

        Random Zahl = new Random(); //Randomiser wird erzeugt
        int ZufallsEintrag = Zahl.nextInt(Namen.length); //Randomiser wird definiert, bekommt einen Wert (int) aus dem Array, definiert dadurch "ZufallsEintrag" mit Zahl zwischen 0 und 7.

        return Namen[ZufallsEintrag]; //Entsprechende Random Zahl wird in das Array geschrieben, wodurch der entsprechende Name ausgegeben werden kann.
    }
}