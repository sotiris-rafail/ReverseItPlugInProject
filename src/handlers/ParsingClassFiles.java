package handlers;

import java.util.ArrayList;
import java.util.List;

import org.apache.bcel.classfile.*;
import Classes.ClassForAttributes;
import Classes.ClassForClass;
import Classes.ClassForMethods;
import umlclassdiagrams.UMLClassDrawer;

public class ParsingClassFiles {
	public List<ClassForAttributes> attriList = new ArrayList<>();
	public List<ClassForMethods> methodList = new ArrayList<>();
	public ClassForClass classobj;
	public ClassForMethods methodobj;
	public ClassForAttributes attriobj;
	public SearchingForAttributes sfa;
	public UMLClassDrawer ucd;
	
	public void ParsingListwithClassFiles(String path) {
		
		try {
			ClassParser cp = new ClassParser(path);
			sfa = new SearchingForAttributes();
			cp = new ClassParser(path);
			ucd = new UMLClassDrawer();
			//Check If the Class on path is a Class
			if(cp.parse().isClass()) {
				classobj = new ClassForClass(true,false,false);
				cp = new ClassParser(path);
				String className = cp.parse().getClassName();
				String simpleClassName = className.substring(className.lastIndexOf('.') + 1);
				classobj.setClassName(simpleClassName);
				cp = new ClassParser(path);
				//Check if the class on the path has a SuperClass
				String superclassname = cp.parse().getSuperclassName();
				if(!(superclassname.equals("java.lang.Object"))) {
					String simpleSuperClassName = superclassname.substring(superclassname.lastIndexOf('.') + 1);
					classobj.setWhatExtends(simpleSuperClassName);
					//System.out.println(simpleSuperClassName);
					if(!(superclassname.isEmpty())){
						classobj.setDoesExtention(true);
					}
				}
				cp = new ClassParser(path);
				//Check if the class on the path implements an Interface
				String[] interfaceTable = cp.parse().getInterfaceNames();
				List<String> interfaces = new ArrayList<>();
				for(int i = 0; i < interfaceTable.length; i++) {
					String simpleIntefaceName = interfaceTable[i].substring(interfaceTable[i].lastIndexOf('.') + 1);
					//System.out.println(simpleIntefaceName);
					interfaces.add(simpleIntefaceName);
				}
				classobj.setWhichInterface(interfaces);
				if(!(interfaces.isEmpty())){
					classobj.setDoesImplementation(true);
				}
				//System.out.println(classobj.toString1());
				//The attributes of each class
				//System.out.println("Attributes on Class");
				Field fields[] = new Field[1000];
				cp = new ClassParser(path);
				fields = cp.parse().getFields();
				for(int i = 0; i < fields.length; i++) {
					
					String string2 = fields[i].toString();
					String[] parts2 = string2.split(" "); 
					attriobj = new ClassForAttributes(parts2[0],parts2[1],parts2[2]);
					attriList.add(attriobj);
					//System.out.println(attriobj.toString());
				}
				//The methods of each class
				cp = new ClassParser(path);
				Method meth[] = new Method[1000];
				meth = cp.parse().getMethods();
				//System.out.println("Methods on Class");
				for(int i = 0; i < meth.length; i++) {
					String string = meth[i].toString();
					String[] parts = string.split(" ");
					String[] attributesFromMethods = sfa.findAttributes(meth[i].toString());
					if(parts[0].equalsIgnoreCase("public") || parts[0].equalsIgnoreCase("protected") || parts[0].equalsIgnoreCase("private") || parts[0].equalsIgnoreCase("static")) {
						methodobj = new ClassForMethods(parts[0],parts[1],meth[i].getName(),attributesFromMethods,simpleClassName);
						methodList.add(methodobj);
						//System.out.println(methodobj.toString());	
					}
				}
			}
			//Check if the class on the path is an Interface
			cp = new ClassParser(path);
			if(cp.parse().isInterface()){
				classobj = new ClassForClass(true);
				cp = new ClassParser(path);
				String className = cp.parse().getClassName();
				String simpleClassName = className.substring(className.lastIndexOf('.') + 1);
				classobj.setClassName(simpleClassName);
				//System.out.println(classobj.toString2());
			}
			//Check if the class on the path is an Abstract Class
			cp = new ClassParser(path);
			if(cp.parse().isAbstract()){
				classobj = new ClassForClass(true,"nothing");
				cp = new ClassParser(path);
				String className = cp.parse().getClassName();
				String simpleClassName = className.substring(className.lastIndexOf('.') + 1);
				classobj.setClassName(simpleClassName);
				//System.out.println(classobj.toString3());
			}
			ucd.buildTable(classobj, methodList, attriList);
		}catch(Exception e) {
			
			System.out.println(e.getMessage());
			System.out.println(e.getCause().toString());
			System.exit(0);
		}
	}
	public ClassForClass getObjectForClasses() {

		return classobj;
	}
	public ClassForAttributes getObjectForAttributes() {
		
		return attriobj;
	}
	public ClassForMethods getObjectForMethods() {
	
	return methodobj;
}
}