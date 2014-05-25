/**
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.pampa.code
import groovy.json.JsonSlurper

class TestCodeGeneratorTest extends spock.lang.Specification {

    public static final String BLOCK_DESCRIPTION = "blockDescription"
    public static final String BLOCK_NAME = "block1"

    def "The test class cannot be generated if the specification name is null. All the tests must have a name"() {
        when: "The test code generators is built without an specification name"
        testCodeGenerator().with {
            specificationDescription = "any description"
            testScenarios = [new TestScenario()]
            generate()
        }

        then: "An illegal Argument exception must be thrown with the message 'You need to set an specification name'"
        IllegalArgumentException ex = thrown()
        ex.message == "You need to set an specification name."
    }

    def "The test class cannot be generated if the test scenarios is null. It makes no sense to create a test without scenarios"() {
        when: "The test code generators is built without the test scenarios"
        testCodeGenerator().with {
            specificationDescription = "any description"
            specificationName = "any name"
            testScenarios = []
            generate()
        }

        then: "An illegal Argument exception must be thrown. With the message 'You need to set an the test scenarios.'"
        IllegalArgumentException ex = thrown()
        ex.message == "You need to set the test scenarios."
    }


    def "The test class cannot be generated if the test scenarios is empty. It makes no sense to create a test without scenarios"() {
        when: "The test code generators is built without the test scenarios"
        testCodeGenerator().with {
            specificationDescription = "any description"
            specificationName = "any name"
            generate()
        }

        then: "An illegal Argument exception must be thrown. With the message 'You need to set an the test scenarios.'"
        IllegalArgumentException ex = thrown()
        ex.message == "You need to set the test scenarios."
    }


    def "The test class can be created without description."(){

        when: "The test class is created without description but with test name and scenarios"
        def writer = new StringWriter()
        def generator = testCodeGenerator()
        generator.with {
            specificationName = "any name"
            testScenarios = [testScenario()];

        }
        def code = generator.generate()
        code.writeTo(writer)
        def json = new JsonSlurper().parseText(writer.toString());

        then: "No exception must be thrown"
        notThrown IllegalArgumentException

        and:
        code != null

        and:
        json.specification == "any name"
        json.scenarios[0].blocks[0].name == BLOCK_NAME
        json.scenarios[0].blocks[0].description == BLOCK_DESCRIPTION
    }

    private TestScenario testScenario() {
        def scenario = new TestScenario()
        scenario.with {
            def block = new ScenarioBlock()
            block.with {
                name = BLOCK_NAME
                description = BLOCK_DESCRIPTION
            }
            blocks.add(block)
            name = "scenario1"

        }
        return scenario
    }

    TestCodeGenerator testCodeGenerator() {
        return new TestCodeGenerator() {
            @Override
            protected URL getTemplate() {
                return getClass().getResource("/testCodeTemplate.txt")
            }
        }
    }
}
