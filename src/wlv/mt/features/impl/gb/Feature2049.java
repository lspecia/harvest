/**
 *
 */
package wlv.mt.features.impl.gb;

import wlv.mt.features.impl.Feature;
import wlv.mt.features.util.*;
import java.util.*;

/**
 * average size of hypotheses in the n-best list
 *
 * @author cat
 *
 */
public class Feature2049 extends Feature {

    public Feature2049() {
        setIndex("2049");
        setDescription("average size of hypotheses in the n-best list");
    }

    /* (non-Javadoc)
     * @see wlv.mt.features.impl.Feature#run(wlv.mt.features.util.Sentence, wlv.mt.features.util.Sentence)
     */
    @Override
    public void run(Sentence source, Sentence target) {
        // TODO Auto-generated method stub
        float nbestSize = (Integer) source.getValue("nbestSize");
        if (nbestSize == 0) {
            setValue(0);
        }
        int wordCount = 0;
        Iterator<Translation> it = source.getTranslations().iterator();
        while (it.hasNext()) {
            wordCount += Integer.parseInt(it.next().getAttribute("noTokens"));
        }
        setValue(wordCount / nbestSize);
    }
}
