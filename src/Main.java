import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;

import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Texture;
import javax.media.j3d.Texture2D;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class Main extends Applet{

	//on initialise
	public Main(){
		this.setLayout(new BorderLayout());
		//on creer un canvas
		Canvas3D canvas =new Canvas3D(SimpleUniverse.getPreferredConfiguration());
		this.add(canvas,BorderLayout.CENTER);
		
		//on creer un SimpleUniverse et on rajoute notre scene
		SimpleUniverse simpleu=new SimpleUniverse(canvas);
		simpleu.getViewingPlatform().setNominalViewingTransform();
		BranchGroup scene= createSceneGraph(simpleu);
		scene.compile();
		simpleu.addBranchGraph(scene);
	}

	public BranchGroup createSceneGraph(SimpleUniverse simpleu){
		//creation BranchGroup
		BranchGroup parent =new BranchGroup();

		// On récupere le TG de la caméra
		TransformGroup view=simpleu.getViewingPlatform().getViewPlatformTransform();

		// le mouvement aura lieu avec le TG de la caméra 
		mouvbehav behav=new mouvbehav(view);

		// On définit une zone d'influence très grande pour que l'interaction soit active sur une très grande zone
		BoundingSphere influPartout=new BoundingSphere(new Point3d(),1000.0);
		behav.setSchedulingBounds(influPartout);

		// lien d'héritage
		parent.addChild(behav);

		//creation d'un cylindre deformé vers le haut qui tourne
		Cylindre cy=new Cylindre(0.0f,2f,2f,50,new Color3f(1f,0,1f),"bordure",new TransformGroup());
		cy.Translate(0f, -0.5f, 0);
		cy.Tourne(-1, 40000);
		parent.addChild(cy.getParentMax());
		
		//d'un panneau qui tourne
		Panneau cy2=new Panneau(1.0f,2f,new Color3f(0f,1f,1f),"plein",new TransformGroup());
		cy2.Tourne(-1, 40000);
		parent.addChild(cy2.getParentMax());
	
		return parent;
	}


	

	public static void main(String[] args){
		Frame frame=new MainFrame(new Main(),256,256);
	}
}
