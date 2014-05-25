/**
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.pampa.code

/**
 * <p>
 * Represents the Given-when-then blocks of a {@see <a href="http://dannorth.net/introducing-bdd/">BDD specification</a>}
 * </p>
 */
class ScenarioBlock {
    /**
     * <p>
     * The name of the block, left as a string to allows any block name, not tight to an enum.
     * </p>
     */
    def name

    /**
     * <p>
     * The information about what the block is testing (the block documentation)
     * </p>
     */
    def description
}
