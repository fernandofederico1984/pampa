/*
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.pampa.spec.parsed;

import java.util.List;

import opennlp.tools.parser.Parse;

/**
 *
 */
public class ParsedSentence
{

    public Parse sentence;
    private List<Parse> who;
    private List<Parse> what;

    public ParsedSentence(Parse sentence, List<Parse> who, List<Parse> what)
    {
        this.sentence = sentence;
        this.who = who;
        this.what = what;
    }

    public String getSentence()
    {
        return sentence.toString();
    }

    public List<Parse> getWho()
    {
        return who;
    }

    public List<Parse> getWhat()
    {
        return what;
    }

    public boolean isUnderstandable()
    {
        return what != null && who != null;
    }

    @Override
    public String toString()
    {
        StringBuffer stringBuffer = new StringBuffer();
        if (who != null)
        {
            stringBuffer.append("WHO: ");
            for (Parse p : who)
            {
                if ( p != null ) {
                    stringBuffer.append(";" + p.toString());
                }
            }
        }
        else
        {
            stringBuffer.append("WHO: ? ");

        }

        if (what != null)
        {
            stringBuffer.append(" WHAT: ");
            for (Parse p : what)
            {
                if (p != null)
                {
                    stringBuffer.append(";" + p.toString());
                }

            }
        }
        else
        {
            stringBuffer.append(" WHAT: ? ");

        }

        return stringBuffer.toString();
    }

    public void show()
    {
        sentence.show();
    }
}
