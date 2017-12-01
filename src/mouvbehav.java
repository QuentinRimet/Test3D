/*
écrit par:Roswell
email:philgauthier_@hotmail.com
 
*/

import java.awt.AWTEvent;
import java.awt.event.KeyEvent;
import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;

import javax.media.j3d.Behavior;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupOnAWTEvent;
import javax.vecmath.Vector3f;

class mouvbehav extends Behavior
{	
	//transformGroup que l'on souhaite modifier
	private TransformGroup TG;
	//permet de modifier le transformGroup a modifier
	private Transform3D rot=new Transform3D();
	private Transform3D rotation=new Transform3D();
	private Vector3f translation=new Vector3f();
	//permet de detecter les evenement clavier
	private WakeupOnAWTEvent keyEvent=new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED);
	

	mouvbehav(TransformGroup TG)
	{
		this.TG=TG;
	}
	
	//on initialise la detection du clavier
	public void initialize()
	{
		this.wakeupOn(keyEvent);
	}
	
	//on effectue une operation quand on touche une touche
	public void processStimulus(Enumeration criteria)
	{
		AWTEvent events[]=keyEvent.getAWTEvent();
		rot.setIdentity();
		TG.getTransform(rotation);
		
		//si "e" on se deplace de cote vers la droite
		if (((KeyEvent)events[0]).getKeyChar()=='e')
		{
			for(int i=0;i<25;i++){
				Timer time=new Timer();
				time.schedule(new TimerTask(){

					@Override
					public void run() {
						translation(0.003f,0f,0f);
					}
					
				}, i*5);
				;}
		}
		else
			//si "a" on se deplace de cote vers la gauche
		if (((KeyEvent)events[0]).getKeyChar()=='a')
		{
			for(int i=0;i<25;i++){
				Timer time=new Timer();
				time.schedule(new TimerTask(){

					@Override
					public void run() {
						translation(-0.003f,0f,0f);
					}
					
				}, i*5);
				;}
		}
		else
			//si "s" on se deplace vers l'arrierre
		if (((KeyEvent)events[0]).getKeyChar()=='s')
		{
			for(int i=0;i<25;i++){
				Timer time=new Timer();
				time.schedule(new TimerTask(){

					@Override
					public void run() {
						translation(0f,0f,0.003f);
					}
					
				}, i*5);
				;}
		}
		else
			//si "z" on se deplace de cote vers devant
		if (((KeyEvent)events[0]).getKeyChar()=='z')
		{
			for(int i=0;i<25;i++){
				Timer time=new Timer();
				time.schedule(new TimerTask(){

					@Override
					public void run() {
						translation(0f,0f,-0.003f);
					}
					
				}, i*5);
				;}
		}
		else
			//si "q" on se tourne vers la gauche
		if (((KeyEvent)events[0]).getKeyChar()=='q')
			for(int i=0;i<25;i++){
				Timer time=new Timer();
				time.schedule(new TimerTask(){

					@Override
					public void run() {
						roty(0.002f);
					}
					
				}, i*5);
				;}
		else
			//si "d" on se tourne vers la droite
		if (((KeyEvent)events[0]).getKeyChar()=='d')			
			for(int i=0;i<25;i++){
				Timer time=new Timer();
				time.schedule(new TimerTask(){

					@Override
					public void run() {
						roty(-0.002f);
					}
					
				}, i*5);
				;}
		
		
		//le detecteur se rendort jusqu'a une detection d'evenement clavier
		this.wakeupOn(keyEvent);
	}
	
	//permet de tourner vers un cote
	void roty(float y){
		
		rot.setIdentity();
		TG.getTransform(rotation);
		rot.rotY(y);
		rotation.mul(rot);
		TG.setTransform(rotation);
		
	}
	
	//permet de se deplacer
	void translation(float x,float y,float z){
		
		rot.setIdentity();
		TG.getTransform(rotation);
		translation.set(x,y,z);
		rot.setTranslation(translation);
		rotation.mul(rot);
		TG.setTransform(rotation);
		
	}
}
