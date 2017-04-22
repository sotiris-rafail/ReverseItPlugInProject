/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;
import java.util.ArrayList;
import java.util.List;

public class ClassForClass {
    
	private String className;
 	private boolean isClass = false;
    private boolean isInterface = false;
    private boolean isAbstract = false;
    private boolean doesExtention = false;
    private boolean doesImplementation = false;
    private List<String> whatExtends= new ArrayList<String>();
    private List<String> whichInterface=new ArrayList<String>();
    private String one = "no";
    private List<ClassForMethods> methods = new ArrayList<>();
    private List<ClassForAttributes> attris = new ArrayList<>();
    
    public ClassForClass(){}
	public ClassForClass(boolean isClass, boolean doesExtention, boolean doesImplementation) {
        
        this.isClass = true;
        this.isInterface = false;
        this.isAbstract = false;
        this.doesExtention = doesExtention;
        this.doesImplementation = doesImplementation;
        one = "yes";

    }
    public ClassForClass(boolean isInterface) {

        this.isClass = false;
        this.isInterface = isInterface;
        this.isAbstract = false;
    }
    public ClassForClass(boolean isAbstract,String nothing) {

        this.isClass = false;
        this.isInterface = false;
        this.isAbstract = isAbstract;
    }
    public void findTheTruth(String motherClass,String InterfaceImplementation){
        if(one.equals("yes")) {
            if (doesExtention) {
                whatExtends.add(motherClass);
            }
            if (doesImplementation) {
                whichInterface.add(InterfaceImplementation);
            }
        }
    }

    public String ForCLass() {
        return "ClassForClass{" +
        		"ClassName="+className+
                ", isClass=" + isClass +
                ", doesExtention=" + doesExtention +
                ", doesImplementation=" + doesImplementation +
                ", whatExtends=" + show(whatExtends) +
                ", whichInterface=" + whichInterface +"\n"+
                " Attributes="+ showAttri(attris) +"\n"+
                " Methods="+ showMethods(methods)+
                '}';
    }

    public String ForIntercface() {
        return "ClassForClass{" +
        		"ClassName="+className+
                ", isInterface=" + isInterface +"\n"+
                " Methods="+ showMethods(methods) +
                '}';
    }

    public String ForAbstract() {
        return "ClassForClass{" +
        		"ClassName="+className+
                ", isAbstract=" + isAbstract +"\n"+
                " Attributes="+ showAttri(attris) +"\n"+
                " Methods="+ showMethods(methods)+
                '}';
    }
    
    public String getClassName() {
  		return className;
  	}
  	public void setClassName(String className) {
  		this.className = className;
  	}

    public List<String> getWhichInterface() {
        return whichInterface;
    }

    public void setWhichInterface(List<String> whichInterface) {
        this.whichInterface = whichInterface;
    }

    public boolean isClass() {
        return isClass;
    }

    public void setClass(boolean aClass) {
        isClass = aClass;
    }

    public boolean isInterface() {
        return isInterface;
    }

    public void setInterface(boolean anInterface) {
        isInterface = anInterface;
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public void setAbstract(boolean anAbstract) {
        isAbstract = anAbstract;
    }

    public boolean isDoesExtention() {
        return doesExtention;
    }

    public void setDoesExtention(boolean doesExtention) {
        this.doesExtention = doesExtention;
    }

    public boolean isDoesImplementation() {
        return doesImplementation;
    }

    public void setDoesImplementation(boolean doesImplementation) {
        this.doesImplementation = doesImplementation;
    }

    public List<String> getWhatExtends() {
        return whatExtends;
    }

    public void setWhatExtends(String whatExtends) {
        this.whatExtends.add(whatExtends);
    }
    public List<ClassForMethods> getMethods() {
		return methods;
	}

	public void setMethods(List<ClassForMethods> methods) {
		this.methods = methods;
	}
    public List<ClassForAttributes> getAttris() {
		return attris;
	}

	public void setAttris(List<ClassForAttributes> attris) {
		this.attris = attris;
	}
    public String show(List<String> whatExtends){
    	String superClassNames= "null";
    	try{
    		if(!(whatExtends.isEmpty()) || (whatExtends != null)){
    			superClassNames = "";
    			for(String name: whatExtends){
    				superClassNames += name;
    			}
    		}
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    	}
    	return superClassNames;
    }
    public String showAttri(List<ClassForAttributes> whatExtends){
    	String superClassNames= "null";
    	try{
    		if(!(whatExtends.isEmpty()) || (whatExtends != null)){
    			superClassNames = "";
    			for(ClassForAttributes name: whatExtends){
    				superClassNames +=  name+"\n";
    			}
    		}
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    	}
    	return superClassNames;
    }
    public String showMethods(List<ClassForMethods> whatExtends){
    	String superClassNames= "null";
    	try{
    		if(!(whatExtends.isEmpty()) || (whatExtends != null)){
    			superClassNames = "";
    			for(ClassForMethods name: whatExtends){
    				superClassNames += name+"\n";
    			}
    		}
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    	}
    	return superClassNames;
    }
}
