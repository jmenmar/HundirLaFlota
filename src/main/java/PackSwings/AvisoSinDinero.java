package PackSwings;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;

public class AvisoSinDinero extends JDialog {
	private JLabel labelAviso;
	private JLabel label;
	private JPanel panelCentral;
	private JPanel panelSur;
	private JPanel panelNorte;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AvisoSinDinero dialog = new AvisoSinDinero();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public AvisoSinDinero() {

		initialize();
	}

	private void initialize() {
		setTitle("Error");
		setBounds(100, 100, 250, 150);
		getContentPane().add(getPanelCentral(), BorderLayout.CENTER);
		getContentPane().add(getPanelSur(), BorderLayout.SOUTH);
		getContentPane().add(getPanelNorte(), BorderLayout.NORTH);
	}

	private JLabel getLabelAviso() {
		if (labelAviso == null) {
			labelAviso = new JLabel(" Dinero insuficiente");
		}
		return labelAviso;
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("");
			label.setIcon(new ImageIcon(
					AvisoSinDinero.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif")));
		}
		return label;
	}

	private JPanel getPanelCentral() {
		if (panelCentral == null) {
			panelCentral = new JPanel();
			panelCentral.add(getLabel());
			panelCentral.add(getLabelAviso());
		}
		return panelCentral;
	}

	private JPanel getPanelSur() {
		if (panelSur == null) {
			panelSur = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelSur.getLayout();
			flowLayout.setVgap(8);
		}
		return panelSur;
	}

	private JPanel getPanelNorte() {
		if (panelNorte == null) {
			panelNorte = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelNorte.getLayout();
			flowLayout.setVgap(13);
		}
		return panelNorte;
	}
}
