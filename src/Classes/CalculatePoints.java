package Classes;

import java.util.List;

public class CalculatePoints {

	public void calculatePointsForMethods(List<ClassForClass> classes) {
		for(ClassForClass classs : classes) {
			if(classs.isClass() || classs.isAbstract()) {
				classs.setPoints(calculatePointsForClasses(classs));
				for(ClassForMethods method : classs.getMethods()) {
					if (method.isHasAttributes() != null){
						for(int i = 0; i < method.isHasAttributes().length; i++) {
							method.setPoints(method.getPoints() + getPointsFromMethodType(method, classes));
							method.setPoints(method.getPoints() + Points.hasAttributes);
							method.setPoints(method.getPoints() + getPointsFromMethod(method.isHasAttributes()[i], classes));
						}
					}else {
						method.setPoints(method.getPoints() + getPointsFromMethodType(method, classes));
					}
				}
			} 
			else if(classs.isInterface()) {
				for(ClassForClass clas : classes) {
					if(clas.isClass() || clas.isAbstract()) {
						if(clas.isDoesImplementation()) {
							for(String interFace : clas.getWhichInterface()) {
								if(interFace.equals(classs.getClassName())) {
									classs.setInterfacePoints(classs.getInterfacePoints()+1);
								}
							}
						}
					}
				}
			}
		}
	}
	
	private double getPointsFromMethodType(ClassForMethods method, List<ClassForClass> classes) {
		if (method.getType().equals("void")) {
			return Points.voidMethod;
		}
		for(ClassForClass classs : classes) {
			if (method.getType().equals(classs.getClassName())){
				return Points.customReturnType;
			} else {
				return Points.standardReturnType;
			}
		}
		return 0;
	}
	
	private double getPointsFromMethod(String attribute, List<ClassForClass> classes) {
		for(ClassForClass classs : classes){
			if (attribute.contains(classs.getClassName())){
				return Points.eachCustomAttribute;
			} else {
					return Points.eachStandardAttrbite;
			}
		}
		return 0;
		
	}
	
	private  double calculatePointsForClasses(ClassForClass classs) {
		double points = 0;
		if (!classs.getAttris().isEmpty()) {
			for(ClassForAttributes attri : classs.getAttris()) {
				if (attri.getType().contains(classs.getClassName())) {
					points += Points.eachCustomClassObject * Points.eachMethod;
				} else {
					points += Points.eachStandardClassObject * Points.eachMethod;
				}
			}
		}
		return points;
	}
}
