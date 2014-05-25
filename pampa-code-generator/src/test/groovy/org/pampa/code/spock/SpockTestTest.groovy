/**
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.pampa.code.spock

import org.pampa.code.TestScenario

class SpockTestTest extends spock.lang.Specification {

    def "If test destination package contains spaces the fail with illegal argument exception"(){
        def test = new SpockTest()
        when: "The spock test is created with a package with with spaces"
        test.destinationPackage = "any with spaces"
        test.specificationName = "Specification"
        test.testScenarios.add(new TestScenario())
        test.generate()

        then: "Expect an exception to be thrown"
        IllegalArgumentException ex = thrown()
        ex.message == "Destination package must not have spaces"
    }


    def "If test destination package is null then throw an exception"(){
        def test = new SpockTest()
        when: "There is no packege"
        test.specificationName = "Specification"
        test.testScenarios.add(new TestScenario())
        test.generate()

        then: "Expect an exception to be thrown"
        IllegalArgumentException ex = thrown()
        ex.message == "Destination package must not be null"
    }

    def "If everything is well set then the test should be created"(){
        def test = new SpockTest()
        when: "All information is in place"
        test. with {destinationPackage = "any.without.spaces"
        specificationName = "Specification"
        testScenarios.add(new TestScenario())}

        StringWriter writer = new StringWriter()
        test.generate().writeTo(writer)

        then: "The test template must be created"
        writer.toString() != null
    }

}
