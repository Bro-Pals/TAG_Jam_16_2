package bropals.tag16_2;

import java.awt.image.BufferedImage;

public class Animation {
	
	private BufferedImage[][] frames;
	private int track;
	private int frame;
	private long progress;
	private long millisecondsPerFrame;
	
	public Animation(BufferedImage[][] frames) {
		this.frames = frames;
		track = 0;
		frame = 0;
		progress = 0;
		millisecondsPerFrame = 20;
	}
	
	public void update(long millisecondsPerFrame) {
		this.progress += millisecondsPerFrame;
		if (this.millisecondsPerFrame <= progress) {
			frame++;
			if (frame>=frames.length) {
				frame = 0;
			}
		}
	}
	
	public void setTrack(int track) {
		this.track = track;
	}
	
	public BufferedImage getCurrentFrame() {
		return frames[track][frame];
	}
}