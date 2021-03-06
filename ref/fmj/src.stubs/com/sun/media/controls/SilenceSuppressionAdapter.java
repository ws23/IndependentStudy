package com.sun.media.controls;

import java.awt.Component;

import javax.media.Codec;
import javax.media.control.SilenceSuppressionControl;

/**
 * TODO: stub.
 * @author Ken Larson
 *
 */
public class SilenceSuppressionAdapter implements SilenceSuppressionControl
{

	protected Codec owner;
	protected boolean silenceSuppression;
	protected boolean isSetable;
	Component component;
	String CONTROL_STRING;
	
	public SilenceSuppressionAdapter(Codec owner, boolean silenceSuppression, boolean isSetable)
	{	
		this.owner = owner;
		this.silenceSuppression = silenceSuppression;
		this.isSetable = isSetable;
	}

	public boolean getSilenceSuppression()
	{
		return silenceSuppression;
	}

	public boolean setSilenceSuppression(boolean silenceSuppression)
	{
		if (isSetable)
			this.silenceSuppression = silenceSuppression;
		return this.silenceSuppression;
	}

	public boolean isSilenceSuppressionSupported()
	{
		return isSetable;
	}
	
	public Component getControlComponent()
	{
		return component;	// TODO: is null
	}
}
