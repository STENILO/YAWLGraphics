/**
 * 
 */
package javaPackage.yawl.graphics.forFigures;

import java.math.BigInteger;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.common.util.Enumerator;
import org.pnml.tools.epnk.gmf.extensions.graphics.figures.TransitionFigure;

import yawl.Transition;
import yawl.TypeOfT;
import yawl.TypeOfTransition;





public class TransitionInYawl extends TransitionFigure{
	private enum Type {AND,XOR,OR}
	private Type splittype;
	private Type jointype;
	
	public TransitionInYawl(Transition action) {
		super(action);
		splittype = getSplitType();
		jointype = getJoinType();
		
	}
	
	@Override
		public void update() {
	
		Type oldJoinType = jointype;
		Type oldSplitType = splittype;
		
		splittype = getSplitType();
		jointype = getJoinType();
		
		if ((oldSplitType != splittype) || (oldJoinType != jointype)) {
			this.repaint();
		}
		
	}
	
		
	private Type getJoinType() {
		if (this.transition instanceof Transition) {
			TypeOfTransition TypeOfTransition = ((Transition) transition).getTypeOfJoin();
			if (TypeOfTransition != null) {
				switch (TypeOfTransition.getText()) {
					case AND:
						return Type.AND;
					case OR:
						return Type.OR;
					case XOR:
						return Type.XOR;
				}
			}
		}
		
		return Type.AND;
		
	}

	private Type getSplitType() {
		TypeOfTransition type = (yawl.TypeOfTransition) ((Transition) transition).getTypeOfSplit();
		if (type != null) {
			switch (type.getText()) {
			case AND:
				return Type.AND;
			case OR:
				return Type.OR;
			case XOR:
				return Type.XOR;
			}
		}
		return Type.AND;
	}
	
	@Override
	protected void fillShape(Graphics graphics) {
		super.fillShape(graphics);
		org.eclipse.draw2d.geometry.Rectangle rectangle = this.getClientArea();
		int m = 0;
		int cx = rectangle.x + rectangle.width/2;
		int cy = rectangle.y + rectangle.height/2;
		TypeOfTransition transitionType;
		if (transition instanceof Transition) {
			// transitionType = ((Transition) transition).getTypeOfJoin();
			// graphics.setLineWidth(2);
			// if (transitionType != null) {
			
				if(jointype == Type.AND) {
					graphics.setBackgroundColor(getForegroundColor());
					// Set up AND join figure
					PointList points = new PointList();
					points.addPoint(new Point(cx-20, cy+20));
					points.addPoint(new Point(cx, cy));
					points.addPoint(new Point(cx-20, cy-20));
					// Draw the triangle figure
					graphics.drawPolygon(points);
					// Draw the vertical line in center
					graphics.drawLine(new Point(cx, cy+20), new Point(cx, cy-20));
				}
				else if(jointype == Type.OR){
					graphics.setBackgroundColor(getForegroundColor());
					// Set up OR split figure
					PointList points = new PointList();
					points.addPoint(new Point(cx-20, cy));
					points.addPoint(new Point(cx-10, cy+20));
					points.addPoint(new Point(cx, cy));
					points.addPoint(new Point(cx-10, cy-20));
					// Draw the triangle figure
					graphics.drawPolygon(points);
					// Draw the vertical line in center
					graphics.drawLine(new Point(cx, cy+20), new Point(cx, cy-20));
				}
				else if(jointype == Type.XOR){
					graphics.setBackgroundColor(getForegroundColor());
					// Set up XOR join figure
					PointList points = new PointList();
					points.addPoint(new Point(cx, cy+20));
					points.addPoint(new Point(cx-20, cy));
					points.addPoint(new Point(cx, cy-20));
					// Draw the figure
					graphics.drawPolygon(points);
				}
			// }
			
 //			transitionType = (TypeOfTransition) ((Transition) transition).getTypeOfSplit();
			// if (transitionType != null) {
				
				if(splittype == Type.AND) {
					graphics.setBackgroundColor(getForegroundColor());
					// Set up AND split figure
					PointList points = new PointList();
					points.addPoint(new Point(cx, cy));
					points.addPoint(new Point(cx+20, cy+20));
					points.addPoint(new Point(cx+20, cy-20));
					// Draw the triangle figure
					graphics.drawPolygon(points);
					// Draw the vertical line in center
					graphics.drawLine(new Point(cx, cy+20), new Point(cx, cy-20));
				}
				else if(splittype  == Type.OR){
					graphics.setBackgroundColor(getForegroundColor());
					// Set up OR join figure
					PointList points = new PointList();
					points.addPoint(new Point(cx, cy));
					points.addPoint(new Point(cx+10, cy+20));
					points.addPoint(new Point(cx+20, cy));
					points.addPoint(new Point(cx+10, cy-20));
					// Draw the triangle figure
					graphics.drawPolygon(points);
					// Draw the vertical line in center
					graphics.drawLine(new Point(cx, cy+20), new Point(cx, cy-20));
				}
				else if(splittype  == Type.XOR){
					graphics.setBackgroundColor(getForegroundColor());
					// Set up XOR split figure
					PointList points = new PointList();
					points.addPoint(new Point(cx, cy-20));
					points.addPoint(new Point(cx+20, cy));
					points.addPoint(new Point(cx, cy+20));
					
					// Draw the figure
					graphics.drawPolygon(points);
				}
			// }
			//m = getMarking((TypeOfTransition)transition);
		}
		/*

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
		*/
	}

	/*private int getMarking(TypeOfTransition transition) {
		 Marking marking = Marking.getMarking();
		if (marking != null) {
			BigInteger value = marking.getText();
			if (value != null) {
				return value.intValue();
			}
		}
		
		return 0;
	}*/
}
