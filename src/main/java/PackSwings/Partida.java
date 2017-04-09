package PackSwings;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JDesktopPane;

import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import packMainJava.Casilla;

import java.awt.Panel;

public class Partida extends JFrame {
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	
	int tableroUsuario[][]= new int[10][10];
	int tableroIA[][]= new int[10][10];

	
	public void iniciarPartida(){
		for(int n=0;n<10;n++){
			for(int m=0;m<10;m++){
				// 0 significa agua
				tableroUsuario[n][m]=0;
				tableroIA[n][m]=0;
			}
		}
	}
	
	public void pintarTablero(Graphics g, int tab[][],int x, int y){
		for(int n=0;n<10;n++){
			for(int m=0;m<10;m++){
				if (tab[n][m]==0){
					g.drawRect(x+n*30, y+m*30,30,30);
				}
			 }
	     }
	}
	
	//Esta clase Paint si la pones como comentario, al darle al Run,
	//te saldra la interfaz que hemos creado, sino te saldran los tableros solo
	//mi duda es como meter estos pintarTablero dentro de los jPanels 
	//para que sean los panels los Arrays de casillas

	public void paint(Graphics g){
	
		pintarTablero(g,tableroUsuario, 200, 200);
		pintarTablero(g,tableroIA, 600, 200);
		
	} 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Partida frame = new Partida();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Partida() {

		initialize();
		iniciarPartida();
		
	}

	private void initialize() {
		setBounds(100, 100, 1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(getPanel(), BorderLayout.CENTER);
		
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(new Color(175, 238, 238));
			panel.setPreferredSize(new Dimension(20, 20));
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGap(46)
						.addComponent(getPanel_1(), GroupLayout.PREFERRED_SIZE, 366, GroupLayout.PREFERRED_SIZE)
						.addGap(62)
						.addComponent(getPanel_3(), GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(151, Short.MAX_VALUE))
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGap(98)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addComponent(getPanel_3(), Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(getPanel_1(), GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(99, Short.MAX_VALUE))
			);
			panel.setLayout(gl_panel);
		}
		return panel;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setPreferredSize(new Dimension(349, 349));
			panel_1.setSize(new Dimension(100, 100));
			panel_1.setBackground(Color.BLUE);
			GroupLayout gl_panel_1 = new GroupLayout(panel_1);
			gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(
					Alignment.LEADING).addGap(0, 349, Short.MAX_VALUE));
			gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(
					Alignment.LEADING).addGap(0, 349, Short.MAX_VALUE));
			panel_1.setLayout(gl_panel_1);
		}
		return panel_1;
	}


	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setPreferredSize(new Dimension(100, 100));
			panel_3.setBackground(Color.BLUE);
			GroupLayout gl_panel_3 = new GroupLayout(panel_3);
			gl_panel_3.setHorizontalGroup(gl_panel_3.createParallelGroup(
					Alignment.LEADING).addGap(349, 349, Short.MAX_VALUE));
			gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(
					Alignment.LEADING).addGap(349, 349, Short.MAX_VALUE));
			panel_3.setLayout(gl_panel_3);

		}
		return panel_3;
	}
}
