package bropals.tag16_2;

import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.io.File;
import javax.imageio.ImageIO;

public class Assets {
	
	private static Assets assets = new Assets();
	
	public static Assets getAssets() {
		return assets;
	}
	
	private HashMap<String, BufferedImage> images;
	private HashMap<String, Clip> sounds;
	
	public Assets() {
		sounds = new HashMap<String, Clip>();
		images = new HashMap<String, BufferedImage>();
	}
	
	/**
		Plays a sound based on the path
	*/
	public void playSound(String path) {
		Clip clip = sounds.get(path);
		if (clip==null) {
			try {
				AudioInputStream ais = AudioSystem.getAudioInputStream(new File("assets/" + path));
				clip = AudioSystem.getClip();
				clip.open(ais);
				ais.close();
				sounds.put(path, clip);
			} catch(Exception e) {
				System.err.println("Could not load sound " + path);
			}
		}
		clip.setFramePosition(0);
		clip.start();
	}
	
	
	/**
		Gets an image based on the path
	*/
	public BufferedImage getImage(String path) {
		BufferedImage image = images.get(path);
		if (image==null) {
			try {
				image = (BufferedImage)ImageIO.read(new File("assets/" + path));
				images.put(path, image);
			} catch(Exception e) {
				System.err.println("Could not image " + path);
			}
		}
		return image;
	}
}