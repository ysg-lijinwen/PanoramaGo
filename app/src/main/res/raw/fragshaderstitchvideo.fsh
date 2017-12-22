#extension GL_OES_EGL_image_external : require
precision highp float;
precision highp int;
precision lowp samplerExternalOES;

varying highp float v_pos_x;
varying highp float v_pos_y;
varying highp float v_pos_z;
varying highp vec2 v_TexCoordinateA;
varying highp vec2 v_TexCoordinateB;

uniform samplerExternalOES u_Texture;

uniform bool forward;
uniform bool isYuv420;

uniform int devicetype;//设备类型 1为twin 2为thetas


uniform samplerExternalOES SamplerY;
uniform samplerExternalOES SamplerU;
uniform samplerExternalOES SamplerV;
uniform mat3 colorConversionMatrix;

uniform int modetype;
uniform float gamma;

uniform float yaw0;
uniform float yaw1;
float M_PI  = 3.14159265358979;
uniform float v_maskdegree;


vec4 getColor(vec4 colA,vec4 colB,float degree,float maskdegree);

void main() {
    highp vec4 colA;
    highp vec4 colB;
    
    if(!isYuv420){
        colA = texture2D(u_Texture, v_TexCoordinateA);
        colB = texture2D(u_Texture, v_TexCoordinateB);
        
    }else{
        mediump vec3 yuv;
        mediump vec3 yuv2;
        
        
        // Subtract constants to map the video range start at 0
//        yuv.x = (texture2D(SamplerY, v_TexCoordinateA).r - (16.0/255.0));
//        yuv.y = (texture2D(SamplerU, v_TexCoordinateA).r - 0.5);
//        yuv.z = (texture2D(SamplerV, v_TexCoordinateA).r - 0.5);
//        colA =  vec4(colorConversionMatrix * yuv,1);
//        
//        
//        
//        // Subtract constants to map the video range start at 0
//        yuv2.x = (texture2D(SamplerY, v_TexCoordinateB).r - (16.0/255.0));
//        yuv2.y = (texture2D(SamplerU, v_TexCoordinateB).r - 0.5);
//        yuv2.z = (texture2D(SamplerV, v_TexCoordinateB).r - 0.5);
//        colB = vec4(colorConversionMatrix * yuv2,1);
    }
    
    
    colA.a = 1.0;
    colB.a = 1.0;
    float alpha = 0.0;
    float  v_masksize = v_maskdegree/180.0;
    if(modetype==1){//全景模式
            float  point;
            point = v_pos_x;
            float  masksize = v_masksize;
            float  Midsize = 0.0;
            
            if(point < Midsize-masksize){
                alpha = 0.0;
            }else if( point >Midsize+masksize){
                alpha = 1.0;
            }else{
                float  index =  point - (Midsize - masksize);
                float al =  index * 1.0 / (masksize * 2.0);
                alpha = al;
            }
            if(yaw0 > yaw1)
            {
                alpha = 1.0 - alpha;
            }
            vec4 t_rgb = colA * alpha + colB * (1.0 - alpha);
            vec3 v_rgb = pow( vec3(t_rgb.r,t_rgb.g,t_rgb.b),  vec3(1.0/gamma) );
            gl_FragColor = vec4(v_rgb,1.0);
        
    }else if(modetype==2){//平面模式
     
            
            //180 R //7 R1
            float degree = v_pos_x*180.0+180.0;
            float maskdegree = v_maskdegree;
            
            float R = 90.0;
            float R1 = v_maskdegree / 2.0;
            
            
            float pos_y_d =  (v_pos_y + 1.0) * 90.0;
            
            if(pos_y_d <  R1 || pos_y_d >  180.0 - R1){
                
                maskdegree = 90.0;
            }
            else{
                if(pos_y_d < 90.0){
                    pos_y_d = 90.0  - pos_y_d;
                }else{
                    pos_y_d = pos_y_d - 90.0;
                }
                
                //calc blend width
                maskdegree = (sqrt(R * R - ( pos_y_d) * (pos_y_d))- sqrt((R - R1) * (R - R1) - ( pos_y_d) * (pos_y_d)))/ sqrt(R * R - ( pos_y_d) * ( pos_y_d)) * 90.0;
                
                
            }
            
            vec4 cola1 = colA;
            vec4 colb1 = colB;
            if(yaw0 > yaw1)
            {
                cola1 = colB;
                colb1 = colA;
            }
           vec4 t_rgb = getColor(cola1,colb1,degree,maskdegree);
           vec3 v_rgb = pow( vec3(t_rgb.r,t_rgb.g,t_rgb.b),  vec3(1.0/gamma) );
           gl_FragColor = vec4(v_rgb,1.0);
    }else if(modetype==3){//前后模式
        gl_FragColor  = vec4(255.0,0.0,0.0,1.0);
    }
}





vec4 getColor(vec4 colA,vec4 colB,float degree,float maskdegree){
    vec4 col;
    float yaw00 = 90.0;
    float yaw11 = 270.0;
    if(yaw00<yaw11)
    {
        if(yaw11+maskdegree>360.0){
            if(degree<maskdegree*2.0){
                degree+=360.0;
            }
        }
        if(yaw00-maskdegree<0.0){
            if(degree>(360.0-2.0*maskdegree)){
                degree-=360.0;
            }
        }
        if(degree>(yaw00+maskdegree)&&degree<=(yaw11-maskdegree)){
            col = colB;
        }else if(degree>(yaw11-maskdegree)&&degree<=(yaw11+maskdegree)){
            float  index =  degree - (yaw11 - maskdegree);
            float al =  index * 1.0 / (maskdegree * 2.0);
            col = colA * al + colB * (1.0 - al);
        }else if(degree>(yaw00-maskdegree)&&degree<=(yaw00+maskdegree)){
            float  index =  degree - (yaw00 - maskdegree);
            float al =  index * 1.0 / (maskdegree * 2.0);
            col = colB * al + colA * (1.0 - al);
        }else {
            col = colA;
        }
    }
    return col;
}
