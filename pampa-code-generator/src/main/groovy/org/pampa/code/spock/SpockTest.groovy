/**
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.pampa.code.spock

import org.pampa.code.TestCodeGenerator

/**
 * <p>
 * {@link TestCodeGenerator} for spock tests.
 * </p>
 *
 * @author Fernando Federico
 * @since 1.0
 */
class SpockTest extends TestCodeGenerator {

    /**
     * <p>
     * The package name of the resulting test
     * </p>
     */
    def destinationPackage


    @Override
    URL getTemplate() {
        return getClass().getClassLoader().getResource("templates/spockSpecificationTemplate.txt")
    }

    @Override
    protected validate() {
        super.validate()
        if (!destinationPackage)
            throw new IllegalArgumentException("Destination package must not be null")

        if (destinationPackage.contains(" "))
            throw new IllegalArgumentException("Destination package must not have spaces")
    }
}
