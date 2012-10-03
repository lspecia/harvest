/**
 *
 */
package wlv.mt.features.impl.bb;

import java.util.ArrayList;
import java.util.HashSet;
import wlv.mt.features.impl.*;
import wlv.mt.features.util.*;

/**
 * Named Entity feature: difference in number of ORGANIZATION entities in source
 * and target normalised by total number of person entities (max between english
 * and arabic)
 *
 * @author Catalina Hallett
 *
 *
 */
public class Feature1118 extends Feature {

    public Feature1118() {
        setIndex(1118);
        setDescription("Named Entity feature: difference in number of ORGANIZATION entities in source and target normalised by total number of person entities (max between english and arabic)");
        HashSet res = new HashSet();
        //requires named entities
        res.add("ner");
        setResources(res);
    }

    public void run(Sentence source, Sentence target) {
        int nerSource = (Integer) source.getValue("ner");
        int nerTarget = (Integer) target.getValue("ner");
        int orgSource = ((ArrayList<String>) source.getValue("organization")).size();
        int orgTarget = ((ArrayList<String>) target.getValue("organization")).size();
//		System.out.println("FEATURE 1116: "+orgSource+" "+orgTarget);
        if (nerSource == 0 && nerTarget == 0) {
            setValue(0);
            return;
        }
        setValue((float) Math.abs(orgSource - orgTarget) / Math.max(nerSource, nerTarget));
    }
}
