/**
 *
 */
package wlv.mt.features.impl.bb;

import java.util.HashSet;

import wlv.mt.features.impl.Feature;
import wlv.mt.features.util.Sentence;

/**
 *
 * ratio of percentage of nouns in the source and target
 *
 * @author cat
 *
 */
public class Feature1092 extends Feature {

    public Feature1092() {
        setIndex(1092);
        setDescription("ratio of percentage of nouns in the source and target");
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
        float noContent = (Integer) source.getValue("nouns");
        float perc1 = (float) noContent / noWords;
        noWords = target.getNoTokens();
        noContent = (Integer) target.getValue("nouns");
        float perc2 = (float) noContent / noWords;
        if (perc2 == 0) {
            setValue(0);
        } else {
            setValue(perc1 / perc2);
        }


    }
}
