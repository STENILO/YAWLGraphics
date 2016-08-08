/**
 * 
 */
package javaPackage.yawl.graphics.forDecoration;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;


public class ResetHeadOfArcForDecoration extends PolygonDecoration
{
private static final PointList ARROW = new PointList();
	
	static {
		ARROW.addPoint(  0,  0);
		ARROW.addPoint(-12,  4);
		ARROW.addPoint( -7,  0);
		ARROW.addPoint(-12, -4);
		
		
		ARROW.addPoint(  -7,  0);
		ARROW.addPoint(-24,  4);
		ARROW.addPoint(-19,  0);
		ARROW.addPoint(-24, -4);
		ARROW.addPoint(  -7,  0);
		ARROW.addPoint(-12,  -4);
	}
	
	public ResetHeadOfArcForDecoration() {
		super();
		this.setScale(1, 1);
		this.setTemplate(ARROW);
	}
	
	@Override
	public Rectangle getBounds() {
		// The bounding box computed by PolygonDecoration (actually by Polyline) is a 
		// bit too tight for the double arrow head shape (with very pointed angles);
		// therefore, a slightly bigger bounding box is computed here!
		if (bounds == null) {
			int expand = (int) (getLineWidthFloat()) + 1;
			bounds = getPoints().getBounds().getExpanded(expand, expand);
		}
		return bounds;
	}

}
