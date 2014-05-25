/**
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.pampa.code

import groovy.text.SimpleTemplateEngine

/**
 * <p>
 * A user specifies a list of acceptance criteria that needs to be developed by the programmer and validated by the QA.
 * The {@link TestCodeGenerator} is in charge of create the template in for developers and QAs to guide their activities.
 * <p>
 * <p>
 * Each test is created based on a set of specs defined in natural language. Each test case will be ignored by default,
 * developers need to remove the ignore set of each test and start development. This is thought this way so the new tests
 * can be integrated into the source code without affecting current build and block other users (in case the user decides
 * to include the new tests directly into the code repository)
 * </p>
 * <p>
 * The {@link TestCodeGenerator} returns a test {@link Writable} so it can be flushed into any file/external system
 * </p>
 * <p>
 * This class is abstract so test can be implemented in different programming languages or different templates.
 * </p>
 *
 * @author Fernando Federico
 * @since 1.0
 */
abstract class TestCodeGenerator {

    /**
     * <p>
     * General description of the specification. This is the global explanation of what is trying to be tested.
     * </p>
     */
    def specificationDescription

    /**
     * <p>
     * The name of the specification. It has to be a camel case no name. The format validation matches with:
     * </p>
     */
    def specificationName

    /**
     * <p>
     * The covered scenarios for the test case that will cover the specification
     * </p>
     */
    def List<TestScenario> testScenarios = []

    /**
     * <p>
     * Generates a {@link Writable} object that contains the code test that validates the specification that has been
     * set to the {@link TestCodeGenerator}. The template used to generate the test plus the extra properties for that
     * particular template are taken from the {@link #getTemplate()}
     * </p>
     *
     * @return A {@link Writable} object with the test code
     * @throws IllegalArgumentException
     */
    def final Writable generate() {
        validate()
        def engine = new SimpleTemplateEngine()
        return engine.createTemplate(getTemplate())
                .make(properties)
    }

    /**
     * @return The location of the test template to be used to generate the test
     */
    protected abstract URL getTemplate()

    /**
     * <p>
     * Validation of the attributes for the tests
     * </p>
     */
    protected def validate(){
        if ( !specificationName )
            throw new IllegalArgumentException("You need to set an specification name.")

        if (!testScenarios )
            throw new IllegalArgumentException("You need to set the test scenarios.")
    }
}