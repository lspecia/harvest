/**
 *
 */
package wlv.mt.features.impl.bb;

import java.util.HashSet;
import java.util.StringTokenizer;

import wlv.mt.features.impl.Feature;
import wlv.mt.features.util.Sentence;
import wlv.mt.tools.Giza;
import wlv.mt.tools.Giza2;

/**
 * average number of translations per source word in the sentence (threshold in
 * giza1: prob > 0.5)
 *
 * @author Catalina Hallett
 *
 *
 */
public class Feature1024 extends Feature {

    final static Float probThresh = 0.50f;

    public Feature1024() {
        setIndex(1024);
        setDescription("average number of translations per source word in the sentence (threshold in giza1: prob > 0.5)");
        HashSet res = new HashSet<String>();
        res.add("Giza");
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
            value = Giza.getWordProbabilityCount(word.toLowerCase(), probThresh);
            probSum += value;
        }

        float result = probSum / noTokens;

        setValue(result);
    }
}
