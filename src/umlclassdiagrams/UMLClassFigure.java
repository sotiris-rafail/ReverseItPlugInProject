package umlclassdiagrams;

import org.eclipse.draw2d.*;
import org.eclipse.swt.graphics.Color;

public class UMLClassFigure extends Figure {

	public static Color classColor = new Color(null, 255, 255, 206);
	private CompartmentFigure attributeFigure = new CompartmentFigure();
	private CompartmentFigure methodFigure = new CompartmentFigure();
	private CompartmentFigure packageFigure = new CompartmentFigure();
	
	public UMLClassFigure(Label name) {
		
		ToolbarLayout layout = new ToolbarLayout();
	    setLayoutManager(layout);
		setBorder(new LineBorder(ColorConstants.black, 1));
		setBackgroundColor(classColor);
		setOpaque(true);
		
		add(name);	
		add(packageFigure);
	    add(attributeFigure);
	    add(methodFigure);
	}
	
	public CompartmentFigure getAttributesCompartment() {
		
	    return attributeFigure;
	}
	
	public CompartmentFigure getMethodsCompartment() {
		
		return methodFigure;
	}

	public CompartmentFigure getPackageFigure() {
		return packageFigure;
	}
}
