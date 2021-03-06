/**
 *
 */
package wlv.mt.features.impl.bb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import wlv.mt.features.impl.Feature;
import wlv.mt.features.util.Sentence;
import wlv.mt.tools.LanguageModel;

/**
 * average trigram frequency in quartile 3 of frequency (lower frequency words)
 * in the corpus of the source sentence
 *
 * @author Catalina Hallett
 *
 */
public class Feature1056 extends Feature {

    static int size = 3;
    static int quart = 3;

    public Feature1056() {
        setIndex(1056);
        setDescription("average trigram frequency in quartile 3 of frequency (lower frequency words) in the corpus of the source sentence");
        HashSet res = new HashSet<String>();
        res.add("ngramcount");
//		res.add(FeatureExtractor.getPosTagger());
//		res.add(FeatureExtractor.getGiza());

        setResources(res);
    }

    /* (non-Javadoc)
     * @see wlv.mt.features.impl.Feature#run(wlv.mt.features.util.Sentence, wlv.mt.features.util.Sentence)
     */
    @Override
    public void run(Sentence source, Sentence target) {
        // TODO Auto-generated method stub
        ArrayList<String> ngrams = source.getNGrams(size);
        Iterator<String> it = ngrams.iterator();
        String ngram;
        int count = 0;
        int freq;
        int totalFreq = 0;
        int cutOffLow = LanguageModel.getCutOff(size, quart - 1);
        int cutOffHigh = LanguageModel.getCutOff(size, quart);
        while (it.hasNext()) {
            ngram = it.next();
            freq = LanguageModel.getFreq(ngram, size);
            if (freq <= cutOffHigh && freq > cutOffLow) {
                count++;
            }
        }
        if (count == 0) {
            setValue(0);
        } else {
            setValue((float) count / ngrams.size());
        }
    }
}
