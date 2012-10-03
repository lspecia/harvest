/**
 *
 */
package wlv.mt.features.impl.gb;

import wlv.mt.features.impl.Feature;
import wlv.mt.features.util.Sentence;
import wlv.mt.features.util.*;
import java.util.*;

/**
 * IBM dist_sc
 *
 * @author cat
 *
 */
public class Feature2029 extends Feature {

    public Feature2029() {
        setIndex("2029");
        HashSet<String> res = new HashSet<String>();
        res.add("dist_sc");
        setResources(res);
        setDescription("IBM dist_sc");
    }

    /* (non-Javadoc)
     * @see wlv.mt.features.impl.Feature#run(wlv.mt.features.util.Sentence, wlv.mt.features.util.Sentence)
     */
    @Override
    public void run(Sentence source, Sentence target) {
        // TODO Auto-generated method stub
        Translation best = source.getBest();
        float value = Float.parseFloat(best.getAttribute("dist_sc"));
        setValue(value);
    }
}
