package wlv.mt.features.impl.bb;

import java.util.ArrayList;
import java.util.List;

import wlv.mt.features.impl.Feature;
import wlv.mt.features.util.Sentence;

/**
 *
 * absolute difference between number of : in source and target normalised by
 * target length
 *
 * @author Catalina Hallett
 *
 */
public class Feature1067 extends Feature {

    public Feature1067() {
        setIndex(1067);
        setDescription("absolute difference between number of : in source and target normalised by target length");
    }
    
    @Override
    public List<String> getRequiredProcessors(){
    	List<String> processorNames = super.getRequiredProcessors();
    	processorNames.add("POSProcessor");
    	return processorNames;
    }

    public void run(Sentence source, Sentence target) {
        float sourceCount;
        float targetCount;
        if (!source.isSet("count_:")) {
            sourceCount = source.countChar(':');
        } else {
            sourceCount = (Integer) source.getValue("count_:");
        }

        if (!target.isSet("count_:")) {
            targetCount = target.countChar(':');
        } else {
            targetCount = (Integer) target.getValue("count_:");
        }


        float noTokensSource = 1;
        float noTokensTarget = 1;
        if (source.isSet("noTokens")) {
            noTokensSource = source.getNoTokens();
        }
        if (target.isSet("noTokens")) {
            noTokensTarget = target.getNoTokens();
        }

        setValue(Math.abs(sourceCount - targetCount) / noTokensTarget);
    }
}
