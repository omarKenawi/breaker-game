package game;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		GamePlay gameplay = new GamePlay();
		frame.setBounds(500, 130, 700, 600);
		frame.setTitle("Break Breaker");
		frame.add(gameplay);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
