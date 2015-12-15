package com.game.goose.util;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.game.goose.GameFrame;

public final class SpinnerBuilder {

	public static JSpinner createNewAngleSpinner(final GameFrame parentFrame, final SpiralLayout layout) {

		final JSpinner angleSpinner = new JSpinner(
				new SpinnerNumberModel(Math.toDegrees(layout.getRadiusStep()), 1.0, 360.0, 5.0));
		angleSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				double angle = (Double) angleSpinner.getValue();
				layout.setAngleStep(Math.toRadians(angle));
				parentFrame.refreshPanel();
			}
		});
		return angleSpinner;
	}

	public static JSpinner createNewRadiusSpinner(final GameFrame parentFrame, final SpiralLayout layout) {

		final JSpinner radiusSpinner = new JSpinner(new SpinnerNumberModel((int) layout.getRadiusStep(), 1, 1000, 1));
		radiusSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int radius = (Integer) radiusSpinner.getValue();
				layout.setRadiusStep(radius);
				parentFrame.refreshPanel();
			}
		});
		return radiusSpinner;
	}
}
