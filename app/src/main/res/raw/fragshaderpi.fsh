precision mediump float; 
uniform sampler2D u_Texture;
varying vec2 v_TexCoordinate; 
varying highp float v_pos_x;

uniform bool forward; 
uniform bool front;

uniform bool isYuv420;


uniform sampler2D SamplerY;
uniform sampler2D SamplerU;
uniform sampler2D SamplerV;
uniform mat3 colorConversionMatrix;


//是否使用滤镜效果
uniform bool useFliter;

//亮度   -1.0 to 1.0, with 0.0 as the normal level
uniform lowp float brightness;
//饱和度 0.0 - 2.0, with 1.0 as the default
uniform  highp float saturation;
//对比度 0.0 to 4.0, with 1.0 as the normal level
uniform  lowp float contrast;
//曝光
uniform lowp float exposure;
//Gamma
uniform lowp float gamma;

const mediump vec3 luminanceWeighting = vec3(0.213, 0.715, 0.072);
 
void main(){ 
 	if(forward)
	{
		if(v_TexCoordinate.y < 0.999 && v_TexCoordinate.y >0.001){
     	   gl_FragColor = texture2D(u_Texture, v_TexCoordinate);
   		 }else {
    	   gl_FragColor  = vec4(0.0,0.0,0.0,1.0);
		}
 	}else if(front){
 		if(v_pos_x >0.65||v_pos_x <-0.65){
			gl_FragColor  = vec4(0.0,0.0,0.0,1.0);
		}else {
				gl_FragColor = texture2D(u_Texture, v_TexCoordinate);
		}
 	}else{	
		if(!isYuv420){
            gl_FragColor = texture2D(u_Texture, v_TexCoordinate);
        }else{
            mediump vec3 yuv;
            mediump vec3 rgb;
             // Subtract constants to map the video range start at 0
            yuv.x = (texture2D(SamplerY, v_TexCoordinate).r - (16.0/255.0));
            yuv.y = (texture2D(SamplerU, v_TexCoordinate).r - 0.5);
            yuv.z = (texture2D(SamplerV, v_TexCoordinate).r - 0.5);
            rgb = colorConversionMatrix * yuv;
            gl_FragColor = vec4(rgb,1);
          }	
        
    }
	
	if(useFliter){
	//原图
	lowp vec4 textureColor = texture2D(u_Texture, v_TexCoordinate);
	//亮度
	lowp vec4 brightness_v=vec4((textureColor.rgb + vec3(brightness)), textureColor.w);
	//曝光
	lowp vec4 exposure_v=vec4(brightness_v.rgb * pow(1.5, exposure), brightness_v.w);
	
	//饱和度
	lowp float luminance = dot(exposure_v.rgb, luminanceWeighting);
	lowp vec3 greyScaleColor = vec3(luminance);
    lowp vec4 saturation_v = vec4(mix(greyScaleColor, exposure_v.rgb, saturation), exposure_v.w);
	
	//对比度
	gl_FragColor = vec4(((saturation_v.rgb - vec3(0.5)) * contrast + vec3(0.5)), saturation_v.w);
	
	}else{
	
	gl_FragColor = texture2D(u_Texture, v_TexCoordinate);
	}
 }
