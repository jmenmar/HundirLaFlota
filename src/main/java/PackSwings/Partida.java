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
import javax.swing.JLabel;
import java.awt.Font;

public class Partida extends JFrame {
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JButton[][] mCasillas = null;
	private JButton[][] mCasillas2 = null;
	
	private int numeroDeFilas = 10;
	private int numeroDeColumnas = 10;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	
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
			
			JLabel lblTableroJugador = new JLabel("TABLERO JUGADOR");
			lblTableroJugador.setFont(new Font("Tahoma", Font.BOLD, 20));
			
			JLabel lblTableroIa = new JLabel("TABLERO IA");
			lblTableroIa.setFont(new Font("Tahoma", Font.BOLD, 20));
			
			JButton btnNewButton = new JButton("New button");
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(46)
								.addComponent(getPanel_1(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(63)
								.addComponent(getPanel_3(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(69)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(btnNewButton)
									.addComponent(getButton_2())
									.addComponent(getButton_1())
									.addComponent(getButton())))
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(118)
								.addComponent(lblTableroJugador)
								.addGap(228)
								.addComponent(lblTableroIa, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(47, Short.MAX_VALUE))
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGap(54)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblTableroJugador)
							.addComponent(lblTableroIa, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addComponent(getPanel_1(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(getPanel_3(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(9)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(getButton(), GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(getButton_1(), GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(getButton_2(), GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(129, Short.MAX_VALUE))
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
			inicializar2();
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
	
	public void inicializar2(){
		mCasillas2 = new JButton[10][10];
		for(int n=0;n<10;n++){
			for(int m=0;m<10;m++){
				JButton temp = new JButton();
				getContentPane().add(temp);
				mCasillas2[n][m] = temp;
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
		      JButton temp = mCasillas2[n][m];
		      //fijar cada casilla a una posicion y tamaño en funcion de su fila y columna
		      temp.setBounds(446 +(m * anchoDeCasilla),100+(n * altoDeCasilla), anchoDeCasilla, altoDeCasilla);
			}
		} 
		
	}
	private JButton getButton() {
		if (button == null) {
			button = new JButton("New button");
		}
		return button;
	}
	private JButton getButton_1() {
		if (button_1 == null) {
			button_1 = new JButton("New button");
		}
		return button_1;
	}
	private JButton getButton_2() {
		if (button_2 == null) {
			button_2 = new JButton("New button");
		}
		return button_2;
	}
}
