package PackSwings;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class Tienda extends JFrame {

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tienda frame = new Tienda();
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
	public Tienda() {
		initialize();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 440);
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
		}
		return panelRadar;
	}

	private JLabel getLabelImagenMisil() {
		if (labelImagenMisil == null) {
			labelImagenMisil = new JLabel("");
			labelImagenMisil.setHorizontalAlignment(SwingConstants.CENTER);
			labelImagenMisil.setIcon(new ImageIcon(
					"C:\\Users\\JAVIER\\git\\isoftproyecto-hundirflotajaj\\src\\main\\resources\\misil.png"));
		}
		return labelImagenMisil;
	}

	private JLabel getLabelImagenEscudo() {
		if (labelImagenEscudo == null) {
			labelImagenEscudo = new JLabel("");
			labelImagenEscudo.setHorizontalAlignment(SwingConstants.CENTER);
			labelImagenEscudo.setIcon(new ImageIcon(
					"C:\\Users\\JAVIER\\git\\isoftproyecto-hundirflotajaj\\src\\main\\resources\\escudo.png"));
		}
		return labelImagenEscudo;
	}

	private JLabel getLabelImagenRadar() {
		if (labelImagenRadar == null) {
			labelImagenRadar = new JLabel("");
			labelImagenRadar.setHorizontalAlignment(SwingConstants.CENTER);
			labelImagenRadar.setIcon(new ImageIcon(
					"C:\\Users\\JAVIER\\git\\isoftproyecto-hundirflotajaj\\src\\main\\resources\\radar.png"));
		}
		return labelImagenRadar;
	}

	private JButton getBotonComprarMisil() {
		if (botonComprarMisil == null) {
			botonComprarMisil = new JButton("Comprar");
		}
		return botonComprarMisil;
	}

	private JButton getBotonComprarEscudo() {
		if (botonComprarEscudo == null) {
			botonComprarEscudo = new JButton("Comprar");
		}
		return botonComprarEscudo;
	}

	private JButton getBotonComprarRadar() {
		if (botonComprarRadar == null) {
			botonComprarRadar = new JButton("Comprar");
		}
		return botonComprarRadar;
	}

	private JPanel getPanelCabecera() {
		if (panelCabecera == null) {
			panelCabecera = new JPanel();
			panelCabecera.setBackground(new Color(0, 51, 0));
			FlowLayout flowLayout = (FlowLayout) panelCabecera.getLayout();
			flowLayout.setVgap(15);
			flowLayout.setHgap(30);
			flowLayout.setAlignment(FlowLayout.LEFT);
			panelCabecera.add(getLabelTienda());
			panelCabecera.add(getLabelDinero());
		}
		return panelCabecera;
	}

	private JLabel getLabelDinero() {
		if (labelDinero == null) {
			labelDinero = new JLabel("dinero disponible $");
			labelDinero.setFont(new Font("Stencil", Font.PLAIN, 14));
			labelDinero.setForeground(new Color(204, 204, 0));
		}
		return labelDinero;
	}

	private JLabel getLabelTienda() {
		if (labelTienda == null) {
			labelTienda = new JLabel("TIENDA");
			labelTienda.setForeground(new Color(255, 255, 255));
			labelTienda.setHorizontalAlignment(SwingConstants.CENTER);
			labelTienda.setFont(new Font("Stencil", Font.PLAIN, 27));
		}
		return labelTienda;
	}

	private JPanel getPanelPie() {
		if (panelPie == null) {
			panelPie = new JPanel();
			panelPie.setBackground(new Color(0, 51, 0));
			FlowLayout fl_panelPie = (FlowLayout) panelPie.getLayout();
			fl_panelPie.setVgap(10);
			fl_panelPie.setHgap(20);
			fl_panelPie.setAlignment(FlowLayout.RIGHT);
			panelPie.add(getBotonVolver());
		}
		return panelPie;
	}

	private JButton getBotonVolver() {
		if (botonVolver == null) {
			botonVolver = new JButton("Volver");
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
					"  RADAR\r\n  Permite ver una sección de 2x2          \r\n  casillas del tablero enemigo.");
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
					" MISIL\r\n Un sólo impacto con uno de estos      \r\n será suficiente para hundir un \r\n barco enemigo");
		}
		return txtrMisilUnSlo;
	}
}
