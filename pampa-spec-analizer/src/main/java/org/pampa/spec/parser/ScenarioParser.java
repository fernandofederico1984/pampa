/*
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.pampa.spec.parser;

import org.pampa.spec.parsed.ParsedScenario;

/**
 * <p>
 * Representation of a testing scenario
 * </p>
 *
 * @author Federico, Fernando
 * @since 1.0
 */
public class ScenarioParser
{

    private StepParser precondition;

    private StepParser action;
    private StepParser outcome;

    private final String description;


    public ScenarioParser(String description)
    {
        this.description = description;
    }

    public ScenarioParser given(String precondition)
    {
        this.precondition = new StepParser(precondition);

        return this;
    }


    public ScenarioParser and(String andStep)
    {
        if (action == null)
        {
            precondition.and(andStep);
        }
        else if (outcome == null)
        {
            action.and(andStep);
        }
        else
        {
            outcome.and(andStep);
        }
        return this;
    }


    public ScenarioParser but(String butStep)
    {
        if (action == null)
        {
            precondition.but(butStep);
        }
        else if (outcome == null)
        {
            action.but(butStep);
        }
        else
        {
            outcome.but(butStep);
        }
        return this;
    }

    public ScenarioParser when(String action)
    {
        this.action = new StepParser(action);
        return this;
    }

    public ScenarioParser then(String outcome)
    {
        this.outcome = new StepParser(outcome);
        return this;
    }

    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer("Scenario:")
                .append(description).append("\n");

        buffer.append("\tGIVEN " + precondition.toString());
        buffer.append("\tWHEN " + action.toString());
        buffer.append("\tTHEN " + outcome.toString());

        return buffer.toString();
    }

    public ParsedScenario parse()
    {
        ParsedScenario parsedScenario = new ParsedScenario();
        parsedScenario.setDescription(TextParser.instance().parse(new ParsingContext(null), description));
        ParsingContext context = new ParsingContext(null);
        parsedScenario.setPrecondition(precondition.parse(context));
        parsedScenario.setAction(action.parse(context));
        parsedScenario.setOutcome(outcome.parse(context));

        return parsedScenario;
    }
}
