package org.carnegiesciencecenter.buhl;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

public class EditUnitMotorGUI extends JDialog implements ActionListener, FocusListener
{

	private final JPanel contentPanel = new JPanel();
	private EditBankGUI parentWindow;
	private JComboBox comboBoxUnitName;
	private JButton okButton, cancelButton;
	private JTextField 	textFieldMinWidth, textFieldMaxWidth, 
						textFieldMinHeight, textFieldMaxHeight, 
						textFieldMinPos, textFieldMaxPos;
	private UnitMotor toBeEdited;
	private String toBeEditedName;	// The original name of the unit to be edited

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditUnitMotorGUI dialog = new EditUnitMotorGUI(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @wbp.parser.constructor
	 */
	public EditUnitMotorGUI(EditBankGUI parent) 
	{
		initialize(parent);
		toBeEdited = null;
	}
	
	public EditUnitMotorGUI(EditBankGUI parent, Unit toEdit)
	{
		initialize(parent);;
		
		// Make sure we were given the right kind of unit
		if (toEdit.getClass() == UnitMotor.class)
			toBeEdited = (UnitMotor) toEdit;
		else
		{
			JOptionPane.showMessageDialog(this, "Unit to be edited is of wrong type", "Error", JOptionPane.ERROR_MESSAGE);
			closeWindow();
			return;
		}
		
		// Fill in parameter values
		if (toEdit != null && toEdit.getName().length() > 0)
		{
			// Move selection to correct name
			toBeEditedName = toBeEdited.getName();
			char uName = toBeEditedName.charAt(0);
			if (!(uName < 'A' || uName > 'X'))
				comboBoxUnitName.setSelectedIndex(uName - 'A');
			
			textFieldMinWidth.setText(String.valueOf(toBeEdited.getMinWidth()));
			textFieldMaxWidth.setText(String.valueOf(toBeEdited.getMaxWidth()));
			textFieldMinHeight.setText(String.valueOf(toBeEdited.getMinHeight()));
			textFieldMaxHeight.setText(String.valueOf(toBeEdited.getMaxHeight()));
			textFieldMinPos.setText(String.valueOf(toBeEdited.getMinPos()));
			textFieldMaxPos.setText(String.valueOf(toBeEdited.getMaxPos()));
		}
	}
	
	private void initialize(EditBankGUI parent)
	{
		parentWindow = parent;
		
		setTitle("Motor Units");
		setBounds(100, 100, 246, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("Unit Name");
			label.setToolTipText("The name of the unit");
			label.setBounds(10, 14, 82, 15);
			contentPanel.add(label);
		}
		{
			comboBoxUnitName = new JComboBox();
			comboBoxUnitName.setToolTipText("List of possible unit names (only A-X are possible in SPICE)");
			comboBoxUnitName.setModel(new DefaultComboBoxModel(new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X"}));
			comboBoxUnitName.setSelectedIndex(0);
			comboBoxUnitName.setBounds(99, 11, 46, 20);
			contentPanel.add(comboBoxUnitName);
		}
		{
			JLabel lblMinimumWidth = new JLabel("Minimum Width");
			lblMinimumWidth.setBounds(10, 59, 135, 14);
			contentPanel.add(lblMinimumWidth);
		}
		{
			JLabel lblMaximumHeight = new JLabel("Maximum Width");
			lblMaximumHeight.setBounds(10, 84, 135, 14);
			contentPanel.add(lblMaximumHeight);
		}
		{
			JLabel lblMinumumHeight = new JLabel("Minumum Height");
			lblMinumumHeight.setBounds(10, 109, 135, 14);
			contentPanel.add(lblMinumumHeight);
		}
		{
			JLabel lblMaximumHeight_1 = new JLabel("Maximum Height");
			lblMaximumHeight_1.setBounds(10, 134, 135, 14);
			contentPanel.add(lblMaximumHeight_1);
		}
		{
			textFieldMinWidth = new JTextField();
			textFieldMinWidth.setText("0.0");
			textFieldMinWidth.setColumns(10);
			textFieldMinWidth.setBounds(151, 56, 63, 20);
			textFieldMinWidth.addFocusListener(this);
			contentPanel.add(textFieldMinWidth);
		}
		{
			textFieldMaxWidth = new JTextField();
			textFieldMaxWidth.setText("0.0");
			textFieldMaxWidth.setColumns(10);
			textFieldMaxWidth.setBounds(151, 81, 63, 20);
			textFieldMaxWidth.addFocusListener(this);
			contentPanel.add(textFieldMaxWidth);
		}
		{
			textFieldMinHeight = new JTextField();
			textFieldMinHeight.setText("0.0");
			textFieldMinHeight.setColumns(10);
			textFieldMinHeight.setBounds(151, 106, 63, 20);
			textFieldMinHeight.addFocusListener(this);
			contentPanel.add(textFieldMinHeight);
		}
		{
			textFieldMaxHeight = new JTextField();
			textFieldMaxHeight.setText("0.0");
			textFieldMaxHeight.setColumns(10);
			textFieldMaxHeight.setBounds(151, 131, 63, 20);
			textFieldMaxHeight.addFocusListener(this);
			contentPanel.add(textFieldMaxHeight);
		}
		{
			JLabel label = new JLabel("Minimum Position");
			label.setBounds(10, 162, 135, 14);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Maximum Position");
			label.setBounds(10, 187, 135, 14);
			contentPanel.add(label);
		}
		{
			textFieldMinPos = new JTextField();
			textFieldMinPos.setText("0.0");
			textFieldMinPos.setColumns(10);
			textFieldMinPos.setBounds(151, 159, 63, 20);
			textFieldMinPos.addFocusListener(this);
			contentPanel.add(textFieldMinPos);
		}
		{
			textFieldMaxPos = new JTextField();
			textFieldMaxPos.setText("0.0");
			textFieldMaxPos.setColumns(10);
			textFieldMaxPos.setBounds(151, 184, 63, 20);
			textFieldMaxPos.addFocusListener(this);
			contentPanel.add(textFieldMaxPos);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(this);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
	}

	/**
	 * Closes the window.
	 */
	private void closeWindow()
	{
		this.dispose();
		this.setVisible(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == cancelButton)
			closeWindow();
		else if (e.getSource() == okButton)
		{
			String name = (String) comboBoxUnitName.getSelectedItem();
			
			// textFieldMinWidth, textFieldMaxWidth, textFieldMinHeight, textFieldMaxHeight, textFieldMinPos, textFieldMaxPos;
			double minWid = EditUnitSlideProjGUI.StringToDouble(textFieldMinWidth.getText());
			double maxWid = EditUnitSlideProjGUI.StringToDouble(textFieldMaxWidth.getText());
			double minHgt = EditUnitSlideProjGUI.StringToDouble(textFieldMinHeight.getText());
			double maxHgt = EditUnitSlideProjGUI.StringToDouble(textFieldMaxHeight.getText());
			double minPos = EditUnitSlideProjGUI.StringToDouble(textFieldMinPos.getText());
			double maxPos = EditUnitSlideProjGUI.StringToDouble(textFieldMaxPos.getText());
			
			if (   minWid 	== Double.MAX_VALUE
				|| maxWid 	== Double.MAX_VALUE
				|| minHgt 	== Double.MAX_VALUE
				|| maxHgt 	== Double.MAX_VALUE
				|| minPos 	== Double.MAX_VALUE
				|| maxPos 	== Double.MAX_VALUE )
				JOptionPane.showMessageDialog(this, "Every item must have a valid number");
			else		
			{
				if (parentWindow != null)
				{
					// In case name was changed
					if (toBeEdited != null)
						parentWindow.removeUnit(toBeEditedName);
					
					// Create new Unit and add it to parent
					UnitMotor unit = new UnitMotor(name, minWid, maxWid, minHgt, maxHgt, minPos, maxPos);
					parentWindow.addUnit(unit);
				}
				
				closeWindow();
			}
		}
	}

	@Override
	public void focusGained(FocusEvent e) 
	{
		// When the textfield is tabbed to, highlight its text
		if (e.getComponent().getClass() == JTextField.class)
			((JTextField) e.getComponent()).selectAll();
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
