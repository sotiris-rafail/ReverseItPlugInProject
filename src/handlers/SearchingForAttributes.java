package handlers;

public class SearchingForAttributes {

    public String[] findAttributes(String string, boolean hasPackage){
    	
    	String[] attributesOfMethod = {};
    	if(!hasPackage){
    		attributesOfMethod = splitWIthoutDots(string);
    	}else{
    			attributesOfMethod = splitWIthDots(string);			
    	}
        return attributesOfMethod;
    }
    
    public String[] splitWIthoutDots(String string){
    	String[] extractAttributes = string.split("\\(");
  	    String[] removeParethesis = extractAttributes[1].split("\\)");
  	    String[] attributesOfMethod = {};
		if(!(removeParethesis.length == 0)){
  	    	attributesOfMethod  = removeParethesis[0].split(",");
  	    }
  	    if (attributesOfMethod.length == 0) {
  	    	attributesOfMethod = null;
  	    }
    	return attributesOfMethod;
    }
    
    public String[] splitWIthDots(String string){
    	String[] extractAttributes = string.split("\\(");
  	    String[] removeParethesis = extractAttributes[1].split("\\)");
  	    String[] attributesOfMethod = {};
		if(!(removeParethesis.length == 0)){
  	    	attributesOfMethod  = removeParethesis[0].split(",");
  	    }
  	    if (attributesOfMethod.length == 0) {
  	    	attributesOfMethod = null;
  	    }
  	    if(attributesOfMethod != null){
  	    	if(attributesOfMethod[0].equals("netbeans")){
  	    		attributesOfMethod = null;
  	    	}
  	    }
    	return attributesOfMethod;
    }
}
