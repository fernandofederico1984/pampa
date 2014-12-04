/*
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.pampa.spec.parser;

import opennlp.tools.parser.Parse;

/**
 *
 */
class ParsingContext
{
    private Parse subject;
    private Parse what;

    public ParsingContext(Parse subject)
    {
        this.subject = subject;
    }

    public Parse getSubject()
    {
        return subject;
    }

    public void setSubject(Parse subject)
    {
        this.subject = subject;
    }


    public Parse getWhat()
    {
        return what;
    }

    public void setWhat(Parse what)
    {
        this.what = what;
    }
}
