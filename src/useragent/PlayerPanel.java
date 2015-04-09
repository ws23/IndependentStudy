package useragent;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.media.MediaLocator;
import javax.media.format.AudioFormat;
import javax.media.protocol.FileTypeDescriptor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import net.sf.fmj.ui.dialogs.RTPReceivePanel;
import net.sf.fmj.ui.dialogs.URLPanel;
import net.sf.fmj.ui.wizards.RTPTransmitWizard;
import net.sf.fmj.ui.wizards.TrackConfig;
import net.sf.fmj.ui.wizards.TranscodeWizard;
import net.sf.fmj.utility.LoggerSingleton;
import net.sf.fmj.utility.PathUtils;
import net.sf.fmj.utility.URLUtils;

/**
 * 
 * @author Warren Bloomer
 *
 */
public class PlayerPanel extends JPanel {

	private static final Logger logger = LoggerSingleton.logger;

	private PlayerPanelPrefs prefs;

	
	private TransportControlPanel transportControlPanel = null;
	private JLabel statusBar = null;
	private JPanel videoPanel = null;
	private ContainerPlayer containerPlayer = null;  //  @jve:decl-index=0:visual-constraint="557,162"

	
	/**
	 * This method initializes videoPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getVideoPanel() {
		if (videoPanel == null) {
			videoPanel = new JPanel();
			
			videoPanel.setLayout(new BorderLayout());
			//videoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		    //TitledBorder
	        //mediaBorder = new TitledBorder(
	        //    BorderConstants.etchedBorder, "Media" );
			//videoPanel.setBorder(mediaBorder);
	        
			videoPanel.setBackground(SystemColor.controlShadow);
		}
		return videoPanel;
	}

	
	/**
	 * This method initializes containerPlayer	
	 * 	
	 * @return net.sf.fmj.ui.application.ContainerPlayer	
	 */
	private ContainerPlayer getContainerPlayer() {
		if (containerPlayer == null) {
			containerPlayer = new ContainerPlayer(getVideoPanel());
			containerPlayer.setAutoLoop(prefs.autoLoop);
			containerPlayer.setContainerPlayerStatusListener(new ContainerPlayerStatusListener() {

				public void onStatusChange(final String newStatus)
				{
					logger.fine("Status change: " + newStatus);	
					SwingUtilities.invokeLater(new Runnable() {
						public void run()
						{
							statusBar.setText(newStatus);
						}
					});
					
				}
				
			});
		}
		return containerPlayer;
	}

	/**
	 * This method initializes 
	 * 
	 */
	public PlayerPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
		
		loadPrefs();
		 
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(363, 218));
        
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        this.add(southPanel, BorderLayout.SOUTH);
        
        
        southPanel.add(getTransportControlPanel(), BorderLayout.NORTH);
        southPanel.add(getStatusBar(), BorderLayout.SOUTH);
        
        this.add(getVideoPanel(), BorderLayout.CENTER);
        
       
			
	}
	
	/** load preferences */
	private void loadPrefs()
	{
		try {
			final File f = PlayerPanelPrefs.getFile();
			if (!f.exists())
			{
				logger.fine("FMJStudio prefs file does not exist.  Using defaults.");
				setDefaultPrefs();
				return;
			}
			FileReader reader = new FileReader(f);
			prefs = new PlayerPanelPrefs();
			prefs.load(reader);
		}
		catch (Exception e) {
			logger.warning("Problem loading FMJStudio prefs: " + e + ".  Using defaults.");
			setDefaultPrefs();
		}
	}
	
	/** create default prefs and save them. */
	private void setDefaultPrefs()
	{
		prefs = new PlayerPanelPrefs();
		
		prefs.recentUrls.add("http://stream.lrz-muenchen.de:31337/m945-hq.ogg"); // internet ogg radio, munich student station....
		prefs.recentUrls.add("http://stream.lrz-muenchen.de:31337/m945-hq.mp3"); // internet mp3 radio, munich student station....
		prefs.recentUrls.add("file://samplemedia/hen.mp3");
		prefs.recentUrls.add("file://samplemedia/lion_roar.mp3");
		prefs.recentUrls.add("file://samplemedia/betterway.wav"); // from EJMF
		prefs.recentUrls.add("file://samplemedia/issues.au"); // from EJMF
		prefs.recentUrls.add("file://samplemedia/gulp.wav"); // from EJMF
		prefs.recentUrls.add("file://samplemedia/gulp2.wav"); // from EJMF
		prefs.recentUrls.add("file://samplemedia/Gloria_Patri.ogg");
		prefs.recentUrls.add("file://samplemedia/safexmas.mov"); // from EJMF
		prefs.recentUrls.add("http://fmj.larsontechnologies.com/samplemedia/Apollo_15_liftoff_from_inside_LM.ogg"); // Apollo 15 movie from wikimedia
		//prefs.recentUrls.add("http://upload.wikimedia.org/wikipedia/commons/d/d0/Apollo_15_liftoff_from_inside_LM.ogg"); // Apollo 15 movie from wikimedia
		prefs.recentUrls.add("http://www.surfshooterhawaii.com//cgi-bin/axispush555.cgi?dummy=garb"); // sample IP surf cam, streaming MJPG
		prefs.recentUrls.add("http://towercam.uu.edu/axis-cgi/mjpg/video.cgi"); // sample IP camera, streaming MJPG
		prefs.recentUrls.add("http://www.easylife.org/386dx/smells.mp3"); // just being silly now
			
		
		prefs.rtpTransmitWizardConfig.url = "file://samplemedia/gulp2.wav";
		prefs.rtpTransmitWizardConfig.trackConfigs = new TrackConfig[] {new TrackConfig(true, new AudioFormat(AudioFormat.ULAW_RTP, 8000.0, 8, 1, AudioFormat.LITTLE_ENDIAN, AudioFormat.SIGNED))};
		prefs.rtpTransmitWizardConfig.destUrl = "rtp://192.168.1.4:8000/audio/16"; //RTPUrlParser.parse("rtp://192.168.1.4:8000/audio/16");

		prefs.transcodeWizardConfig.url = "file://samplemedia/gulp2.wav";
		prefs.transcodeWizardConfig.contentDescriptor = new FileTypeDescriptor(FileTypeDescriptor.WAVE);
		prefs.transcodeWizardConfig.trackConfigs = new TrackConfig[] {new TrackConfig(true, new AudioFormat(AudioFormat.LINEAR, 8000.0, 8, 1, -1, AudioFormat.UNSIGNED))};
		prefs.transcodeWizardConfig.destUrl = URLUtils.createUrlStr(new File(PathUtils.getTempPath(), "gulp2-transcoded.wav"));
		// TODO: format
		
		savePrefs();
   
	}
	
	/** save preferences. */
	private void savePrefs()
	{
		try
		{
			FileWriter fileWriter = new FileWriter(PlayerPanelPrefs.getFile());
			prefs.write(fileWriter);
			fileWriter.flush();
			fileWriter.close();
		}
		catch (Exception e)
		{
			logger.log(Level.WARNING, "savePrefs failed: " + e, e);
		}
	}


	private Frame getParentFrame()
	{
		// TODO: there must be a better way in swing to do this...
		Container c = getParent();
		while (c != null)
		{
			if (c instanceof Frame)
				return (Frame) c;
			
			c = c.getParent();
		}
		throw new RuntimeException("No parent frame");
	}
	
	public void onTransmitRTP()
	{
		RTPTransmitWizard w = new RTPTransmitWizard(getParentFrame(), prefs.rtpTransmitWizardConfig);
		boolean result = w.run();
		
		//  store preferences if successful
		if (result)
		{
			containerPlayer.setRealizedStartedProcessor(w.getResult().processor);
			
			prefs.rtpTransmitWizardConfig = w.getConfig();	// TODO: this is the same object, we need to copy somewhere.
			savePrefs();
		}
		
	}
	
	public void onTranscode()
	{
		TranscodeWizard w = new TranscodeWizard(getParentFrame(), prefs.transcodeWizardConfig);
		boolean result = w.run();
		
		//  store preferences if successful
		if (result)
		{
			containerPlayer.setRealizedStartedProcessor(w.getResult().processor);
			
			prefs.transcodeWizardConfig = w.getConfig();	// TODO: this is the same object, we need to copy somewhere.
			savePrefs();
		}		
	}
	
	public void onReceiveRTP()
	{
		String url = RTPReceivePanel.run(getParentFrame());
		if (url == null)
			return;	// cancel
	//	addMediaLocatorAndLoad(url);
	}
	
	public void onOpenURL()
	{
		String url = URLPanel.run(getParentFrame());
		if (url == null)
			return;	// cancel
	//	addMediaLocatorAndLoad(url);
	}
	
	public void onAutoPlay(boolean value)
	{
		logger.fine("onAutoPlay: " + value);
		prefs.autoPlay = value;
		savePrefs();
	}
	
	public void onAutoLoop(boolean value)
	{
		prefs.autoLoop = value;
		if (containerPlayer != null)
			containerPlayer.setAutoLoop(value);
		
		savePrefs();
	}
	
	
	

	public PlayerPanelPrefs getPrefs()
	{
		return prefs;
	}

	/**
	 * This method initializes transportControlPanel	
	 * 	
	 * @return net.sf.fmj.ui.control.TransportControlPanel	
	 */
	private TransportControlPanel getTransportControlPanel() {
		if (transportControlPanel == null) {
			transportControlPanel = new TransportControlPanel();
			//transportControlPanel.setPlayer(getContainerPlayer());
		}
		return transportControlPanel;
	}
	
	private JLabel getStatusBar() {
		if (statusBar == null) {
			statusBar = new JLabel();
			statusBar.setText(" ");	// so it will lay out properly
		}
		return statusBar;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
