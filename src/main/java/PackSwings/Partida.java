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
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import packMainJava.Casilla;
import packMainJava.Tablero;

import java.awt.Panel;

public class Partida extends JFrame {
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JButton[][] mCasillas = null;
	
	private int numeroDeFilas = 10;
	private int numeroDeColumnas = 10;
	
//	int tableroUsuario[][]= new int[10][10];
//	int tableroIA[][]= new int[10][10];
	

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
						.addComponent(getPanel_1(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(62)
						.addComponent(getPanel_3(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(206, Short.MAX_VALUE))
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGap(98)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addComponent(getPanel_1(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(getPanel_3(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(114, Short.MAX_VALUE))
			);
			panel.setLayout(gl_panel);
		}
		return panel;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setPreferredSize(new Dimension(335, 335));
			panel_1.setBackground(Color.BLUE);
			GroupLayout gl_panel_1 = new GroupLayout(panel_1);
			gl_panel_1.setHorizontalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addGap(0, 335, Short.MAX_VALUE)
			);
			gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addGap(0, 335, Short.MAX_VALUE)
			);
			panel_1.setLayout(gl_panel_1);
			inicializar();
			ordenar();
		}
		return panel_1;
	}


	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setPreferredSize(new Dimension(335, 335));
			panel_3.setBackground(Color.BLUE);
			GroupLayout gl_panel_3 = new GroupLayout(panel_3);
			gl_panel_3.setHorizontalGroup(gl_panel_3.createParallelGroup(
					Alignment.LEADING).addGap(349, 349, Short.MAX_VALUE));
			gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(
					Alignment.LEADING).addGap(349, 349, Short.MAX_VALUE));
			panel_3.setLayout(gl_panel_3);
			inicializar();
			ordenar2();
		}
		return panel_3;
	}
	
	
	public void inicializar(){
		mCasillas = new JButton[10][10];
		for(int n=0;n<10;n++){
			for(int m=0;m<10;m++){
				JButton temp = new JButton();
				getContentPane().add(temp);
				mCasillas[n][m] = temp;
			 }
	     }
	}
	
	public void ordenar(){
		int anchoTotal = 335;
		int altoTotal = 335;
		int anchoDeCasilla = anchoTotal / 10;
		int altoDeCasilla = altoTotal /10;
		
		for(int n=0;n<10;n++){
			for(int m=0;m<10;m++){
			  //obtenemos una referencia al boton actual
		      JButton temp = mCasillas[n][m];
		      //fijar cada casilla a una posicion y tamaño en funcion de su fila y columna
		      temp.setBounds(48 +(m * anchoDeCasilla),100+(n * altoDeCasilla), anchoDeCasilla, altoDeCasilla);
			}
		} 
		
	}
	
	public void ordenar2(){
		int anchoTotal = 335;
		int altoTotal = 335;
		int anchoDeCasilla = anchoTotal / 10;
		int altoDeCasilla = altoTotal /10;
		
		for(int n=0;n<10;n++){
			for(int m=0;m<10;m++){
			  //obtenemos una referencia al boton actual
		      JButton temp = mCasillas[n][m];
		      //fijar cada casilla a una posicion y tamaño en funcion de su fila y columna
		      temp.setBounds(446 +(m * anchoDeCasilla),100+(n * altoDeCasilla), anchoDeCasilla, altoDeCasilla);
			}
		} 
		
	}
}
