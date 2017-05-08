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
import packMainJava.Bomba;
import packMainJava.Casilla;
import packMainJava.IA;
import packMainJava.Inventario;
import packMainJava.Jugador;
import packMainJava.Misil;
import packMainJava.Player;
import packMainJava.Recurso;
import packMainJava.Status;
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

public class Partida extends JFrame implements Observer, ActionListener {
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JButton[][] mCasillas = null;
	private JButton[][] mCasillas2 = null;

	int partidaEstado = 1;
	private Player jugador;
	private int numeroDeFilas = 10;
	private int numeroDeColumnas = 10;
	private JRadioButton rdbtnBomba;
	private JRadioButton rdbtnMisil;
	private JRadioButton rdbtnReparar;
	private JRadioButton rdbtnEscudo;
	private JRadioButton rdbtnRadar;
	private JButton TIENDA;
	private static Tablero tableroJ;
	private static Tablero tableroIA;
	Inventario inv = Inventario.getInventario(); // Instancia única al Singleton
	Tienda shop = Tienda.getTienda(); // Instancia única al Singleton
	private JRadioButton rdbtnSubmarino;
	private JRadioButton rdbtnDestructor;
	private JRadioButton rdbtnFragata;
	private JRadioButton rdbtnPortaaviones;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnHorizontal;
	private JRadioButton rdbtnVertical;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	boolean turno = true;
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();

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
		jugador = Player.getPlayer();
		jugador.addObserver(this);
		update(null, null);
	}

	private void initialize() {
		setBounds(100, 100, 1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(getPanel(), BorderLayout.CENTER);
		if (partidaEstado == 1) {
			rdbtnMisil.setEnabled(false);
			rdbtnBomba.setEnabled(false);
			rdbtnEscudo.setEnabled(false);
			rdbtnRadar.setEnabled(false);
			rdbtnReparar.setEnabled(false);
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

			JRadioButton Bomba = new JRadioButton("Nº de Bombas");
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
					gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup().addGroup(gl_panel
									.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
											.createSequentialGroup()
											.addGap(28).addGroup(gl_panel
													.createParallelGroup(Alignment.TRAILING, false)
													.addGroup(gl_panel.createSequentialGroup()
															.addComponent(getPanel_1(), GroupLayout.PREFERRED_SIZE,
																	GroupLayout.DEFAULT_SIZE,
																	GroupLayout.PREFERRED_SIZE)
															.addGap(63).addComponent(
																	getPanel_3(), GroupLayout.PREFERRED_SIZE,
																	GroupLayout.DEFAULT_SIZE,
																	GroupLayout.PREFERRED_SIZE))
													.addGroup(gl_panel.createSequentialGroup().addGap(18)
															.addComponent(getRdbtnPortaaviones(),
																	GroupLayout.PREFERRED_SIZE, 124,
																	GroupLayout.PREFERRED_SIZE)
															.addPreferredGap(ComponentPlacement.RELATED)
															.addGroup(gl_panel
																	.createParallelGroup(Alignment.LEADING, false)
																	.addComponent(getRdbtnHorizontal(),
																			GroupLayout.DEFAULT_SIZE,
																			GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																	.addComponent(getRdbtnSubmarino(),
																			GroupLayout.DEFAULT_SIZE, 124,
																			Short.MAX_VALUE))
															.addPreferredGap(ComponentPlacement.RELATED)
															.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																	.addGroup(gl_panel.createSequentialGroup()
																			.addComponent(getRdbtnDestructor(),
																					GroupLayout.PREFERRED_SIZE, 124,
																					GroupLayout.PREFERRED_SIZE)
																			.addPreferredGap(ComponentPlacement.RELATED)
																			.addComponent(getRdbtnFragata(),
																					GroupLayout.PREFERRED_SIZE,
																					124, GroupLayout.PREFERRED_SIZE))
																	.addComponent(getRdbtnVertical(),
																			GroupLayout.PREFERRED_SIZE, 124,
																			GroupLayout.PREFERRED_SIZE))
															.addPreferredGap(ComponentPlacement.RELATED,
																	99, Short.MAX_VALUE)
															.addComponent(getTIENDA(), GroupLayout.PREFERRED_SIZE, 138,
																	GroupLayout.PREFERRED_SIZE)))
											.addGap(62)
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
													.addComponent(getRdbtnBomba(), GroupLayout.DEFAULT_SIZE,
															GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addComponent(getRdbtnEscudo(), GroupLayout.DEFAULT_SIZE,
															GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addComponent(getRdbtnRadar(), GroupLayout.DEFAULT_SIZE, 114,
															Short.MAX_VALUE)
													.addComponent(getRdbtnReparar(), 0, 0, Short.MAX_VALUE)
													.addComponent(getRdbtnMisil(), Alignment.TRAILING,
															GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
															Short.MAX_VALUE)))
									.addGroup(gl_panel.createSequentialGroup().addGap(118)
											.addComponent(lblTableroJugador).addGap(228).addComponent(lblTableroIa,
													GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)))
									.addContainerGap(20, Short.MAX_VALUE)));
			gl_panel.setVerticalGroup(
					gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup().addGap(54).addGroup(
									gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblTableroJugador)
											.addComponent(
													lblTableroIa, GroupLayout.PREFERRED_SIZE, 25,
													GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
											.createSequentialGroup()
											.addGap(9)
											.addComponent(getRdbtnBomba(), GroupLayout.PREFERRED_SIZE, 60,
													GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(getRdbtnMisil(), GroupLayout.PREFERRED_SIZE, 60,
													GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(getRdbtnReparar(), GroupLayout.PREFERRED_SIZE, 60,
													GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(getRdbtnEscudo(), GroupLayout.PREFERRED_SIZE, 60,
													GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(getRdbtnRadar(), GroupLayout.PREFERRED_SIZE,
													60, GroupLayout.PREFERRED_SIZE)
											.addContainerGap(83,
													Short.MAX_VALUE))
											.addGroup(gl_panel.createSequentialGroup().addGroup(gl_panel
													.createParallelGroup(Alignment.LEADING)
													.addComponent(getPanel_1(), GroupLayout.PREFERRED_SIZE,
															GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
													.addComponent(getPanel_3(), GroupLayout.PREFERRED_SIZE,
															GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
													.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
															.addGroup(gl_panel.createSequentialGroup()
																	.addPreferredGap(ComponentPlacement.RELATED, 41,
																			Short.MAX_VALUE)
																	.addComponent(getTIENDA(),
																			GroupLayout.PREFERRED_SIZE, 58,
																			GroupLayout.PREFERRED_SIZE)
																	.addGap(30))
															.addGroup(gl_panel.createSequentialGroup().addGap(32)
																	.addGroup(gl_panel
																			.createParallelGroup(Alignment.LEADING)
																			.addGroup(gl_panel
																					.createParallelGroup(
																							Alignment.BASELINE)
																					.addComponent(getRdbtnSubmarino(),
																							GroupLayout.PREFERRED_SIZE,
																							48,
																							GroupLayout.PREFERRED_SIZE)
																					.addComponent(
																							getRdbtnPortaaviones(),
																							GroupLayout.PREFERRED_SIZE,
																							48,
																							GroupLayout.PREFERRED_SIZE))
																			.addComponent(getRdbtnDestructor(),
																					GroupLayout.PREFERRED_SIZE, 48,
																					GroupLayout.PREFERRED_SIZE)
																			.addComponent(getRdbtnFragata(),
																					GroupLayout.PREFERRED_SIZE, 48,
																					GroupLayout.PREFERRED_SIZE))
																	.addPreferredGap(ComponentPlacement.RELATED)
																	.addGroup(gl_panel
																			.createParallelGroup(Alignment.BASELINE)
																			.addComponent(getRdbtnHorizontal())
																			.addComponent(getRdbtnVertical()))
																	.addContainerGap()))))));
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
				temp.setBounds(48 + (m * anchoDeCasilla), 100 + (n * altoDeCasilla), anchoDeCasilla, altoDeCasilla);
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
				temp.addActionListener(this);
			}
		}
		IA.getIA().colocarBarcosPropios();
		testBarcosIA();

	}

	public void testBarcosIA() {
		Casilla testudo;
		System.out.println("Test barcos");
		System.out.print("[-]");
		for (int foo = 0; foo < 10; foo++) {
			System.out.print("[" + foo + "]");
		}
		System.out.println("");
		for (int m = 0; m < numeroDeColumnas; m++) {
			System.out.print("[" + m + "]");
			for (int n = 0; n < numeroDeFilas; n++) {
				testudo = tableroIA.getCasilla(n, m);
				System.out.print("[");
				if (testudo.getOcupadaPor() != null) {
					System.out.print("x");
				} else {
					System.out.print(" ");
				}
				System.out.print("]");
			}
			System.out.print("\n");
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
				int x = n;
				int y = m;
				// fijar cada casilla a una posicion y tama�o en funcion de su
				// fila y columna
				temp.setBounds(446 + (m * anchoDeCasilla), 100 + (n * altoDeCasilla), anchoDeCasilla, altoDeCasilla);
			}
		}

	}

	private JRadioButton getRdbtnMisil() {
		if (rdbtnMisil == null) {
			rdbtnMisil = new JRadioButton("x" + inv.getNumMisiles());
			buttonGroup_2.add(rdbtnMisil);
			rdbtnMisil.setHorizontalAlignment(SwingConstants.LEFT);
			rdbtnMisil.setIcon(new ImageIcon(this.getClass().getResource("/misil.png")));
		}
		return rdbtnMisil;
	}

	private JRadioButton getRdbtnBomba() {
		if (rdbtnBomba == null) {
			rdbtnBomba = new JRadioButton("--");
			buttonGroup_2.add(rdbtnBomba);
			rdbtnBomba.setHorizontalAlignment(SwingConstants.LEFT);
			rdbtnBomba.setIcon(new ImageIcon(this.getClass().getResource("/bomba.png")));
		}
		return rdbtnBomba;
	}

	private JRadioButton getRdbtnReparar() {
		if (rdbtnReparar == null) {
			rdbtnReparar = new JRadioButton("x");
			buttonGroup_2.add(rdbtnReparar);
			rdbtnReparar.setHorizontalAlignment(SwingConstants.LEFT);
			rdbtnReparar.setIcon(new ImageIcon(this.getClass().getResource("/reparar.png")));
		}
		return rdbtnReparar;
	}

	private JRadioButton getRdbtnEscudo() {
		if (rdbtnEscudo == null) {
			rdbtnEscudo = new JRadioButton("x");
			buttonGroup_2.add(rdbtnEscudo);
			rdbtnEscudo.setHorizontalAlignment(SwingConstants.LEFT);
			rdbtnEscudo.setIcon(new ImageIcon(this.getClass().getResource("/escudo.png")));
		}
		return rdbtnEscudo;
	}

	private JRadioButton getRdbtnRadar() {
		if (rdbtnRadar == null) {
			rdbtnRadar = new JRadioButton("x");
			buttonGroup_2.add(rdbtnRadar);
			rdbtnRadar.setHorizontalAlignment(SwingConstants.LEFT);
			rdbtnRadar.setIcon(new ImageIcon(this.getClass().getResource("/radar.png")));
		}
		return rdbtnRadar;
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
		getRdbtnMisil().setText("x" + inv.getNumMisiles());
		getRdbtnReparar().setText("x" + inv.getNumReparaciones());
		getRdbtnEscudo().setText("x" + inv.getNumEscudos());
		getRdbtnRadar().setText("x" + inv.getNumRadares());

		// Comprobar qué ha cambiado y actualizar y pintar recorriendo
		if (partidaEstado == 1) {
			for (int n = 0; n < 10; n++) {
				for (int m = 0; m < 10; m++) {
					if (jugador.getEstadoCasillaBarcoJugador(n, m) == CasillaEstado.OCUPADA)
						mCasillas[n][m].setBackground(Color.GREEN);
				}
			}
		}
		// Pinta los escudos
		if (partidaEstado == 2) {
			for (int n = 0; n < 10; n++) {
				for (int m = 0; m < 10; m++) {
				 	if(jugador.getCasillaJugador(n, m).getOcupadaPor()!=null)
				    	if (jugador.getBarcoEnCasilla(n, m).isProtegido())
				     		mCasillas[n][m].setBackground(Color.CYAN);
				}
				// Hay que poner el de radar con el mCasillas2[][],el
				// reparacion, y lo de bomba y misil
			}
		  //Radar
			for (int n = 0; n < 10; n++) {
				for (int m = 0; m < 10; m++) {
					if (jugador.getCasillaIA(n,m).isDetectada())
						mCasillas2[n][m].setBackground(Color.GREEN);
				}
			}
		  //Reparar
			for (int n = 0; n < 10; n++) {
				for (int m = 0; m < 10; m++) {
					if (jugador.getEstadoCasillaBarcoJugador(n, m) == CasillaEstado.OCUPADA && 
							jugador.getCasillaJugador(n, m).getOcupadaPor().getEstado()== Status.INTACTO &&
							  !jugador.getCasillaJugador(n, m).getOcupadaPor().isProtegido())
						mCasillas[n][m].setBackground(Color.GREEN);
				  }
				}
			
			
		}

		// Comprobaría los botones del los barcos
		rdbtnPortaaviones.setEnabled(jugador.comprobarNumBarcos(TipoDeBarco.PORTAAVIONES));
		rdbtnSubmarino.setEnabled(jugador.comprobarNumBarcos(TipoDeBarco.SUBMARINO));
		rdbtnDestructor.setEnabled(jugador.comprobarNumBarcos(TipoDeBarco.DESTRUCTOR));
		rdbtnFragata.setEnabled(jugador.comprobarNumBarcos(TipoDeBarco.FRAGATA));

		if (rdbtnPortaaviones.isEnabled() == false && rdbtnFragata.isEnabled() == false
				&& rdbtnSubmarino.isEnabled() == false && rdbtnDestructor.isEnabled() == false) {
			partidaEstado = 2;
			rdbtnMisil.setEnabled(true);
			rdbtnBomba.setEnabled(true);
			rdbtnEscudo.setEnabled(true);
			rdbtnRadar.setEnabled(true);
			rdbtnReparar.setEnabled(true);
			TIENDA.setEnabled(true);
			rdbtnHorizontal.setEnabled(false);
			rdbtnVertical.setEnabled(false);
		}
		if (partidaEstado == 3) {
			System.out.println("Partida finaliza, cerrando en unos segundos");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.exit(0);
		}

	}

	private JRadioButton getRdbtnPortaaviones() {
		if (rdbtnPortaaviones == null) {
			rdbtnPortaaviones = new JRadioButton("PORTAAVIONES");
			buttonGroup.add(rdbtnPortaaviones);
		}
		return rdbtnPortaaviones;
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
			rdbtnFragata.setSelected(true);
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

	@SuppressWarnings("null")
	public void actionPerformed(ActionEvent e) {
		// Recibimos la notificacion de que alguno de los botones fue presionado

		// Verificar que el causante de este evento sea un JButton y que estemos
		// en el estado de colocar barcos
		if (e.getSource() instanceof JButton && partidaEstado == 1) {
			// Obtenemos una referencia al objeto causante del evento
			JButton temp = (JButton) e.getSource();
			// Realizamos las operaciones que queremos realizar sobre el boton
			// clicado
			// La idea es que crea un barco del tipo del cual el radiobutton
			// este marcado para poder pasarselo a los metodos
			// Horizontal sera 0 y vertical sera 1
			boolean hor = true;
			if (rdbtnHorizontal.isSelected() == true) {
				hor = true;
			} else if (rdbtnVertical.isSelected() == true) {
				hor = false;
			}
			// Calculamos la posicion del boton en X e Y en su tablero
			// Aviso, estan invertidas, la X es la Y y la Y es la X
			int posX = (temp.getX() - 48) / (335 / 10);
			int posY = (temp.getY() - 100) / (335 / 10);
			TipoDeBarco tipo = TipoDeBarco.FRAGATA;
			if (rdbtnSubmarino.isSelected() == true && rdbtnSubmarino.isEnabled() == true) {
				tipo = TipoDeBarco.SUBMARINO;
			} else if (rdbtnDestructor.isSelected() == true && rdbtnDestructor.isEnabled() == true) {
				tipo = TipoDeBarco.DESTRUCTOR;
			} else if (rdbtnPortaaviones.isSelected() == true && rdbtnPortaaviones.isEnabled() == true) {
				tipo = TipoDeBarco.PORTAAVIONES;
			} else if (rdbtnFragata.isSelected() == true && rdbtnFragata.isEnabled() == true) { // Else
				tipo = TipoDeBarco.FRAGATA;
			}

			jugador.addBarco(tipo, posY, posX, hor);

		}
		if (e.getSource() instanceof JButton && partidaEstado == 2) {
			// Obtenemos una referencia al objeto causante del evento
			/*
			 * while(partidaEstado == 2) { while(turno){ JButton temp =
			 * (JButton) e.getSource(); Recurso recurso = null; boolean
			 * cierraTurno = false; if(rdbtnBomba.isSelected() &&
			 * rdbtnBomba.isEnabled()){
			 * 
			 * recurso = Bomba.getBomba(); cierraTurno = true;
			 * 
			 * }else if(rdbtnMisil.isSelected() && rdbtnMisil.isEnabled()){
			 * 
			 * recurso = Misil.getMisil(); cierraTurno = true;
			 * 
			 * }else if(rdbtnRadar.isSelected() && rdbtnRadar.isEnabled()){
			 * 
			 * recurso = Radar.getRadar(); cierraTurno = false;
			 * 
			 * }else if(rdbtnEscudo.isSelected() && rdbtnEscudo.isEnabled()){
			 * 
			 * recurso = Escudo.getShield(); cierraTurno = false;
			 * 
			 * }else if(rdbtnReparar.isSelected() && rdbtnReparar.isEnabled()){
			 * 
			 * recurso = Reparacion.getReparacion(); cierraTurno = false;
			 * 
			 * }
			 * 
			 * int casX = (temp.getX() - 48)/(335/10); int casY = (temp.getY() -
			 * 100)/(335/10);
			 * 
			 * recurso.act(casY, casX, tableroIA); if(cierraTurno) { turno =
			 * false; } } }
			 */
			// Realizamos las operaciones que queremos realizar sobre el boton
			// clicado
			// Calculamos la posicion del boton en X e Y en su tablero
			// Aviso, estan invertidas, la X es la Y y la Y es la X
			// mCasillas[posYJ][posXJ].setBackground(Color.GREEN);
			// mCasillas2[posYIA][posXIA].setBackground(Color.GREEN);

			// Aqui debes
			/*
			 * if(temp.getName().equals("Bomba") ||
			 * temp.getName().equals("Misil") ){
			 * System.out.println("comprobar"); turno=false; }
			 * 
			 * if(turno==false){ //Hace lo de la IA de disparar aleatoriamente y
			 * asi }
			 */
			JButton temp = (JButton) e.getSource();

			// Realizamos las operaciones que queremos realizar sobre el boton
			// clicado
			// Calculamos la posicion del boton en X e Y en su tablero
			// Aviso, estan invertidas, la X es la Y y la Y es la X
			// mCasillas[posYJ][posXJ].setBackground(Color.GREEN);
			// mCasillas2[posYIA][posXIA].setBackground(Color.GREEN);

			// Aqui debes
			/*
			 * if(temp.getName().equals("Bomba") ||
			 * temp.getName().equals("Misil") ){
			 * System.out.println("comprobar"); turno=false; }
			 * 
			 * if(turno==false){ //Hace lo de la IA de disparar aleatoriamente y
			 * asi }
			 */

			if (rdbtnEscudo.isSelected() == true) {
				// TableroJ
				int posXJ = (temp.getX() - 48) / (335 / 10);
				int posYJ = (temp.getY() - 100) / (335 / 10);
				jugador.setEscudoEnBarco(posYJ, posXJ);
			}
			if (rdbtnReparar.isSelected() == true) {
				// TableroJ
				int posXJ = (temp.getX() - 48) / (335 / 10);
				int posYJ = (temp.getY() - 100) / (335 / 10);
				jugador.repararBarco(posYJ, posXJ);

			}
			if (rdbtnRadar.isSelected() == true) {
				// TableroIA
				int posXIA = (temp.getX() - 446) / (335 / 10);
				int posYIA = (temp.getY() - 100) / (335 / 10);
				jugador.usarRadar(posYIA,posXIA);
			}
		}
	}

	public static Tablero getTableroJugador() {
		return tableroJ;
	}

	public static Tablero getTableroIA() {
		return tableroIA;
	}

}
