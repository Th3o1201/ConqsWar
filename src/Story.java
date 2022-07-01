public class Story {
    public static String[] Geschichte() {
//Das Array beinhaltet die Geschichte, es kann ohne Probleme mehr geschrieben werden
        String [] Intro = {"Du bist der Hauptprotagonist Starkiller, du hast vor kurzem Darth Vader und Darth Sidious in die Knie gezwungen. Nun ist es dein Ziel, deine kleine Hütte auszubauen und möglicherweise wirst du ja das neue Coruscant erschaffen.",
                "Du machst dich auf den Weg um Holz zu sammeln, deine Lichtschwerter hast du verloren, aber du bist ja zum Glück Starkiller und hast eine Menge Macht, also reist du die Bäume einfach mit der Macht aus dem Boden.",
                "Auf deiner Tour kommen dir Komische Gestalten entgegen, welche dir aber nicht wohlgesonnen zu sein scheinen.","Der erste von ihnen fordert dich zum Kampf heraus!"};

        return Intro;
    }
    public static String[] boss() {
        //Geschichte des Bosses wird in Main nach maximierung der Hütte aufgerufen
        String [] BossGeschichte = {"Du hast es geschafft, deine Hütte ist nun vollständig verbessert.", "Doch es gibt Nutzer der Macht, die dich weiterhin verfolgt haben.", "Einer davon ist der gefürchtete Chosen One! Er hat dich gejagt, ohne das du seiner Existenz überhaupt bewusst warst.",
                "Nun hat er von einem reisenden Händler, welcher deine mickrige Hütte auf seiner durchreise gesehen hat, herausbekommen, wo du dich befindest.", "Mach dich bereit dem mächtigsten Macht Nutzer die Stirn zu bieten!"};

        return BossGeschichte;
    }
}

