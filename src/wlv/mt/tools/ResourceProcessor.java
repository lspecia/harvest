/**
 *
 */
package wlv.mt.tools;

import java.util.ArrayList;
import java.util.List;

import wlv.mt.features.util.*;
import wlv.mt.util.PropertiesManager;

/**
 * Abstract class that is the base class for all classes that 
 * operate as (pre-)procesing tools. The task of such a sub-class is to
 * (a) Know the language it has been loaded with (optional)
 * (b) provide a function that constructs a ResourceProcessor 
 * 		from the config files parameters
 * (c) provide a function that processes a Sentence and augments
 * 		the Sentence.setvalue() with the results of the preprocessing
 * Notice: due to the dynamic calling of the processors, in fact they
 * have to be initialized within the initializeFromProperties
 *
 * @author Catalina Hallett, Eleftherios Avramidis
 *
 */
public abstract class ResourceProcessor {
	
	/**
	 * @param language: The language of this particular Processor
	 */
	protected String language;
	protected String input;
	
	/**
	 * Every ResourceProcessor sub-class should be able to read its 
	 * parameters from the configuration file. This is done by reading
	 * strings from the passed PropertiesManager
	 * @param language: The name of the language that this processor is responsible for
	 * 	(this helps with retrieving parameters)
	 * @param pm : A properties manager, which contains 
	 * 	the settings for the Resources
	 */
	public ResourceProcessor() {
		
	}
	public abstract void initializeFromProperties(String sourceFile, PropertiesManager resourceManager);
	public void initializeFromProperties(String sourceFile, PropertiesManager resourceManager, String input, String language ){
		this.language = language;
		this.input = input;
		this.initializeFromProperties(sourceFile, resourceManager);
	}
	/**
	 * It is possible that a ResourceProcessor has specific pre-processing
	 * dependencies. This is not implemented yet, but it should help
	 * ordering the processors (e.g. a parsers needs tokenized but non-truecased input
	 * whereas a language model scorer needs both)
	 * @return a list of the names of the processors that need to have been completed
	 * before this processor is started
	 */
	public List<String> getDependencies(){
		return new ArrayList<String>();
	}
	/**
	 * Every ResourceProcessor sub-class should be able to process 
	 * one sentence and set values on the sentence level.
	 * @param source
	 * @throws Exception
	 */
    public abstract void processNextSentence(Sentence source) throws Exception;
}
