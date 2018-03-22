import java.io.*;
import sun.audio.*;

public class SpaceMusic {

	private AudioStream bgmStream;
	private AudioPlayer audioPlayer;
	private InputStream bgm;
	private AudioData bgmData;
	private ContinuousAudioDataStream bgmLoop;
	private String musicFile;

	/**
	 * Creates a music player through the use of the java.io package and
	 * sun.audio package. Creates a default audio player, sets the default of
	 * the string musicFile, then creates a file input that takes the musicFile,
	 * puts into an audio stream object, then creates an audio data object that
	 * takes the bgm data and then loops it. If the any error occurs, catch the
	 * error and output a message through the console.
	 */
	public SpaceMusic() {

		audioPlayer = AudioPlayer.player;

		musicFile = ("spaceBGM.wav");

		try {

			bgm = new FileInputStream(new File(musicFile));
			bgmStream = new AudioStream(bgm);
			bgmData = bgmStream.getData();
			bgmLoop = new ContinuousAudioDataStream(bgmData);

		} catch (IOException e) {
			System.out.println(e.getMessage() + "!");
			e.printStackTrace();
		}
	}

	/**
	 * Creates a music object and is the same of the no-arg constructor but
	 * instead of having a default file, the constructor passes a string in
	 * which the name of the file and including its type is stored within the
	 * class field music file.
	 * 
	 * @param music
	 *            Passes a string which is the name of the music file that wants
	 *            to be used.
	 */
	public SpaceMusic(String music) {

		audioPlayer = AudioPlayer.player;

		musicFile = music;

		try {

			bgm = new FileInputStream(new File(music));
			bgmStream = new AudioStream(bgm);
			bgmData = bgmStream.getData();
			bgmLoop = new ContinuousAudioDataStream(bgmData);

		} catch (IOException e) {
			System.out.println(e.getMessage() + "!");
			e.printStackTrace();
		}
	}

	/**
	 * Starts playing the music that wants to be used within a loop.
	 */
	public void playMusic() {
		audioPlayer.start(bgmLoop);
	}

	/**
	 * Stops playing the music that would be playing.
	 */
	public void stopMusic() {
		audioPlayer.stop(bgmLoop);
	}

}
