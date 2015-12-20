/*
 * RTPReceivePanel.java
 *
 * Created on June 20, 2007, 9:08 AM
 */

package net.sf.fmj.ui.dialogs;

import java.awt.Component;
import java.awt.Frame;

import net.sf.fmj.ui.objeditor.ComponentValidationException;
import net.sf.fmj.ui.objeditor.ComponentValidator;
import net.sf.fmj.ui.objeditor.ObjEditor;
import net.sf.fmj.ui.objeditor.ObjEditorOKCancelDialog;
import net.sf.fmj.ui.utils.ErrorDialog;

/**
 *
 * @author Ken Larson
 */
public class RTPReceivePanel extends javax.swing.JPanel implements ObjEditor {
    
    /** Creates new form RTPReceivePanel */
    public RTPReceivePanel() {
        initComponents();
    }
    
	public static String run(Frame parent)
	{
		final RTPReceivePanel p = new RTPReceivePanel();
		return (String) ObjEditorOKCancelDialog.run(parent, p, "", "Open RTP Session");
		
	}
    
    public Component getComponent() 
    {
		return this;
	}

	public Object getObject() 
	{
		return url;
	}

	public void setObjectAndUpdateControl(Object o) 
	{
		this.url = (String) o;
		
		// TODO: parse URL
	}

    private String url;
    
    public boolean validateAndUpdateObj()
    {
    	url = null;
    	
    	ComponentValidator v = new ComponentValidator();
    	try {
			v.validateNotEmpty(textAddress, labelAddress);
			v.validateNotEmpty(textPort, labelPort);
			v.validateInteger(textPort, labelPort);
			v.validateNotEmpty(comboTTL, labelTTL);
			v.validateInteger(comboTTL, labelTTL);

    	} 
    	catch (ComponentValidationException e) {
			ErrorDialog.showError(this, e.getMessage());
			return false;
		}

    	final String sessionAddress = textAddress.getText();
		final int port = Integer.parseInt(textPort.getText());
		final int ttl = Integer.parseInt((String) comboTTL.getSelectedItem());
		// TODO: full URL? "rtp://address:port[:ssrc]/content-type/[ttl]"
		
		url = "rtp://" + sessionAddress + ":" + port;	// TODO: TTL without content type?  JMStudio appears to not include the TTL in the URL.
		return true;

    }
    
    public String getUrl()
    {	return url;
    }

    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        labelAddress = new javax.swing.JLabel();
        textAddress = new javax.swing.JTextField();
        labelPort = new javax.swing.JLabel();
        textPort = new javax.swing.JTextField();
        labelTTL = new javax.swing.JLabel();
        comboTTL = new javax.swing.JComboBox();

        setLayout(new java.awt.GridBagLayout());

        labelAddress.setText("Address:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        add(labelAddress, gridBagConstraints);

        textAddress.setPreferredSize(new java.awt.Dimension(80, 19));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        add(textAddress, gridBagConstraints);

        labelPort.setText("Port:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        add(labelPort, gridBagConstraints);

        textPort.setPreferredSize(new java.awt.Dimension(40, 19));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        add(textPort, gridBagConstraints);

        labelTTL.setText("TTL:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        add(labelTTL, gridBagConstraints);

        comboTTL.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "8", "16", "32", "64", "128", "255" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        add(comboTTL, gridBagConstraints);

    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox comboTTL;
    private javax.swing.JLabel labelAddress;
    private javax.swing.JLabel labelPort;
    private javax.swing.JLabel labelTTL;
    private javax.swing.JTextField textAddress;
    private javax.swing.JTextField textPort;
    // End of variables declaration//GEN-END:variables
    
}
