/**
 *
 */
package wlv.mt.features.impl.bb;

import wlv.mt.features.impl.Feature;
import java.util.*;
import wlv.mt.features.util.Sentence;

/**
 *
 * percentage of content words in the source
 *
 * @author cat
 *
 */
public class Feature1083 extends Feature {

    public Feature1083() {
        setIndex(1083);
        setDescription("percentage of content words in the source");
        HashSet res = new HashSet();
        res.add("sourcePosTagger");
        setResources(res);
    }

    /* (non-Javadoc)
     * @see wlv.mt.features.impl.Feature#run(wlv.mt.features.util.Sentence, wlv.mt.features.util.Sentence)
     */
    @Override
    public void run(Sentence source, Sentence target) {
        // TODO Auto-generated method stub
        float noWords = source.getNoTokens();
        float noContent = (Integer) source.getValue("contentWords");
        setValue(noContent / noWords);
    }
}
