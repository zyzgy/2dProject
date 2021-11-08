import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class BallGame extends JFrame{

	Image desk = Toolkit.getDefaultToolkit().getImage("desk.jpg");
	Image whiteBall = Toolkit.getDefaultToolkit().getImage("ball.png");
	Image redBall = Toolkit.getDefaultToolkit().getImage("ball_red.png");
	Image orangeBall = Toolkit.getDefaultToolkit().getImage("ball_orange.png");
	Image purpleBall = Toolkit.getDefaultToolkit().getImage("ball_purple.png");
	double x= 200;
	double y= 250;
	
	double x_red= 300;
	double y_red= 250;
	
	double x_orange= 330;
	double y_orange= 290;
	
	double x_purple= 330;
	double y_purple= 230;
	
	
	double speed = 0;
	double speed_red = 0;
	//Set the movement angle of the white ball	
	double degree =0;
	double degree_red =0;
	
	double speed_orange = 0;
	double degree_orange =0;
	
	double speed_purple = 0;
	double degree_purple =0;

	
	double startx;
	double starty;
	double endx;
	double endy;
	
	int score;
	
	boolean showLine=false;
	//Judge whether it fails
	//boolean isFail=true;
	Image offScreenImage =null;
		
	public void launch() {
		this.setVisible(true);
		this.setSize(856,524);
		this.setLocation(100,210);
		this.setTitle("Billiards Game");
		this.setResizable(false);
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				//The left mouse button is clicked
				if (e.getButton()==1) {
					showLine=true;
					//Get the coordinates of the center of the ball
					startx=x+15;
					starty=y+15;
					
					endx=e.getX();
					endy=e.getY();
					
					//System.out.println(getAngle(startx,starty,endx,endy));
					//Change the direction of the white ball
					degree=getAngle(startx,starty,endx,endy);
					
					
				}
				//The right mouse button is clicked
				if (e.getButton()==3) {
					showLine=false;
					speed=15;
				}
			
			}
		});
		
	
		
		while (true) {
			repaint();
			try {
				Thread.sleep(40);//Execute once every 40 milliseconds, 25 times every second
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//Draw the content
	@	Override
	public void paint(Graphics g) {
		//Double Buffer
		if(offScreenImage==null) {
			offScreenImage=this.createImage(856,524);
		}
		 Graphics gImage= offScreenImage.getGraphics();
	     gImage.fillRect(0, 0, 856, 501);
	     gImage.drawImage(desk,0,20,null);
	     gImage.drawImage(whiteBall,(int)x,(int)y,null);
	     gImage.drawImage(redBall,(int)x_red,(int)y_red,null);
	     gImage.drawImage(orangeBall,(int)x_orange,(int)y_orange,null);
	     gImage.drawImage(purpleBall,(int)x_purple,(int)y_purple,null);

	     //change the color
	     gImage.setColor(Color.ORANGE);
	     
	     if (showLine) {
	    	 gImage.drawLine((int)startx, (int)starty, (int)endx, (int)endy);
	     }
			
			
		
		gImage.setColor(Color.white);
		gImage.setFont(new Font("Arial",Font.BOLD,18));
		gImage.drawString("Score:"+score, 730, 100);
			
	     
	     
	     
	     //print the line 
	     g.drawImage(offScreenImage, 0, 0, null);
		
		
		
		//g.drawImage(desk, 0, 20, null);
	    //The white ball is 200 from the left of the window and 220 from the top of the window
		//g.drawImage(whiteBall, (int)x, (int)y, null);
		



		if(Math.sqrt((x-x_purple)*(x-x_purple)+(y-y_purple)*(y-y_purple)) <32){
			
			speed_purple=speed;
			degree_purple=0.5*Math.PI-degree;
			degree =Math.PI+degree;
			
			}	
		if(Math.sqrt((x-x_red)*(x-x_red)+(y-y_red)*(y-y_red)) <32){
			
			speed_red=speed;
			degree_red=0.5*Math.PI-degree;
			degree =Math.PI+degree;
			
			}
		
		if(Math.sqrt((x-x_orange)*(x-x_orange)+(y-y_orange)*(y-y_orange)) <32){
			
			speed_orange=speed;
			degree_orange=0.5*Math.PI-degree;
			degree =Math.PI+degree;
			
			}

		
		//The angle changes when the ball hits the upper and lower borders
		if(y<95 || y>445) {
			degree =-degree;
			
		}
		//The angle changes when the ball hits the right and left borders	
		if(x<33 || x>780) {
			degree =Math.PI-degree;
			
		}
		if((x_red>30 && x_red<60&& y_red>80&& y_red<100) || (x_red>370 && x_red<390&& y_red>80 && y_red<100) || (x_red>755 && x_red<780&& y_red>80 && y_red<100)
	|| (x_red>30 && x_red<60&& y_red>440&& y_red<445) || (x_red>370 && x_red<390&& y_red>440 && y_red<445) || (x_red>755 && x_red<780&& y_red>440 && y_red<445)) {
			
			score++;
			speed_red=0;
			x_red= 200;
			y_red= 220;
			
		}
		if((x_orange>30 && x_orange<60&& y_orange>80&& y_orange<100) || (x_orange>370 && x_orange<390&& y_orange>80 && y_orange<100) || (x_orange>755 && x_orange<780&& y_orange>80 && y_orange<100)
				|| (x_orange>30 && x_orange<60&& y_orange>440&& y_orange<445) || (x_orange>370 && x_orange<390&& y_orange>440 && y_orange<445) || (x_orange>755 && x_orange<780&& y_orange>440 && y_orange<445)) {
						
						score=score+2;
						speed_orange=0;
						x_orange= 200;
						y_orange= 400;
						
					}
		if((x_purple>30 && x_purple<60&& y_purple>80&& y_purple<100) || (x_purple>370 && x_purple<390&& y_purple>80 && y_purple<100) || (x_purple>755 && x_purple<780&& y_purple>80 && y_purple<100)
				|| (x_purple>30 && x_purple<60&& y_purple>440&& y_purple<445) || (x_purple>370 && x_purple<390&& y_purple>440 && y_purple<445) || (x_purple>755 && x_purple<780&& y_purple>440 && y_purple<445)) {
						
						score=score+3;
						speed_purple=0;
						x_purple= 200;
						y_purple= 340;
						
					}
	
	
		//The angle changes when the red ball hits the right and left borders	
		if(x_red<30 || x_red>780) {
					degree_red =-degree_red;
					
				}
		//The angle changes when the red ball hits the upper and lower borders
		if(y_red<95 || y_red>445) {
					degree_red =Math.PI-degree_red;
					
				}
				

		//The angle changes when the orange ball hits the right and left borders	
		if(x_orange<30 || x_orange>780) {
					degree_orange =-degree_orange;
					
				}
		//The angle changes when the orange ball hits the upper and lower borders
		if(y_orange<95 || y_orange>445) {
					degree_orange =Math.PI-degree_orange;
					
				}

		//The angle changes when the purple ball hits the right and left borders	
		if(x_purple<30 || x_purple>780) {
					degree_purple =-degree_purple;
					
				}
		//The angle changes when the purple ball hits the upper and lower borders
		if(y_purple<95 || y_purple>445) {
					degree_purple =Math.PI-degree_purple;
					
				}
		


		
		
		//Ball collision determination
		if(Math.sqrt((x_orange-x_red)*(x_orange-x_red)+(y_orange-y_red)*(y_orange-y_red)) <30){
			if(speed_red!=0) {
			speed_orange=speed_red;
			degree_orange=Math.PI-degree_red;
			degree_red =Math.PI+degree_red;
			}
			if(speed_orange!=0){
				speed_red=speed_orange;
				degree_red=Math.PI-degree_orange;
				degree_orange =Math.PI+degree_orange;
			}
				
			}

		//Purple ball
	
		
		if(Math.sqrt((x_purple-x_red)*(x_purple-x_red)+(y_purple-y_red)*(y_purple-y_red)) <30){
			if(speed_red!=0) {
			speed_purple=speed_red;
			degree_purple=Math.PI-degree_red;
			degree_red =Math.PI+degree_red;
			}
			if(speed_purple!=0){
				speed_red=speed_purple;
				degree_red=Math.PI-degree_purple;
				degree_purple =Math.PI+degree_purple;
				}
			}
		
		if(Math.sqrt((x_purple-x_orange)*(x_purple-x_orange)+(y_purple-y_orange)*(y_purple-y_orange)) <30){
			if(speed_orange!=0) {
			speed_purple=speed_orange;
			degree_purple=Math.PI-degree_orange;
			degree_orange =Math.PI+degree_orange;
			} 
			if(speed_purple!=0){
				speed_orange=speed_purple;
				degree_orange=Math.PI-degree_purple;
				degree_purple =Math.PI+degree_purple;
				}else {
					speed_purple=3;
				}
					
				
			}

		if(speed>0) {
			speed =speed-0.18;//Coefficient of friction
			x=x+speed*Math.cos(degree);
			y=y+speed*Math.sin(degree);
		}

		if(speed_red>0) {
			speed_red =speed_red-0.18;
			x_red=x_red+speed_red*Math.sin(degree_red);
			y_red=y_red+speed_red*Math.cos(degree_red);
								
		}
		if(speed_orange>0) {
			speed_orange =speed_orange-0.18;
			x_orange=x_orange+speed_orange*Math.sin(degree_orange);
			y_orange=y_orange+speed_orange*Math.cos(degree_orange);
								
		}
		if(speed_purple>0) {
			speed_purple =speed_purple-0.18;
			x_purple=x_purple+speed_purple*Math.sin(degree_purple);
			y_purple=y_purple+speed_purple*Math.cos(degree_purple);
								
		}

		

		}
		

	 public double getAngle(double startx,double starty,double endx,double endy){
	        double tempx = endx - startx;
	        double tempy = endy - starty;
	        //Diagonal length
	        double z = Math.sqrt(tempx * tempx + tempy * tempy);
	        //0 to pi/2
	        double angle = (float)(Math.asin(Math.abs(tempy)/z));

	        if (tempx > 0 && tempy < 0){
	            //First quadrant
	            angle = Math.PI * 2 - angle;
	        } else if (tempx < 0 && tempy < 0){
	            //Second quadrant
	            angle = Math.PI + angle;
	        } else if (tempx < 0 && tempy > 0){
	            //Third quadrant
	            angle = Math.PI - angle;
	        } else if (tempx > 0 && tempy > 0){
	            //Forth quadrant
	        }
	        return angle;
	        
	        
	    }
	 
	 
	
	
public static void main(String[] args) {
		BallGame bg= new BallGame();
		bg.launch();

	}
}
