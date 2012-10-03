/**
 *
 */
package wlv.mt.features.impl.gb;

import wlv.mt.features.impl.Feature;
import wlv.mt.features.util.Sentence;
import wlv.mt.features.util.*;
import java.util.*;

/**
 * IBM constraint_block_wt
 *
 * @author cat
 *
 */
public class Feature2041 extends Feature {

    public Feature2041() {
        setIndex("2041");
        HashSet<String> res = new HashSet<String>();
        res.add("constraint_block_wt");
        setResources(res);
    }

    /* (non-Javadoc)
     * @see wlv.mt.features.impl.Feature#run(wlv.mt.features.util.Sentence, wlv.mt.features.util.Sentence)
     */
    @Override
    public void run(Sentence source, Sentence target) {
        // TODO Auto-generated method stub
        Translation best = source.getBest();
        float value = Float.parseFloat(best.getAttribute("constraint_block_wt"));
        setValue(value);
    }
}
