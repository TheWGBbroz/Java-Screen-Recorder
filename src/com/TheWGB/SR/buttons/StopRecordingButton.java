package com.TheWGB.SR.buttons;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.TheWGB.SR.ScreenRecorder;

@SuppressWarnings("serial")
public class StopRecordingButton extends JButton implements ActionListener {
	public StopRecordingButton(int x, int y, int w, int h) {
		setText("Stop Recording");
		setLocation(x, y);
		setSize(w, h);
		setLayout(new FlowLayout());
		addActionListener(this);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(ScreenRecorder.getInstance().getRecorder().isRecording()) {
			ScreenRecorder.getInstance().getRecorder().stop();
		}
	}
}
