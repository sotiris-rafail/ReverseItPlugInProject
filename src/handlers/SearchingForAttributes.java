package handlers;

public class SearchingForAttributes {

    public String[] findAttributes(String string){
	String[] attributesOfMethod = {};
	    String[] extractAttributes = string.split("\\(");
	    String[] removeParethesis = extractAttributes[1].split("\\)");
	    if(!(removeParethesis.length == 0)){
		attributesOfMethod = removeParethesis[0].split(",");
	    }
	    if (attributesOfMethod.length == 0) {
		attributesOfMethod = null;
	    }
        return attributesOfMethod;
    }
}
