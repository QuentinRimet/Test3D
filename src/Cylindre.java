import javax.media.j3d.Geometry;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TriangleStripArray;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;

public class Cylindre extends Forme{
	
	public Cylindre(float haut,float bas,float hauteur,int pane,Color3f color,String app,TransformGroup trg){
		super(MaGeometry(haut,bas,hauteur,pane,color),MonApp(app),trg);
	}
	
	//permet de creer une geometry qui fabrique un cylindre modifiable
	static Geometry MaGeometry(float radius1,float radius2,float height,int nbpane,Color3f color){

		int tab[]=new int[1];
		tab[0]=(nbpane*2)+2;
		TriangleStripArray geom=new TriangleStripArray((nbpane*2)+2,TriangleStripArray.COORDINATES|TriangleStripArray.COLOR_3,tab);
		for(int i=0;i<(nbpane*2)+2;i++)
			geom.setColor(i, color);

		double angle=2*Math.PI/nbpane;

		Point3f point=new Point3f();
		for(int i=0;i<nbpane;i++){
			//initialisation du premier panneaux
			if(i==0){
				point.x=(float)(radius1*Math.cos(0*angle));
				point.y=(float)height;
				point.z=(float)(radius1*Math.sin(0*angle));
				geom.setCoordinate(0, point);

				point.x=(float)(radius2*Math.cos(0*angle));
				point.y=0f;
				point.z=(float)(radius2*Math.sin(0*angle));
				geom.setCoordinate(1, point);

				point.x=(float)(radius1*Math.cos(1*angle));
				point.y=(float)height;
				point.z=(float)(radius1*Math.sin(1*angle));
				geom.setCoordinate(2, point);

				point.x=(float)(radius2*Math.cos(1*angle));
				point.y=0f;
				point.z=(float)(radius2*Math.sin(1*angle));
				geom.setCoordinate(3, point);
			}
			//on construit les autres panneaux du cylindre
			else
			{
				point.x=(float)(radius1*Math.cos((i+1)*angle));
				point.y=(float)height;
				point.z=(float)(radius1*Math.sin((i+1)*angle));
				geom.setCoordinate((i+1)*2, point);

				point.x=(float)(radius2*Math.cos((i+1)*angle));
				point.y=0f;
				point.z=(float)(radius2*Math.sin((i+1)*angle));
				geom.setCoordinate((i+1)*2+1, point);
			}
		}
		return geom;
	}

}
