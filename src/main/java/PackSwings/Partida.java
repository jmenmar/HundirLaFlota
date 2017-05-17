package PackSwings;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;

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
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
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
	private String ganador;

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
		jugador.setOponente(IA.getIA());
		IA.getIA().setOponente(jugador);
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
			panel.setBackground(new Color(0, 51, 102));
			panel.setPreferredSize(new Dimension(20, 20));

			JLabel lblTableroJugador = new JLabel("TABLERO JUGADOR");
			lblTableroJugador.setForeground(new Color(204, 255, 255));
			lblTableroJugador.setFont(new Font("Tahoma", Font.BOLD, 20));

			JLabel lblTableroIa = new JLabel("TABLERO IA");
			lblTableroIa.setForeground(new Color(204, 255, 255));
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
		// Tablero tableroJ = new Tablero(true);
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
		// tableroIA = new Tablero();
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
		Barco testudo;
		System.out.println("Test barcos");
		System.out.print("[-]");
		for (int foo = 0; foo < 10; foo++) {
			System.out.print("[" + foo + "]");
		}
		System.out.println("");
		for (int n = 0; n < numeroDeColumnas; n++) {
			System.out.print("[" + n + "]");
			for (int m = 0; m < numeroDeFilas; m++) {
				testudo = IA.getIA().getBarcoEnCasilla(n, m);
				System.out.print("[");
				if (testudo != null) {
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
			rdbtnMisil.setFont(new Font("Tahoma", Font.BOLD, 11));
			rdbtnMisil.setBackground(new Color(102, 153, 255));
			rdbtnMisil.setForeground(new Color(0, 0, 0));
			buttonGroup_2.add(rdbtnMisil);
			rdbtnMisil.setHorizontalAlignment(SwingConstants.LEFT);
			rdbtnMisil.setIcon(new ImageIcon(this.getClass().getResource("/misil.png")));
		}
		return rdbtnMisil;
	}

	private JRadioButton getRdbtnBomba() {
		if (rdbtnBomba == null) {
			rdbtnBomba = new JRadioButton("--");
			rdbtnBomba.setFont(new Font("Tahoma", Font.BOLD, 11));
			rdbtnBomba.setBackground(new Color(102, 153, 255));
			rdbtnBomba.setForeground(new Color(0, 0, 0));
			buttonGroup_2.add(rdbtnBomba);
			rdbtnBomba.setHorizontalAlignment(SwingConstants.LEFT);
			rdbtnBomba.setIcon(new ImageIcon(this.getClass().getResource("/bomba.png")));
		}
		return rdbtnBomba;
	}

	private JRadioButton getRdbtnReparar() {
		if (rdbtnReparar == null) {
			rdbtnReparar = new JRadioButton("x");
			rdbtnReparar.setFont(new Font("Tahoma", Font.BOLD, 11));
			rdbtnReparar.setBackground(new Color(102, 153, 255));
			rdbtnReparar.setForeground(new Color(0, 0, 0));
			buttonGroup_2.add(rdbtnReparar);
			rdbtnReparar.setHorizontalAlignment(SwingConstants.LEFT);
			rdbtnReparar.setIcon(new ImageIcon(this.getClass().getResource("/reparar.png")));
		}
		return rdbtnReparar;
	}

	private JRadioButton getRdbtnEscudo() {
		if (rdbtnEscudo == null) {
			rdbtnEscudo = new JRadioButton("x");
			rdbtnEscudo.setFont(new Font("Tahoma", Font.BOLD, 11));
			rdbtnEscudo.setBackground(new Color(102, 153, 255));
			rdbtnEscudo.setForeground(new Color(0, 0, 0));
			buttonGroup_2.add(rdbtnEscudo);
			rdbtnEscudo.setHorizontalAlignment(SwingConstants.LEFT);
			rdbtnEscudo.setIcon(new ImageIcon(this.getClass().getResource("/escudo.png")));
		}
		return rdbtnEscudo;
	}

	private JRadioButton getRdbtnRadar() {
		if (rdbtnRadar == null) {
			rdbtnRadar = new JRadioButton("x");
			rdbtnRadar.setFont(new Font("Tahoma", Font.BOLD, 11));
			rdbtnRadar.setBackground(new Color(102, 153, 255));
			rdbtnRadar.setForeground(new Color(0, 0, 0));
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
					if (jugador.getCasillaJugador(n, m).getOcupadaPor() != null)
						if (jugador.getBarcoEnCasilla(n, m).isProtegido())
							mCasillas[n][m].setBackground(Color.CYAN);
				}
				// Hay que poner el de radar con el mCasillas2[][],el
				// reparacion, y lo de bomba y misil
			}
			// Radar
			for (int n = 0; n < 10; n++) {
				for (int m = 0; m < 10; m++) {
					if (IA.getIA().getCasillaJugador(n, m).isDetectada())
						mCasillas2[n][m].setBackground(Color.GREEN);
				}
			}
			// Reparar
			for (int n = 0; n < 10; n++) {
				for (int m = 0; m < 10; m++) {
					if (jugador.getEstadoCasillaBarcoJugador(n, m) == CasillaEstado.OCUPADA
							&& jugador.getCasillaJugador(n, m).getOcupadaPor().getEstado() == Status.INTACTO
							&& !jugador.getCasillaJugador(n, m).getOcupadaPor().isProtegido())
						mCasillas[n][m].setBackground(Color.GREEN);
				}
			}
			// Bomba
			for (int n = 0; n < 10; n++) {
				for (int m = 0; m < 10; m++) {
					Casilla casTemp = IA.getIA().getCasillaJugador(n, m);
					boolean finder = false;
					Barco pBarco = casTemp.getOcupadaPor();
					if (pBarco != null) {
						/*
						 * for (int i = 0; i < pBarco.getModelo().getLongitud();
						 * i++) { if (pBarco.getImpactos()[i] == casTemp) {
						 * finder = true; } }
						 */
						Set<Casilla> sethOsiris = pBarco.getMapa().keySet();
						for (Casilla casanova : sethOsiris) {
							if (casanova == casTemp)
								finder = true;
						}
					}
					/*
					 * IA.getIA().getEstadoCasillaBarcoJugador(n, m) ==
					 * CasillaEstado.OCUPADA && finder &&
					 * !IA.getIA().getCasillaJugador(n,
					 * m).getOcupadaPor().isProtegido() &&
					 * IA.getIA().getCasillaJugador(n, m).isRevelado()
					 */
					if (jugador.getCasillaIA(n, m).getEstado() == CasillaEstado.TOCADO) {
						if (IA.getIA().getBarcoEnCasilla(n, m).getEstado() == Status.HUNDIDO) {
							mCasillas2[n][m].setBackground(Color.RED);
						} else {
							mCasillas2[n][m].setBackground(Color.ORANGE);
						}
						// Si el disparo va al agua ---> Pintar para reflejar el
						// disparo en el agua
					} else if (jugador.getCasillaIA(n, m).getEstado() == CasillaEstado.AGUA
							&& IA.getIA().getCasillaJugador(n, m).isRevelado()) {
						mCasillas2[n][m].setBackground(Color.BLUE);
					}
				}
			}
			// Misil
			for (int n = 0; n < 10; n++) {
				for (int m = 0; m < 10; m++) {
					if (IA.getIA().getEstadoCasillaBarcoJugador(n, m) == CasillaEstado.OCUPADA
							&& IA.getIA().getCasillaJugador(n, m).getOcupadaPor().getEstado() == Status.HUNDIDO
							&& !IA.getIA().getCasillaJugador(n, m).getOcupadaPor().isProtegido()
							&& IA.getIA().getCasillaJugador(n, m).isRevelado()) {
						mCasillas2[n][m].setBackground(Color.RED);
					} else if (IA.getIA().getEstadoCasillaBarcoJugador(n, m) != CasillaEstado.OCUPADA
							&& IA.getIA().getCasillaJugador(n, m).isRevelado()) {
						mCasillas2[n][m].setBackground(Color.BLUE);
					}
				}
			}
			// Recibir disparo
			for (int n = 0; n < 10; n++) {
				for (int m = 0; m < 10; m++) {
					if (jugador.getEstadoCasillaBarcoJugador(n, m) == CasillaEstado.OCUPADA
							&& jugador.getBarcoEnCasilla(n, m).getEstado() == Status.HUNDIDO) {
						mCasillas[n][m].setBackground(Color.RED);
					} else if (jugador.getEstadoCasillaBarcoJugador(n, m) == CasillaEstado.OCUPADA
							&& jugador.getCasillaJugador(n, m).isRevelado()) {
						mCasillas[n][m].setBackground(Color.ORANGE);
					} else if (jugador.getEstadoCasillaBarcoJugador(n, m) == CasillaEstado.AGUA
							&& jugador.getCasillaJugador(n, m).isRevelado()) {
						mCasillas[n][m].setBackground(Color.WHITE);
					}
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
		if (partidaEstado == 2) {
			if (jugador.comprobarFinPartida()) {
				partidaEstado = 3;
				ganador = "Has sido derrotado. Quizás la próxima vez.";
			} else if (IA.getIA().comprobarFinPartida()) {
				partidaEstado = 3;
				ganador = "Enhorabuena, eres el vencedor!";
			}

		}
		if (partidaEstado == 3) {
			JOptionPane cartel = new JOptionPane(ganador, JOptionPane.INFORMATION_MESSAGE);
			JDialog dialog = cartel.createDialog("Fin de la batalla");
			dialog.setAlwaysOnTop(true);
			dialog.setVisible(true);
			System.exit(0);
		}

	}

	private JRadioButton getRdbtnPortaaviones() {
		if (rdbtnPortaaviones == null) {
			rdbtnPortaaviones = new JRadioButton("PORTAAVIONES");
			rdbtnPortaaviones.setBackground(new Color(0, 51, 102));
			rdbtnPortaaviones.setForeground(new Color(255, 255, 255));
			buttonGroup.add(rdbtnPortaaviones);
		}
		return rdbtnPortaaviones;
	}

	private JRadioButton getRdbtnSubmarino() {
		if (rdbtnSubmarino == null) {
			rdbtnSubmarino = new JRadioButton("SUBMARINO");
			rdbtnSubmarino.setBackground(new Color(0, 51, 102));
			rdbtnSubmarino.setForeground(new Color(255, 255, 255));
			buttonGroup.add(rdbtnSubmarino);
		}
		return rdbtnSubmarino;
	}

	private JRadioButton getRdbtnDestructor() {
		if (rdbtnDestructor == null) {
			rdbtnDestructor = new JRadioButton("DESTRUCTOR");
			rdbtnDestructor.setBackground(new Color(0, 51, 102));
			rdbtnDestructor.setForeground(new Color(255, 255, 255));
			buttonGroup.add(rdbtnDestructor);
		}
		return rdbtnDestructor;
	}

	private JRadioButton getRdbtnFragata() {
		if (rdbtnFragata == null) {
			rdbtnFragata = new JRadioButton("FRAGATA");
			rdbtnFragata.setBackground(new Color(0, 51, 102));
			rdbtnFragata.setForeground(new Color(255, 255, 255));
			rdbtnFragata.setSelected(true);
			buttonGroup.add(rdbtnFragata);
		}
		return rdbtnFragata;
	}

	private JRadioButton getRdbtnHorizontal() {
		if (rdbtnHorizontal == null) {
			rdbtnHorizontal = new JRadioButton("HORIZONTAL");
			rdbtnHorizontal.setBackground(new Color(0, 51, 102));
			rdbtnHorizontal.setForeground(new Color(255, 255, 255));
			rdbtnHorizontal.setSelected(true);
			buttonGroup_1.add(rdbtnHorizontal);
		}
		return rdbtnHorizontal;
	}

	private JRadioButton getRdbtnVertical() {
		if (rdbtnVertical == null) {
			rdbtnVertical = new JRadioButton("VERTICAL");
			rdbtnVertical.setBackground(new Color(0, 51, 102));
			rdbtnVertical.setForeground(new Color(255, 255, 255));
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
				jugador.usarRadar(posYIA, posXIA, IA.getIA());
				inv.restarRadar();
			}
			if (rdbtnBomba.isSelected() == true) {
				// TableroIA
				int posXIA = (temp.getX() - 446) / (335 / 10);
				int posYIA = (temp.getY() - 100) / (335 / 10);
				jugador.usarBomba(posYIA, posXIA);
			}
			if (rdbtnMisil.isSelected() == true) {
				// TableroIA
				int posXIA = (temp.getX() - 446) / (335 / 10);
				int posYIA = (temp.getY() - 100) / (335 / 10);
				jugador.usarMisil(posYIA, posXIA);
				inv.restarMisil();
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
