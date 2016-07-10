package me.hao0.basic.nio;

import javax.swing.*;
import java.awt.Color;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ActionTest {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				ActionFrame frame = new ActionFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}		
		});
	}
}

class ActionFrame extends JFrame{
	public ActionFrame(){
		setTitle("ActionTest");
		setSize(this.DEFAULT_WIDTH,this.DEFAULT_HIEGHT);
		
		buttonPanel = new JPanel();
		
	}
	
	public class ColorAction extends AbstractAction{

		public ColorAction(String name, Icon icon, Color c){
			
		}
		
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
	
	private JPanel buttonPanel;
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HIEGHT = 200;
}
