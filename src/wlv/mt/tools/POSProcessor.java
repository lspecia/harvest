package wlv.mt.tools;

import java.io.*;

import wlv.mt.features.util.*;
import wlv.mt.util.PropertiesManager;

/**
 * The POSProcessor class analyses a file produced by a pos tagger and sets
 * certain values to a Sentence object These values are, currently, the number
 * of nouns, verbs, pronouns and content words
 *
 */
public class POSProcessor extends ResourceProcessor {

    BufferedReader br;
    int sentCount;
//    static String XPOS=".XPOS";
//    BufferedWriter bwXPos;

    /**
     * initialise a POSProcessor from an input file The POSProcessor expect an
     * input file in a fixed format, where each line is of the type:<br> <i>word
     * DT	word</i> (tokens separated by tab) <br>
     *
     *
     * @param input the input file
     *
     */
    
    @Override
    public void initializeFromProperties(String sourceFile, PropertiesManager resourceManager) {
    	outputFile = runPOS(resourceManager, sourceFile, "source");
    	
    }
    
    
//    public void runPOSTagger() {
//        // required by BB features 65-69, 75-80
//        String sourceOutput = runPOS(sourceFile, sourceLang, "source");
//        String targetOutput = runPOS(targetFile, targetLang, "target");
//
//    }

    /**
     * runs the part of speech tagger
     *
     * @param file input file
     * @param lang language
     * @param type source or target
     * @return path to the output file of the POS tagger
     */
    public String runPOS(PropertiesManager resourceManager, String file, String type) {
    	String lang = language;
        String posName = resourceManager.getString(lang + ".postagger");
        String langResPath = input + File.separator + lang;
        File f = new File(file);
        String absoluteSourceFilePath = f.getAbsolutePath();
        String fileName = f.getName();
        String relativeFilePath = langResPath + File.separator + fileName
                + ".pos";
        String absoluteOutputFilePath = (new File(relativeFilePath))
                .getAbsolutePath();
        String posSourceTaggerPath = resourceManager.getString(lang
                + ".postagger.exePath");
        String outPath = "";
        try {
            Class<PosTagger> c = (Class<PosTagger>) Class.forName(posName, false, ClassLoader.getSystemClassLoader());
            PosTagger tagger = c.newInstance();
            tagger.setParameters(type, posName, posSourceTaggerPath,
                    absoluteSourceFilePath, absoluteOutputFilePath);
            //TODO: fix forceRun
            PosTagger.ForceRun(true);
            outPath = tagger.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // returns the path of the output file; this is for convenience only so
        // we do't have to calculate it again
        return outPath;

    }

    
    public void initialize(String input) {
        try {
            System.out.println("INPUT TO POSPROCESSOR:" + input);
            br = new BufferedReader(new FileReader(input));
//			bwXPos = new BufferedWriter(new FileWriter(input+getXPOS()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        sentCount = 0;
    }

    /**
     * Reads the pos tags associated to a sentence and counts the number of
     * content words The count for each type of content word is addedd as a
     * value to the sentence
     *
     * @see Sentence.setValue()
     * @param sent the sentence to be analysed
     */
    public void processNextSentence(Sentence sent) throws Exception {
        int tokCount = sent.getNoTokens();
        String line = br.readLine();
        int contentWords = 0;
        int nounWords = 0;
        int verbWords = 0;
        int pronWords = 0;
        int otherContentWords = 0;
        int count = 0;

        while (line != null && (count < tokCount)) {
            if (!line.trim().isEmpty()) {
                String[] split = line.split("\t");
                String tag = split[1];
                if (tag.contains("SENT")) {
                    tag = tag.split(" ")[0];
                } else if (PosTagger.isNoun(tag)) {
                    nounWords++;
//					System.out.println("is noun");
                } else if (PosTagger.isPronoun(tag)) {
                    pronWords++;
                } else if (PosTagger.isVerb(tag)) {
                    verbWords++;
                    //					System.out.println("is verb");
                } else if (PosTagger.isAdditional(tag)) {
                    otherContentWords++;
                }
                //    	  	bwXPos.write(tag);
                count++;
            }
            line = br.readLine();
        }
        //   bwXPos.newLine();
        contentWords = nounWords + verbWords + otherContentWords;
        sent.setValue("contentWords", contentWords);
        sent.setValue("nouns", nounWords);
        sent.setValue("verbs", verbWords);
        sent.setValue("prons", pronWords);
//        line = br.readLine();
//        if (line==null) {
//           	System.out.println("SENTENCE IS NULL: "+sent.getIndex()+"\t"+sent.getText());
//        	br.close();
        // 	bwXPos.close();
//        }
    }
    /*      public static String getXPOS(){
     return XPOS;
     }*/




}
