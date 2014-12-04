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

public class ScenarioParserTest {
    @Test
    public void scenario1() {
        ScenarioParser scenarioParser = new ScenarioParser("Mule restarts")
                .given("the Agent Console is started or connected with Mule")
                .when("mule goes down and it goes up again")
                .then("Agent Console should appear connected with mule but with a different connection id");

         System.out.println(scenarioParser.toString());

        showParsed(scenarioParser.parse());
    }

    @Test
    public void scenario2() {
        ScenarioParser scenarioParser = new ScenarioParser("When mule goes down it tries to reconnect with the administration console after it re-start")
                .given("Mule is started and connected with the Agent Console")
                .when("mule goes down")
                .and("it goes up again")
                .then("Agent Console should appear connected with mule")
                .but("The connection id must be different from the previous one");

        System.out.println(scenarioParser.toString());

        showParsed(scenarioParser.parse());
    }


    @Test
    public void scenario3() {
        ScenarioParser scenarioParser = new ScenarioParser("The Plugin deploys an application THEN the Console should receive a deployment notification")
                .given("The Plugin starts with a Deployment Service that is configured with VALID credentials")
                .when("the Plugin deploys an application")
                .then("the Console should have received a deployment notification")
                .and("The application's name in the notification should be equal to the deployed application");

        System.out.println(scenarioParser.toString());

        showParsed(scenarioParser.parse());
    }

    @Test
    public void scenario4() {
        ScenarioParser scenarioParser = new ScenarioParser("Agent Plugin with a Deployment Service with INVALID credentials WHEN the Plugin deploys an application THEN the Console shouldn't receive a deployment notification")
                .given("The Plugin starts with a Deployment Service that is configured with invalid credentials")
                .when("the Plugin deploys an application")
                .then("the Console shouldn't have received any deployment notifications");

        System.out.println(scenarioParser.toString());

        showParsed(scenarioParser.parse());
    }

    @Test
    public void scenario5() {
        ScenarioParser scenarioParser = new ScenarioParser("a WebSocket Transport with VALID credentials WHEN the Agent Plugin initialises and then tries to stop THEN The message should be 'Trying to execute phase STOP from stage INITIALISE and it is not possible'")
                .given("A WebSocket Transport configured with valid credentials")
                .when("the transport is started")
                .and("tries to stop")
                .then("Logging Level should be WARN and the message should be 'Trying to execute phase stop from initialise stop and it is not possible'");

        System.out.println(scenarioParser.toString());

        showParsed(scenarioParser.parse());
    }


    private void showParsed(ParsedScenario parsed) {
        System.out.println("I Got this:");

        System.out.println(parsed.toString());
    }


}
