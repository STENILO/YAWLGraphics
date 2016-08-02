/**
 * 
 */
package javaPackage.yawl.graphics.forFigures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.pnml.tools.epnk.gmf.extensions.graphics.figures.PlaceFigure;

import yawl.Place;
import yawl.TypeOfC;
import yawl.TypeOfCondition;




public class ConditionInYawl extends PlaceFigure
{
private enum Type { NORMAL, START, END }
	
	private Type type;


	public ConditionInYawl(Place place) {
		super(place);
		type = getType();
	}

	@Override
	public void update() {
		Type oldType = type;
		type = getType();
		if (oldType != type) {
			this.repaint();
		}
	}
		
	private Type getType() {
		if (this.place instanceof Place) {
			TypeOfCondition placeType = ((Place) place).getType();
			if (placeType != null) {
				switch (placeType.getText().getValue()) {
					case TypeOfC.NORMAL_VALUE:
						return Type.NORMAL;
					case TypeOfC.START_VALUE:
						return Type.START;
					case TypeOfC.FINISH_VALUE:
						return Type.END;
				}
			}
		}
		return Type.NORMAL;
	}

	
	@Override
	protected void fillShape(Graphics graphics) {
		super.fillShape(graphics);
		org.eclipse.draw2d.geometry.Rectangle rectangle = this.getClientArea();
		int m = 0;
		int cx = rectangle.x + rectangle.width/2;
		int cy = rectangle.y + rectangle.height/2;
		if (place instanceof Place) {
			//m = getMarking((Condition) place);
			if(type == Type.END) {
				graphics.setBackgroundColor(ColorConstants.red);
				graphics.fillRectangle(cx-6, cy-6, 12, 12);
			}
			if(type == Type.START){
				graphics.setBackgroundColor(ColorConstants.green);
				PointList points = new PointList();
				points.addPoint(new Point(cx-7, cy-10));
				points.addPoint(new Point(cx+10, cy));
				points.addPoint(new Point(cx-7, cy+10));
				graphics.fillPolygon(points);
			}
		}

		if (m == 0) {
			return;
		} else if (m == 1) {
			graphics.setBackgroundColor(getForegroundColor());
			graphics.fillOval(cx-6, cy-6, 12, 12);
		} else if (m == 2) {
			graphics.setBackgroundColor(getForegroundColor());
			graphics.fillOval(cx-11, cy-11, 12, 12);
			graphics.fillOval(cx, cy, 12, 12);
		} else if (m == 3) {
			graphics.setBackgroundColor(getForegroundColor());
			graphics.fillOval(cx-6, cy-13, 12, 12);
			graphics.fillOval(cx-13, cy, 12, 12);
			graphics.fillOval(cx+1, cy, 12, 12);
		} else if (m == 4) {
			graphics.setBackgroundColor(getForegroundColor());
			graphics.fillOval(cx-13, cy-13, 12, 12);
			graphics.fillOval(cx+1, cy-13, 12, 12);
			graphics.fillOval(cx-13, cy+1, 12, 12);
			graphics.fillOval(cx+1, cy+1, 12, 12);
		} else {
			graphics.drawString(""+m, cx-5, cy-7);
		}
	}

/*	private int getMarking(Condition condition)
  {
		 Marking marking =condition.getMarking();
		if (marking != null) {
			BigInteger value = marking.getText();
			if (value != null) {
				return value.intValue();
			}
		}
		
		return 0;
	}*/
}