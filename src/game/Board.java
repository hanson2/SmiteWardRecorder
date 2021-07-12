package game;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.Insets;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import javax.imageio.ImageIO;

import javax.swing.*;

public class Board extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TimerPanel timerPanel;
	ImagePanel imagePanel;

	public Board() {
		super("hello");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.timerPanel = new TimerPanel();
		this.imagePanel = new ImagePanel(new MapListener(this));
		this.add(this.timerPanel, BorderLayout.SOUTH);
		this.add(this.imagePanel, BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);
	}
	
	

}

class TimerPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel label;
	public long lastTickTime;
	private Timer timer;

	public TimerPanel() {
		setLayout(new GridBagLayout());
		label = new JLabel(String.format("%03d:%02d", 0, -60));

		timer = new Timer(100, (ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				long runningTime = System.currentTimeMillis() - lastTickTime;
				Duration duration = Duration.ofMillis(runningTime);
				long minutes = duration.toMinutes();
				duration = duration.minusMinutes(minutes);
				long millis = duration.toMillis();
				long seconds = millis / 1000;
				if (minutes < 1) {
					seconds = 60 - seconds;
					seconds*=-1;
				}
				label.setText(String.format("%03d:%02d", minutes, seconds));

			}
		});

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.insets = new Insets(4, 4, 4, 4);
		add(label, gbc);

		JButton start = new JButton("Start");
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!timer.isRunning()) {
					lastTickTime = System.currentTimeMillis();
					timer.start();
				}
			}
		});
		JButton stop = new JButton("End");
		stop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				Game.storeWards();
			}
		});

		gbc.gridx = 0;
		gbc.gridy++;
		gbc.weightx = 0;
		gbc.gridwidth = 1;
		add(start, gbc);
		gbc.gridx++;
		add(stop, gbc);
	}
}

class ImagePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage image;

	public ImagePanel(MapListener mapListener) {
		try {
			image = ImageIO.read(new File("C:\\Users\\Keith\\eclipse-workspace\\SmiteWardRecorder\\BaseS8 map.png"));
			this.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
		} catch (IOException ex) {
			System.out.println(ex);
		}
		addMouseListener(mapListener);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}

}

class PersonSelector extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	PersonSelector(Point point, int minutes, int seconds){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Button support = new Button("Support");
		support.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Game.addWard("Support", point, minutes, seconds);
				Button button = (Button) e.getSource();
				JPanel parent = (JPanel) button.getParent();
				JPanel grandparent = (JPanel) parent.getParent();
				parent.removeAll();
				grandparent.revalidate();
				grandparent.repaint();
			}
			
		});
		this.add(support);
		
		Button jg = new Button("JG");
		jg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Game.addWard("JG", point, minutes, seconds);
				Button button = (Button) e.getSource();
				JPanel parent = (JPanel) button.getParent();
				JPanel grandparent = (JPanel) parent.getParent();
				parent.removeAll();
				grandparent.revalidate();
				grandparent.repaint();
			}
			
		});
		this.add(jg);
		
		Button adc = new Button("ADC");
		adc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Game.addWard("ADC", point, minutes, seconds);
				Button button = (Button) e.getSource();
				JPanel parent = (JPanel) button.getParent();
				JPanel grandparent = (JPanel) parent.getParent();
				parent.removeAll();
				grandparent.revalidate();
				grandparent.repaint();
			}
			
		});
		this.add(adc);
		
		Button mid = new Button("Mid");
		mid.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Game.addWard("Mid", point, minutes, seconds);
				Button button = (Button) e.getSource();
				JPanel parent = (JPanel) button.getParent();
				JPanel grandparent = (JPanel) parent.getParent();
				parent.removeAll();
				grandparent.revalidate();
				grandparent.repaint();
			}
			
		});
		this.add(mid);
		
		Button solo = new Button("Solo");
		solo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Game.addWard("Solo", point, minutes, seconds);
				Button button = (Button) e.getSource();
				JPanel parent = (JPanel) button.getParent();
				JPanel grandparent = (JPanel) parent.getParent();
				parent.removeAll();
				grandparent.revalidate();
				grandparent.repaint();
			}
			
		});
		this.add(solo);
	}
}

class MapListener implements MouseListener {
	Board board;

	MapListener(Board board) {
		this.board = board;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		long runningTime = System.currentTimeMillis() - this.board.timerPanel.lastTickTime;
		Duration duration = Duration.ofMillis(runningTime);
		long minutes = duration.toMinutes();
		duration = duration.minusMinutes(minutes);
		long millis = duration.toMillis();
		long seconds = millis / 1000;
		if (minutes < 1) {
			seconds = 60 - seconds;
		}else {
			minutes-=1;
		}
		
		PersonSelector ps = new PersonSelector(e.getLocationOnScreen(), Math.toIntExact(minutes), Math.toIntExact(seconds));
		this.board.add(ps, BorderLayout.EAST);
		this.board.pack();

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
