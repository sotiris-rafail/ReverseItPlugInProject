/**
 * Created by swthrhs on 14/12/2016.
 */
package Classes;


public class ClassForMethods {
    private String access;
    private String type;
    private String methodName;
    private String[] hasAttributes;
    private String whereBelongs;
    
	public ClassForMethods(String access, String type, String methodName, String[] hasAttributes, String whereBelongs) {
        this.type = type;
        this.whereBelongs = whereBelongs;
        this.access = access;
        this.hasAttributes = hasAttributes;
        this.methodName = methodName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWhereBelongs() {
        return whereBelongs;
    }

    public void setWhereBelongs(String whereBelongs) {
        this.whereBelongs = whereBelongs;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String[] isHasAttributes() {
        return hasAttributes;
    }

    public void setHasAttributes(String[]  hasAttributes) {
        this.hasAttributes = hasAttributes;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }


    @Override
    public String toString() {
    	
        return "ClassForMethods{" +
                "access='" + access + '\'' +
                ", type='" + type + '\'' +
                ", methodName='" + methodName + '\'' +
                ", hasAttributes=" + showAttributes(hasAttributes) +
                ", whereBelongs='" + whereBelongs + '\'' +
                '}';
    }
    public String showAttributes(String[] table){
    	String stringToShow = "null";
    	if(!(table == null)){
    		stringToShow = "";
    		for(int i = 0;i<table.length;i++){
    			stringToShow += table[i].toString() + ""; 
    		}
    	}
    	return stringToShow;
    }
}
