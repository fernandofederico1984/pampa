package org.pampa.spec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.pampa.spec.parsed.ParsedScenario;
import org.pampa.spec.parsed.ParsedSentence;
import org.pampa.spec.parser.ScenarioParser;

/**
 *
 */

// TODO: checkear ambiguedad antes  (recomendar passive voice)
// TODO: Agregar diccionario

public class ScenarioParserTest
{
     @Test
    public void scenario1(){
         ScenarioParser scenarioParser = new ScenarioParser("Mule restarts")
                 .given("the Agent Console started and connected with Mule")
                 .when("mule goes down")
                    .and("it goes up again")
                 .then("Agent Console should appear connected with mule")
                    .but("with a different connection id");

         System.out.println(scenarioParser.toString());

         ParsedScenario accept = scenarioParser.parse();

         showParsed(accept);

         List<ParsedSentence> notUnderstandableSentences = accept.getOutcome().getNotUnderstandableSentences();

         assertTrue(accept.getAction().getNotUnderstandableSentences().isEmpty());
         assertTrue(accept.getPrecondition().getNotUnderstandableSentences().isEmpty());
         assertFalse(notUnderstandableSentences.isEmpty());

         assertEquals("the Agent Console", accept.getPrecondition().getDescription().get(0).getWho().toString());
         assertEquals("started and connected with Mule", accept.getPrecondition().getDescription().get(0).getWhat().toString());
         assertEquals("mule", accept.getAction().getDescription().get(0).getWho().toString());
         assertEquals("goes down", accept.getAction().getDescription().get(0).getWhat().toString());
         assertEquals("mule", accept.getAction().getNext().getDescription().get(0).getWho().toString());
         assertEquals("goes up again", accept.getAction().getNext().getDescription().get(0).getWhat().toString());
         assertEquals("Agent Console", accept.getOutcome().getDescription().get(0).getWho().toString());
         assertEquals("should appear connected with mule", accept.getOutcome().getDescription().get(0).getWhat().toString());

         System.out.println("Cannot understand:");

         for ( ParsedSentence sentence : notUnderstandableSentences ){
             System.out.println(sentence.getSentence());
         }

         System.out.println("-------------------------------------");

     }

    @Test
    public void scenario2(){
        ScenarioParser scenarioParser = new ScenarioParser("Mule restarts")
                .given("Mule is started and connected with the Agent Console")
                .when("mule goes down")
                .and("it goes up again")
                .then("Agent Console should appear connected with mule")
                .but("The connection id must be different from the previous one");

        System.out.println(scenarioParser.toString());

        ParsedScenario accept = scenarioParser.parse();

        showParsed(accept);


        assertEquals("Mule", accept.getPrecondition().getDescription().get(0).getWho().toString());
        assertEquals("is started and connected with the Agent Console", accept.getPrecondition().getDescription().get(0).getWhat().toString());
        assertEquals("mule", accept.getAction().getDescription().get(0).getWho().toString());
        assertEquals("goes down", accept.getAction().getDescription().get(0).getWhat().toString());
        assertEquals("mule", accept.getAction().getNext().getDescription().get(0).getWho().toString());
        assertEquals("goes up again", accept.getAction().getNext().getDescription().get(0).getWhat().toString());
        assertEquals("Agent Console", accept.getOutcome().getDescription().get(0).getWho().toString());
        assertEquals("should appear connected with mule", accept.getOutcome().getDescription().get(0).getWhat().toString());
        assertEquals("The connection id", accept.getOutcome().getNext().getDescription().get(0).getWho().toString());
        assertEquals("must be different from the previous one", accept.getOutcome().getNext().getDescription().get(0).getWhat().toString());

        List<ParsedSentence> notUnderstandableSentences = accept.getOutcome().getNotUnderstandableSentences();

        assertTrue(accept.getAction().getNotUnderstandableSentences().isEmpty());
        assertTrue(accept.getPrecondition().getNotUnderstandableSentences().isEmpty());
        assertTrue(notUnderstandableSentences.isEmpty());


        showOk();
    }


    @Test
    public void scenario3(){
        ScenarioParser scenarioParser = new ScenarioParser("The Plugin deploys an application THEN the Console should receive a deployment notification")
                .given("The Plugin starts with a Deployment Service that is configured with VALID credentials")
                .when("the Plugin deploys an application")
                .then("the Console should have received a deployment notification")
                    .and("The application's name in the notification should be equal to the deployed application");

        System.out.println(scenarioParser.toString());

        ParsedScenario parsed = scenarioParser.parse();

        showParsed(parsed);


        List<ParsedSentence> notUnderstandableSentences = parsed.getOutcome().getNotUnderstandableSentences();

        assertTrue(parsed.getAction().getNotUnderstandableSentences().isEmpty());
        assertTrue(parsed.getPrecondition().getNotUnderstandableSentences().isEmpty());
        assertTrue(notUnderstandableSentences.isEmpty());


        assertEquals("The Plugin", parsed.getPrecondition().getDescription().get(0).getWho().toString());
        assertEquals("starts with a Deployment Service that is configured with VALID credentials", parsed.getPrecondition().getDescription().get(0).getWhat().toString());
        assertEquals("the Plugin", parsed.getAction().getDescription().get(0).getWho().toString());
        assertEquals("deploys an application", parsed.getAction().getDescription().get(0).getWhat().toString());
        assertEquals("the Console", parsed.getOutcome().getDescription().get(0).getWho().toString());
        assertEquals("should have received a deployment notification", parsed.getOutcome().getDescription().get(0).getWhat().toString());
        assertEquals("The application's name in the notification", parsed.getOutcome().getNext().getDescription().get(0).getWho().toString());
        assertEquals("should be equal to the deployed application", parsed.getOutcome().getNext().getDescription().get(0).getWhat().toString());

        showOk();
    }

    @Test
    public void scenario4(){
        ScenarioParser scenarioParser = new ScenarioParser("Agent Plugin with a Deployment Service with INVALID credentials WHEN the Plugin deploys an application THEN the Console shouldn't receive a deployment notification")
                .given("The Plugin starts with a Deployment Service that is configured with invalid credentials")
                .when("the Plugin deploys an application")
                .then("the Console shouldn't have received any deployment notifications");

        System.out.println(scenarioParser.toString());

        ParsedScenario parsed = scenarioParser.parse();

        showParsed(parsed);


        List<ParsedSentence> notUnderstandableSentences = parsed.getOutcome().getNotUnderstandableSentences();

        assertTrue(parsed.getAction().getNotUnderstandableSentences().isEmpty());
        assertTrue(parsed.getPrecondition().getNotUnderstandableSentences().isEmpty());
        assertTrue(notUnderstandableSentences.isEmpty());


        assertEquals("The Plugin", parsed.getPrecondition().getDescription().get(0).getWho().toString());
        assertEquals("starts with a Deployment Service that is configured with invalid credentials", parsed.getPrecondition().getDescription().get(0).getWhat().toString());
        assertEquals("the Plugin", parsed.getAction().getDescription().get(0).getWho().toString());
        assertEquals("deploys an application", parsed.getAction().getDescription().get(0).getWhat().toString());
        assertEquals("the Console", parsed.getOutcome().getDescription().get(0).getWho().toString());
        assertEquals("shouldn't have received any deployment notifications", parsed.getOutcome().getDescription().get(0).getWhat().toString());

        showOk();
    }

    @Test
    public void scenario5(){
        ScenarioParser scenarioParser = new ScenarioParser("a WebSocket Transport with VALID credentials WHEN the Agent Plugin initialises and then tries to stop THEN The message should be 'Trying to execute phase STOP from stage INITIALISE and it is not possible'")
                .given("A WebSocket Transport configured with valid credentials")
                .when("the transport is started")
                    .and("tries to stop")
                .then("Logging Level should be WARN and the message should be 'Trying to execute phase stop from initialise stop and it is not possible'" );

        System.out.println(scenarioParser.toString());

        ParsedScenario parsed = scenarioParser.parse();

        showParsed(parsed);


        List<ParsedSentence> notUnderstandableSentences = parsed.getOutcome().getNotUnderstandableSentences();

        assertTrue(parsed.getAction().getNotUnderstandableSentences().isEmpty());
        assertTrue(parsed.getPrecondition().getNotUnderstandableSentences().isEmpty());
        assertTrue(notUnderstandableSentences.isEmpty());


        assertEquals("A WebSocket", parsed.getPrecondition().getDescription().get(0).getWho().toString());
        assertEquals("configured with valid credentials", parsed.getPrecondition().getDescription().get(0).getWhat().toString());
        assertEquals("the transport", parsed.getAction().getDescription().get(0).getWho().toString());
        assertEquals("is started", parsed.getAction().getDescription().get(0).getWhat().toString());
        assertEquals("the transport", parsed.getAction().getNext().getDescription().get(0).getWho().toString());
        assertEquals("tries to stop", parsed.getAction().getNext().getDescription().get(0).getWhat().toString());
        assertEquals("Logging Level", parsed.getOutcome().getDescription().get(0).getWho().toString());
        assertEquals("should be WARN", parsed.getOutcome().getDescription().get(0).getWhat().toString());
        assertEquals("the message", parsed.getOutcome().getDescription().get(0).getWho().toString());
        assertEquals("should be: 'Trying to execute phase stop from initialise stop and it is not possible'", parsed.getOutcome().getDescription().get(0).getWhat().toString());

        showOk();
    }


    private void showParsed(ParsedScenario parsed)
    {
        System.out.println("I Got this:");

        System.out.println(parsed.toString());
    }

    private void showOk()
    {
        System.out.println("Everything was understood!");

        System.out.println("-------------------------------------");
    }


}
