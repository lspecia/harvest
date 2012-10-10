package wlv.mt.tools;

import java.util.Map;
import java.util.Iterator;

import wlv.mt.features.util.Sentence;
import wlv.mt.tools.BParser;
import wlv.mt.util.PropertiesManager;

/**
 * A processor class for the BParser. It loads the 
 * Parser instances, runs those through the sentences 
 * and provides the features
 * 
 * @author Eleftherios Avramids
 *
 */
public class BParserProcessor extends ResourceProcessor {
	
	BParser parser;
	boolean tokenizer;
	
	public void initializeFromProperties(String inputFile, PropertiesManager rm, String language){
		String grammarFilename = rm.getString(language + ".bparser.grammar");
		parser = new BParser(grammarFilename, (language == "chinese"));
		tokenizer = false;
	}
	
	@Override
	public void processNextSentence(Sentence s) {
		parser.getParseFeatures(s.getText(), tokenizer);
		String parseTree = parser.getParseTree();
		s.addParse(parseTree);
		
		s.setValue("bparser.avgConfidence", parser.getAvgConfidence());
		s.setValue("bparser.bestParseConfidence", parser.getBestParseConfidence());
		s.setValue("bparser.n", parser.getParseTreesN());
		s.setValue("bparser.loglikelihood", parser.getLoglikelihood());
	}
	
	
}
