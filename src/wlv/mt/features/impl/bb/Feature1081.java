/**
 * 
 */
package wlv.mt.features.impl.bb;

import java.util.StringTokenizer;

import wlv.mt.features.impl.*;
import wlv.mt.features.util.*;


/**
 * percentage of tokens in the target which do not contain only a-z
 * @author cat
 *   
 */
public class Feature1081 extends Feature{
	public Feature1081(){
		setIndex(1081);
		setDescription("percentage of tokens in the target which do not contain only a-z");
	}
	
	public void run(Sentence source, Sentence target) {
		// TODO Auto-generated method stub
		float noTokens;
		StringTokenizer st = new StringTokenizer(target.getText());
 
			noTokens = target.getNoTokens();
		String token;
		int count = 0;
		while (st.hasMoreTokens()){
			token = st.nextToken();
			if (StringOperations.isNoAlpha(token))			
				count++;
			
		}
		setValue((float)count/noTokens);
	}

}
