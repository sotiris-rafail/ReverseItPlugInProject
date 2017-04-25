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
	String className = "";
	String simpleClassName;
	
	public ClassForClass ParsingListwithClassFiles(String path) {
		
		try {
			ClassParser cp = new ClassParser(path);
			sfa = new SearchingForAttributes();
			cp = new ClassParser(path);
			ucd = new UMLClassDrawer();
			//Check If the Class on path is a Class
			if(cp.parse().isClass()) {
				classobj = new ClassForClass(true,false,false);
				cp = new ClassParser(path);
				className = cp.parse().getClassName();
				simpleClassName = className.substring(className.lastIndexOf('.') + 1);
				classobj.setClassName(simpleClassName);
				cp = new ClassParser(path);
				classobj.setPackageName(cp.parse().getPackageName());
				cp = new ClassParser(path);
				//Check if the class on the path has a SuperClass
				String superclassname = cp.parse().getSuperclassName();
				if(!(superclassname.equals("java.lang.Object"))) {
					String simpleSuperClassName = superclassname.substring(superclassname.lastIndexOf('.') + 1);
					classobj.setWhatExtends(simpleSuperClassName);
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
					interfaces.add(simpleIntefaceName);
				}
				classobj.setWhichInterface(interfaces);
				if(!(interfaces.isEmpty())){
					classobj.setDoesImplementation(true);
				}
				//The attributes of each class
				Field fields[] = new Field[1000];
				cp = new ClassParser(path);
				fields = cp.parse().getFields();
				for(int i = 0; i < fields.length; i++) {
					String string2 = fields[i].toString();
					String[] parts2 = string2.split(" "); 
					if(!(classobj.getPackageName().equals(""))){
						String[] attriType = parts2[1].split("\\.");
						attriobj = new ClassForAttributes(parts2[0],attriType[attriType.length-1],parts2[2]);
					}else{
						attriobj = new ClassForAttributes(parts2[0],parts2[1],parts2[2]);
					}
					attriList.add(attriobj);
				}
				//The methods of each class
				cp = new ClassParser(path);
				Method meth[] = new Method[1000];
				meth = cp.parse().getMethods();
				for(int i = 0; i < meth.length; i++) {
					String string = meth[i].toString();
					String[] parts = string.split(" ");
					String[] attributesFromMethods = sfa.findAttributes(meth[i].toString());
					if(attributesFromMethods != null){
						for(int x =0;x<attributesFromMethods.length;x++){
							if(!(classobj.getPackageName().equals(""))){
								String[] asd = attributesFromMethods[x].split("\\.");
								if(asd.length > 1)
									System.out.println("asd0"+asd[0]+" asd1"+asd[1]);
							}
						}
					}
					if(parts[0].equalsIgnoreCase("public") || parts[0].equalsIgnoreCase("protected") || parts[0].equalsIgnoreCase("private") || parts[0].equalsIgnoreCase("static")) {
						if(meth[i].getName().equals("<init>")) {
							methodobj = new ClassForMethods(parts[0],parts[1],simpleClassName,attributesFromMethods,simpleClassName);
						} else {
							if(!(classobj.getPackageName().equals(""))){
								String[] methType = parts[1].split("\\.");
								methodobj = new ClassForMethods(parts[0],methType[methType.length-1],meth[i].getName(),attributesFromMethods,simpleClassName);
							}else{
								methodobj = new ClassForMethods(parts[0],parts[1],meth[i].getName(),attributesFromMethods,simpleClassName);
							}
						}
						methodList.add(methodobj);
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
				cp = new ClassParser(path);
				classobj.setPackageName(cp.parse().getPackageName());
				//The methods of each class
				cp = new ClassParser(path);
				Method meth[] = new Method[1000];
				meth = cp.parse().getMethods();
				for(int i = 0; i < meth.length; i++) {
					String string = meth[i].toString();
					String[] parts = string.split(" ");
					String[] attributesFromMethods = sfa.findAttributes(meth[i].toString());
					if(parts[0].equalsIgnoreCase("public") || parts[0].equalsIgnoreCase("protected") || parts[0].equalsIgnoreCase("private") || parts[0].equalsIgnoreCase("static")) {
						methodobj = new ClassForMethods(parts[0],parts[2],meth[i].getName(),attributesFromMethods,simpleClassName);
						methodList.add(methodobj);
					}
				}
			}
			//Check if the class on the path is an Abstract Class
			cp = new ClassParser(path);
			if(cp.parse().isAbstract()){
				if(!(classobj.isInterface())){
					classobj = new ClassForClass(true,"nothing");
					cp = new ClassParser(path);
					className = cp.parse().getClassName();
					simpleClassName = className.substring(className.lastIndexOf('.') + 1);
					classobj.setClassName(simpleClassName);
					cp = new ClassParser(path);
					classobj.setPackageName(cp.parse().getPackageName());
					//The attributes of each class
					Field fields[] = new Field[1000];
					cp = new ClassParser(path);
					fields = cp.parse().getFields();
					for(int i = 0; i < fields.length; i++) {
						String string2 = fields[i].toString();
						String[] parts2 = string2.split(" "); 
						if(!(classobj.getPackageName().equals(""))){
							String[] attriType = parts2[1].split("\\.");
							attriobj = new ClassForAttributes(parts2[0],attriType[attriType.length-1],parts2[2]);
						}else{
							attriobj = new ClassForAttributes(parts2[0],parts2[1],parts2[2]);
						}
						attriList.add(attriobj);
					}
					//The methods of each class
					cp = new ClassParser(path);
					Method meth[] = new Method[1000];
					meth = cp.parse().getMethods();
					for(int i = 0; i < meth.length; i++) {
						String string = meth[i].toString();
						String[] parts = string.split(" ");
						String[] attributesFromMethods = sfa.findAttributes(meth[i].toString());
						if(parts[0].equalsIgnoreCase("public") || parts[0].equalsIgnoreCase("protected") || parts[0].equalsIgnoreCase("private") || parts[0].equalsIgnoreCase("static")) {
							methodobj = new ClassForMethods(parts[0],parts[1],meth[i].getName(),attributesFromMethods,simpleClassName);
							methodList.add(methodobj);
						}
					}
				}
			}
			classobj.setAttris(attriList);
			classobj.setMethods(methodList);

		}catch(Exception e) {
			
			System.out.println(e.getMessage());
			System.out.println(e.getCause().toString());
			System.exit(0);
		}
		return	classobj;
	}
}