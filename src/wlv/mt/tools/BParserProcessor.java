package wlv.mt.tools;

import java.util.Map;
import java.util.Iterator;

import wlv.mt.features.util.Sentence;
import wlv.mt.tools.BParser;

/**
 * A processor class for the BParser. It loads the 
 * Parser instances, runs those through the sentences 
 * and provides the features
 * 
 * @author Eleftherios Avramids
 *
 */
public class BParserProcessor extends ResourceProcessor {
	
	static BParser parser;
	
	public BParserProcessor(String grammarFilename){
		parser = new BParser(grammarFilename);
	}

	@Override
	public void processNextSentence(Sentence s) {
		// Fetch results of parsing the sentence in a Hashmap   
		Map<String, String> parseResults = parser.getParseFeatures(s.getText(), false);
		Iterator<Map.Entry<String, String>> it = parseResults.entrySet().iterator();
		//TODO: Fix the entry of integers and doubles, now everything is passed through as a string
		while (it.hasNext()) {
	        Map.Entry<String, String> pairs = (Map.Entry<String, String>)it.next();
	        s.setValue(pairs.getKey(), pairs.getValue());
	        it.remove(); // avoids a ConcurrentModificationException
	    }
		
	}
	
	
}
