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

	public ClassForClass clc1= new ClassForClass();
	public ClassForAttributes cla1;
	public ClassForMethods clm1;
	public Display d= Display.getDefault();
	public Font font;
	public Label classLabel;
	public Label methodLabel;
	public Label attributeLabel;
	public PolylineConnection connection;
	Figure content = new Figure();
	XYLayout contentsLayout  = new XYLayout();
	Shell shell = new Shell(d);
	public ParsingClassFiles pcl = new ParsingClassFiles();
	LightweightSystem lws = new LightweightSystem(shell);
	UMLClassFigure classFigure;
	List<Label> attributeLabels ;
	List<Label> methodLabels ;
	List<Figure> contents  = new ArrayList<>();
	
	public void startDrawer(){
			d.syncExec(new Runnable(){
				public void run(){
					shell.setSize(400, 400);
					shell.setText("Class Diagram");
					
				}
			});
	}
	public void openShell(){
		shell.open();
	}

	public void stopDrawer(){
		while (!shell.isDisposed())
			while (!d.readAndDispatch())
				d.sleep();	
	}
	
	public void buildTable(ClassForClass cfc, List<ClassForMethods> cfm, List<ClassForAttributes> cfa){
		attributeLabels = new ArrayList<>();
		methodLabels = new ArrayList<>();
		if(cfc.isClass()){
			font = new Font(null, "Arial", 12, SWT.BOLD);
			classLabel = new Label(cfc.getClassName(), new Image(d, UMLClassFigure.class.getResourceAsStream("/class_obj.gif")));
			classLabel.setFont(font);
			if(!(cfa.isEmpty())){
				for(ClassForAttributes obj:cfa){
					//if(obj.getAccess() == "private") {
						attributeLabel = new Label(obj.getAttributeName() + " : " + obj.getType(), 
								new Image(d, UMLClassFigure.class.getResourceAsStream("/field_private_obj.gif")));
						attributeLabels.add(attributeLabel);
					//} else if(obj.getAccess() == "public") {
						//attributeLabel = new Label(obj.getAttributeName() + " : " + obj.getType(), 
							//	new Image(d, UMLClassFigure.class.getResourceAsStream("/field_public_obj.gif")));
						//attributeLabels.add(attributeLabel);
					//}
				}
			}else{
				attributeLabel = new Label(" ");
				attributeLabels.add(attributeLabel);
			}
			if(!(cfm.isEmpty())){
				for(ClassForMethods obj:cfm){
					methodLabel = new Label(obj.getMethodName() + " : " + obj.getType(), 
							new Image(d, UMLClassFigure.class.getResourceAsStream("/methpub_obj.gif")));
					methodLabels.add(methodLabel);
				}
			}else{
				methodLabel = new Label(" ");
				methodLabels.add(methodLabel);
			}
			displayClassDiagrams();
			content.setLayoutManager(contentsLayout);
		}else{
			font = new Font(null, "Arial", 12, SWT.BOLD);
			classLabel = new Label(cfc.getClassName(), new Image(d, UMLClassFigure.class.getResourceAsStream("/class_obj.gif")));
			classLabel.setFont(font);
		}
	}
	
	public void displayClassDiagrams() {
		try {
				final UMLClassFigure classFigure = new UMLClassFigure(classLabel);
				for(Label attri:attributeLabels){
					classFigure.getAttributesCompartment().add(attri);
				}
				for(Label meth:methodLabels){
					classFigure.getMethodsCompartment().add(meth);
				}
			
			contentsLayout.setConstraint(classFigure, new Rectangle(10, 10, -1, -1));

			/*
			// Creating the connection 
			connection = new PolylineConnection();
			ChopboxAnchor sourceAnchor = new ChopboxAnchor(classFigure);
			connection.setSourceAnchor(sourceAnchor);
			
			// Creating the decoration 
			PolygonDecoration decoration = new PolygonDecoration();
			PointList decorationPointList = new PointList();
			decorationPointList.addPoint(0, 0);
			decorationPointList.addPoint(-2, 2);
			decorationPointList.addPoint(-4, 0);
			decorationPointList.addPoint(-2, -2);
			decoration.setTemplate(decorationPointList);
			connection.setSourceDecoration(decoration);
			
			contents.add(connection);
			*/
			
			content.add(classFigure);
			lws.setContents(content);
			openShell();
		} catch(Exception e) {
			
			System.out.println("Error : " + e.getMessage());
		}
	}
}