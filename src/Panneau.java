import javax.media.j3d.Geometry;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TriangleStripArray;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;

import com.sun.j3d.utils.geometry.Box;

public class Panneau extends Forme{
	
	public Panneau(float largeur,float longueur,Color3f color,String app,TransformGroup trg){
		super(MaGeometry(largeur,longueur,color),MonApp(app),trg);
		
	}
	
	//permet de creer une geometry qui fabrique un panneau de couleur données
	static Geometry MaGeometry(float largeur,float longueur,Color3f color){
		
		
		int tab[]=new int[1];
		tab[0]=4;
		TriangleStripArray geom=new TriangleStripArray(4,TriangleStripArray.COORDINATES|TriangleStripArray.COLOR_3,tab);
		for(int i=0;i<4;i++)
			geom.setColor(i, color);

				Point3f point=new Point3f();
				point.x=(float)-longueur/2;
				point.y=(float)-largeur/2;
				point.z=(float)0;
				geom.setCoordinate(0, point);
				point.x=(float)longueur/2;
				point.y=(float)-largeur/2;
				point.z=(float)0;
				geom.setCoordinate(1, point);
				point.x=(float)-longueur/2;
				point.y=(float)largeur/2;
				point.z=(float)0;
				geom.setCoordinate(2, point);
				point.x=(float)longueur/2;
				point.y=(float)largeur/2;
				point.z=(float)0;
				geom.setCoordinate(3, point);
			
		return geom;
	}

}
