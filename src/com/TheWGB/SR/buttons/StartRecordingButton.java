package com.TheWGB.SR.buttons;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.TheWGB.SR.ScreenRecorder;

@SuppressWarnings("serial")
public class StartRecordingButton extends JButton implements ActionListener {
	public StartRecordingButton(int x, int y, int w, int h) {
		setText("Start Recording");
		setLocation(x, y);
		setSize(w, h);
		setLayout(new FlowLayout());
		addActionListener(this);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(!ScreenRecorder.getInstance().getRecorder().isRecording()) {
			ScreenRecorder.getInstance().getRecorder().start();
		}
	}
}
