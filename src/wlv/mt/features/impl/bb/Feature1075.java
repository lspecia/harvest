package wlv.mt.features.impl.bb;

import java.util.ArrayList;
import java.util.List;

import wlv.mt.features.impl.Feature;
import wlv.mt.features.util.Sentence;

/**
 * percentage of punctuation marks in target
 *
 * @author Catalina Hallett
 *
 */
public class Feature1075 extends Feature {

    public Feature1075() {
        setIndex(1075);
        setDescription("percentage of punctuation marks in target");
    }
    
    @Override
    public List<String> getRequiredProcessors(){
    	List<String> processorNames = super.getRequiredProcessors();
    	processorNames.add("POSProcessor");
    	return processorNames;
    }

    public void run(Sentence source, Sentence target) {

        int countT = 0;
        if (target.isSet("count_.")) {
            countT += (Integer) target.getValue("count_.");
        } else {
            countT += target.countChar('.');
        }
        if (target.isSet("count_,")) {
            countT += (Integer) target.getValue("count_,");
        } else {
            countT += target.countChar(',');
        }
        if (target.isSet("count_؟")) {
            countT += (Integer) target.getValue("count_؟");
        } else {
            countT += target.countChar('؟');
        }
        if (target.isSet("count_¿")) {
            countT += (Integer) target.getValue("count_¿");
        } else {
            countT += target.countChar('¿');
        }
        if (target.isSet("count_،")) {
            countT += (Integer) target.getValue("count_،");
        } else {
            countT += target.countChar('،');
        }
        if (target.isSet("count_؛")) {
            countT += (Integer) target.getValue("count_؛");
        } else {
            countT += target.countChar('؛');
        }
        if (target.isSet("count_¡")) {
            countT += (Integer) target.getValue("count_¡");
        } else {
            countT += target.countChar('¡');
        }
        if (target.isSet("count_!")) {
            countT += (Integer) target.getValue("count_!");
        } else {
            countT += target.countChar('!');
        }
        if (target.isSet("count_?")) {
            countT += (Integer) target.getValue("count_?");
        } else {
            countT += target.countChar('?');
        }
        if (target.isSet("count_:")) {
            countT += (Integer) target.getValue("count_:");
        } else {
            countT += target.countChar(':');
        }
        if (target.isSet("count_;")) {
            countT += (Integer) target.getValue("count_;");
        } else {
            countT += target.countChar(';');
        }


        float noTokensTarget = 1;
        if (target.isSet("noTokens")) {
            noTokensTarget = target.getNoTokens();
        }

        target.setValue("noPunct", countT);
        setValue(countT / noTokensTarget);



    }
}