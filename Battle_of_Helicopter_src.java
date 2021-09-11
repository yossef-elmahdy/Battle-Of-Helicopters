/**
 * @(#)FirstSection.java
 *
 * Sample Applet application
 *
 * @author
 * @version 1.00 21/05/29
 */

import java.awt.*;
import java.applet.*;
//import java.util.Random;
//import java.awt.geom.*;
//import java.time.*;
import java.awt.event.*;

public class FirstSection extends Applet implements KeyListener {



	public void init() {
		addKeyListener(this);


	}

	//blue
	int xB = 0, yB = 0;    //coordinates of movement of blue helicopter

	//red
	int xR = 0, yR = 0;    //cordinates of movement of red helicopter

	int angle = 0;  // angle of arcs
	int mv = 10;  //value of move in x or y coordinates when button is pressed

	//Score (MAX: 10)
	int Blue = 0;   //blue score
	int Red = 0;    //red score

	//Shooting (indicate that the key of shoot is pressed and hits a shoot)
	boolean bShoot = false;   //state of bullets of blue
	int xbShoot = 0, ybShoot = 0;  //coordinates of blue bullets


	boolean rShoot = false;  //state of bullets of red
	int xrShoot = 0, yrShoot = 0;  //coordinates of red bullets

	boolean finished = false; //indicate if the game is stopped or finished by winning state or crash state
	int winner = 0;  //if 1 then blue win, 2 then red win

	int maxScore = 3;  //max score that the winner should have

	boolean stopped = false;   //indicate if a crash happened



	public void paint(Graphics g) {
		//cordinates of the applet
		int w = (int)(this.getWidth());
		int h = (int)(this.getHeight());

		//blue
		int[] xpB = {20+xB, 30+xB, 30+xB, 70+xB, 70+xB, 100+xB, 100+xB, 110+xB, 110+xB, 120+xB, 120+xB, 70+xB, 70+xB, 20+xB};
		int[] ypB = {30+yB, 30+yB, 50+yB, 50+yB, 30+yB, 30+yB, 10+yB, 10+yB, 30+yB, 30+yB, 70+yB, 70+yB, 60+yB, 60+yB};

		//BLUE-BODY
		g.setColor(Color.BLUE);
		g.fillPolygon(xpB, ypB, 14);
		g.fillArc(100+xB, 30+yB, 40, 40, 270, 180);

		//BLUE-WINDOWS
		g.setColor(Color.WHITE);
		g.fillRect(80+xB, 50+yB, 10, 10);
		g.fillRect(100+xB, 50+yB, 10, 10);
		g.fillRect(120+xB, 50+yB, 10, 10);

		//BLUE-FANS
		g.setColor(Color.BLACK);
		for (int i = 0; i < 360; i += 90)
		{
			g.fillArc(50+xB, 5+yB, 110, 30, i - angle, 20);
			g.fillArc(5+xB, 15+yB, 40, 40, i - angle, 20);
		}

		//BLUE-MOVEMENT-RESTRICTIONS
		if (xB >= (w-120)) xB = w-120; //stops the blue helicopter from moving right more if it hits the right side of the applet
		if (xB < 0) xB = 0; //stops the helicopter from moving left more if it hits the left side of the applet
		//xB += 5;
		//yB += 5;

		if (yB <= 0) yB = 0;   //stops the helicopter from moving up more if it hits the top of the applet
		if (yB >= (h-80)) yB = h-80;  //stops the helicopter from moving down more if it hits the bottom of the applet

		//BLUE-BULLET-SHOOTING
		if (bShoot)  //a shoot was hit
		{
			g.setColor(Color.BLUE);
			g.fillRect(140+xbShoot, 50+ybShoot, 20, 5);  //the bullet
			xbShoot += 5;   //moving the bullet towards the opposite side (right)
			if (xbShoot > w) bShoot = false;  //hits the side of the applet and miss


			g.drawLine(w-140+xR, yR, w-20+xR, yR);
			g.drawLine(w-140+xR, yR+70, w-20+xR, yR+70);

			g.drawLine(w-140+xR, yR, w-140+xR, yR+70);
			g.drawLine(w-20+xR, yR, w-20+xR, yR+70);

			g.drawLine(100, 50+ybShoot, 200, 50+ybShoot);


			if (((50+ybShoot)>=yR && (50+ybShoot)<(yR+70)) && xbShoot == (w-140+xR)) //if the bullet hits the red helicopter
				Blue += 1;   //a point is hit
		}





		//Red
		//(adjusting its start position according to the  of the applet)
		int[] xpR = {(w-20)+xR, (w-30)+xR, (w-30)+xR, (w-70)+xR, (w-70)+xR, (w-100)+xR, (w-100)+xR, (w-110)+xR, (w-110)+xR, (w-120)+xR, (w-120)+xR, (w-70)+xR, (w-70+xR), (w-20)+xR};
		int[] ypR = {30+yR, 30+yR, 50+yR, 50+yR, 30+yR, 30+yR, 10+yR, 10+yR, 30+yR, 30+yR, 70+yR, 70+yR, 60+yR, 60+yR};

		//RED-BODY
		g.setColor(Color.RED);
		g.fillPolygon(xpR,ypR, 14);
		g.fillArc((w-140)+xR, 30+yR, 40, 40, 90, 180);

		//RED-WINDOWS
		g.setColor(Color.WHITE);
		g.fillRect((w-90)+xR, 50+yR, 10, 10);
		g.fillRect((w-110)+xR, 50+yR, 10, 10);
		g.fillRect((w-130)+xR, 50+yR, 10, 10);

		//RED-FANS
		g.setColor(Color.BLACK);
		for (int i = 0; i < 360; i += 90)
		{
			g.fillArc((w-160)+xR, 5+yR, 110, 30, i + angle, 20);
			g.fillArc((w-45)+xR, 15+yR, 40, 40, i + angle, 20);
		}

		//RED-MOVEMENT-RESTRICTIONS
		if (xR < (-w+150)) xR = (-w+150); //stops the red helicopter from moving left more if it hits the left side of the applet
		if ((xR+w) > w) xR = 0; //stops the red helicopter from moving right more if it hits the right side of the applet


		if (yR <= 0) yR = 0;   //stops the helicopter from moving up more if it hits the top of the applet
		if (yR >= (h-80)) yR = h-80;  //stops the helicopter from moving down more if it hits the bottom of the applet


		//RED-BULLET-SHOOTING
		if (rShoot)  //a shoot was hit
		{
			g.setColor(Color.RED);
			g.fillRect(w-160+xrShoot, 50+yrShoot, 20, 5);
			xrShoot -= 5;
			if (xrShoot < -w) rShoot = false;

			g.drawLine(20+xB, yB, 140+xB, yB);
			g.drawLine(140+xB, yB+70, 20+xB, yB+70);

			g.drawLine(140+xB, yB, 140+xB, yB+70);
			g.drawLine(20+xB, yB, 20+xB, yB+70);


			g.drawLine(w-100, 50+yrShoot, w-200, 50+yrShoot);

			if (((50+yrShoot)>=yB && (50+yrShoot)<(yB+70)) && xrShoot == -(w-200)) //if the bullet hits the red helicopter
				Red += 1;   //a point is hit
		}


		//HITING PHASE
		if ((xB+130) >= (w-140+xR))
		{
			//Red and blue hits other front to front
			if (yR >= yB && yR <= (yB+70)) 	stopped = true;

			//Red hits the blue from the top
			if ((yR+50) >= yB && (yR+50) <= (yB+70)) stopped = true;

			//Blue hits the red from the top
			if ((yB+70) >= (yR+50) && (yB+70) <= (yR+70)) stopped = true;

		}


		if (stopped)
		{
			//Type if crash happened
			g.drawString("Crash Happened", w/2, w/3);
		}



		angle = (angle+10) % 360;

		//Scoreboard
		g.setColor(Color.BLACK);
		String score = "Blue: " + Blue + "    Red: " + Red;
		g.drawString(score, w/2, 30);

		//WIN
		if (Blue == maxScore)
		{
			winner = 1;
			finished = true;
		}


		if (Red == maxScore)
		{
			winner = 2;
			finished = true;
		}

		if (finished)
		{
			if (winner == 1)  g.drawString("Blue IS THE WINNER", w/2, h/2);
			else if (winner == 2) g.drawString("RED IS THE WINNER", w/2, h/2);
			Red = 0;
			Blue = 0;
		}


		repaint(1);
		try { Thread.currentThread().sleep(10); }
		catch (Exception ex) {}
	}

	public void keyPressed(KeyEvent e)
	{
		if (!finished && !stopped)
		{
			//BLUE-MOVEMENT (W-S-A-D, T for shoot)
			if (e.getKeyCode() == e.VK_W) yB -= mv;
			else if (e.getKeyCode() == e.VK_S) yB += mv;

			if (e.getKeyCode() == e.VK_D) xB += mv;
			else if (e.getKeyCode() == e.VK_A) xB -= mv;

			if (e.getKeyCode() == e.VK_T)
			{
				bShoot = true;   //a bullet was shot
				xbShoot = xB;   //start X posisiotn of the bullet is the position of the front of the helicopter
				ybShoot = yB;   //start Y posisiotn of the bullet is the position of the front of the helicopter
			}

			//RED_MOVEMENT (UP-DOWN-LEFT-RIGHT, P for shoot)
			if (e.getKeyCode() == e.VK_UP) yR -= mv;
			else if(e.getKeyCode() == e.VK_DOWN) yR += mv;

			if (e.getKeyCode() == e.VK_LEFT) xR -= mv;
			else if (e.getKeyCode() == e.VK_RIGHT) xR += mv;

			if (e.getKeyCode() == e.VK_P)
			{
				rShoot = true;   //a bullet was shot
				xrShoot = xR;   //start X posisiotn of the bullet is the position of the front of the helicopter
				yrShoot = yR;   //start Y posisiotn of the bullet is the position of the front of the helicopter
		}
		}

	}

	public void keyReleased(KeyEvent e)
	{

	}

	public void keyTyped(KeyEvent e)
	{

	}












}
