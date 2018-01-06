package LLK.entity;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ImageButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int row;
	private int col;
	private int num;
	private int nums;
	
	
	public ImageButton(int row, int col, int num, int nums) {
		super();
		this.row = row;
		this.col = col;
		this.num = num;
		this.nums = nums;
		updateImage(true);
	}
	
	public void updateImage(boolean isShow) {
		if (num <= 0) isShow = false;
		if (isShow) {
			String filename = this.getClass().getResource("/images/" + num + ".jpg").getFile();
			ImageIcon icon = new ImageIcon(filename);
			icon.setImage(icon.getImage().getScaledInstance(500/nums, 500/nums, Image.SCALE_DEFAULT));  
			this.setIcon(icon);
			System.out.println(filename);
		}
		else {
			this.setIcon(null);
		}
	}
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
}
