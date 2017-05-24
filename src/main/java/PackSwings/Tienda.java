package PackSwings;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import packMainJava.Inventario;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Tienda extends JFrame implements Observer {

	private static Tienda mTienda = new Tienda();

	private JPanel panelTienda;
	private JPanel panelCatalogo;
	private JPanel panelMisil;
	private JPanel panelEscudo;
	private JPanel panelRadar;
	private JLabel labelImagenMisil;
	private JLabel labelImagenEscudo;
	private JLabel labelImagenRadar;
	private JButton botonComprarMisil;
	private JButton botonComprarEscudo;
	private JButton botonComprarRadar;
	private JPanel panelCabecera;
	private JLabel labelDinero;
	private JLabel labelTienda;
	private JPanel panelPie;
	private JButton botonVolver;
	private JPanel panelIzq;
	private JTextArea txtrRadarDescripcinDescripcin;
	private JPanel panelDer;
	private JTextArea txtrEscudoAadeseloA;
	private JTextArea txtrMisilUnSlo;

	private int dinero = 1000;
	private int PRECIO_MISIL = 300;
	private int PRECIO_ESCUDO = 200;
	private int PRECIO_RADAR = 250;
	private int misilesDisp = 3;
	private int escudosDisp = 2;
	private int radaresDisp = 2;

	Inventario inv = Inventario.getInventario(); // Instancia única al Singleton
	private JLabel labelMisilDisp;
	private JLabel labelEscudoDisp;
	private JLabel labelRadarDisp;
	// Inventario

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tienda frame = new Tienda();
					frame.setVisible(true);
					frame.setAlwaysOnTop(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tienda() {
		initialize();
		actualizarTienda();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 440);
		panelTienda = new JPanel();
		panelTienda.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelTienda.setLayout(new BorderLayout(0, 0));
		setContentPane(panelTienda);
		panelTienda.add(getPanelCatalogo(), BorderLayout.CENTER);
		panelTienda.add(getPanelCabecera(), BorderLayout.NORTH);
		panelTienda.add(getPanelPie(), BorderLayout.SOUTH);
		panelTienda.add(getPanelIzq(), BorderLayout.WEST);
		panelTienda.add(getPanelDer(), BorderLayout.EAST);
	}

	// ------- SINGLETON ---------------------------------------
	public static Tienda getTienda() {
		return mTienda;
	}

	// GETTER AND SETTER
	public static Tienda getmTienda() {
		return mTienda;
	}

	public static void setmInventario(Tienda mTienda) {
		Tienda.mTienda = mTienda;
	}
	// ----------------------------------------------------------

	private JPanel getPanelCatalogo() {
		if (panelCatalogo == null) {
			panelCatalogo = new JPanel();
			panelCatalogo.setLayout(new BoxLayout(panelCatalogo, BoxLayout.Y_AXIS));
			panelCatalogo.add(getPanelMisil());
			panelCatalogo.add(getPanelEscudo());
			panelCatalogo.add(getPanelRadar());
		}
		return panelCatalogo;
	}

	private JPanel getPanelMisil() {
		if (panelMisil == null) {
			panelMisil = new JPanel();
			panelMisil.setBackground(new Color(102, 153, 51));
			panelMisil.setBorder(new LineBorder(new Color(0, 0, 0)));
			panelMisil.add(getLabelImagenMisil());
			panelMisil.add(getTxtrMisilUnSlo());
			panelMisil.add(getBotonComprarMisil());
			panelMisil.add(getLabel_1());
		}
		return panelMisil;
	}

	private JPanel getPanelEscudo() {
		if (panelEscudo == null) {
			panelEscudo = new JPanel();
			panelEscudo.setBackground(new Color(102, 153, 51));
			panelEscudo.setBorder(new LineBorder(new Color(0, 0, 0)));
			panelEscudo.add(getLabelImagenEscudo());
			panelEscudo.add(getTxtrEscudoAadeseloA());
			panelEscudo.add(getBotonComprarEscudo());
			panelEscudo.add(getLabel_2());
		}
		return panelEscudo;
	}

	private JPanel getPanelRadar() {
		if (panelRadar == null) {
			panelRadar = new JPanel();
			panelRadar.setBackground(new Color(102, 153, 51));
			panelRadar.setBorder(new LineBorder(new Color(0, 0, 0)));
			FlowLayout flowLayout = (FlowLayout) panelRadar.getLayout();
			panelRadar.add(getLabelImagenRadar());
			panelRadar.add(getTxtrRadarDescripcinDescripcin());
			panelRadar.add(getBotonComprarRadar());
			panelRadar.add(getLabel_3());
		}
		return panelRadar;
	}

	private JLabel getLabelImagenMisil() {
		if (labelImagenMisil == null) {
			labelImagenMisil = new JLabel("");
			labelImagenMisil.setHorizontalAlignment(SwingConstants.CENTER);
			labelImagenMisil.setIcon(new ImageIcon(this.getClass().getResource("/misil.png")));
		}
		return labelImagenMisil;
	}

	private JLabel getLabelImagenEscudo() {
		if (labelImagenEscudo == null) {
			labelImagenEscudo = new JLabel("");
			labelImagenEscudo.setHorizontalAlignment(SwingConstants.CENTER);
			labelImagenEscudo.setIcon(new ImageIcon(this.getClass().getResource("/escudo.png")));
		}
		return labelImagenEscudo;
	}

	private JLabel getLabelImagenRadar() {
		if (labelImagenRadar == null) {
			labelImagenRadar = new JLabel("");
			labelImagenRadar.setHorizontalAlignment(SwingConstants.CENTER);
			labelImagenRadar.setIcon(new ImageIcon(this.getClass().getResource("/radar.png")));

		}
		return labelImagenRadar;
	}

	private JButton getBotonComprarMisil() {
		if (botonComprarMisil == null) {
			botonComprarMisil = new JButton("Comprar (300€)");
			botonComprarMisil.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					comprarMisil();
				}
			});
		}
		return botonComprarMisil;
	}

	private JButton getBotonComprarEscudo() {
		if (botonComprarEscudo == null) {
			botonComprarEscudo = new JButton("Comprar (200€)");
			botonComprarEscudo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					comprarEscudo();
				}
			});
		}
		return botonComprarEscudo;
	}

	private JButton getBotonComprarRadar() {
		if (botonComprarRadar == null) {
			botonComprarRadar = new JButton("Comprar (250€)");
			botonComprarRadar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					comprarRadar();
				}
			});
		}
		return botonComprarRadar;
	}

	private JPanel getPanelCabecera() {
		if (panelCabecera == null) {
			panelCabecera = new JPanel();
			panelCabecera.setBorder(new EmptyBorder(15, 20, 10, 20));
			panelCabecera.setBackground(new Color(0, 51, 0));
			panelCabecera.setLayout(new GridLayout(0, 2, 0, 0));
			panelCabecera.add(getLabelTienda());
			panelCabecera.add(getLabelDinero());
		}
		return panelCabecera;
	}

	private JLabel getLabelDinero() {
		if (labelDinero == null) {
			labelDinero = new JLabel("dinero disponible $");
			labelDinero.setHorizontalAlignment(SwingConstants.RIGHT);
			labelDinero.setFont(new Font("Stencil", Font.PLAIN, 20));
			labelDinero.setForeground(new Color(204, 204, 0));
			// labelDinero.set
		}
		return labelDinero;
	}

	private JLabel getLabelTienda() {
		if (labelTienda == null) {
			labelTienda = new JLabel("TIENDA");
			labelTienda.setForeground(new Color(255, 255, 255));
			labelTienda.setHorizontalAlignment(SwingConstants.LEFT);
			labelTienda.setFont(new Font("Stencil", Font.PLAIN, 27));
		}
		return labelTienda;
	}

	private JPanel getPanelPie() {
		if (panelPie == null) {
			panelPie = new JPanel();
			panelPie.setBorder(new EmptyBorder(10, 0, 10, 0));
			panelPie.setBackground(new Color(0, 51, 0));
			GridBagLayout gbl_panelPie = new GridBagLayout();
			gbl_panelPie.columnWidths = new int[] { 491, 63, 0 };
			gbl_panelPie.rowHeights = new int[] { 23, 0 };
			gbl_panelPie.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
			gbl_panelPie.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
			panelPie.setLayout(gbl_panelPie);
			GridBagConstraints gbc_botonVolver = new GridBagConstraints();
			gbc_botonVolver.anchor = GridBagConstraints.NORTHWEST;
			gbc_botonVolver.gridx = 1;
			gbc_botonVolver.gridy = 0;
			panelPie.add(getBotonVolver(), gbc_botonVolver);
		}
		return panelPie;
	}

	private JButton getBotonVolver() {
		if (botonVolver == null) {
			botonVolver = new JButton("Volver");
			botonVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
		}
		return botonVolver;
	}

	private JPanel getPanelIzq() {
		if (panelIzq == null) {
			panelIzq = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelIzq.getLayout();
			flowLayout.setHgap(10);
			panelIzq.setBackground(new Color(0, 51, 0));
		}
		return panelIzq;
	}

	private JTextArea getTxtrRadarDescripcinDescripcin() {
		if (txtrRadarDescripcinDescripcin == null) {
			txtrRadarDescripcinDescripcin = new JTextArea();
			txtrRadarDescripcinDescripcin.setForeground(new Color(0, 0, 0));
			txtrRadarDescripcinDescripcin.setEditable(false);
			txtrRadarDescripcinDescripcin.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtrRadarDescripcinDescripcin.setBackground(new Color(102, 153, 51));
			txtrRadarDescripcinDescripcin.setText(
					"  RADAR\r\n  Permite ver una sección de 4x4          \r\n  casillas del tablero enemigo.");
		}
		return txtrRadarDescripcinDescripcin;
	}

	private JPanel getPanelDer() {
		if (panelDer == null) {
			panelDer = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelDer.getLayout();
			flowLayout.setHgap(10);
			panelDer.setBackground(new Color(0, 51, 0));
		}
		return panelDer;
	}

	private JTextArea getTxtrEscudoAadeseloA() {
		if (txtrEscudoAadeseloA == null) {
			txtrEscudoAadeseloA = new JTextArea();
			txtrEscudoAadeseloA.setForeground(new Color(0, 0, 0));
			txtrEscudoAadeseloA.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtrEscudoAadeseloA.setBackground(new Color(102, 153, 51));
			txtrEscudoAadeseloA.setEditable(false);
			txtrEscudoAadeseloA.setText(
					"  ESCUDO\r\n  Añádeselo a uno de tus barcos \r\n  para protegerlo de un disparo rival. \r\n  Sólo te protegerá del primer impacto.   ");
		}
		return txtrEscudoAadeseloA;
	}

	private JTextArea getTxtrMisilUnSlo() {
		if (txtrMisilUnSlo == null) {
			txtrMisilUnSlo = new JTextArea();
			txtrMisilUnSlo.setForeground(new Color(0, 0, 0));
			txtrMisilUnSlo.setBackground(new Color(102, 153, 51));
			txtrMisilUnSlo.setEditable(false);
			txtrMisilUnSlo.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtrMisilUnSlo.setText(
					" MISIL\r\n Un sólo impacto con uno de estos      \r\n será suficiente para hundir un \r\n barco enemigo.");
		}
		return txtrMisilUnSlo;
	}

	// COMPRAS
	public void comprarMisil() {
		if (dinero >= PRECIO_MISIL && misilesDisp > 0) {
			dinero = dinero - PRECIO_MISIL;
			misilesDisp--;
			actualizarTienda();
			inv.addMisil();
		} else {
			// ERROR: No money
			JOptionPane optionPane = new JOptionPane("Dinero insuficiente", JOptionPane.ERROR_MESSAGE);
			JDialog dialog = optionPane.createDialog("Failure");
			dialog.setAlwaysOnTop(true);
			dialog.setVisible(true);
		}
	}

	public void comprarEscudo() {
		if (dinero >= PRECIO_ESCUDO && escudosDisp > 0) {
			dinero = dinero - PRECIO_ESCUDO;
			escudosDisp--;
			actualizarTienda();
			inv.addEscudo();
		} else {
			// ERROR: No money
			JOptionPane optionPane = new JOptionPane("Dinero insuficiente", JOptionPane.ERROR_MESSAGE);
			JDialog dialog = optionPane.createDialog("Failure");
			dialog.setAlwaysOnTop(true);
			dialog.setVisible(true);
		}
	}

	public void comprarRadar() {
		if (dinero >= PRECIO_RADAR && radaresDisp > 0) {
			dinero = dinero - PRECIO_RADAR;
			radaresDisp--;
			actualizarTienda();
			inv.addRadar();
		} else {
			// ERROR: No money
			JOptionPane optionPane = new JOptionPane("Dinero insuficiente", JOptionPane.ERROR_MESSAGE);
			JDialog dialog = optionPane.createDialog("Failure");
			dialog.setAlwaysOnTop(true);
			dialog.setVisible(true);
		}
	}

	public void actualizarTienda() {
		// Textos
		labelMisilDisp.setText("(" + misilesDisp + " DISP)");
		labelEscudoDisp.setText("(" + escudosDisp + " DISP)");
		labelRadarDisp.setText("(" + radaresDisp + " DISP)");
		labelDinero.setText(dinero + " €");

		// Botones
		if (misilesDisp == 0) {
			botonComprarMisil.setEnabled(false);
		}
		if (escudosDisp == 0) {
			botonComprarEscudo.setEnabled(false);
		}
		if (radaresDisp == 0) {
			botonComprarRadar.setEnabled(false);
		}
	}

	private JLabel getLabel_1() {
		if (labelMisilDisp == null) {
			labelMisilDisp = new JLabel("(5 DISP)");
		}
		return labelMisilDisp;
	}

	private JLabel getLabel_2() {
		if (labelEscudoDisp == null) {
			labelEscudoDisp = new JLabel("(2 DISP)");
		}
		return labelEscudoDisp;
	}

	private JLabel getLabel_3() {
		if (labelRadarDisp == null) {
			labelRadarDisp = new JLabel("(1 DISP)");
		}
		return labelRadarDisp;
	}

	public void update(Observable observable, Object value) {
		// TODO Auto-generated method stub

	}
}
