package PackSwings;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
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

import packMainJava.Barco;
import packMainJava.Casilla;
import packMainJava.IA;
import packMainJava.Inventario;
import packMainJava.Jugador;
import packMainJava.Tablero;
import packMainJava.TipoDeBarco;

import java.awt.Panel;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;

import packMainJava.CasillaEstado;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
public class Partida extends JFrame implements Observer,ActionListener {
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JButton[][] mCasillas = null;
	private JButton[][] mCasillas2 = null;
	
	int partidaEstado=1;

	private int numeroDeFilas = 10;
	private int numeroDeColumnas = 10;
	private JButton Bomba;
	private JButton Misil;
	private JButton Reparar;
	private JButton Escudo;
	private JButton Radar;
	private JButton TIENDA;
	private static Tablero tableroJ;
	private static Tablero tableroIA;
	Inventario inv = Inventario.getInventario(); // Instancia única al Singleton
	Tienda shop = Tienda.getTienda(); // Instancia única al Singleton
	private JRadioButton rdbtnSubmarino;
	private JRadioButton rdbtnDestructor;
	private JRadioButton rdbtnFragata;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnHorizontal;
	private JRadioButton rdbtnVertical;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
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
		if(partidaEstado==1){
			Misil.setEnabled(false);
			//Bomba.setEnabled(false);
			Escudo.setEnabled(false);
			Radar.setEnabled(false);
			Reparar.setEnabled(false);
			TIENDA.setEnabled(false);
		}
		
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

			JButton Bomba = new JButton("Nº de Bombas");
			
			JRadioButton rdbtnNewRadioButton = new JRadioButton("PORTAAVIONES");
			rdbtnNewRadioButton.setSelected(true);
			buttonGroup.add(rdbtnNewRadioButton);
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(46)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(getPanel_1(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(63)
										.addComponent(getPanel_3(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(rdbtnNewRadioButton, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
											.addComponent(getRdbtnHorizontal(), GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(getRdbtnSubmarino(), GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_panel.createSequentialGroup()
												.addComponent(getRdbtnDestructor(), GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(getRdbtnFragata(), GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
											.addComponent(getRdbtnVertical(), GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
										.addComponent(getTIENDA(), GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)))
								.addGap(62)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
									.addComponent(Bomba, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(getEscudo(), GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(getRadar(), GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
									.addComponent(getReparar(), 0, 0, Short.MAX_VALUE)
									.addComponent(getMisil(), Alignment.TRAILING)))
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(118)
								.addComponent(lblTableroJugador)
								.addGap(228)
								.addComponent(lblTableroIa, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(20, Short.MAX_VALUE))
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
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(9)
								.addComponent(Bomba, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(getMisil(), GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(getReparar(), GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(getEscudo(), GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(getRadar(), GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(83, Short.MAX_VALUE))
							.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(getPanel_1(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(getPanel_3(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
										.addComponent(getTIENDA(), GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
										.addGap(30))
									.addGroup(gl_panel.createSequentialGroup()
										.addGap(32)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
											.addComponent(getRdbtnSubmarino(), GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
											.addComponent(rdbtnNewRadioButton, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
											.addComponent(getRdbtnDestructor(), GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
											.addComponent(getRdbtnFragata(), GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
											.addComponent(getRdbtnHorizontal())
											.addComponent(getRdbtnVertical()))
										.addContainerGap())))))
			);
			panel.setLayout(gl_panel);
		}
		return panel;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setPreferredSize(new Dimension(335, 335));
			panel_1.setBackground(Color.BLACK);
			GroupLayout gl_panel_1 = new GroupLayout(panel_1);
			gl_panel_1.setHorizontalGroup(
					gl_panel_1.createParallelGroup(Alignment.LEADING).addGap(0, 335, Short.MAX_VALUE));
			gl_panel_1.setVerticalGroup(
					gl_panel_1.createParallelGroup(Alignment.LEADING).addGap(0, 335, Short.MAX_VALUE));
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
			panel_3.setBackground(Color.BLACK);
			GroupLayout gl_panel_3 = new GroupLayout(panel_3);
			gl_panel_3.setHorizontalGroup(
					gl_panel_3.createParallelGroup(Alignment.LEADING).addGap(349, 349, Short.MAX_VALUE));
			gl_panel_3.setVerticalGroup(
					gl_panel_3.createParallelGroup(Alignment.LEADING).addGap(349, 349, Short.MAX_VALUE));
			panel_3.setLayout(gl_panel_3);
			inicializar2();
			ordenar2();
		}
		return panel_3;
	}

	public void inicializar() {
		mCasillas = new JButton[10][10];
		Tablero tableroJ = new Tablero();
		for (int n = 0; n < 10; n++) {
			for (int m = 0; m < 10; m++) {
				JButton temp = new JButton();
				getContentPane().add(temp);
				mCasillas[n][m] = temp;
				temp.setBackground(Color.BLUE);
				temp.addActionListener(this);
			}
		}
	}

	public void ordenar() {
		int anchoTotal = 335;
		int altoTotal = 335;
		int anchoDeCasilla = anchoTotal / 10;
		int altoDeCasilla = altoTotal / 10;

		for (int n = 0; n < 10; n++) {
			for (int m = 0; m < 10; m++) {
				// obtenemos una referencia al boton actual
				JButton temp = mCasillas[n][m];
				// fijar cada casilla a una posicion y tama�o en funcion de su
				// fila y columna
				temp.setBounds(48 + (m * anchoDeCasilla),
						100 + (n * altoDeCasilla), anchoDeCasilla,
						altoDeCasilla);
			}
		}

	}

	public void inicializar2() {
		mCasillas2 = new JButton[10][10];
		tableroIA = new Tablero();
		for (int n = 0; n < 10; n++) {
			for (int m = 0; m < 10; m++) {
				JButton temp = new JButton();
				getContentPane().add(temp);
				mCasillas2[n][m] = temp;
			}
		}
		int cont = 0;
		int numBarcos = 1;
		Barco hmsVictory;
		for (int tam = 1; tam <= 4; tam++) {
			while (cont < numBarcos) {
				IA.ponerBarco(tableroIA, hmsVictory);
				cont++;
			}
			cont = 0;
			numBarcos++;
		}
	}

	public void ordenar2() {
		int anchoTotal = 335;
		int altoTotal = 335;
		int anchoDeCasilla = anchoTotal / 10;
		int altoDeCasilla = altoTotal / 10;

		for (int n = 0; n < 10; n++) {
			for (int m = 0; m < 10; m++) {
				// obtenemos una referencia al boton actual
				JButton temp = mCasillas2[n][m];
				// fijar cada casilla a una posicion y tama�o en funcion de su
				// fila y columna
				temp.setBounds(446 + (m * anchoDeCasilla),
						100 + (n * altoDeCasilla), anchoDeCasilla,
						altoDeCasilla);
			}
		}

	}

	private JButton getMisil() {
		if (Misil == null) {
			Misil = new JButton("x" + inv.getNumMisiles());
			Misil.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			Misil.setHorizontalAlignment(SwingConstants.LEFT);
			Misil.setIcon(new ImageIcon(this.getClass().getResource(
					"/misil.png")));
		}
		return Misil;
	}

	private JButton getBomba() {
		if (Bomba == null) {
			Bomba = new JButton("Nº de Bombas");
		}
		return Bomba;
	}
	
	private JButton getReparar() {
		if (Reparar == null) {
			Reparar = new JButton("Nº de Reparaciones");
		}
		return Reparar;
	}

	private JButton getEscudo() {
		if (Escudo == null) {
			Escudo = new JButton("Nº de Escudo");
		}
		return Escudo;
	}

	private JButton getRadar() {
		if (Radar == null) {
			Radar = new JButton("Nº de Radares");
		}
		return Radar;
	}

	private JButton getTIENDA() {
		if (TIENDA == null) {
			TIENDA = new JButton("TIENDA");
			TIENDA.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					shop.setVisible(true);
				}
			});
		}
		return TIENDA;
	}

	public void update(Observable observable, Object arg1) {
		// TODO Auto-generated method stub
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
	}
	private JRadioButton getRdbtnSubmarino() {
		if (rdbtnSubmarino == null) {
			rdbtnSubmarino = new JRadioButton("SUBMARINO");
			buttonGroup.add(rdbtnSubmarino);
		}
		return rdbtnSubmarino;
	}
	private JRadioButton getRdbtnDestructor() {
		if (rdbtnDestructor == null) {
			rdbtnDestructor = new JRadioButton("DESTRUCTOR");
			buttonGroup.add(rdbtnDestructor);
		}
		return rdbtnDestructor;
	}
	private JRadioButton getRdbtnFragata() {
		if (rdbtnFragata == null) {
			rdbtnFragata = new JRadioButton("FRAGATA");
			buttonGroup.add(rdbtnFragata);
		}
		return rdbtnFragata;
	}
	private JRadioButton getRdbtnHorizontal() {
		if (rdbtnHorizontal == null) {
			rdbtnHorizontal = new JRadioButton("HORIZONTAL");
			rdbtnHorizontal.setSelected(true);
			buttonGroup_1.add(rdbtnHorizontal);
		}
		return rdbtnHorizontal;
	}
	private JRadioButton getRdbtnVertical() {
		if (rdbtnVertical == null) {
			rdbtnVertical = new JRadioButton("VERTICAL");
			buttonGroup_1.add(rdbtnVertical);
		}
		return rdbtnVertical;
	}

	public void actionPerformed(ActionEvent e) {
		// Recibimos la notificacion de que alguno de los botones fue presionado
		
		//Verificar que el causante de este evento sea un JButton y que estemos en el estado de colocar barcos
		if(e.getSource() instanceof JButton && partidaEstado==1 ){
			//Obtenemos una referencia al objeto causante del evento
			JButton temp = (JButton) e.getSource();
			//Realizamos las operaciones que queremos realizar sobre el boton clicado
		    temp.setBackground(Color.GREEN);
		    
		    		}	
	}
	public static Tablero getTableroJugador() {
		return tableroJ;
	}

	public static Tablero getTableroIA() {
		return tableroIA;
	}
}
