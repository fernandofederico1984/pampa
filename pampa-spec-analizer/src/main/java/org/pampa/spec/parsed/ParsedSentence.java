/*
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.pampa.spec.parsed;

import opennlp.tools.parser.Parse;

/**
 *
 */
public class ParsedSentence
{

    private Parse sentence;
    private Parse who;
    private Parse what;

    public ParsedSentence(Parse sentence, Parse who, Parse what)
    {
        this.sentence = sentence;
        this.who = who;
        this.what = what;
    }

    public String getSentence()
    {
        return sentence.toString();
    }

    public Parse getWho()
    {
        return who;
    }

    public Parse getWhat()
    {
        return what;
    }

    public boolean isUnderstandable(){
           return what != null && who != null;
    }

    @Override
    public String toString()
    {
        StringBuffer stringBuffer = new StringBuffer();
        if ( who != null ) {
            stringBuffer.append("WHO: " + who.toString());
        }
        else{
            stringBuffer.append("WHO: ? ");

        }

        if ( what != null ) {
            stringBuffer.append(" WHAT: " + what.toString());
        }
        else{
            stringBuffer.append(" WHAT: ? ");

        }

        return stringBuffer.toString();
    }

    public void show(){
        sentence.show();
    }
}
