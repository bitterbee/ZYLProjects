precision mediump float;//给出默认的浮点精度

varying vec2 vTexCoord;//从顶点着色器传递过来的纹理坐标
uniform sampler2D sTexture;//纹理内容数据
uniform vec2 uPxD;           // pixel delta values

void main() {
	// 给出卷积内核中各个元素对应像素相对于待处理像素的纹理坐标偏移量
	vec2 offset0=vec2(-1.0,-1.0); vec2 offset1=vec2(0.0,-1.0); vec2 offset2=vec2(1.0,-1.0);
	vec2 offset3=vec2(-1.0,0.0); vec2 offset4=vec2(0.0,0.0); vec2 offset5=vec2(1.0,0.0);
	vec2 offset6=vec2(-1.0,1.0); vec2 offset7=vec2(0.0,1.0); vec2 offset8=vec2(1.0,1.0); 

	// 给出最终求和时的加权因子(为调整亮度)
	const float scaleFactor = 0.9;

	//卷积内核中各个位置的值
	float kernelValue = 1.0/9.0;
	float kernelValue0 = kernelValue; float kernelValue1 = kernelValue; float kernelValue2 = kernelValue;
	float kernelValue3 = kernelValue; float kernelValue4 = kernelValue; float kernelValue5 = kernelValue;
	float kernelValue6 = kernelValue; float kernelValue7 = kernelValue; float kernelValue8 = kernelValue;

	// 获取卷积内核中各个元素对应像素的颜色值
	vec4 p00 = texture2D(sTexture, vTexCoord + offset0.xy * uPxD.xy) * kernelValue0;
    vec4 p10 = texture2D(sTexture, vTexCoord + offset1.xy * uPxD.xy) * kernelValue1;
    vec4 p20 = texture2D(sTexture, vTexCoord + offset2.xy * uPxD.xy) * kernelValue2;
    vec4 p01 = texture2D(sTexture, vTexCoord + offset3.xy * uPxD.xy) * kernelValue3;
    vec4 p11 = texture2D(sTexture, vTexCoord + offset4.xy * uPxD.xy) * kernelValue4;
    vec4 p21 = texture2D(sTexture, vTexCoord + offset5.xy * uPxD.xy) * kernelValue5;
    vec4 p02 = texture2D(sTexture, vTexCoord + offset6.xy * uPxD.xy) * kernelValue6;
    vec4 p12 = texture2D(sTexture, vTexCoord + offset7.xy * uPxD.xy) * kernelValue7;
    vec4 p22 = texture2D(sTexture, vTexCoord + offset8.xy * uPxD.xy) * kernelValue8;

	//颜色求和
	vec4 clr = p00 + p01 + p02 +
               p10 + p11 + p12 +
               p20 + p21 + p22;

    // 灰度化
    float hd = (clr.r + clr.g + clr.b) / 3.0;

	//进行亮度加权后将最终颜色传递给管线
  	gl_FragColor = vec4(hd) * scaleFactor;
}