/**
 *
 */
package wlv.mt.features.impl.gb;

import wlv.mt.features.impl.Feature;
import wlv.mt.features.util.Sentence;
import wlv.mt.features.util.*;
import java.util.*;

/**
 * CMU: distortion feature 7
 *
 * @author cat
 *
 */
public class Feature2020 extends Feature {

    public Feature2020() {
        setIndex(2020);
        setDescription("dist_feat_7");
        HashSet<String> res = new HashSet<String>();
        res.add("dist_feat_7");
        setResources(res);
    }

    /* (non-Javadoc)
     * @see wlv.mt.features.impl.Feature#run(wlv.mt.features.util.Sentence, wlv.mt.features.util.Sentence)
     */
    @Override
    public void run(Sentence source, Sentence target) {
        // TODO Auto-generated method stub
        float value = Float.parseFloat(source.getTranslationAttribute("dist_feat_7"));
        setValue(value);
    }
}
