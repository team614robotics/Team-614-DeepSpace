package frc.lib;

public class TrapezoidalMotionProfile {	
	
	double pos; 
	double maxVelo;
	double maxAccel;

	double tToMaxVel;
	double dToMaxVel;

	double tToVf;
	double dToVf;

	double tCruising;
	double dCruising;
	
	double vi = 0;
	double vf = 0;
	
	boolean reverse = false;
	
	//                 Peak Velocity
	//                    |
	//                    v
	//     _________________________
	//    /                         \
	//   /                           \
	//  /                             \
	// /                               \ <- vF
	// ----------------------------------                              
	// ^
	// |
	// vI
	public TrapezoidalMotionProfile(double pos, double maxVelo, double maxAccel, double vi, double vf) {
		reverse = pos < 0;

		this.pos = pos = Math.abs(pos);
		this.maxVelo = maxVelo = Math.abs(maxVelo);
		this.maxAccel = maxAccel = Math.abs(maxAccel);
		this.vi = vi = Math.abs(vi);
		this.vf = vf = Math.abs(vf);
		
	    tToMaxVel = (maxVelo-vi)/maxAccel;
	    dToMaxVel = (vi*tToMaxVel) + 0.5*maxAccel*(tToMaxVel*tToMaxVel);

	    tToVf = (maxVelo-vf)/maxAccel;
	    dToVf = (maxVelo*tToVf) + 0.5*-maxAccel*(tToVf*tToVf);

	    dCruising = pos-dToMaxVel-dToVf;
	    tCruising = dCruising / maxVelo;

	    if ( Math.abs(pos) - Math.abs(dToMaxVel) - Math.abs(dToVf) < 0 ) {
			if ( vi == 0 && vf == 0 ) {
			    tToMaxVel = tToVf = Math.abs(Math.sqrt(2.0 * maxAccel * (pos / 2.0)) / maxAccel);
			    tCruising = dCruising = 0;
			    dToMaxVel = dToVf = pos / 2.0;
			    this.maxVelo = maxAccel*tToMaxVel;
			}
			else {
			    double sign = Math.signum(vf-vi);
			    double d = Math.abs((Math.pow(vf,2)-Math.pow(vi, 2))/(2*maxAccel*sign));
			    
			    if ( d <= pos ) {
			        this.maxVelo = maxVelo = Math.max(vi, vf);
			        dCruising = pos-d;
			        tCruising = dCruising / maxVelo;
			        
			        if ( sign > 0 ) {
			            tToMaxVel = (vf-vi)/maxAccel;
			            dToMaxVel = d;
			            
			            tToVf = 0;
			            dToVf = 0;
			        }
			        else {
			            tToMaxVel = 0;
			            dToMaxVel = 0;
			            
			            tToVf = (vi-vf)/maxAccel;
			            dToVf = d;
			        }
			    }
			    else {
			        this.maxAccel = maxAccel = ((vf*vf)-(vi*vi))/(2.0*pos);    
			        this.maxVelo = maxVelo = 0;
			        
			        if ( sign > 0 ) {
			            tToMaxVel = (vf-vi)/maxAccel;
			            dToMaxVel = pos;
			            
			            tToVf = 0;
			            dToVf = 0;
			            
			            dCruising = 0;
			            tCruising = 0;
			        }

			        else {
			            tToMaxVel = 0;
			            dToMaxVel = 0;
			            
			            tToVf = (vi-vf)/maxAccel;
			            dToVf = pos;
			            
			            dCruising = 0;
			            tCruising = 0;
			        }
			    }
			}
	    }
	}
	
	public TrapezoidalMotionProfile(double pos, double maxVelo, double maxAccel) {
		this(pos, maxVelo, maxAccel, 0, 0);
	}
	
    public static TrapezoidalMotionProfile triangularProfileInDuration(double t, double d) {
        t /= 2.0;
        double a = d/(t*t);
        double v = a*t;
        
        return new TrapezoidalMotionProfile(d, v, a, 0, 0);   
    }
    
    public static TrapezoidalMotionProfile triangularProfileInDuration(TrapezoidalMotionProfile matchThis, double d) {
        return triangularProfileInDuration(matchThis.getDuration(), d);
    }
    
	
	public ProfilePoint getAtTime(double t) {
		ProfilePoint point = new ProfilePoint();
		
	    if ( t > tToMaxVel+tToVf+tCruising ) {
	        t = tToMaxVel+tToVf+tCruising;
	    }
	    if ( t <= tToMaxVel && tToMaxVel != 0 ) {
	        point.pos = (vi*t) + (0.5) * maxAccel * (t*t);
	        point.vel = (maxAccel * t) + vi;
	        point.acc = maxAccel;
	    }
	    else if ( t < (tToMaxVel+tCruising) ) {
	        double i = t-tToMaxVel;
	        point.pos = dToMaxVel + (maxVelo * i);
	        point.vel = maxVelo;
	        point.acc = 0;
	    }
	    else if ( t <= (tToMaxVel+tToVf+tCruising) ) {
	        double i = t-tToMaxVel-tCruising;
	        point.pos = (dCruising + dToMaxVel) + (maxVelo * i) + (0.5) * -maxAccel * (i*i);
	        point.vel = maxVelo + ((-maxAccel) * i);
	        point.acc = -maxAccel;
	    }

	    if ( t == tToMaxVel+tToVf+tCruising ) {
	        point.pos = pos;
	        point.vel = vf;
	        if ( vf == 0 )
	            point.acc = 0;
	        else
	            point.acc = -maxAccel;
	    }
	    
	    if ( reverse ) {
	    	point.pos = -point.pos;
	    	point.vel = -point.vel;
	    	point.acc = -point.acc;
	    }
		return point;
	}
	
	public double getDuration() {
		return tToMaxVel + tToVf + tCruising;
	}
}