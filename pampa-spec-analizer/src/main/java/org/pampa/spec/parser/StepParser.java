package org.pampa.spec.parser;

import org.pampa.spec.parsed.ParsedStep;

/**
 *
 */
class StepParser
{

    private String description;
    private StepConnector connector;
    private StepParser next;

    public StepParser(String precondition)
    {
        this.description = precondition;
    }

    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer(description + "\n");
        if (next != null)
        {
            buffer.append("\t\t" + connector.toString() + " " + next.toString());
        }
        return buffer.toString();
    }

    public void and(String andStep)
    {
        if (next == null)
        {
            connector = StepConnector.AND;
            next = new StepParser(andStep);
        }
        else
        {
            next.and(andStep);
        }
    }

    public void but(String butStep)
    {
        if (next == null)
        {
            connector = StepConnector.BUT;
            next = new StepParser(butStep);
        }
        else
        {
            next.but(butStep);
        }
    }


    public ParsedStep parse(ParsingContext context)
    {
        TextParser parser = TextParser.instance();
        ParsedStep parsedStep = new ParsedStep();

        parsedStep.setDescription(parser.parse(context, description));
        if (next != null)
        {
            parsedStep.setConnector(connector);
            parsedStep.setNext(next.parse(context));

        }

        return parsedStep;
    }


}
