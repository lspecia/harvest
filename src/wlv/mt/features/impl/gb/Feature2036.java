/**
 *
 */
package wlv.mt.features.impl.gb;

import wlv.mt.features.impl.Feature;
import wlv.mt.features.util.Sentence;
import wlv.mt.features.util.*;
import java.util.*;

/**
 * IBM hole_wt
 *
 * @author cat
 *
 */
public class Feature2036 extends Feature {

    public Feature2036() {
        setIndex("2036");
        HashSet<String> res = new HashSet<String>();
        res.add("hole_wt");
        setResources(res);
    }

    /* (non-Javadoc)
     * @see wlv.mt.features.impl.Feature#run(wlv.mt.features.util.Sentence, wlv.mt.features.util.Sentence)
     */
    @Override
    public void run(Sentence source, Sentence target) {
        // TODO Auto-generated method stub
        Translation best = source.getBest();
        float value = Float.parseFloat(best.getAttribute("hole_wt"));
        setValue(value);
    }
}
