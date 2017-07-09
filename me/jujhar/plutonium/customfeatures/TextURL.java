package me.jujhar.plutonium.customfeatures;

import java.io.*;
import java.net.*;

public class TextURL {
	private static final int BUFFER_SIZE = 1024 * 10;
    private static final int ZERO = 0;
    private final byte[] dataBuffer = new byte[BUFFER_SIZE];
    private final URL urlObject;

    public TextURL(String urlString) throws MalformedURLException
    {
        this.urlObject = new URL(urlString);
    }

    public String read() 
    {
        final StringBuilder sb = new StringBuilder();

        try
        {
            final BufferedInputStream in =
                    new BufferedInputStream(urlObject.openStream());

            int bytesRead = ZERO;

            while ((bytesRead = in.read(dataBuffer, ZERO, BUFFER_SIZE)) >= ZERO)
            {
                sb.append(new String(dataBuffer, ZERO, bytesRead));
            }
        }
        catch (UnknownHostException e)
        {
            return null;
        }
        catch (IOException e)
        {
            return null;
        }

        return sb.toString();
    }
}
