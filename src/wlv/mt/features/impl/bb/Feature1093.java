/**
 *
 */
package wlv.mt.features.impl.bb;

import java.util.HashSet;

import wlv.mt.features.impl.Feature;
import wlv.mt.features.util.Sentence;

/**
 * ratio of percentage of verbs in the source and target
 *
 * @author cat
 *
 */
public class Feature1093 extends Feature {

    public Feature1093() {
        setIndex(1093);
        setDescription("ratio of percentage of verbs in the source and target");
        HashSet res = new HashSet();
        res.add("sourcePosTagger");
        res.add("targetPosTagger");
        setResources(res);
    }
    /* (non-Javadoc)
     * @see wlv.mt.features.impl.Feature#run(wlv.mt.features.util.Sentence, wlv.mt.features.util.Sentence)
     */

    @Override
    public void run(Sentence source, Sentence target) {
        // TODO Auto-generated method stub

        float noWords = source.getNoTokens();
        float noContent = (Integer) source.getValue("verbs");

        float perc1 = (float) noContent / noWords;
        noWords = target.getNoTokens();
        noContent = (Integer) target.getValue("verbs");

        float perc2 = (float) noContent / noWords;

        if (perc2 == 0) {
            setValue(0);
        } else {
            setValue(perc1 / perc2);
        }


    }
}
