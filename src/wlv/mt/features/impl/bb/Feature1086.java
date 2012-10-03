package wlv.mt.features.impl.bb;

import java.util.HashSet;

import wlv.mt.features.impl.Feature;
import wlv.mt.features.util.Sentence;

/**
 * LM log probability of POS of the target
 *
 * @author Catalina Hallett
 *
 */
public class Feature1086 extends Feature {

    public Feature1086() {
        setIndex(1086);
        setDescription("LM log probability of POS of the target");
        HashSet res = new HashSet();
        res.add("poslogprob");
        setResources(res);
    }

    /* (non-Javadoc)
     * @see wlv.mt.features.impl.Feature#run(wlv.mt.features.util.Sentence, wlv.mt.features.util.Sentence)
     */
    @Override
    public void run(Sentence source, Sentence target) {
        // TODO Auto-generated method stub
        setValue((Float) target.getValue("poslogprob"));
    }
}
