/**
 * 
 */
package javaPackage.yawl.graphics.forFigures;

import org.eclipse.swt.SWT;
import org.pnml.tools.epnk.gmf.extensions.graphics.decorations.ArrowHeadDecoration;
import org.pnml.tools.epnk.gmf.extensions.graphics.figures.ArcFigure;

import javaPackage.yawl.graphics.forDecoration.ResetHeadOfArcForDecoration;
import yawl.Arc;
import yawl.TypeOfA;
import yawl.TypeOfArc;



/**
 * 
 * @author Konstantin
 * @author Martin
 * 
 */

public class ArcInYawl extends ArcFigure 
{
	private enum Type { NORMAL, RESETARC }

	private Type type;
	
	public ArcInYawl(Arc arc)
	{
		super(arc);
		type = getType();
		setGraphics();
	}
	
	@Override
	public void update() 
	{
		Type oldType = type;
		type = getType();
		if (oldType != type)
		{
			setGraphics();
		}
	}
	/*
	 * Here the grapgics for arcs is set, on the event of not NORMAL it would be a reset arc and so a
	 * different head decoration is set to our ResetHeadOfArcForDecoration.
	 */
	
	private void setGraphics() {
		if (type == Type.NORMAL) {
			this.setLineStyle(SWT.LINE_SOLID);
			ArrowHeadDecoration df = new ArrowHeadDecoration();
			df.setLineWidth(1);
			this.setTargetDecoration(df);
			
		} else {
			this.setLineStyle(SWT.LINE_DASH);
			ResetHeadOfArcForDecoration targetDecor = new ResetHeadOfArcForDecoration();
			this.setTargetDecoration(targetDecor);
			
		}
		
	}

	private Type getType()
	{
		if (this.arc instanceof Arc) 
		{
			TypeOfArc arctype = ((Arc) arc).getType();
			if (arctype != null)
			{
				switch (arctype.getText().getValue())
				{
					case TypeOfA.NORMAL_VALUE:
						return Type.NORMAL;
					case TypeOfA.RESET_VALUE:
						return Type.RESETARC;
				}
				
			}
			else 
			{
				return Type.NORMAL;	
			}
			
			
		}
		return Type.NORMAL;
	}
}
