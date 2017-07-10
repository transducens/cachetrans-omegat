package org.omegat.plugins.machinetranslators.cachetrans.preferences;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.omegat.util.Preferences;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class CachetransPreferencesDialog extends JDialog {

	private static final long serialVersionUID = -3684055356687934832L;
	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private JTabbedPane tabbedPane;
	private JPanel cachedPanel;
	private JLabel lblIniFile;
	private JPanel panel_for_fileexplorer_cached;
	private FileExplorer fileexplorer_cached;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CachetransPreferencesDialog dialog = new CachetransPreferencesDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CachetransPreferencesDialog() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Forecat preferences");
		setBounds(100, 100, 624, 429);
		getContentPane().setLayout(
				new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 1.0, 1.0, 0.0,
				Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.gridheight = 3;
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 0);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		contentPanel.add(tabbedPane, gbc_tabbedPane);

		cachedPanel = new JPanel();
		tabbedPane.addTab("Cachetrans configuration", null, cachedPanel, null);
		cachedPanel
				.setLayout(new FormLayout(new ColumnSpec[] {
						FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
					FormSpecs.RELATED_GAP_ROWSPEC,
					FormSpecs.DEFAULT_ROWSPEC,
					FormSpecs.RELATED_GAP_ROWSPEC,
					FormSpecs.DEFAULT_ROWSPEC,
					FormSpecs.RELATED_GAP_ROWSPEC,
					FormSpecs.DEFAULT_ROWSPEC,}));
		
				lblIniFile = new JLabel("ini file");
				cachedPanel.add(lblIniFile, "2, 2, right, default");
				
						panel_for_fileexplorer_cached = new JPanel();
						cachedPanel.add(panel_for_fileexplorer_cached, "4, 2, fill, center");
						
								panel_for_fileexplorer_cached.setLayout(new BoxLayout(
										panel_for_fileexplorer_cached, BoxLayout.X_AXIS));
								panel_for_fileexplorer_cached
										.add(fileexplorer_cached = new FileExplorer());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						accept();
						CachetransPreferencesDialog.this.dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						CachetransPreferencesDialog.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		init();
	}

	private void init() {
		fileexplorer_cached.setURI(Preferences
				.getPreference(CachetransPreferences.FORECAT_CACHED_FILE));
	}


	protected String getOkButtonText() {
		return okButton.getText();
	}

	protected void setOkButtonText(String text) {
		okButton.setText(text);
	}

	protected String getCancelButtonText() {
		return cancelButton.getText();
	}

	protected void setCancelButtonText(String text_1) {
		cancelButton.setText(text_1);
	}

	protected String getThisTitle() {
		return getTitle();
	}

	protected void setThisTitle(String title) {
		setTitle(title);
	}

	protected void accept() {
		System.out.println(fileexplorer_cached.getURI());
		Preferences.setPreference(CachetransPreferences.FORECAT_CACHED_FILE,
				fileexplorer_cached.getURI());

		CachetransPreferences.init();
	}
}
