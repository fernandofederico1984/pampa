package org.pampa.spec.parser;

import java.util.LinkedList;
import java.util.List;

import opennlp.tools.parser.Parse;
import org.junit.Test;
import org.pampa.spec.Fefe;
import org.pampa.spec.parsed.ParsedSentence;

/**
 *
 */
public class TextParserTest
{
   @Test
    public void fefe(){
       TextParser instance = TextParser.instance();
       List<ParsedSentence> parse = instance.parse(new ParsingContext(null), "Mule is started and connected with the Agent Console");

       parse.toString();

       List<String> tokens = new LinkedList<String>();
       List<String> tags = new LinkedList<String>();

       Parse sentence = parse.get(0).sentence;
       getValues(tokens, tags, sentence);


       String sent[] = tokens.toArray(new String[0]);
       String pos[] =tags.toArray(new String[0]);



       String[] chunks = new Fefe().chunk(sent, pos);
       for (int i=0;i<chunks.length;i++){
           System.out.println(sent[i] + "---" + chunks[i]);
       }
   }

    private void getValues(List<String> tokens, List<String> tags, Parse sentence)
    {
        if (  sentence.getChildren().length==1 && sentence.getChildren()[0].getType().equals("TK")){
            tokens.add(sentence.toString());
            tags.add(sentence.getType());
        }
        else{
            for ( opennlp.tools.parser.Parse parsed : sentence.getChildren() ){
                getValues(tokens, tags, parsed);
            }
        }
    }
}
