#extension GL_OES_EGL_image_external : require
precision highp float;
precision highp int;
precision lowp samplerExternalOES;

varying highp vec2 v_TexCoordinateA;
varying highp vec2 v_TexCoordinateB;
uniform int modetype;

uniform samplerExternalOES u_Texture;
void main() {
    	highp vec4 colA = texture2D(u_Texture, v_TexCoordinateA);
    	highp vec4 colB = texture2D(u_Texture, v_TexCoordinateB);
        if(modetype ==1){
            gl_FragColor = colA;
        }else{
            gl_FragColor = colB;
        }
    }