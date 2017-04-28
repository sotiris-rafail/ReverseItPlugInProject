package handlers;

public class SearchingForAttributes {

    public String[] findAttributes(String string){
    	
    	//ClassForClass classobj = new ClassForClass();
    	String[] attributesOfMethod = {};
    	String[] splitDot = {};
    	//String[] splitClosingPar = {};
    	String[] splitSpace = {};
	    String[] extractAttributes = string.split("\\(");
	    String[] removeParethesis = extractAttributes[1].split("\\)");
	    if(!(removeParethesis.length == 0)){
	    	attributesOfMethod = removeParethesis[0].split(",");
	    	//splitDot = attributesOfMethod[0].split("\\.");
	    	//splitClosingPar = splitDot[0].split("\\)");
	    	if(attributesOfMethod.length > 1) {
	    		for(int i = 0; i < attributesOfMethod.length; i++) {
	    			splitDot = attributesOfMethod[1].split("\\.");
	    			if(splitDot.length > 1) {
	    				System.out.println("splitDot1 : " + splitDot[1]);
	    				splitSpace = splitDot[1].split("\\ ");
	    				if(splitSpace.length != 0) {
	    					for(int x = 0; x < splitSpace.length; x++) {
	    						System.out.println("splitSpace" + x + " : " + splitSpace[x]);
	    					}
	    					//System.out.println("spltSpace length : " + splitSpace.length);
	    				}
	    			}
	    		}
	    	}
	    	//System.out.println("attributesOfMethod length : " + attributesOfMethod.length);
	    	//System.out.println("splitClosingPar0 : " + splitClosingPar[1]);
	    	/*if(attributesOfMethod != null) {
	    		if(attributesOfMethod.length > 1) {
	    			System.out.println("attributesOfMethod0 : " + attributesOfMethod[0] 
	    					+ " attributesOfMethod1 : " + attributesOfMethod[1]);
	    			for(int i = 0; i < attributesOfMethod.length; i++) {
	    				System.out.println("mphke");
	    				newAttrOfMeth = attributesOfMethod[i].substring(attributesOfMethod[i].lastIndexOf('.') + 1);			
	    				System.out.println("Xwris teleia : " + newAttrOfMeth);
	    			}
	    		}
	    	}*/
	    }
	    if (attributesOfMethod.length == 0) {
	    	attributesOfMethod = null;
	    }
	    if(splitDot.length == 0) {
	    	splitDot = null;
	    }
	    if(splitSpace.length == 0) {
	    	splitSpace = null;
	    }
        return attributesOfMethod;
    }
}
