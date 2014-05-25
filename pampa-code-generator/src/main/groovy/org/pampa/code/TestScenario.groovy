/**
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.pampa.code

/**
 * <p>
 * Representation of an scenario of the functional specification
 * </p>
 */
class TestScenario {
    /**
     * <p>
     * Name of the scenario. In some programming languages can be the scenario description.
     * </p>
     */
    def String name

    /**
     * <p>
     * The pieces of the test scenario.
     * </p>
     */
    def List<ScenarioBlock> blocks = []
}
