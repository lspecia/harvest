/**
 *
 */
package wlv.mt.features.impl.bb;

import java.util.HashSet;
import java.util.StringTokenizer;

import wlv.mt.features.impl.Feature;
import wlv.mt.features.util.Sentence;
import wlv.mt.tools.*;

/**
 * average number of translations per source word in the sentence (threshold in
 * giza2: prob > 0.1)
 *
 * @author Catalina Hallett
 *
 *
 */
public class Feature1021 extends Feature {

    final static Float probThresh = 0.10f;

    public Feature1021() {
        setIndex(1021);
        setDescription("average number of translations per source word in the sentence (threshold in giza2: prob > 0.1)");
        HashSet res = new HashSet<String>();
        res.add("Giza2");
        setResources(res);
    }

    /* (non-Javadoc)
     * @see wlv.mt.features.util.Feature#run(wlv.mt.features.util.Sentence, wlv.mt.features.util.Sentence)
     */
    @Override
    public void run(Sentence source, Sentence target) {
        // TODO Auto-generated method stub
        float noTokens = source.getNoTokens();
        String[] tokens = source.getTokens();
        float probSum = 0;
        float value;
        for (String word : tokens) {
            value = Giza2.getWordProbabilityCount(word.toLowerCase(), probThresh);
            probSum += value;
        }

        float result = probSum / noTokens;

        setValue(result);
    }
}
