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
 * average bigram frequency in quartile 1 of frequency (lower frequency words)
 * in the corpus of the source sentence
 *
 * @author Catalina Hallett
 *
 */
public class Feature1050 extends Feature {

    static int size = 2;
    static int quart = 1;

    public Feature1050() {
        setIndex(1050);
        setDescription("average bigram frequency in quartile 1 of frequency (lower frequency words) in the corpus of the source sentence");
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
        int cutOff = LanguageModel.getCutOff(size, quart);
        while (it.hasNext()) {
            ngram = it.next();
            freq = LanguageModel.getFreq(ngram, size);
            if (freq <= cutOff && freq > 0) {
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
