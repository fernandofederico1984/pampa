package org.pampa.spec.parser;

import java.util.List;

import org.junit.Test;
import org.pampa.spec.parsed.ParsedSentence;

/**
 *
 */
public class TextParserTest
{
   @Test
    public void fefe(){
       TextParser instance = TextParser.instance();
       List<ParsedSentence> parse = instance.parse(new ParsingContext(null), "The bus is big and very comfortable");

       System.out.println("WHO:" + parse.get(0).getWho());
       System.out.println("WHAT:" + parse.get(0).getWhat());
   }
}
