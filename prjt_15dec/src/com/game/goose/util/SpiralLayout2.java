package com.game.goose.util;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.LayoutManager2;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SpiralLayout2 implements LayoutManager2 {

	private enum Size {
		MIN, MAX, PREF
	}

	private double radiusStep;
	private double angleStep;
	private static final double DEFAULT_RADIAN = Math.toRadians(15);
	private static final double DEFAULT_ANGLE = 5;
	private static final String IMG_PATH = "D:\\dev\\cp\\max\\Project_java_2015-11-26\\Project_java\\images\\puit.png";
	private static final String BACKGROUND_PATH = "D:\\dev\\cp\\max\\Project_java_2015-11-26\\Project_java\\images\\noir.png";

	public SpiralLayout2() {
		this(DEFAULT_ANGLE, DEFAULT_RADIAN);
	}

	public SpiralLayout2(double radius, double stepSize) {
		this.radiusStep = radius;
		this.angleStep = stepSize;
	}

	public void setRadiusStep(double radiusStep) {
		this.radiusStep = radiusStep;
	}

	public void setAngleStep(double angleStep) {
		this.angleStep = angleStep;
	}

	@Override
	public void addLayoutComponent(String name, Component comp) {
		// calculated on the fly
	}

	@Override
	public void removeLayoutComponent(Component comp) {
		// calculated on the fly
	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		return getSize(parent, Size.PREF);
	}

	private Dimension getSize(Container parent, Size size) {
		doLayoutContainer(parent, Short.MAX_VALUE, Short.MAX_VALUE, size);

		Point min = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
		Point max = new Point(0, 0);
		for (Component component : parent.getComponents()) {
			Dimension preferredSize = getSize(component, size);
			min.x = Math.min(min.x, component.getX());
			min.y = Math.min(min.y, component.getY());
			max.x = Math.max(max.x, component.getX() + preferredSize.width);
			max.y = Math.max(max.y, component.getY() + preferredSize.height);
		}
		int center = Short.MAX_VALUE / 2;
		return new Dimension(Math.max(Math.abs(center - min.x), Math.abs(center - max.x) * 2),
				Math.max(Math.abs(center - min.y), Math.abs(center - max.y) * 2));
	}

	private Dimension getSize(Component component, Size size) {
		switch (size) {
		case MAX:
			return component.getMaximumSize();
		case MIN:
			return component.getMinimumSize();
		case PREF:
			return component.getPreferredSize();
		default:
			assert false : "Unknown size: " + size;
			return new Dimension();
		}
	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		return getSize(parent, Size.MIN);
	}

	@Override
	public void layoutContainer(Container parent) {
		doLayoutContainer(parent, parent.getWidth(), parent.getHeight(), Size.PREF);
	}

	private List<Rectangle> doLayoutContainer(Container parent, int width, int height, Size size) {

		Point offset = new Point(width / 2, height / 2);
		List<Rectangle> componentBounds = new ArrayList<Rectangle>();
		double angle = 0;
		double radius = 1;
		for (Component component : parent.getComponents()) {
			Dimension preferredSize = getSize(component, size);
			Rectangle bounds;
			do {
				bounds = new Rectangle(add(calculatePoint(angle, radius), offset), preferredSize);
				angle += angleStep;
				radius += radiusStep;
			} while (overlaps(bounds, componentBounds));

			component.setBounds(bounds);
			componentBounds.add(bounds);
		}
		return componentBounds;
	}

	private Point calculatePoint(double angle, double radius) {
		double x = radius * Math.cos(angle);
		double y = radius * Math.sin(angle);
		return new Point((int) x, (int) y);
	}

	private boolean overlaps(Rectangle bounds, List<Rectangle> componentBounds) {
		for (Rectangle other : componentBounds) {
			if (other.intersects(bounds)) {
				return true;
			}
		}
		return false;
	}

	private Point add(Point a, Point b) {
		return new Point(a.x + b.x, a.y + b.y);
	}

	@Override
	public void addLayoutComponent(Component comp, Object constraints) {
		// calculated on the fly
	}

	@Override
	public Dimension maximumLayoutSize(Container parent) {
		return getSize(parent, Size.MAX);
	}

	@Override
	public float getLayoutAlignmentX(Container target) {
		return 0.5f;
	}

	@Override
	public float getLayoutAlignmentY(Container target) {
		return 0.5f;
	}

	@Override
	public void invalidateLayout(Container target) {
		// calculated on the fly
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				final SpiralLayout2 layout = new SpiralLayout2();
				final JLabel gameArea = new JLabel(new ImageIcon(BACKGROUND_PATH));

				gameArea.setLayout(layout);

				JPanel buttons = new JPanel();
				buttons.add(new JLabel("Radius step:"));
				buttons.add(layout.getNewRadiusSpinner(gameArea, layout));
				buttons.add(new JLabel("Angle step"));
				buttons.add(layout.getNewAngleSpinner(gameArea,layout));
				// 5-15
				for (int i = 1; i <= 63; i++) {
					Icon icon = new ImageIcon(IMG_PATH);
					JLabel lab = new JLabel(icon);
					gameArea.add(lab);
				}
				JFrame frame = new JFrame("Test");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.getContentPane().add(buttons, BorderLayout.PAGE_START);
				frame.getContentPane().add(gameArea);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	public Component getNewAngleSpinner(final JLabel zone,final SpiralLayout2 layout) {
		
		final JSpinner angleSpinner = new JSpinner(
				new SpinnerNumberModel(Math.toDegrees(layout.angleStep), 1.0, 360.0, 5.0));
		angleSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				double angle = (Double) angleSpinner.getValue();
				layout.setAngleStep(Math.toRadians(angle));
				zone.revalidate();
			}
		});
		return angleSpinner;
	}

	public Component getNewRadiusSpinner(final JLabel zone,final  SpiralLayout2 layout) {
		
		final JSpinner radiusSpinner = new JSpinner(new SpinnerNumberModel((int) layout.radiusStep, 1, 1000, 1));
		radiusSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int radius = (Integer) radiusSpinner.getValue();
				layout.setRadiusStep(radius);
				zone.revalidate();
			}
		});
		return radiusSpinner;
	}
}