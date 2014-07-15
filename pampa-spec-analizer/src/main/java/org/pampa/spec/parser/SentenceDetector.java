/*
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.pampa.spec.parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

/**
 * <p>
 * From a text it gets a list of sentences.
 * </p>
 *
 * @author Federico, Fernando
 * @since 1.0
 */
final class SentenceDetector
{

    public static final String SENTENCE_MODEL = "en-sent.bin";

    /**
     * <p>
     * The NLP sentence detector
     * </p>
     */
    private final SentenceDetectorME sentenceDetector;


    /**
     * <p>
     * Creates an instance of sentence detector, reads the model from the classpath
     * </p>
     *
     * @throws <p> {@link RuntimeException} in case the sentence model could not be found or closed.
     *             </p>
     */
    public SentenceDetector()
    {
        InputStream modelIn = null;
        try
        {
            modelIn = getClass().getClassLoader().getResourceAsStream(SENTENCE_MODEL);
            sentenceDetector = new SentenceDetectorME(new SentenceModel(modelIn));
        }
        catch (IOException e)
        {
            throw new RuntimeException("Sentence model could not be found");
        }
        finally
        {
            if (modelIn != null)
            {
                close(modelIn);
            }
        }
    }

    /**
     * @param text <p>
     *             The complete text
     *             </p>
     * @return <p>
     *         All the sentences of the text
     *         </p>
     */
    public List<String> parse(String text)
    {
        return Arrays.asList(sentenceDetector.sentDetect(text));
    }

    private void close(InputStream modelIn)
    {
        try
        {
            modelIn.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException("Model could not be closed");
        }
    }
}
