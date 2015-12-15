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

public class TestClass2 implements LayoutManager2 {

    private enum Size { MIN, MAX, PREF }

    private final double radiusStep=4;
    private final double angleStep=15;
    private static final String IMG_PATH="C:\\Users\\Max\\workspace\\Project_java\\images\\puit.png";


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
        for(Component component : parent.getComponents()) {
            Dimension preferredSize = getSize(component, size);
            min.x = Math.min(min.x, component.getX());
            min.y = Math.min(min.y, component.getY());
            max.x = Math.max(max.x, component.getX() + preferredSize.width);
            max.y = Math.max(max.y, component.getY() + preferredSize.height);
        }
        int center = Short.MAX_VALUE / 2;
        return new Dimension(
                Math.max(Math.abs(center - min.x), Math.abs(center - max.x) * 2),
                Math.max(Math.abs(center - min.y), Math.abs(center - max.y) * 2));
    }

    private Dimension getSize(Component component, Size size) {
        switch(size) {
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
        doLayoutContainer(parent,
                parent.getWidth(), parent.getHeight(), Size.PREF);
    }

    private List<Rectangle> doLayoutContainer(Container parent,
            int width, int height, Size size) {

        Point offset = new Point(width / 2, height / 2);
        List<Rectangle> componentBounds = new ArrayList<Rectangle>();
        double angle = 0;
        double radius = 1;
        for(Component component : parent.getComponents()) {
            Dimension preferredSize = getSize(component, size);
            Rectangle bounds;
            do {
                bounds = new Rectangle(
                        add(calculatePoint(angle, radius), offset),
                        preferredSize);
                angle += angleStep;
                radius += radiusStep;
            }
            while(overlaps(bounds, componentBounds));

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
        for(Rectangle other : componentBounds) {
            if(other.intersects(bounds)) {
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
                final TestClass2 layout = new TestClass2();
                final JPanel panel = new JPanel(layout);

               
                for(int i = 1; i <= 63; i++) {
                	Icon icon = new ImageIcon(IMG_PATH);
                	JLabel lab = new JLabel(icon);
                    panel.add(lab);
                }
                JFrame frame = new JFrame("Test");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.getContentPane().add(panel);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}