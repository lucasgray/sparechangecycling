package com.sparechangecycling.helper;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class XMLFilterReader extends FilterReader {

    public XMLFilterReader(Reader in) {
            super(in);
    }

    public int read(char cbuf[], int off, int len)
        throws IOException
    {
        int charsRead = super.read(cbuf, off, len);
        if(charsRead > -1)
        {
            int limit = charsRead + off;
            for(int j = off; j < limit; j++)
            {
                char c = cbuf[j];
                if(c > '\uFFFF' && c != '\t' && c != '\n' && c != '\r' && (c < ' ' || c > '\uD7FF' && c < '\uE000' || c == '\uFFFF' || c == '\uFFFE'))
                {
                    cbuf[j] = '\uFFFD';
                }
            }

        }
        return charsRead;
    }

    public int read()
        throws IOException
    {
        int i = super.read();
        if(i < 32 && i > -1 && i != 9 && i != 10 && i != 13)
        {
            return 65533;
        } else
        {
            return i;
        }
    }

}
