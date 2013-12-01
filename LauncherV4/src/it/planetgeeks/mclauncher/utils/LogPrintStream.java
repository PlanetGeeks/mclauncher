package it.planetgeeks.mclauncher.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * @author PlanetGeeks
 *
 */

public class LogPrintStream extends PrintStream
{ 
    private final PrintStream second;
    private final PrintStream third;
    
    public LogPrintStream(OutputStream main, PrintStream second, PrintStream third)
    {
    	super(main);
    	this.second = second;
    	this.third = third;
    }
    
    @Override
    public void close()
    {
    	super.close();
    	second.close();
    	third.close();
    }
    
    @Override
    public void flush()
    {
    	super.flush();
    	second.flush();
    	third.close();
    }
   
    @Override
    public void write(byte[] buf, int off, int len)
    {
    	super.write(buf, off, len);
    	second.write(buf, off , len);
    	third.write(buf, off, len);
    }
    
    @Override
    public void write(int b)
    {
    	super.write(b);
    	second.write(b);
    	third.write(b);
    }
    
    @Override
    public void write(byte[] b) throws IOException
    {
    	super.write(b);
    	second.write(b);
    	third.write(b);
    }
}
