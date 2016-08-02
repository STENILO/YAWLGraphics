/**
 * 
 */
package javaPackage.yawl.graphics.forFigures;

import org.eclipse.swt.SWT;
import org.pnml.tools.epnk.gmf.extensions.graphics.figures.ArcFigure;

import yawl.Arc;
import yawl.TypeOfA;
import yawl.TypeOfArc;





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

	
	private void setGraphics() {
		if (type == Type.NORMAL) {
			this.setLineStyle(SWT.LINE_SOLID);
			
		} else {
			this.setLineStyle(SWT.LINE_DASH);
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
