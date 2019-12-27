package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;


import algorithms.*;
import dataStructure.*;
import utils.*;

/**
 * This class makes a gui window to represent a graph and
 * use the Algorithms from class Graph_Algo on live.
 * (use the methods and represent it on the gui window while it is still up).
 * @author YosefTwito and EldarTakach
 */

public class Graph_Gui extends JFrame implements ActionListener, MouseListener{
	
	graph gr;
	
	public Graph_Gui(graph g){
		initGUI(g);
	}
	
	
	public void paint(Graphics d) {
		super.paint(d);
		
		if (gr != null) 
		{
			//get nodes
			Collection<node_data> nodes = gr.getV();
			
			for (node_data n : nodes) {
				//draw nodes
				Point3D p = n.getLocation();
				d.setColor(Color.BLACK);
				d.fillOval(p.ix(), p.iy(), 11, 11);
				
				//draw nodes-key's
				d.setColor(Color.BLUE);
				d.drawString(""+n.getKey(), p.ix()-4, p.iy()-4);
				
				//check if there are edges
				if (gr.edgeSize()==0) { continue; }
				if ((gr.getE(n.getKey())!=null)) {
					//get edges
					Collection<edge_data> edges = gr.getE(n.getKey());
					for (edge_data e : edges) {
						//draw edges
						d.setColor(Color.GREEN);
						((Graphics2D) d).setStroke(new BasicStroke(2,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
						Point3D p2 = gr.getNode(e.getDest()).getLocation();
						d.drawLine(p.ix()+5, p.iy()+5, p2.ix()+5, p2.iy()+5);
						//draw direction
						d.setColor(Color.MAGENTA);
						d.fillOval((int)((p.ix()*0.7)+(0.3*p2.ix()))+2, (int)((p.iy()*0.7)+(0.3*p2.iy())), 9, 9);
						//draw weight
						String sss = ""+String.valueOf(e.getWeight());
						d.drawString(sss, 1+(int)((p.ix()*0.7)+(0.3*p2.ix())), (int)((p.iy()*0.7)+(0.3*p2.iy()))-2);
					}
				}	
			}
		}	
	}
	
	
	private void initGUI(graph g) {
		this.gr=g;
		this.setSize(1280, 720);
		this.setTitle("Hello and welcome to The Maze Of Waze !");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon img = new ImageIcon("navigate.jpg");
		this.setIconImage(img.getImage());
		
		
		MenuBar menuBar = new MenuBar();
		this.setMenuBar(menuBar);
		
		Menu file = new Menu("File ");
		menuBar.add(file);
		
		Menu alg  = new Menu("Algorithms ");
		menuBar.add(alg);
		
		MenuItem item2 = new MenuItem("Init From textFile ");
		item2.addActionListener(this);
		file.add(item2);
		
		MenuItem item3 = new MenuItem("Save as textFile ");
		item3.addActionListener(this);
		file.add(item3);
		
		MenuItem item4 = new MenuItem("Save as png ");
		item4.addActionListener(this);
		file.add(item4);
		
		MenuItem item5 = new MenuItem("Show Shortest Path  ");
		item5.addActionListener(this);
		alg.add(item5);
		
		MenuItem item1 = new MenuItem("Shortest Path Distance");
		item1.addActionListener(this);
		alg.add(item1);
		
		MenuItem item6 = new MenuItem("The SalesMan Problem");
		item6.addActionListener(this);
		alg.add(item6);
		
		MenuItem item7 = new MenuItem("Is it Conncected ?"  );
		item7.addActionListener(this);
		alg.add(item7);
	 
		
		this.addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent Command) {
		String str = Command.getActionCommand();		
		Graph_Algo t=new Graph_Algo();
		JFileChooser j;
		FileNameExtensionFilter filter;
		
		switch(str) {
		
		case "Init From textFile ": ////////////////////////////////////// gotta check /////////////////
			System.out.println("Init From textFile: ");
			t=new Graph_Algo();

			j = new JFileChooser(FileSystemView.getFileSystemView());
			j.setDialogTitle("Init graph out of text file.."); 
			filter = new FileNameExtensionFilter(" .txt","txt");
			j.setFileFilter(filter);

			int returnVal = j.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				System.out.println("You chose to open this file: " + j.getSelectedFile().getName());
				t.init(j.getSelectedFile().getAbsolutePath());
			}			
			break;

		case "Save as textFile ": ////////////////////////////////////// gotta check /////////////////
			System.out.println("Save as textFile: ");
			t=new Graph_Algo();

			j = new JFileChooser(FileSystemView.getFileSystemView());
			j.setDialogTitle("Save graph to text file.."); 
			filter = new FileNameExtensionFilter(" .txt","txt");
			j.setFileFilter(filter);

			int userSelection = j.showSaveDialog(null);
			if (userSelection == JFileChooser.APPROVE_OPTION) {
				System.out.println("Save as file: " + j.getSelectedFile().getAbsolutePath());
				t.save(j.getSelectedFile().getAbsolutePath());
			}
			break;

		case "Save as png ": // done !
			System.out.println("Save as png ");
			try {
				BufferedImage i = new BufferedImage(this.getWidth(), this.getHeight()+45, BufferedImage.TYPE_INT_RGB);
				Graphics g = i.getGraphics();
				paint(g);
				ImageIO.write(i, "png", new File("SavedGraph.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		case "Show Shortest Path ": //////////////////////////////////////// gotta check, and fix! ////////
			try {
				System.out.println("Show Shortest Path ");
				JFrame SSPin = new JFrame();
				String SourceNodeSSP = JOptionPane.showInputDialog(SSPin,"Enter Source-Node:");
				String DestNodeSSP = JOptionPane.showInputDialog(SSPin,"Enter Destination-Node:");

				int srcSSP = Integer.parseInt(SourceNodeSSP);
				int destSSP = Integer.parseInt(DestNodeSSP);

				Graph_Algo newGSSP = new Graph_Algo();
				newGSSP.init(gr);

				List<node_data> SSPdis = newGSSP.shortestPath(srcSSP, destSSP);
				List<edge_data> SSPe = new ArrayList<edge_data>();
				for (int i=0; i<SSPdis.size()-1; i++) {
					SSPe.add(this.gr.getEdge(SSPdis.get(i).getKey(), SSPdis.get(i+1).getKey()));
				}
				JFrame SSP = new JFrame("Show Shortest Path: ");	
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "Shortest Path Distance": // done !
			try
			{
				JFrame SPDinput = new JFrame();
				String SourceNodeSPD = JOptionPane.showInputDialog(SPDinput,"Enter Source-Node:");
				String DestNodeSPD = JOptionPane.showInputDialog(SPDinput,"Enter Destination-Node:");

				int srcSPD = Integer.parseInt(SourceNodeSPD);
				int destSPD = Integer.parseInt(DestNodeSPD);
				
				Graph_Algo newg = new Graph_Algo();
				newg.init(this.gr);
				double x = newg.shortestPathDist(srcSPD, destSPD);
				JOptionPane.showMessageDialog(SPDinput, "The Shortest Path Distance is: " + x);
				
				System.out.println("Shortest Path Distance is:" + x);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case "The SalesMan Problem": 
			System.out.println("The SalesMan Problem");
			break;

		case "Is it Conncected ?": ////////////////////////////////////////// done - gotta check //////////////
			Graph_Algo isCga = new Graph_Algo();
			isCga.init(this.gr);
			if (isCga.isConnected()) { System.out.println("The graph is Connected !"); }
			else { System.out.println("The graph is not Connected !");
			break;
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("mouseClicked");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("mousePressed");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("mouseReleased");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("mouseEntered");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("mouseExited");
	}
}