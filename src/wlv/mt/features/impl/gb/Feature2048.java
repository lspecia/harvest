/**
 *
 */
package wlv.mt.features.impl.gb;

import wlv.mt.features.impl.Feature;
import wlv.mt.features.util.Sentence;
import wlv.mt.features.util.*;
import java.util.*;

/**
 * ratio of log prob (base model score) of the current hypothesis to the sum of
 * the log prob score of all hypothesis
 *
 * @author cat
 *
 */
public class Feature2048 extends Feature {

    public Feature2048() {
        setIndex("2048");
        HashSet<String> res = new HashSet<String>();
//		res.add("log_prob_feat");
        setResources(res);
        setDescription("ratio of log prob (base model score) of the current hypothesis to the sum of the log prob score of all hypothesis ");
    }

    /* (non-Javadoc)
     * @see wlv.mt.features.impl.Feature#run(wlv.mt.features.util.Sentence, wlv.mt.features.util.Sentence)
     */
    @Override
    public void run(Sentence source, Sentence target) {
        // TODO Auto-generated method stub
        Translation best = source.getBest();
//		float value = Float.parseFloat(best.getAttribute("log_prob_feat"));
        float value = Float.parseFloat(best.getAttribute("prob"));
        Iterator<Translation> it = source.getTranslations().iterator();
        float logProbSum = 0;
        while (it.hasNext()) {
            //		logProbSum+=Float.parseFloat(it.next().getAttribute("log_prob_feat"));
            logProbSum += Float.parseFloat(it.next().getAttribute("prob"));
        }

        if (logProbSum == 0) {
            setValue(0);
        } else {
            setValue(value / logProbSum);
        }
    }
}
