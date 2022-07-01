import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {
//Aus internet geklaut

    Clip clip;


    String thestatus;

    AudioInputStream audioStream;
    static String thePath;

    public SoundPlayer()
            throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        audioStream = AudioSystem.getAudioInputStream(new File(thePath).getAbsoluteFile());

        clip = AudioSystem.getClip();

        clip.open(audioStream);

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void play()
    {

        clip.start();

        thestatus = "play";
    }
}