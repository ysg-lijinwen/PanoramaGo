#extension GL_OES_EGL_image_external : require
precision highp float;
precision highp int;
precision lowp samplerExternalOES;
varying highp float v_pos_x;
varying highp float v_pos_z;
varying highp vec2 v_TexCoordinate0;
varying highp vec2 v_TexCoordinate1;
varying highp vec2 v_TexCoordinate2;
varying highp vec2 v_TexCoordinate3;

uniform samplerExternalOES u_Texture0;
uniform samplerExternalOES u_Texture1;
uniform samplerExternalOES u_Texture2;
uniform samplerExternalOES u_Texture3;

uniform int modetype;
uniform float yaw0;
uniform float yaw1;
uniform float yaw2;
uniform float yaw3;

highp float M_PI  = 3.14159265358979;
  
vec4 get3DCol(vec4 col0,vec4 col1,vec4 col2,vec4 col3,float angledegree){
	vec4 col;
	if(yaw0<yaw3&&yaw3<yaw2&&yaw2<yaw1)
	{	
		if(yaw1+3.0>360.0){
			if(angledegree<6.0){
				angledegree+=360.0;
			}
		}
		if(yaw0-3.0<0.0){
			if(angledegree>324.0){
				angledegree-=360.0;
			}
		}
		if(angledegree>(yaw0+3.0)&&angledegree<=(yaw3-3.0)){
  			col = col3;
  		}else if(angledegree>(yaw3-3.0)&&angledegree<=(yaw3+3.0)){
			float  index =  angledegree - (yaw3 - 3.0);
			float al =  index * 1.0 / (3.0 * 2.0);
 			col = col2 * al + col3 * (1.0 - al);	  		
	 	}else if(angledegree>(yaw3+3.0)&&angledegree<=(yaw2-3.0)){
			col = col2;
	  	}else if(angledegree>(yaw2-3.0)&&angledegree<=(yaw2+3.0)){
			float  index =  angledegree - (yaw2 - 3.0);
			float al =  index * 1.0 / (3.0 * 2.0);
	 		col = col1 * al + col2 * (1.0 - al);
		}else if(angledegree>(yaw2+3.0)&&angledegree<=(yaw1-3.0)){
	  		col = col1;
	  	}else if(angledegree>(yaw1-3.0)&&angledegree<=(yaw1+3.0)){
			float  index =  angledegree - (yaw1 - 3.0);
			float al =  index * 1.0 / (3.0 * 2.0);
	 		col = col0 * al + col1 * (1.0 - al);
	 	}else if(angledegree>(yaw1+3.0)||angledegree<=(yaw0-3.0)){
			col = col0;
	   	}else if(angledegree>(yaw0-3.0)&&angledegree<=(yaw0+3.0)){
			float  index =  angledegree - (yaw0- 3.0);
			float al =  index * 1.0 / (3.0 * 2.0);
			col = col3 * al + col0 * (1.0 - al);	
 		}
	}else if(yaw1<yaw0&&yaw0<yaw3&&yaw3<yaw2){
		if(yaw2+3.0>360.0){
			if(angledegree<6.0){
				angledegree+=360.0;
			}
		}
		if(yaw1-3.0<0.0){
			if(angledegree>324.0){
				angledegree-=360.0;
			}
		}
		if(angledegree>(yaw1+3.0)&&angledegree<=(yaw0-3.0)){
  			col = col0;
  		}else if(angledegree>(yaw0-3.0)&&angledegree<=(yaw0+3.0)){
			float  index =  angledegree - (yaw0 - 3.0);
			float al =  index * 1.0 / (3.0 * 2.0);
 			col = col3 * al + col0 * (1.0 - al);	  		
	 	}else if(angledegree>(yaw0+3.0)&&angledegree<=(yaw3-3.0)){
  			col = col3;
  		}else if(angledegree>(yaw3-3.0)&&angledegree<=(yaw3+3.0)){
			float  index =  angledegree - (yaw3 - 3.0);
			float al =  index * 1.0 / (3.0 * 2.0);
 			col = col2 * al + col3 * (1.0 - al);	  		
	 	}else if(angledegree>(yaw3+3.0)&&angledegree<=(yaw2-3.0)){
	  		col = col2;
	  	}else if(angledegree>(yaw2-3.0)&&angledegree<=(yaw2+3.0)){
			float  index =  angledegree - (yaw2 - 3.0);
			float al =  index * 1.0 / (3.0 * 2.0);
	 		col = col1 * al + col2 * (1.0 - al);
	 	}else if(angledegree>(yaw2+3.0)||angledegree<=(yaw1-3.0)){
	  		col = col1;
	  	}else if(angledegree>(yaw1-3.0)&&angledegree<=(yaw1+3.0)){
	   		float  index =  angledegree - (yaw1- 3.0);
			float al =  index * 1.0 / (3.0 * 2.0);
	 		col = col0 * al + col1 * (1.0 - al);	
	   	}
 	}else if(yaw2<yaw1&&yaw1<yaw0&&yaw0<yaw3){
 		if(yaw3+3.0>360.0){
			if(angledegree<6.0){
	  			angledegree+=360.0;
			}
		}
		if(yaw2-3.0<0.0){
			if(angledegree>324.0){
				angledegree-=360.0;
			}
		}
		if(angledegree>(yaw2+3.0)&&angledegree<=(yaw1-3.0)){
  			col = col1;
  		}else if(angledegree>(yaw1-3.0)&&angledegree<=(yaw1+3.0)){
			float  index =  angledegree - (yaw1 - 3.0);
			float al =  index * 1.0 / (3.0 * 2.0);
 			col = col0 * al + col1 * (1.0 - al);	  		
	 	}else if(angledegree>(yaw1+3.0)&&angledegree<=(yaw0-3.0)){
  			col = col0;
  		}else if(angledegree>(yaw0-3.0)&&angledegree<=(yaw0+3.0)){
			float  index =  angledegree - (yaw0 - 3.0);
			float al =  index * 1.0 / (3.0 * 2.0);
 			col = col3 * al + col0 * (1.0 - al);	  		
	 	}else if(angledegree>(yaw0+3.0)&&angledegree<=(yaw3-3.0)){
  			col = col3;
  		}else if(angledegree>(yaw3-3.0)&&angledegree<=(yaw3+3.0)){
			float  index =  angledegree - (yaw3 - 3.0);
			float al =  index * 1.0 / (3.0 * 2.0);
 			col = col2 * al + col3 * (1.0 - al);	  		
		}else if(angledegree>(yaw3+3.0)||angledegree<=(yaw2-3.0)){
			col = col2;
	   	}else if(angledegree>(yaw2-3.0)&&angledegree<=(yaw2+3.0)){
			float  index =  angledegree - (yaw2- 3.0);
			float al =  index * 1.0 / (3.0 * 2.0);
	 		col = col1 * al + col2 * (1.0 - al);	
	   	}
 	}else if(yaw3<yaw2&&yaw2<yaw1&&yaw1<yaw0){
 		if(yaw0+3.0>360.0){
			if(angledegree<6.0){
				angledegree+=360.0;
			}
		}
		if(yaw3-3.0<0.0){
			if(angledegree>324.0){
				angledegree-=360.0;
			}
		}			
		if(angledegree>(yaw3+3.0)&&angledegree<=(yaw2-3.0)){
  			col = col2;
  		}else if(angledegree>(yaw2-3.0)&&angledegree<=(yaw2+3.0)){
			float  index =  angledegree - (yaw2 - 3.0);
			float al =  index * 1.0 / (3.0 * 2.0);
 			col = col1 * al + col2 * (1.0 - al);	  		
	 	}else if(angledegree>(yaw2+3.0)&&angledegree<=(yaw1-3.0)){
  			col = col1;
  		}else if(angledegree>(yaw1-3.0)&&angledegree<=(yaw1+3.0)){
			float  index =  angledegree - (yaw1 - 3.0);
			float al =  index * 1.0 / (3.0 * 2.0);
 			col = col0 * al + col1 * (1.0 - al);	  		
	 	}else if(angledegree>(yaw1+3.0)&&angledegree<=(yaw0-3.0)){
	  		col = col0;
	  	}else if(angledegree>(yaw0-3.0)&&angledegree<=(yaw0+3.0)){
			float  index =  angledegree - (yaw0 - 3.0);
			float al =  index * 1.0 / (3.0 * 2.0);
	 		col = col3 * al + col0 * (1.0 - al);
	 	}else if(angledegree>(yaw0+3.0)||angledegree<=(yaw3-3.0)){
	  		col = col3;
	   	}else if(angledegree>(yaw3-3.0)&&angledegree<=(yaw3+3.0)){
			float  index =  angledegree - (yaw3- 3.0);
			float al =  index * 1.0 / (3.0 * 2.0);
	 		col = col2 * al + col3 * (1.0 - al);	
	   	}
 	}
	return col;
}
void main() {
    highp vec4 col0 = texture2D(u_Texture0, v_TexCoordinate0);
    highp vec4 col1 = texture2D(u_Texture1, v_TexCoordinate1);
    highp vec4 col2 = texture2D(u_Texture2, v_TexCoordinate2);
    highp vec4 col3 = texture2D(u_Texture3, v_TexCoordinate3);
  	float  xx = v_pos_x;
  	float  zz = v_pos_z;
  	if(modetype==1){//全景模式
  		float angle = acos(xx/sqrt(zz * zz + xx * xx));
  		if(zz<0.0){
  			angle = M_PI*2.0- angle;
  		}
  		float angledegree= angle/M_PI*180.0;
 		gl_FragColor = get3DCol(col0,col1,col2,col3,angledegree);
	 }
	 else if(modetype==2){//平面模式
		float degree = xx*180.0+180.0;
		gl_FragColor = get3DCol(col0,col1,col2,col3,degree);
 	 }
 }