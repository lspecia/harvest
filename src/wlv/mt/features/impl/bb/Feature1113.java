/**
 *
 */
package wlv.mt.features.impl.bb;

import java.util.HashSet;

import wlv.mt.features.impl.Feature;
import wlv.mt.features.util.Sentence;

/**
 * absolute difference between the number of ADVP phrases in the source and
 * target normalised by the total number of phrasal tags in the sentence
 *
 * @author Catalina Hallett
 *
 *
 */
public class Feature1113 extends Feature {

    public Feature1113() {
        setIndex("1113");
        setDescription("absolute difference between the number of ADVP phrases in the source and target normalised by the total number of phrasal tags in the sentence");
        HashSet res = new HashSet();
        //requires named entities
        res.add("stf");
        setResources(res);
    }

    /* (non-Javadoc)
     * @see wlv.mt.features.impl.Feature#run(wlv.mt.features.util.Sentence, wlv.mt.features.util.Sentence)
     */
    @Override
    public void run(Sentence source, Sentence target) {
        float sourcePP = (Integer) source.getValue("ADVP");
        float phrasesSource = (Integer) source.getValue("phrase_tags");
        float targetPP = (Integer) target.getValue("ADVP");
        float phrasesTarget = (Integer) source.getValue("phrase_tags");

        float sourceNorm = phrasesSource == 0 ? 0 : sourcePP / phrasesSource;
        float targetNorm = phrasesTarget == 0 ? 0 : targetPP / phrasesTarget;
        setValue(Math.abs(sourceNorm - targetNorm));

    }
}
