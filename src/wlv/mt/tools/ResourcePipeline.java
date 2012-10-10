/**
 *
 */
package wlv.mt.tools;

import java.util.*;
import wlv.mt.features.util.*;
import wlv.mt.util.PropertiesManager;

/**
 * @author Catalina Hallett
 *
 */
public class ResourcePipeline {

    private ArrayList<ResourceProcessor> resources;

    public ResourcePipeline() {
        resources = new ArrayList<ResourceProcessor>();
    }
    
    public void add(ResourceProcessor proc) {
        resources.add(proc);
    }

    public void addResourceProcessor(ResourceProcessor proc) {
        resources.add(proc);
    }
    
    /**
     * Allows each one of the processors in the pipeline to run
     * once in the beginning of the dataset (e.g. to load grammars 
     * or do the full processing and dump it into a file)
     * @param resourceManager an object that manages all the configuration
     * 	parameters
     */
    public void initialize(String sourceFile, PropertiesManager resourceManager, String input, String language){
    	for (ResourceProcessor resource:resources){
    		resource.initializeFromProperties(sourceFile, resourceManager, input, language);    		
    	}
 
    }

    public void processSentence(Sentence sent) throws Exception {
        Iterator<ResourceProcessor> it = resources.iterator();
        while (it.hasNext()) {
            it.next().processNextSentence(sent);
        }
    }
}
