package net.sf.fmj.test.compat.sun;

import java.io.IOException;

import javax.media.IncompatibleSourceException;

import net.sf.fmj.test.tracing.TracingDataSource;
import net.sf.fmj.test.tracing.TracingPullBufferDataSource;

import junit.framework.TestCase;

import com.sun.media.parser.RawPullStreamParser;

/**
 * 
 * @author Ken Larson
 *
 */
public class RawPullStreamParserTest extends TestCase
{

	public void testRawPullStreamParser() throws Exception
	{
		RawPullStreamParser p = new RawPullStreamParser();
		
		assertEquals(p.getName(), "Raw pull stream parser");
		
		try
		{
			p.setSource(new TracingDataSource());
			assertTrue(false);
		} catch (IncompatibleSourceException e)
		{
		} catch (IOException e)
		{
			e.printStackTrace();
			assertTrue(false);
		}
		
		{
			TracingPullBufferDataSource ds = new TracingPullBufferDataSource();
			try
			{
				p.setSource(ds);
				assertTrue(false);
			} catch (IncompatibleSourceException e)
			{
				//e.printStackTrace();
			} catch (IOException e)
			{
				e.printStackTrace();
				assertTrue(false);
			}
			assertEquals(ds.getStringBuffer().toString(), "");
		}
		
		{
			TracingPullBufferDataSource ds = new TracingPullBufferDataSource();
			try
			{
				p.setSource(ds);
				assertTrue(false);
			} catch (IncompatibleSourceException e)
			{
				//e.printStackTrace();
			} catch (IOException e)
			{
				e.printStackTrace();
				assertTrue(false);
			}
			assertEquals(ds.getStringBuffer().toString(), "");
			assertEquals(p.getTracks(), null);
		}
	}
}
