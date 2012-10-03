/**
 *
 */
package wlv.mt.features.impl.gb;

import wlv.mt.features.impl.Feature;
import wlv.mt.features.util.Sentence;
import wlv.mt.features.util.*;
import java.util.*;

/**
 * CMU: distortion feature 6
 *
 * @author cat
 *
 */
public class Feature2019 extends Feature {

    public Feature2019() {
        setIndex(2019);
        setDescription("dist_feat_6");
        HashSet<String> res = new HashSet<String>();
        res.add("dist_feat_6");
        setResources(res);
    }

    /* (non-Javadoc)
     * @see wlv.mt.features.impl.Feature#run(wlv.mt.features.util.Sentence, wlv.mt.features.util.Sentence)
     */
    @Override
    public void run(Sentence source, Sentence target) {
        // TODO Auto-generated method stub
        float value = Float.parseFloat(source.getTranslationAttribute("dist_feat_6"));
        setValue(value);
    }
}
