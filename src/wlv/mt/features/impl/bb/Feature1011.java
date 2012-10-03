package wlv.mt.features.impl.bb;

import java.util.HashSet;

import wlv.mt.features.impl.Feature;
import wlv.mt.features.util.Sentence;

/**
 *
 * source sentence perplexity without end of sentence marker
 */
public class Feature1011 extends Feature {

    public Feature1011() {
        setIndex(1011);
        setDescription("source sentence perplexity without end of sentence marker");
        HashSet res = new HashSet<String>();
        res.add("ppl");
        setResources(res);
    }

    @Override
    public void run(Sentence source, Sentence target) {
        // TODO Auto-generated method stub
        setValue((Float) source.getValue("ppl1"));
    }
}
