package wlv.mt.features.impl.bb;

import java.util.ArrayList;
import java.util.List;

import wlv.mt.features.impl.Feature;
import wlv.mt.features.util.*;

/**
 * absolute difference between number of : in source and target
 *
 * @author Catalina Hallett
 *
 */
public class Feature1066 extends Feature {

    public Feature1066() {
        setIndex(1066);
        setDescription("absolute difference between number of : in source and target");
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


        setValue(Math.abs(sourceCount - targetCount));
    }
}