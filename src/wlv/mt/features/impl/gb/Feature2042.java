package wlv.mt.features.impl.gb;

import wlv.mt.features.util.*;
import wlv.mt.features.impl.*;
import java.util.*;

/**
 * maximum size of the bi-phrases for the target sentence, in terms of number of
 * words
 *
 * @author Catalina Hallett
 *
 */
public class Feature2042 extends Feature {

    public Feature2042() {
        setIndex("2042");
        HashSet res = new HashSet<String>();
        res.add("phrases");
        setResources(res);

        setDescription("maximum size of the bi-phrases for the target sentence, in terms of number of words");
    }

    public void run(Sentence source, Sentence target) {
        TreeSet<Phrase> phrases = source.getPhrases();
        Iterator<Phrase> itPhrase = phrases.iterator();
        Phrase crtPhrase;
        int max = 0;
        while (itPhrase.hasNext()) {
            crtPhrase = itPhrase.next();
            String[] tokens = crtPhrase.getText().split(" ");
            if (max < tokens.length) {
                max = tokens.length;
            }
        }
        setValue(max);
    }
}
