package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class sounds {

	Clip clip; 
	URL soundUrl[] = new URL[10];
	
	public sounds() {
		
		soundUrl[0] = getClass().getResource("/Sounds/menumusic.wav");
	}
	public void setFile(int i) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundUrl[i]);
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
		} catch (Exception e) {
		
		}
	}
	public void play() {
		clip.start();
	}
	public void loop() {
		clip.loop(clip.LOOP_CONTINUOUSLY);
	}
	public void stop() {
		clip.stop();
	}
}

