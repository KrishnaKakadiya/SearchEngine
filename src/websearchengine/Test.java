package websearchengine;
import java.awt.*;

import javax.swing.*;


public class Test {
	
	public static void main(String args[])
	{
		window();
	}
	
	static void window()
	{
		JPanel jp= new JPanel();
		JFrame jf=new JFrame("chickoo...");
		
		JLabel jl=new JLabel("Asignment:");
		jf.setVisible(true);
		jf.setSize(200, 100);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//jf.add(jl);
		jp.add(jl);
	}
	
}
