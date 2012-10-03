/**
 *
 */
package wlv.mt.features.impl.gb;

import wlv.mt.features.impl.Feature;
import wlv.mt.features.util.Sentence;
import wlv.mt.features.util.*;
import java.util.*;

/**
 * IBM phrase prior
 *
 * @author cat
 *
 */
public class Feature2028 extends Feature {

    public Feature2028() {
        setIndex("2028");
        HashSet<String> res = new HashSet<String>();
        res.add("phrasePrior");
        setResources(res);
    }

    /* (non-Javadoc)
     * @see wlv.mt.features.impl.Feature#run(wlv.mt.features.util.Sentence, wlv.mt.features.util.Sentence)
     */
    @Override
    public void run(Sentence source, Sentence target) {
        // TODO Auto-generated method stub
        Translation best = source.getBest();
        float value = Float.parseFloat(best.getAttribute("phrasePrior"));
        setValue(value);
    }
}
