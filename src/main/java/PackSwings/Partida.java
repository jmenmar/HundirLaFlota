package PackSwings;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JDesktopPane;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Partida extends JFrame {
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;

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
			panel.setPreferredSize(new Dimension(20, 20));
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(
					Alignment.TRAILING).addGroup(
					gl_panel.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE,
									Short.MAX_VALUE)
							.addComponent(getPanel_1(),
									GroupLayout.PREFERRED_SIZE, 380,
									GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(getPanel_3(),
									GroupLayout.PREFERRED_SIZE, 530,
									GroupLayout.PREFERRED_SIZE).addGap(464)));
			gl_panel.setVerticalGroup(gl_panel
					.createParallelGroup(Alignment.TRAILING)
					.addGroup(
							gl_panel.createSequentialGroup()
									.addGroup(
											gl_panel.createParallelGroup(
													Alignment.LEADING)
													.addGroup(
															gl_panel.createSequentialGroup()
																	.addGap(60)
																	.addComponent(
																			getPanel_1(),
																			GroupLayout.PREFERRED_SIZE,
																			440,
																			GroupLayout.PREFERRED_SIZE))
													.addGroup(
															gl_panel.createSequentialGroup()
																	.addGap(63)
																	.addComponent(
																			getPanel_3(),
																			GroupLayout.PREFERRED_SIZE,
																			435,
																			GroupLayout.PREFERRED_SIZE)))
									.addContainerGap(61, Short.MAX_VALUE)));
			panel.setLayout(gl_panel);
		}
		return panel;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setPreferredSize(new Dimension(349, 349));
			panel_1.setSize(new Dimension(100, 100));
			panel_1.setBackground(Color.RED);
			GroupLayout gl_panel_1 = new GroupLayout(panel_1);
			gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(
					Alignment.LEADING).addGap(0, 349, Short.MAX_VALUE));
			gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(
					Alignment.LEADING).addGap(0, 349, Short.MAX_VALUE));
			panel_1.setLayout(gl_panel_1);
		}
		return panel_1;
	}

	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBackground(Color.BLUE);
			panel_2.setLayout(new GridLayout(10, 10, 0, 0));
		}
		return panel_2;
	}

	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setPreferredSize(new Dimension(100, 100));
			panel_3.setBackground(Color.CYAN);
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
