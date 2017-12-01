import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Geometry;
import javax.media.j3d.Node;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.PositionPathInterpolator;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

public class Forme extends Shape3D{
	
	private TransformGroup TG;
	private Transform3D mod=new Transform3D();
	private Transform3D modif=new Transform3D();

	public Forme(Geometry maGeometry, Appearance monApp,TransformGroup trg) {
		super(maGeometry,monApp);
		TG=trg;
		trg.addChild(this);
	}
	
	//permet de changer l'apparence de l'objet
	static Appearance MonApp(String code){
		Appearance app=new Appearance();
		if(code.equalsIgnoreCase("bordure"))
			app.setPolygonAttributes(new PolygonAttributes(PolygonAttributes.POLYGON_LINE,PolygonAttributes.CULL_NONE,0f));
		else if(code.equalsIgnoreCase("plein"))
			app.setPolygonAttributes(new PolygonAttributes(PolygonAttributes.POLYGON_FILL,PolygonAttributes.CULL_NONE,0f));
		return app;
	}
	
	//permet de faire tourner l'objet sur lui meme
	public void Tourne(int tour,int freq){
		TransformGroup tf=new TransformGroup();
		tf.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Alpha rotation=new Alpha(tour,freq);
		RotationInterpolator ri=new RotationInterpolator(rotation,tf);
		ri.setAxisOfRotation(this.modif);
		BoundingSphere bounds=new BoundingSphere(this.getBounds());
		ri.setSchedulingBounds(bounds);
		tf.addChild(ri);
		tf.addChild(this.getParentMax());
	
	}
	
	//permet de deplacer l'objet l'objet sur un chemin de point predefinie
	public void Deplace(Point3f[] chemin,float[] timePosition,int tour,int freq){
		Transform3D trans=new Transform3D();
		
		Alpha transAlpha=new Alpha(tour,freq);
		
		TransformGroup tt=new TransformGroup();
		tt.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		PositionPathInterpolator interpolator=new PositionPathInterpolator(transAlpha,tt,trans,timePosition,chemin);
		BoundingSphere bounds=new BoundingSphere();
		interpolator.setSchedulingBounds(bounds);
		tt.addChild(interpolator);
		tt.addChild(this.getParentMax());
		
	}
	
	//permet de modifier les coordonées de l'objet
	void Translate(float x,float y,float z){
		mod.setIdentity();
		TG.getTransform(modif);
		mod.setTranslation(new Vector3f(x,y,z));
		modif.mul(mod);
		TG.setTransform(modif);
	}
	
	//permet de changer la rotation de l'objet
	void Rotation(float x,float y,float z){
		rotx(x);
		roty(y);
		rotz(z);
	}
	//permet de changer la rotation y de l'objet
	private void roty(float y) {
		mod.setIdentity();
		TG.getTransform(modif);
		mod.rotY(y);
		modif.mul(mod);
		TG.setTransform(modif);
	}
	//permet de changer la rotation z de l'objet
	private void rotz(float z) {
		mod.setIdentity();
		TG.getTransform(modif);
		mod.rotZ(z);
		modif.mul(mod);
		TG.setTransform(modif);
	}
	//permet de changer la rotation x de l'objet
	private void rotx(float x) {
		mod.setIdentity();
		TG.getTransform(modif);
		mod.rotX(x);
		modif.mul(mod);
		TG.setTransform(modif);
	}

	//permet de recuperer le parent le plus haut pour emboiter plus facilement les TransformGroup
	Node getParentMax(){
		Node retour=this;
		while(retour.getParent()!=null)
			retour=retour.getParent();
			return retour;
		
	}
}
