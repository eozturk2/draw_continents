package continent;

import java.util.ArrayList;
import java.util.Collections;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import acm.graphics.GLine;
import continent.Unit;
import java.util.concurrent.ThreadLocalRandom;


public class DrawContinents extends GraphicsProgram {
	static int HEIGHT = 600;
	static int WIDTH = 1200;
	static int MAXSTEP = 5*HEIGHT;
	static int DIVISIONS = 5;
	boolean TEST = false;
	boolean MAPTEST = false;
	boolean ITERATIONTEST = false;
	int x1, y1, xfin, yfin;
	private RandomGenerator rgen = new RandomGenerator();
	Unit[][] map = new Unit[HEIGHT][WIDTH];
	Unit fin = new Unit(0,0);
	
	ArrayList<Unit> past = new ArrayList<Unit>();
	
	public void trace(int x1, int y1, int maxstep) {
		//x1 = WIDTH/2;
		//y1 = HEIGHT/2;
		int x = x1;
		int y = y1;
		Unit jumpto = new Unit(0, 0);
		
		while(true) {
		int branch = 1;
		Unit[][] map = new Unit[HEIGHT][WIDTH];
		for (int j=0;j<map.length;j++) {
			for(int i=0;i<map[j].length;i++) {
				map[j][i] = new Unit(i,j);
				if(TEST) {System.out.print(map[j][i].num+" ");}
			}
		if(TEST) {System.out.print("\n");}
		}
		if (TEST){System.out.print(map[y][x].num+"\n");}
		int iterations = 0;
		while (true) {
			Unit start = map[y][x];
			start.passed = true;
            ArrayList<Unit> options = new ArrayList<Unit>();
            int s = 0;
            while (true){
            if(x==0 && y==0) {s = 1;break;}
            if(x==WIDTH-1 && y==0) {s = 2;break;}
            if(x==WIDTH-1 && y==HEIGHT-1) {s = 3;break;}
            if(x==0 && y==HEIGHT-1) {s = 4;break;}
            if(x==0) {s = 5;break;}
            if(y==0) {s = 6;break;}
            if(x==WIDTH-1) {s = 7;break;}
            if(y==HEIGHT-1) {s = 8;break;}
            break;
            }
            switch (s) { //edge check
            case 1: {
            		options.add(map[y+1][x+1]);  
            		options.add(map[y][x+1]);
            		options.add(map[y+1][x]);
            		break;
            	}
            case 2: {
                	options.add(map[y][x-1]);
                	options.add(map[y+1][x-1]);
                	options.add(map[y+1][x]);
                	break;
            	}
            case 3: {
            		options.add(map[y-1][x]);
            		options.add(map[y-1][x-1]);
            		options.add(map[y][x-1]);
            		break;
            	}
            case 4: {
            		options.add(map[y-1][x]);
            		options.add(map[y-1][x+1]);
            		options.add(map[y][x+1]);
            		break;
            	}
            case 5: {
            		options.add(map[y-1][x]);
            		options.add(map[y-1][x+1]);
            		options.add(map[y][x+1]);
            		options.add(map[y+1][x+1]);
            		options.add(map[y+1][x]);
            		break;
            	}
            case 6: {
            		options.add(map[y][x-1]);
            		options.add(map[y+1][x-1]);
            		options.add(map[y+1][x]);
            		options.add(map[y+1][x+1]);
            		options.add(map[y][x+1]);
            		break;
            	}
            	
            case 7: {
            		options.add(map[y-1][x]);
            		options.add(map[y+1][x]);
            		options.add(map[y-1][x-1]);
            		options.add(map[y][x-1]);
            		options.add(map[y+1][x-1]);
            		break;
            	}
            case 8: {
            		options.add(map[y][x-1]);
            		options.add(map[y][x+1]);
            		options.add(map[y-1][x-1]);
            		options.add(map[y-1][x]);
            		options.add(map[y-1][x+1]);
            		break;
            	}
            default: options.add(map[y][x-1]);
        		options.add(map[y][x+1]);
        		options.add(map[y-1][x-1]);
        		options.add(map[y-1][x]);
        		options.add(map[y-1][x+1]);
        		options.add(map[y+1][x-1]);
        		options.add(map[y+1][x]);
        		options.add(map[y+1][x+1]);
        		break;
            }
            	if(TEST){for(int n=0;n<options.size();n++) {
            		System.out.print("Weights of all options: "+options.get(n).num+" ");}System.out.print("\n");if(MAPTEST){break;}} //map testing
            	
            	ArrayList<Integer> optionsnum = new ArrayList<Integer>();
        
            	int a = options.size(); 
            	for(int n=0;n<a;n++) { 
            		if (options.get(n).passed) {
            			past.add(options.get(n));
            			options.remove(options.get(n));
            			a -= 1;
            		}else {
            	optionsnum.add(options.get(n).num);
            	}}
            	Collections.sort(optionsnum, Collections.reverseOrder());
            	if (TEST) {System.out.print("Sorted list of numbers of options: "+optionsnum+"\n");}
            	if (TEST) {System.out.print("Options: "+options+"\n");}
            	for(int n=0;n<a;n++) {  
            		if (optionsnum.size()==0) {
            			x = (int) past.get(past.size()-branch).getX();
            			y = (int) past.get(past.size()-branch).getY();
            			branch += 1;
                		break;
                	}
            		
            		if (optionsnum.get(0)==options.get(n).num) {
            			jumpto = options.get(n);
            			GLine line = new GLine(jumpto.getX(),jumpto.getY(),start.getX(),start.getY());
                    	add(line); 
                    	x = (int) jumpto.getX();
                    	if (TEST) {System.out.print("x coordinate of point to jump: "+x+"\n");}
                    	y = (int) jumpto.getY();
                    	if (TEST) {System.out.print("y coordinate of point to jump: "+y+"\n");} 
            			if (TEST) {System.out.print("Number of point to jump: "+jumpto.num+"\n");
            			break;
            		}
            	}
        
            }
				iterations +=1;
            	if(ITERATIONTEST) {break;} 
            	if(iterations == maxstep) {
            		
            		break;
            	}
            		
            	}
		xfin = x;
		yfin = y;
		jumpto.num = 0;
		break;
			}
		
		}
	public void trace(int x1, int y1, int x2, int y2) {
		//x1 = WIDTH/2;
		//y1 = HEIGHT/2;
		int x = x1;
		int y = y1;
		boolean done = false;
		
		while(true) {
		int branch = 1;
		while (true) {
			Unit start = map[y][x];
			start.passed = true;
            ArrayList<Unit> options = new ArrayList<Unit>();
            int s = 0;
            while (true){
                if(x==0 && y==0) {s = 1;break;}
                if(x==WIDTH-1 && y==0) {s = 2;break;}
                if(x==WIDTH-1 && y==HEIGHT-1) {s = 3;break;}
                if(x==0 && y==HEIGHT-1) {s = 4;break;}
                if(x==0) {s = 5;break;}
                if(y==0) {s = 6;break;}
                if(x==WIDTH-1) {s = 7;break;}
                if(y==HEIGHT-1) {s = 8;break;}
                break;
                }
            
            switch (s) { //edge check
            case 1: {
            		options.add(map[y+1][x+1]);  
            		options.add(map[y][x+1]);
            		options.add(map[y+1][x]);
            		break;
            	}
            case 2: {
                	options.add(map[y][x-1]);
                	options.add(map[y+1][x-1]);
                	options.add(map[y+1][x]);
                	break;
            	}
            case 3: {
            		options.add(map[y-1][x]);
            		options.add(map[y-1][x-1]);
            		options.add(map[y][x-1]);
            		break;
            	}
            case 4: {
            		options.add(map[y-1][x]);
            		options.add(map[y-1][x+1]);
            		options.add(map[y][x+1]);
            		break;
            	}
            case 5: {
            		options.add(map[y-1][x]);
            		options.add(map[y-1][x+1]);
            		options.add(map[y][x+1]);
            		options.add(map[y+1][x+1]);
            		options.add(map[y+1][x]);
            		break;
            	}
            case 6: {
            		options.add(map[y][x-1]);
            		options.add(map[y+1][x-1]);
            		options.add(map[y+1][x]);
            		options.add(map[y+1][x+1]);
            		options.add(map[y][x+1]);
            		break;
            	}
            	
            case 7: {
            		options.add(map[y-1][x]);
            		options.add(map[y+1][x]);
            		options.add(map[y-1][x-1]);
            		options.add(map[y][x-1]);
            		options.add(map[y+1][x-1]);
            		break;
            	}
            case 8: {
            		options.add(map[y][x-1]);
            		options.add(map[y][x+1]);
            		options.add(map[y-1][x-1]);
            		options.add(map[y-1][x]);
            		options.add(map[y-1][x+1]);
            		break;
            	}
            default: options.add(map[y][x-1]);
        		options.add(map[y][x+1]);
        		options.add(map[y-1][x-1]);
        		options.add(map[y-1][x]);
        		options.add(map[y-1][x+1]);
        		options.add(map[y+1][x-1]);
        		options.add(map[y+1][x]);
        		options.add(map[y+1][x+1]);
        		break;
            }
            	if(TEST){for(int n=0;n<options.size();n++) {
            		System.out.print("Weights of all options: "+options.get(n).num+" ");}System.out.print("\n");if(MAPTEST){break;}} //map testing
            	
            	ArrayList<Integer> optionsnum = new ArrayList<Integer>();
        
            	int a = options.size(); 
            	for(int n=0;n<a;n++) { 
            		if (options.get(n).passed) {
            			past.add(options.get(n));
            			options.remove(options.get(n));
            			a -= 1;
            		}else {
            	optionsnum.add(options.get(n).num);
            	}}
            	Collections.sort(optionsnum);
            	if (TEST) {System.out.print("Sorted list of numbers of options: "+optionsnum+"\n");}
            	if (TEST) {System.out.print("Options: "+options+"\n");}
            	for(int n=0;n<a;n++) {  
            		if (optionsnum.size()==0) {
            			x = (int) past.get(past.size()-branch).getX();
            			y = (int) past.get(past.size()-branch).getY();
            			branch += 1;
                		break;
                	}
            		if (optionsnum.get(0) == options.get(n).num) {
            			Unit jumpto = options.get(n);
            			double dist = Math.sqrt(Math.pow((x-x2), 2)+Math.pow((y-y2), 2));
            			if(dist <= 5) done = true;
            			GLine line = new GLine(jumpto.getX(),jumpto.getY(),start.getX(),start.getY());
                    	add(line); 
                    	x = (int) jumpto.getX();
                    	if (TEST) {System.out.print("x coordinate of point to jump: "+x+"\n");}
                    	y = (int) jumpto.getY();
                    	if (TEST) {System.out.print("y coordinate of point to jump: "+y+"\n");} 
            			if (TEST) {System.out.print("Number of point to jump: "+jumpto.num+"\n");
            			break;
            		}
            	}
        
            }
            	if(ITERATIONTEST) {break;}
            	//pause(25);
            	if((x == x2 && y == y2)||done) {
            		break;
            	}
            		
            }
			break;
			
		}
		
	}
	public void mapFirst() {
		for (int j=0;j<map.length;j++) {
			for(int i=0;i<map[j].length;i++) {
				map[j][i] = new Unit(i,j);
			}
		}
	}
	public void zeroIn(int radius, int divisions, int lowest, Unit point, double dista) {
		double r = dista/(radius/divisions);
		point.num = (ThreadLocalRandom.current().nextInt((int)((r/divisions)*lowest),(int)(((r+1)/divisions)*lowest)));
	}
	public void reMap(int PFx, int PFy, int p) {
		for(int j=0;j<map.length;j++) {
			for(int i=0;i<map[j].length;i++) {
				double dist = Math.sqrt(Math.pow((j-PFy), 2)+Math.pow((i-PFx), 2));
				int n = ((int)dist)/p;
				if (n == 0) {
					zeroIn(p,DIVISIONS,p,map[j][i],dist);
				}else{
				map[j][i].num = (rgen.nextInt(n*p,(n+1)*p));
				}
			}
		}
		}
	public void run() {
		this.resize(WIDTH, HEIGHT);
		Continent(WIDTH, HEIGHT, 2500);
		Continent(WIDTH+500, HEIGHT-20, 3000);
		Continent(WIDTH+100, HEIGHT-250, 500);
		Continent(WIDTH-200, HEIGHT-100, 200);
		}
	public void Continent(int xbound, int ybound,int maxstep) {
		mapFirst();
		trace(xbound/2,ybound/2, maxstep);
		reMap(xfin,yfin,10);
		trace(xbound/2, ybound/2, xfin, yfin);
	}
}