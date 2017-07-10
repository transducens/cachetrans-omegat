package org.omegat.plugins.machinetranslators.cachetrans.preferences;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FileExplorer extends JPanel {

	private static final long serialVersionUID = -1953675112089401255L;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public FileExplorer() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);
		
		JPanel panel = new JPanel();
		add(panel);
		
		JButton button = new JButton("...");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				int result = jfc.showOpenDialog(FileExplorer.this);
				
				if (result == JFileChooser.APPROVE_OPTION)
				{
					textField.setText(jfc.getSelectedFile().getAbsolutePath());
				}
			}
		});
		add(button);

	}
	
	public String getURI()
	{
		return textField.getText();
	}
	
	public void setURI(String uri)
	{
		textField.setText(uri);
	}

}
