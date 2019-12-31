package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
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
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import algorithms.*;
import dataStructure.*;
import utils.*;

/**
 * This class makes a gui window to represent a graph and use the Algorithms
 * from class Graph_Algo on live. dataStructure contains the "dots" created in
 * the background and also on the screen.
 * 
 * @authors Matan Greenberg, Or Mendel
 */

public class Graph_Gui extends JFrame implements ActionListener, MouseListener {

	graph gr;

	public Graph_Gui(graph g) {
		initGUI(g);
	}

	public void paint(Graphics d) {
		super.paint(d);

		if (gr != null) {
			// get nodes
			Collection<node_data> nodes = gr.getV();

			for (node_data n : nodes) {
				// draw nodes
				Point3D p = n.getLocation();
				d.setColor(Color.BLUE);
				d.drawOval(p.ix(), p.iy(), 20, 20);

				// check if there are edges
				if (gr.edgeSize() == 0)
					continue;
				if ((gr.getE(n.getKey()) != null)) {
					// get edges
					Collection<edge_data> edges = gr.getE(n.getKey());
					for (edge_data e : edges) {
						// draw edges
						d.setColor(Color.gray);
						((Graphics2D) d).setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
						Point3D p2 = gr.getNode(e.getDest()).getLocation();
						d.drawLine(p.ix() + 5, p.iy() + 5, p2.ix() + 5, p2.iy() + 5);
						// draw direction
						d.setColor(Color.MAGENTA);
						d.fillOval((int) ((p.ix() * 0.2) + (0.8 * p2.ix())) + 2,
								(int) ((p.iy() * 0.2) + (0.8 * p2.iy())), 9, 9);
						// draw weight
						d.setColor(Color.RED);
						String sss = "" + String.valueOf(e.getWeight());
						d.drawString(sss, 1 + (int) ((p.ix() * 0.2) + (0.8 * p2.ix())),
								(int) ((p.iy() * 0.2) + (0.8 * p2.iy())) - 2);
					}
				}
				// draw nodes-key's
				d.setColor(Color.RED);
				d.drawString("" + n.getKey(), p.ix() + 9, p.iy() + 12);
			}
		}
	}

	private void initGUI(graph g) {
		this.gr = g;
		this.setSize(1000, 730);
		this.setTitle("The MAZE Of WAZE");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon img = new ImageIcon("navigate.jpg");
		this.setIconImage(img.getImage());

		MenuBar menuBar = new MenuBar();
		this.setMenuBar(menuBar);

		Menu file = new Menu("File");
		menuBar.add(file);

		Menu alg = new Menu("Algorithms");
		menuBar.add(alg);

		Menu structur = new Menu("Structure");
		menuBar.add(structur);

		MenuItem item_s1 = new MenuItem("Add node");
		item_s1.addActionListener(this);
		structur.add(item_s1);

		MenuItem item_s2 = new MenuItem("Remove node");
		item_s2.addActionListener(this);
		structur.add(item_s2);

		MenuItem item_s3 = new MenuItem("Connect nodes");
		item_s3.addActionListener(this);
		structur.add(item_s3);

		MenuItem item2 = new MenuItem("Init From textFile");
		item2.addActionListener(this);
		file.add(item2);

		MenuItem item3 = new MenuItem("Save as textFile");
		item3.addActionListener(this);
		file.add(item3);

		MenuItem item4 = new MenuItem("Save as png");
		item4.addActionListener(this);
		file.add(item4);

		MenuItem item4_1 = new MenuItem("Save as jpg");
		item4.addActionListener(this);
		file.add(item4_1);

		MenuItem item5 = new MenuItem("Find shortest Path");
		item5.addActionListener(this);
		alg.add(item5);

		MenuItem item1 = new MenuItem("shortest path distance");
		item1.addActionListener(this);
		alg.add(item1);

		MenuItem item6 = new MenuItem("Traveling Salesman Problem (TSP)");
		item6.addActionListener(this);
		alg.add(item6);

		MenuItem item7 = new MenuItem("isConnected?");
		item7.addActionListener(this);
		alg.add(item7);

		this.addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent Command) {
		String str = Command.getActionCommand();

		switch (str) {

		case "Add node":
			add_node();
			break;

		case "Remove node":
			remove_node();
			break;

		case "Connect nodes":
			connect_nodes();
			break;

		case "Init From textFile":
			init_txt();
			break;
			
		case "Save as textFile":
			save_txt();
			break;
			
		case "Find shortest Path":
			shortest_path_way();
			break;
			
		case "shortest path distance":
			shortest_path_dist();
			break;
			
			


		case "Save as png": // undone !
			System.out.println("Save as png ");
			try {
				BufferedImage i = new BufferedImage(this.getWidth(), this.getHeight() + 45, BufferedImage.TYPE_INT_RGB);
				Graphics g = i.getGraphics();
				paint(g);
				ImageIO.write(i, "png", new File("SavedGraph.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
//		case "Add node":
//			System.out.println("Adding new node...");
//			JFrame jf=new JFrame();
//			String tmp = JOptionPane.showInputDialog(jf,"Enter node key:");
//			int key=Integer.parseInt(tmp);
//			jf=new JFrame();
//			tmp=JOptionPane.showInputDialog(jf,"Enter coordinates:");
//			MouseEvent m = 

//			mouseClicked_forCircle();
//			KeyEvent m = null;
//			int xx = m.
//			int yy = m.getYOnScreen();
//			Point3D p = new Point3D(xx,yy,0);
//			node no = new node(key,p,1);
//			gr.addNode(no);
//			Graphics d = null;
//			d.fillOval(xx, yy, 11, 11);
//			break;
//		case "Find shortest Path": //////////////////////////////////////// gotta check, and fix! ////////
//			try {
//				System.out.println("Show Shortest Path ");
//				JFrame SSPin = new JFrame();
//				String SourceNodeSSP = JOptionPane.showInputDialog(SSPin, "Enter Source-Node:");
//				String DestNodeSSP = JOptionPane.showInputDialog(SSPin, "Enter Destination-Node:");
//
//				int srcSSP = Integer.parseInt(SourceNodeSSP);
//				int destSSP = Integer.parseInt(DestNodeSSP);
//
//				Graph_Algo newGSSP = new Graph_Algo(gr);
////				newGSSP.init(gr);
//
//				List<node_data> SSPdis = newGSSP.shortestPath(srcSSP, destSSP);
//				List<edge_data> SSPe = new ArrayList<edge_data>();
//				for (int i = 0; i < SSPdis.size() - 1; i++) {
//					SSPe.add(this.gr.getEdge(SSPdis.get(i).getKey(), SSPdis.get(i + 1).getKey()));
//				}
//				JFrame SSP = new JFrame("Show Shortest Path: ");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			break;


		case "Traveling Salesman Problem (TSP)":
			System.out.println("The SalesMan Problem");
			break;

		case "isConnected?": ////////////////////////////////////////// done - gotta check //////////////
			Graph_Algo isCga = new Graph_Algo(this.gr);
//			isCga.init(this.gr);
			if (isCga.isConnected()) {
				System.out.println("The graph is Connected !");
			} else {
				System.out.println("The graph is not Connected !");
				break;
			}
		}
	}

	private void shortest_path_dist() {
		Graph_Algo g = new Graph_Algo(gr);
		String src = JOptionPane.showInputDialog(this,"insert source node key");
		String dest = JOptionPane.showInputDialog(this,"insert destination node key");
		try {
			double d = g.shortestPathDist(Integer.parseInt(src), Integer.parseInt(dest));
			JOptionPane.showMessageDialog(this, "the distance is: "+d,"INFORMATION ",JOptionPane.INFORMATION_MESSAGE);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, "ERROR "+e.getMessage(),"ERROR ",JOptionPane.ERROR_MESSAGE);
		}
		
	}

	private void shortest_path_way()
	{
		Graph_Algo g = new Graph_Algo(gr);
		String src = JOptionPane.showInputDialog(this,"insert source node key");
		String dest = JOptionPane.showInputDialog(this,"insert destination node key");
		ArrayList <node_data> tmp = new ArrayList<node_data>();
		try
		{
			tmp = (ArrayList<node_data>) g.shortestPath(Integer.parseInt(src), Integer.parseInt(dest));
			String s = "";
			if(!tmp.isEmpty())
			{
				for(node_data n:tmp)
				{
					s=s.concat(n.getKey()+"-->");
				}
			}
			else
				JOptionPane.showMessageDialog(this, "There is no path","ERROR",JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(this, "The shortest path is: "+s,"INFORMATION",JOptionPane.INFORMATION_MESSAGE);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, "ERROR "+e.getMessage(),"ERROR ",JOptionPane.ERROR_MESSAGE);
		}
	}

	//*****************************************fix*****************************************
	private void save_txt() 
	{
		Graph_Algo g = new Graph_Algo(gr);
		try {
			FileDialog fd = new FileDialog(this, "Save as txt file", FileDialog.SAVE);
			fd.setFile("*.txt");
			fd.setDirectory("C:\\Users");
			fd.setFilenameFilter(new FilenameFilter() 
			{
				@Override
				public boolean accept(File dir, String name) {
					return name.endsWith(".txt");
				}
			});
			fd.setVisible(true);
			String fileName = fd.getFile();
			g.save(fileName);
		} catch (Exception ex) {
			System.out.print("Error saving file " + ex);
			System.exit(2);
		}
	}

	//*****************************************fix*****************************************
	private void init_txt()
	{
		Graph_Algo g = new Graph_Algo();
		try {
			FileDialog fd = new FileDialog(this, "Open text file", FileDialog.LOAD);
			fd.setFile("*.txt");
			fd.setDirectory("C:\\Users");
			fd.setFilenameFilter(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.endsWith(".txt");
				}
			});
			fd.setVisible(true);
			String folder = fd.getDirectory();
			String fileName = fd.getFile();
			g.init(fileName);
		} catch (Exception ex) {
			System.out.print("Error reading file " + ex);
			System.exit(2);
		}
	}

	private void connect_nodes() {
		Graph_Algo g = new Graph_Algo(gr);
		String src = JOptionPane.showInputDialog("insert source node key");
		String dest = JOptionPane.showInputDialog("insert destination node key");
		String weight = JOptionPane.showInputDialog("insert weight for edge");
		//Collection c = (Collection) g.nodesMap;
		try {
//			if (g.nodesMap.get(Integer.parseInt(src)) == null || g.nodesMap.get(Integer.parseInt(dest)) == null) {
//				JOptionPane.showMessageDialog(this, "ERROR : 1 key or more hadn't found in the graph", "ERROR ",JOptionPane.ERROR_MESSAGE);
//				return;
//			}
			gr.connect(Integer.parseInt(src), Integer.parseInt(dest), Double.parseDouble(weight));
			JOptionPane.showMessageDialog(this, "success", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "ERROR " + e.getMessage(), "ERROR ", JOptionPane.ERROR_MESSAGE);
		}
		repaint();

	}

	private void remove_node() {
		String s = JOptionPane.showInputDialog(this, "enter a key to remove node");
		try {
			int k = Integer.parseInt(s);
			node_data n = gr.removeNode(k);
			if (n != null) {
				JOptionPane.showMessageDialog(this, "node removed sucssesfully", "INFORMATION",
						JOptionPane.INFORMATION_MESSAGE);
			} else
				JOptionPane.showMessageDialog(this, "node does not exist", "ERROR ", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "ERROR " + e.getMessage(), "ERROR ", JOptionPane.ERROR_MESSAGE);
		}
		repaint();
	}

	private void add_node() {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("mouseClicked");
	}

	public void mouseClicked_forCircle(MouseEvent e) {
		System.out.println("matan ha gever");
		int x = e.getX();
		int y = e.getY();
		int radius = 20;
		drawCircle(x - (radius / 2), y - (radius / 2));
	}

	@Override
	/**
	 * Connect nodes in the graph
	 * 
	 * @param e
	 */
	public void mousePressed(MouseEvent e) {
		System.out.println("mousePressed");
	}

	/**
	 * 
	 */
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
//		System.exit(1000);
	}

	/**
	 * Important function!!!
	 * 
	 * @param g
	 */
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2 = (Graphics2D) g;
	}

	public void drawCircle(int x, int y) {
		Graphics g = getGraphics();
		int radius = 20;
		g.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);

	}
}