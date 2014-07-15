/*
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.pampa.spec.parser;

import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;

/**
 * <p>
 * Tokenizer and POS tagger of a sentence.
 * </p>
 * <p>
 * This parser receives a complete sentence as a text and returns the POS tagged tokens of that sentence.
 * </p>
 *
 * @author Federico, Fernando
 * @since 1.0
 */
final class SentenceParser
{

    /**
     * <p>
     * Location of the model used to perform chunking over the sentence
     * </p>
     */
    public static final String MODEL_LOCATION = "en-parser-chunking.bin";

    /**
     * <p>
     * NLP Parser that does chunking and POS tagging.
     * </p>
     */
    private final Parser parser;

    /**
     * <p>
     * Creates an instance of the parser. Reads the chunking model and initializes the NLP parser.
     * </p>
     *
     * @throws <p> {@link RuntimeException} in case the chunking model could not be found or closed.
     *             </p>
     */
    public SentenceParser()
    {
        {
            InputStream modelIn = null;
            try
            {
                modelIn = getClass().getClassLoader().getResourceAsStream(MODEL_LOCATION);
                ParserModel model = new ParserModel(modelIn);
                parser = ParserFactory.create(model);
            }
            catch (IOException e)
            {
                throw new RuntimeException("Chunking model could not be found");
            }
            finally
            {
                if (modelIn != null)
                {
                    close(modelIn);
                }
            }
        }
    }

    /**
     * <p>
     * Gets a sentence in a String format and returns a Parse sentence with all the POS tagged elements
     * </p>
     *
     * @param sentence <p>
     *                 The sentence to be parsed
     *                 </p>
     * @return <p>
     *         A Parse sentence
     *         </p>
     */
    public Parse parse(String sentence)
    {
        return ParserTool.parseLine(sentence, parser, 1)[0];
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
