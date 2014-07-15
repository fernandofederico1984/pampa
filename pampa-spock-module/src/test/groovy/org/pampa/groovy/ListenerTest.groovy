package org.pampa.groovy

import org.pampa.spock.Pampa
import spock.lang.Specification

@Pampa
class ListenerTest extends Specification
{
    def "when mule gets bored he goes to sleep"(){
        given:"Mule worked until late"
        System.out.println("sleep")
        when: "mule is in a meeting"
        System.out.println("Hola")
        then: "it goes to sleep"
        true
    }
}
