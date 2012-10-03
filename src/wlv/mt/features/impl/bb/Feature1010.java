package wlv.mt.features.impl.bb;

import java.util.HashSet;

import wlv.mt.features.impl.Feature;
import wlv.mt.features.util.Sentence;

/**
 * source sentence perplexity
 *
 * @author Catalina Hallett
 *
 */
public class Feature1010 extends Feature {

    public Feature1010() {
        setIndex(1010);
        setDescription("source sentence perplexity");
        HashSet res = new HashSet<String>();
        res.add("ppl");
    }

    @Override
    public void run(Sentence source, Sentence target) {
        // TODO Auto-generated method stub
        setValue((Float) source.getValue("ppl"));
    }
}
