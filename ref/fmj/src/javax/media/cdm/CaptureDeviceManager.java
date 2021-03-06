package javax.media.cdm;

import java.util.Vector;

import javax.media.CaptureDeviceInfo;
import javax.media.Format;

import net.sf.fmj.utility.Registry;

/**
 * 
 * Coding complete.
 * TODO: commit.
 * @author Ken Larson
 *
 */
public class CaptureDeviceManager extends javax.media.CaptureDeviceManager
{
	public CaptureDeviceManager()
	{
		super();
	}

	public static synchronized CaptureDeviceInfo getDevice(String deviceName)
	{	
		final Vector v = getDeviceList();
		for (int i = 0; i < v.size(); ++i)
		{
			final CaptureDeviceInfo captureDeviceInfo = (CaptureDeviceInfo) v.get(i);
			if (captureDeviceInfo.getName().equals(deviceName))
				return captureDeviceInfo;
		}
		return null;
	}

	public static synchronized Vector getDeviceList()	// not in javax.media.CaptureDeviceManager
	{	return Registry.getInstance().getDeviceList();
	}
	
	public static synchronized Vector getDeviceList(Format format)
	{	
		final Vector v = getDeviceList();
		final Vector result = new Vector();
		for (int i = 0; i < v.size(); ++i)
		{
			final CaptureDeviceInfo captureDeviceInfo = (CaptureDeviceInfo) v.get(i);
			if (format == null)
			{	result.add(captureDeviceInfo);
			}
			else
			{
				final Format[] formats = captureDeviceInfo.getFormats();
				
				for (int j = 0; j < formats.length; ++j)
				{
					if (format.matches(formats[j]))
					{
						result.add(captureDeviceInfo);
						break;
					}
				}
			}
					
				
		}
		return result;
		
	}

	public static synchronized boolean addDevice(CaptureDeviceInfo newDevice)
	{	
		return Registry.getInstance().addDevice(newDevice);
	}

	public static synchronized boolean removeDevice(CaptureDeviceInfo device)
	{	return Registry.getInstance().removeDevice(device);
	}

	public static synchronized void commit() throws java.io.IOException
	{	
		Registry.getInstance().commit();
	}
}
