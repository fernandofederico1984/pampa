/*
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.pampa.spec.parser;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import opennlp.tools.parser.Parse;
import org.pampa.spec.parsed.ParsedSentence;

/**
 * <p>
 * Process text in any form and returns a List of {@link opennlp.tools.parser.Parse} elements.
 * </p>
 * <p>
 * This Class:
 * <li>
 * Detects sentences in the text by using the {@link SentenceDetector}
 * </li>
 * <li>
 * Parses each sentence's tokens by using the {@link SentenceParser}
 * </li>
 * </p>
 *
 * @author Federico, Fernando
 * @since 1.0
 */
final class TextParser
{

    private static final TextParser instance = new TextParser();
    /**
     * <p>
     * The sentence detector to split the text into sentences
     * </p>
     */
    private final SentenceDetector sentenceDetector = new SentenceDetector();

    /**
     * <p>
     * Sentence Parser to tokenize and POS tag tokens in a the sentence
     * </p>
     */
    private final SentenceParser parser = new SentenceParser();

    public static TextParser instance()
    {
        return instance;
    }

    private TextParser(){

    }

    /**
     * @param text <p>
     *             The complete text to parse.
     *             </p>
     * @return <p>
     *         The text gets divided by sentence, each sentence is parsed and returned in the list.
     *         </p>
     */
    public List<Parse> findSentences(String text)
    {
        List<Parse> sentences = new ArrayList<Parse>();
        List<String> detect = sentenceDetector.parse(text);

        for (String detectedSentence : detect)
        {
            String[] texts = detectedSentence.split("\\sand\\s|\\sbut\\s|\\sor\\s");
            for ( String subSentence : texts ){
                Parse parse = parser.parse(subSentence);
                sentences.add(parse);

            }
        }
        return sentences;
    }

    public List<ParsedSentence> parse(ParsingContext context, String text)
    {
        List<ParsedSentence> parsedSentences = new ArrayList<ParsedSentence>();
        List<Parse> sentences = findSentences(text);

        for (Parse sentence : sentences)
        {
            Parse np = findNp(sentence);
            if (np == null)
            {
                np = context.getSubject();
            }
            else{
                Parse prp = findPRP(np);
                if (prp != null)
                {
                    np = context.getSubject();
                }
            }


            parsedSentences.add(new ParsedSentence(sentence, np, findVp(sentence)));
            context.setSubject(np);
        }

        return parsedSentences;
    }

    private Parse findNp(Parse parse)
    {
        for (Parse child : parse.getChildren())
        {
            String type = child.getType();
            child.show();
            if (type.equals("NP"))
            {
                return child;
            }
            else
            {
                if (!type.equals("VP") && !type.equals("PP"))
                {
                    Parse np = findNp(child);
                    if (np != null)
                    {
                        return np;
                    }
                }
            }

        }
        return null;
    }

   private Parse findTag(Parse root, String ... tags){
       for (Parse child : root.getChildren())
       {
           String type = child.getType();
           if (Arrays.asList(tags).contains(type))
           {
               return child;
           }

       }
       return null;
   }

    private Parse findPRP(Parse parse)
    {
        for (Parse child : parse.getChildren())
        {
            String type = child.getType();
            if (type.equals("PRP"))
            {
                return child;
            }

        }
        return null;
    }

    private Parse findVp(Parse parse)
    {
        for (Parse child : parse.getChildren())
        {
            String type = child.getType();
            if (type.equals("VP"))
            {
                return child;
            }
            else
            {
                if (!type.equals("NP") && !type.equals("PP"))
                {
                    Parse np = findVp(child);
                    if (np != null)
                    {
                        return np;
                    }
                }

            }

        }
        return null;
    }



}
