/*
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.pampa.spec.parsed;

import java.util.ArrayList;
import java.util.List;

import org.pampa.spec.parser.StepConnector;

/**
 *
 */
public class ParsedStep
{
    private List<ParsedSentence> description;
    private StepConnector connector;
    private ParsedStep next;

    public List<ParsedSentence> getDescription()
    {
        return description;
    }

    public void setDescription(List<ParsedSentence> description)
    {
        this.description = description;
    }

    public StepConnector getConnector()
    {
        return connector;
    }

    public void setConnector(StepConnector connector)
    {
        this.connector = connector;
    }

    public ParsedStep getNext()
    {
        return next;
    }

    public void setNext(ParsedStep next)
    {
        this.next = next;
    }

    public List<ParsedSentence> getNotUnderstandableSentences(){
        List<ParsedSentence> notUnderstandable = new ArrayList<ParsedSentence>();
        for ( ParsedSentence parsedSentence : description ){
            if ( !parsedSentence.isUnderstandable() ){
                notUnderstandable.add(parsedSentence);
            }
        }

        if ( next != null ){
            notUnderstandable.addAll(next.getNotUnderstandableSentences());
        }

        return notUnderstandable;
    }

    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        for ( ParsedSentence sentence : description ){
            buffer.append(sentence.toString() + " \n");
        }

        if (next != null)
        {

            buffer.append("\t\t" + connector.toString() + " " + next.toString());
        }
        return buffer.toString();
    }

}
