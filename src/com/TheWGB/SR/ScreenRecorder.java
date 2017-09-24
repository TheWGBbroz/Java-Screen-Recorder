package com.TheWGB.SR;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.TheWGB.SR.buttons.StartRecordingButton;
import com.TheWGB.SR.buttons.StopRecordingButton;
import com.TheWGB.SR.recorder.Recorder;

@SuppressWarnings("serial")
public class ScreenRecorder extends JPanel implements Runnable {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 280;
	public static final int FPS_LOC = 30;
	public static final String TITLE = "Screen Recorder";
	private static ScreenRecorder instance;
	
	public static void main(String[] args) {
		JFrame window = new JFrame(TITLE);
		
		window.setContentPane(new ScreenRecorder());
		window.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		window.setFocusable(true);
		window.requestFocus();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
	}
	
	public static ScreenRecorder getInstance() {
		return instance;
	}
	
	private boolean running;
	private Recorder recorder;
	private BufferedImage image;
	private Graphics2D g;
	
	public ScreenRecorder() {
		Thread t = new Thread(this);
		t.start();
		
		instance = this;
	}
	
	@Override
	public void run() {
		init();
		
		running = true;
		
		long start;
		long elapsed;
		long wait;
		
		while(running) {
			start = System.currentTimeMillis();
			
			update();
			
			draw();
			drawToScreen();
			
			elapsed = System.currentTimeMillis() - start;
			
			wait = (1000 / FPS_LOC) - elapsed;
			
			if(wait < 0)
				wait = 5;
			
			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Recorder getRecorder() {
		return recorder;
	}
	
	private void init() {
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		
		recorder = new Recorder(60, "recording.mp4");
		
		add(new StartRecordingButton(0, 0, 125, 50));
		add(new StopRecordingButton(WIDTH - 125 - 10, 0, 125, 50));
	}
	
	private void update() {
		
	}
	
	private void draw() {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.PLAIN, 18));
		if(recorder.isRecording())
			g.drawString("State: Recording", 10, HEIGHT-45);
		else
			g.drawString("State: Idling", 10, HEIGHT-45);
		
		paintComponents(g);
	}
	
	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
		g2.dispose();
	}
}
