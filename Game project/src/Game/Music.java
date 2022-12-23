import javax.sound.sampled.*;
import java.io.*;

//This class is completely borrowed from https://www.geeksforgeeks.org/play-audio-file-using-java/
//to add music to our game

public class Music {

    // to store current position
    Long currentFrame;
    Clip clip;

    // current status of clip
    String status;

    AudioInputStream audioInputStream;
    //static String filePath;

    // constructor to initialize streams and clip
    public Music()
            throws UnsupportedAudioFileException,
            IOException, LineUnavailableException {
        // create AudioInputStream object
        audioInputStream =
                AudioSystem.getAudioInputStream(new File("../../lib/Music/GameMusic.wav").getAbsoluteFile());

        // create clip reference
        clip = AudioSystem.getClip();

        // open audioInputStream to the clip
        clip.open(audioInputStream);

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void play()
    {
        //start the clip
        clip.start();

        status = "play";
    }

    // Method to stop the audio
    public void stop()
    {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }
}