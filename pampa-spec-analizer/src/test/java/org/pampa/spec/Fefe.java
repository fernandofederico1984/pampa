package org.pampa.spec;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.parser.Parse;

/**
 *
 */
public class Fefe
{

    private ChunkerME chunker;

    public Fefe()
    {
        InputStream modelIn = null;

        try {
            modelIn = getClass().getClassLoader().getResourceAsStream("en-chunker.bin");
            ChunkerModel model = new ChunkerModel(modelIn);
            chunker = new ChunkerME(model);
        } catch (IOException e) {
            // Model loading failed, handle the error
            e.printStackTrace();
        } finally {
            if (modelIn != null) {
                try {
                    modelIn.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public String[] chunk( String sent[],  String pos[] ){
        return chunker.chunk(sent, pos);
    }
    public void show(Parse sentence){
        List<String> tokens = new LinkedList<String>();
        List<String> tags = new LinkedList<String>();

        getValues(tokens, tags, sentence);


        String sent[] = tokens.toArray(new String[0]);
        String pos[] =tags.toArray(new String[0]);



        String[] chunks = chunker.chunk(sent, pos);

        for (int i=0 ; i<chunks.length; i++){
            System.out.print(sent[i] + "(" + chunks[i] + ") -- ");
        }

        System.out.println("");
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
