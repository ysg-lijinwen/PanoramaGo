precision highp float;
precision highp int;
precision lowp sampler2D;

varying highp vec2 v_TexCoordinateA;
varying highp vec2 v_TexCoordinateB;
varying highp float v_pos_x;
uniform sampler2D u_Texture;
uniform int modetype;


void main() {
    	highp vec4 colA = texture2D(u_Texture, v_TexCoordinateA);
    	highp vec4 colB = texture2D(u_Texture, v_TexCoordinateB);
        if(modetype ==1){
            gl_FragColor = colA;
        }else{
            gl_FragColor = colB;
        }
     }