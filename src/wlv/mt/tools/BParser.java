package wlv.mt.tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.berkeley.nlp.PCFGLA.CoarseToFineMaxRuleParser;
import edu.berkeley.nlp.PCFGLA.CoarseToFineNBestParser;
import edu.berkeley.nlp.PCFGLA.Corpus;
import edu.berkeley.nlp.PCFGLA.Grammar;
import edu.berkeley.nlp.PCFGLA.Lexicon;
import edu.berkeley.nlp.PCFGLA.ParserData;
import edu.berkeley.nlp.PCFGLA.TreeAnnotations;
import edu.berkeley.nlp.io.PTBLineLexer;
import edu.berkeley.nlp.syntax.Tree;
import edu.berkeley.nlp.util.Numberer;

/**
 * A wrapper around Berkeley Parser
 * 
 * @author Eleftherios Avramidis
 */ 


public class BParser {
	PTBLineLexer tokenizer;
	Boolean tokenize;
	CoarseToFineMaxRuleParser parser;
	int kbest;
	
	public String parseTree;
	public double avgConfidence;
	public double bestParseConfidence;
	public int n;
	public double loglikelihood;
	
	
	/**
	 * It returns the parse tree of the last parsed sentence as a tree
	 * @return a string containing the parse tree
	 */
	public String getParseTree(){
		return this.parseTree;
	}
	
	public double getAvgConfidence(){
		return this.avgConfidence;
	}
	
	public double getBestParseConfidence(){
		return this.bestParseConfidence;
	}
	
	public int getParseTreesN(){
		return this.n;
	}
	
	public double getLoglikelihood(){
		return this.loglikelihood;
	}
	
	

	public BParser(String grammarFilename, boolean chinese){

		System.err.println("Loading grammar from file " + grammarFilename);
		ParserData pData = ParserData.Load(grammarFilename);		
		
		Grammar grammar = pData.getGrammar();
	    Lexicon lexicon = pData.getLexicon();
	    Numberer.setNumberers(pData.getNumbs());
	    
	    kbest = 1000;
	    //TODO:
	    if (chinese) Corpus.myTreebank = Corpus.TreeBankType.CHINESE;
	    double threshold = 1.0;
	    
	    //kbest parser
	    parser = new CoarseToFineNBestParser(grammar, lexicon, kbest ,threshold,-1, false , false , false , false, false, false, true);
	    parser.binarization = pData.getBinarization();
	    tokenizer = new PTBLineLexer();
	    System.err.print("BParser initialized\n");
	}
	
	
	
	
	public void getParseFeatures(String line){
		this.getParseFeatures(line, false);
	}
	
	public void getParseFeatures(String line, Boolean tokenize){
		System.out.println("Parsing... " +line);
		try {
			System.out.println ("parsing first string");
			List<String> sentence;
			
			if (!tokenize) sentence = Arrays.asList(line.split(" "));
			  else sentence = tokenizer.tokenizeLine(line);
					
			if (sentence.size()>=80)  
    			System.err.println("Skipping sentence with "+sentence.size()+" words since it is too long."); 
    		
			List<Tree<String>> parsedTrees = parser.getKBestConstrainedParses(sentence, null, kbest);	
			
			this.getNBestTreeFeatures(parsedTrees, parser);
			this.loglikelihood = this.getLogLikelihood();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error parsing");
		}
	}
	
	
	private void getNBestTreeFeatures(List<Tree<String>> parseTrees, CoarseToFineMaxRuleParser parser) {
		double sumConfidence = 0;
		double bestConfidence = Double.NEGATIVE_INFINITY;
		String bestParse = new String();
		
		for (Tree<String> parsedTree : parseTrees){
			
			if (! parsedTree.getChildren().isEmpty() ){
				parsedTree = TreeAnnotations.unAnnotateTree(parsedTree, false);
				double confidence = parser.getLogLikelihood(parsedTree);
				sumConfidence += confidence;
				if (confidence > bestConfidence){
					bestConfidence = confidence;
					bestParse = parsedTree.getChildren().get(0)+" )";
				}
			}
			
		}
		int n = parseTrees.size();
		double avgConfidence = sumConfidence / n;
		this.n = n;
		this.bestParseConfidence = bestConfidence;
		this.avgConfidence = avgConfidence;
		this.parseTree = bestParse;
	}
	

	
	public Double getLogLikelihood(){
		return parser.getLogLikelihood();
	}

}