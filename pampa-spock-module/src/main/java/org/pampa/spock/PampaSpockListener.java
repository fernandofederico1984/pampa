package org.pampa.spock;

import java.util.List;

import org.pampa.spec.parsed.ParsedScenario;
import org.pampa.spec.parser.ScenarioParser;
import org.spockframework.runtime.AbstractRunListener;
import org.spockframework.runtime.model.BlockInfo;
import org.spockframework.runtime.model.FeatureInfo;

/**
 *
 */
public class PampaSpockListener  extends AbstractRunListener
{



    @Override
    public void beforeFeature(FeatureInfo feature)
    {
        ScenarioParser scenarioParser =  new ScenarioParser(feature.getFeatureMethod().getName());
        List<BlockInfo> blocks = feature.getBlocks();
        for ( BlockInfo blockInfo : blocks){
            if ( "WHEN".equals(blockInfo.getKind().name()) ){
                if ( !blockInfo.getTexts().isEmpty() ){
                    scenarioParser.when(blockInfo.getTexts().get(0));
                    continue;
                }
            }
            else if ( "SETUP".equals(blockInfo.getKind().name())){
                if ( !blockInfo.getTexts().isEmpty() ){
                    scenarioParser.given(blockInfo.getTexts().get(0));
                    continue;
                }
            }
            else if ( "THEN".equals(blockInfo.getKind().name())){
                if ( !blockInfo.getTexts().isEmpty() ){
                    scenarioParser.then(blockInfo.getTexts().get(0));
                    continue;
                }
            }
            else if ( "AND".equals(blockInfo.getKind().name())){
                if ( !blockInfo.getTexts().isEmpty() ){
                    scenarioParser.and(blockInfo.getTexts().get(0));
                    continue;
                }
            }


        }

        ParsedScenario accept = scenarioParser.parse();

        showParsed(accept);

    }

    private void showParsed(ParsedScenario parsed)
    {
        System.out.println("I Got this:");

        System.out.println(parsed.toString());
    }
}
