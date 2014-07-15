/*
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.pampa.spec.parsed;

import java.util.List;


public class ParsedScenario
{

    private List<ParsedSentence> description;
    private ParsedStep precondition;
    private ParsedStep action;
    private ParsedStep outcome;

    public List<ParsedSentence> getDescription()
    {
        return description;
    }

    public ParsedStep getPrecondition()
    {
        return precondition;
    }

    public ParsedStep getAction()
    {
        return action;
    }

    public ParsedStep getOutcome()
    {
        return outcome;
    }

    public void setOutcome(ParsedStep outcome)
    {
        this.outcome = outcome;
    }

    public void setDescription(List<ParsedSentence> description)
    {
        this.description = description;
    }

    public void setPrecondition(ParsedStep precondition)
    {
        this.precondition = precondition;
    }

    public void setAction(ParsedStep action)
    {
        this.action = action;
    }

    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer("Scenario:")
                .append(description.toString()).append("\n");

        buffer.append("\tGIVEN " + precondition.toString());
        buffer.append("\tWHEN " + action.toString());
        buffer.append("\tTHEN " + outcome.toString());

        return buffer.toString();
    }


}
