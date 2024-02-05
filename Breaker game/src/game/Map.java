
 package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Arrays;

public class Map {
	private int[][] map;
	public int brickWidth;
	public int brickHeight;

	public Map(int row, int col) {
		map = new int[row][col];
		for (int[] rows : map) {
			Arrays.fill(rows, 1);
		}
		brickWidth = 540 / col;
		brickHeight = 150 / row;
	}
	public int rows() {
		return map.length;
		
	}
	public int colums() {
		return map[0].length;
		
	}
	public void setBreakValue(int val , int i , int j) {
		map[i][j]=val;
		
	}
	public int getBreakValue(int i , int j) {
		return map[i][j];
		
	}

	public void drow(Graphics2D gr) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] > 0) {
					gr.setColor(Color.DARK_GRAY);
					gr.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
					gr.setStroke(new BasicStroke(3));
					gr.setColor(Color.white);
					gr.drawRect(j * brickWidth +80, i * brickHeight + 50, brickWidth, brickHeight);
				}
				

			}
		}
		

	}

}
