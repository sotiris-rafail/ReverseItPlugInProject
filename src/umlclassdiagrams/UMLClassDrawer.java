package umlclassdiagrams;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.*;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import Classes.ClassForAttributes;
import Classes.ClassForClass;
import Classes.ClassForMethods;
import handlers.ParsingClassFiles;

public class UMLClassDrawer {
	List<UMLClassFigure> listFigures = new ArrayList<>();
	List<ClassForClass> allClasses = new ArrayList<>();
	public ClassForClass clc1= new ClassForClass();
	public ClassForAttributes cla1;
	public ClassForMethods clm1;
	public Display d= Display.getDefault();
	public Font classfont;
	public Font packageFont;
	public Label classLabel;
	public Label methodLabel;
	public Label attributeLabel;
	public Label packageLabel;
	Figure content = new Figure();
	XYLayout contentsLayout  = new XYLayout();
	PolylineConnection connection = new PolylineConnection();
	PolygonDecoration decoration = new PolygonDecoration();
	Shell shell = new Shell(d);
	public ParsingClassFiles pcl = new ParsingClassFiles();
	LightweightSystem lws = new LightweightSystem(shell);
	UMLClassFigure classFigure;
	List<Label> attributeLabels ;
	List<Label> methodLabels ;
	List<Label> classLabels = new ArrayList<>() ;
	public void startDrawer(){
			d.syncExec(new Runnable(){
				public void run(){
					//shell.setSize(400, 400);
					shell.setText("Class Diagram");
					
				}
			});
			openShell();
			stopDrawer();
	}
	public void openShell(){
		shell.open();
	}

	public void stopDrawer(){
		while (!shell.isDisposed())
			while (!d.readAndDispatch())
				d.sleep();	
	}
	public void kill(){
		if(shell.isDisposed()){
			shell.close();
		}
	}
	
	public void buildTable(List<ClassForClass> classList){
		allClasses = classList;
		for(ClassForClass clas:classList){
			attributeLabels = new ArrayList<>();
			methodLabels = new ArrayList<>();
			//if class is CLass
			if(clas.isClass()){
				classLabel = new Label(clas.getClassName(), new Image(d, UMLClassFigure.class.getResourceAsStream("/resources/class/class_obj.png")));
				classfont = new Font(null, "Arial", 12, SWT.BOLD);
				if(!(clas.getPackageName().equals(""))){
					packageFont = new Font(null, "Arial", 8, 0);
					packageLabel = new Label(clas.getPackageName(), new Image(d, UMLClassFigure.class.getResourceAsStream("/resources/class/package_obj.png")));
					packageLabel.setFont(packageFont);
				}
				classLabel.setFont(classfont);
				if(!(clas.getAttris().isEmpty())){
					for(ClassForAttributes obj:clas.getAttris()){
						if(obj.getAccess().equals("private")) {
							attributeLabel = new Label(obj.getAttributeName() + " : " + obj.getType(), 
								new Image(d, UMLClassFigure.class.getResourceAsStream("/resources/field/field_private_obj.png")));
							attributeLabels.add(attributeLabel);
						} else if(obj.getAccess().equals("public")) {
							attributeLabel = new Label(obj.getAttributeName() + " : " + obj.getType(), 
									new Image(d, UMLClassFigure.class.getResourceAsStream("/resources/field/field_public_obj.png")));
							attributeLabels.add(attributeLabel);
						} else if(obj.getAccess().equals("protected")) {
							attributeLabel = new Label(obj.getAttributeName() + " : " + obj.getType(), 
									new Image(d, UMLClassFigure.class.getResourceAsStream("/resources/field/field_protected_obj.png")));
							attributeLabels.add(attributeLabel);
						} else if(obj.getAccess().equals("final")) {
							attributeLabel = new Label(obj.getAttributeName() + " : " + obj.getType(), 
									new Image(d, UMLClassFigure.class.getResourceAsStream("/resources/field/final_field.png")));
							attributeLabels.add(attributeLabel);
						}else {
							attributeLabel = new Label(obj.getAttributeName() + " : " + obj.getType(), 
									new Image(d, UMLClassFigure.class.getResourceAsStream("/resources/field/field_default_obj.png")));
							attributeLabels.add(attributeLabel);
						}
					}
				}else{
					attributeLabel = new Label(" ");
					attributeLabels.add(attributeLabel);
				}
				if(!(clas.getMethods().isEmpty())){
					for(ClassForMethods obj:clas.getMethods()){
						String typeOfAttribute = "";
						String attrMeth[] = {};
						if(obj.isHasAttributes() != null) {
							for(int i = 0; i < obj.isHasAttributes().length; i++) {
								attrMeth = obj.isHasAttributes()[i].split(" ");
								if(i == 0){
									String[] checkForDots = attrMeth[0].split("\\.");
									if(checkForDots.length == 2){
										typeOfAttribute = checkForDots[1];
									}else{
										typeOfAttribute = attrMeth[0];
									}
								} else {
									String[] checkForDots = attrMeth[1].split("\\.");
									if(checkForDots.length == 2){
										typeOfAttribute += ", " + checkForDots[1];
									}else{
										typeOfAttribute += ", " +  attrMeth[1];
									}
								}
							}
						}
						
						if(obj.getAccess().equals("public") && obj.getType().equals("void") && obj.getMethodName().equals(clas.getClassName())) {
							methodLabel = new Label(obj.getMethodName() + "(" + typeOfAttribute + ")" + " : " + obj.getType(), 
									new Image(d, UMLClassFigure.class.getResourceAsStream("/resources/method/methpub_constr_obj.png")));
								methodLabels.add(methodLabel);
						}else if(obj.getAccess().equals("") && obj.getType().equals("void") && obj.getMethodName().equals(clas.getClassName())) {
							methodLabel = new Label(obj.getMethodName() + "(" + typeOfAttribute + ")" + " : " + obj.getType(), 
									new Image(d, UMLClassFigure.class.getResourceAsStream("/resources/method/default_constructor.png")));
								methodLabels.add(methodLabel);
						}else if(obj.getAccess().equals("public") && obj.getType().equals("static")) {
							methodLabel = new Label(obj.getMethodName() + "(" + typeOfAttribute + ")" + " : " + obj.getType(), 
								new Image(d, UMLClassFigure.class.getResourceAsStream("/resources/method/methpub_static_obj.png")));
							methodLabels.add(methodLabel);
						}else if(obj.getAccess().equals("public")) {
							methodLabel = new Label(obj.getMethodName() + "(" + typeOfAttribute + ")" + " : " + obj.getType(), 
								new Image(d, UMLClassFigure.class.getResourceAsStream("/resources/method/methpub_obj.png")));
							methodLabels.add(methodLabel);
						}else if(obj.getAccess().equals("private")) {
							methodLabel = new Label(obj.getMethodName() + "(" + typeOfAttribute + ")" + " : " + obj.getType(), 
								new Image(d, UMLClassFigure.class.getResourceAsStream("/resources/method/methpri_obj.png")));
							methodLabels.add(methodLabel);
						}else if(obj.getAccess().equals("protected")) {
							methodLabel = new Label(obj.getMethodName() + "(" + typeOfAttribute + ")" + " : " + obj.getType(), 
								new Image(d, UMLClassFigure.class.getResourceAsStream("/resources/method/methpro_obj.png")));
							methodLabels.add(methodLabel);
						}
					}
				}else{
					methodLabel = new Label(" ");
					methodLabels.add(methodLabel);
				}
				//If class is Interface
			}else if(clas.isInterface()){
				classfont = new Font(null, "Arial", 12, SWT.ITALIC);
				classLabel = new Label(clas.getClassName(), new Image(d, UMLClassFigure.class.getResourceAsStream("/resources/class/int_obj.png")));
				if(!(clas.getPackageName().equals(""))){
					packageFont = new Font(null, "Arial", 8, 0);
					packageLabel = new Label(clas.getPackageName(), new Image(d, UMLClassFigure.class.getResourceAsStream("/resources/class/package_obj.png")));
					packageLabel.setFont(packageFont);
				}
				classLabel.setFont(classfont);
				if(!(clas.getAttris().isEmpty())){
					for(ClassForAttributes obj:clas.getAttris()){
						if(obj.getAccess().equals("private")) {
							attributeLabel = new Label(obj.getAttributeName() + " : " + obj.getType(), 
								new Image(d, UMLClassFigure.class.getResourceAsStream("/resources/field/field_private_obj.png")));
							attributeLabels.add(attributeLabel);
						} else if(obj.getAccess().equals("public")) {
							attributeLabel = new Label(obj.getAttributeName() + " : " + obj.getType(), 
									new Image(d, UMLClassFigure.class.getResourceAsStream("/resources/field/field_public_obj.png")));
							attributeLabels.add(attributeLabel);
						} else if(obj.getAccess().equals("protected")) {
							attributeLabel = new Label(obj.getAttributeName() + " : " + obj.getType(), 
									new Image(d, UMLClassFigure.class.getResourceAsStream("/resources/field/field_protected_obj.png")));
							attributeLabels.add(attributeLabel);
						} else {
							attributeLabel = new Label(obj.getAttributeName() + " : " + obj.getType(), 
									new Image(d, UMLClassFigure.class.getResourceAsStream("/resources/field/field_default_obj.png")));
							attributeLabels.add(attributeLabel);
						}
					}
				}else{
					attributeLabel = new Label(" ");
					attributeLabels.add(attributeLabel);
				}
				if(!(clas.getMethods().isEmpty())){
					for(ClassForMethods obj:clas.getMethods()){
						String typeOfAttribute = "";
						String attrMeth[] = {};
						if(obj.isHasAttributes() != null) {
							for(int i = 0; i < obj.isHasAttributes().length; i++) {
								attrMeth = obj.isHasAttributes()[i].split(" ");
								if(i == 0){
									typeOfAttribute = attrMeth[0];
								} else {
									typeOfAttribute += ", " +  attrMeth[1];;
								}
							}
						}
						if(obj.getAccess().equals("public")) {
							methodLabel = new Label(obj.getMethodName() + "(" + typeOfAttribute + ")" + " : " + obj.getType(), 
								new Image(d, UMLClassFigure.class.getResourceAsStream("/resources/method/methpub_obj.png")));
							methodLabels.add(methodLabel);
						} else if(obj.getAccess().equals("private")) {
							methodLabel = new Label(obj.getMethodName() + "(" + typeOfAttribute + ")" + " : " + obj.getType(), 
								new Image(d, UMLClassFigure.class.getResourceAsStream("/resources/method/methpri_obj.png")));
							methodLabels.add(methodLabel);
						}else if(obj.getAccess().equals("protected")) {
							methodLabel = new Label(obj.getMethodName() + "(" + typeOfAttribute + ")" + " : " + obj.getType(), 
								new Image(d, UMLClassFigure.class.getResourceAsStream("/resources/method/methpri_obj.png")));
							methodLabels.add(methodLabel);
						}else if(obj.getAccess().equals("static")) {
							methodLabel = new Label(obj.getMethodName() + "(" + typeOfAttribute + ")" + " : " + obj.getType(), 
								new Image(d, UMLClassFigure.class.getResourceAsStream("/resources/method/methpri_obj.png")));
							methodLabels.add(methodLabel);
						}
					}
				}else{
					methodLabel = new Label(" ");
					methodLabels.add(methodLabel);
				}
			//if class is abstract
			}else if(clas.isAbstract()){
				classfont = new Font(null, "Arial", 12, SWT.ITALIC);
				classLabel = new Label(clas.getClassName(), new Image(d, UMLClassFigure.class.getResourceAsStream("/resources/class/abstract_class_obj.png")));
				if(!(clas.getPackageName().equals(""))){
					packageFont = new Font(null, "Arial", 8, 0);
					packageLabel = new Label(clas.getPackageName(), new Image(d, UMLClassFigure.class.getResourceAsStream("/resources/class/package_obj.png")));
					packageLabel.setFont(packageFont);
				}
				classLabel.setFont(classfont);
				if(!(clas.getAttris().isEmpty())){
					for(ClassForAttributes obj:clas.getAttris()){
						if(obj.getAccess().equals("private")) {
							attributeLabel = new Label(obj.getAttributeName() + " : " + obj.getType(), 
								new Image(d, UMLClassFigure.class.getResourceAsStream("/resources/field/field_private_obj.png")));
							attributeLabels.add(attributeLabel);
						} else if(obj.getAccess().equals("public")) {
							attributeLabel = new Label(obj.getAttributeName() + " : " + obj.getType(), 
									new Image(d, UMLClassFigure.class.getResourceAsStream("/resources/field/field_public_obj.png")));
							attributeLabels.add(attributeLabel);
						} else if(obj.getAccess().equals("protected")) {
							attributeLabel = new Label(obj.getAttributeName() + " : " + obj.getType(), 
									new Image(d, UMLClassFigure.class.getResourceAsStream("/resources/field/field_protected_obj.png")));
							attributeLabels.add(attributeLabel);
						} else {
							attributeLabel = new Label(obj.getAttributeName() + " : " + obj.getType(), 
									new Image(d, UMLClassFigure.class.getResourceAsStream("/resources/field/field_default_obj.png")));
							attributeLabels.add(attributeLabel);
						}
					}
				}else{
					attributeLabel = new Label(" ");
					attributeLabels.add(attributeLabel);
				}
				if(!(clas.getMethods().isEmpty())){
					for(ClassForMethods obj:clas.getMethods()){
						String typeOfAttribute = "";
						String attrMeth[] = {};
						if(obj.isHasAttributes() != null) {
							for(int i = 0; i < obj.isHasAttributes().length; i++) {
								attrMeth = obj.isHasAttributes()[i].split(" ");
								if(i == 0){
									typeOfAttribute = attrMeth[0];
								} else {
									typeOfAttribute += ", " +  attrMeth[1];;
								}
							}
						}
						if(obj.getAccess().equals("public") && obj.getType().equals("void") && obj.getMethodName().equals(clas.getClassName())) {
							methodLabel = new Label(obj.getMethodName() + "(" + typeOfAttribute + ")" + " : " + obj.getType(), 
									new Image(d, UMLClassFigure.class.getResourceAsStream("/resources/method/methpub_constr_obj.png")));
								methodLabels.add(methodLabel);
						}else if(obj.getAccess().equals("public")) {
							methodLabel = new Label(obj.getMethodName() + "(" + typeOfAttribute + ")" + " : " + obj.getType(), 
								new Image(d, UMLClassFigure.class.getResourceAsStream("/resources/method/methpub_obj.png")));
							methodLabels.add(methodLabel);
						} else if(obj.getAccess().equals("private")) {
							methodLabel = new Label(obj.getMethodName() + "(" + typeOfAttribute + ")" + " : " + obj.getType(), 
								new Image(d, UMLClassFigure.class.getResourceAsStream("/resources/method/methpri_obj.png")));
							methodLabels.add(methodLabel);
						}else if(obj.getAccess().equals("protected")) {
							methodLabel = new Label(obj.getMethodName() + "(" + typeOfAttribute + ")" + " : " + obj.getType(), 
								new Image(d, UMLClassFigure.class.getResourceAsStream("/resources/method/methpri_obj.png")));
							methodLabels.add(methodLabel);
						}else if(obj.getAccess().equals("static")) {
							methodLabel = new Label(obj.getMethodName() + "(" + typeOfAttribute + ")" + " : " + obj.getType(), 
								new Image(d, UMLClassFigure.class.getResourceAsStream("/resources/method/methpri_obj.png")));
							methodLabels.add(methodLabel);
						}						
					}
				}else{
					methodLabel = new Label(" ");
					methodLabels.add(methodLabel);
				}
			}
			displayClassDiagrams();
		}
		content.setLayoutManager(contentsLayout);
		startDrawer();
	}
	int x = 0;
	int y = 0;
	int count = 0;
	List<PolylineConnection> connections = new ArrayList<>();
	public void displayClassDiagrams() {
		try {
			if(count%2 == 0 && count != 0){
				y =+200;
			}else if(count % 2 != 0){
				y +=200;	
			}
				final UMLClassFigure classFigure = new UMLClassFigure(classLabel);
				if(packageLabel != null){
					classFigure.getPackageFigure().add(packageLabel);
				}
				for(Label attri:attributeLabels){
					classFigure.getAttributesCompartment().add(attri);
				}
				for(Label meth:methodLabels){
					classFigure.getMethodsCompartment().add(meth);
				}
			classLabels.add(classLabel);
			contentsLayout.setConstraint(classFigure, new Rectangle(10+x, 10+y, -1, -1));
			x +=200;
			count++;
			
			// Creating the connection 
			for(ClassForClass clss:allClasses){
				if(classLabel.getText().equals(clss.getClassName())){
					if(!clss.getCoonectsWith().isEmpty()){
						ChopboxAnchor sourceAnchor = new ChopboxAnchor(classFigure);
						connection.setSourceAnchor(sourceAnchor);
						break;
					}
				}
			}
			ChopboxAnchor targetAnchor;
			for(Label lbl: classLabels){
				for(ClassForClass clss:allClasses){
					if(!lbl.getText().equals(clss.getClassName())){
						if(!clss.getCoonectsWith().isEmpty()){							
							for(String string : clss.getCoonectsWith()){
								if(lbl.getText().equals(string)){
									targetAnchor = new ChopboxAnchor(classFigure);
									connection.setTargetAnchor(targetAnchor);
									clss.getCoonectsWith().remove(string);
									if(clss.getCoonectsWith().isEmpty()){
										break;
									}
								}
							}
						}
					}
				}
			}
			connection.setTargetDecoration(decoration);
			content.add(classFigure);
			content.add(connection);
			lws.setContents(content);
		} catch(Exception e) {
			
			System.out.println("Error : " + e.getMessage());
		}
	}
}