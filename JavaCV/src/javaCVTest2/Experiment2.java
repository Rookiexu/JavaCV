package javaCVTest2;



import org.bytedeco.javacpp.opencv_core.Mat;

import com.rookiex.image.util.JavaCVUtil;
import com.rookiex.image.util.MyUtil;


/**
 * @author RookieXu
 * @功能 		实现视觉测量实验2，图像滤波与增强 
 * @日期和时间 2016年12月18日 下午10:32:32
 * @版本 v0.1
 *
 */
public class Experiment2 {
	
	public static void main(String[] args) {
		
		String filePath1 = "image/hui.jpg";
		String filePath2 = "image/新垣结衣.jpg";
		String filePath3 = "image/gaussian.jpg";
		
		Mat out = new Mat();
		
		JavaCVUtil util = new JavaCVUtil();
		MyUtil myUtil = new MyUtil(util);
		
	     //显示灰度图原图
		Mat image1 = myUtil.imRead(filePath1);  
		myUtil.imShow(image1, "灰度图");
		
		//线性增强
	     double coefficient = 1.5;
	     myUtil.linearity(filePath1, coefficient);
	      
	     //非线性增强
	     myUtil.nonlinearity(filePath1);

	     //加载新垣结衣原图并显示
	     Mat image2 = myUtil.imRead(filePath2);  
	     myUtil.imShow(image2, "新垣结衣");  
		
	   //加入高斯噪声并显示（因为加入过程耗时较长、改为直接处理噪声图
	     Mat image3 = myUtil.imRead(filePath3);
	     myUtil.imShow(image3,"【噪声图】");
	     
	   //中值滤波1 并显示 
	     myUtil.medianBlur(filePath3,out,3);  
	     
	 	//中值滤波2 并显示    
	     myUtil.medianBlur(filePath3,out,7);  

	     
	 	//均值滤波1 并显示       	   
	     myUtil.Blur(filePath3, out, 3);

	     
	 	//均值滤波2 并显示 
	     myUtil.Blur(filePath3, out, 7);

		/*
     
     //显示灰度图原图
     Mat image2 = JavaCVUtil.imRead("image/hui.jpg");           
     JavaCVUtil.imShow( image2,"【灰度图】"); 
     
     //线性增强
     double coefficient = 1.5;
     Mat out5 = JavaCVUtil.linearity(image2, coefficient);
     JavaCVUtil.imShow(out5,"线性增强【效果图】");  
    
     //非线性增强
     Mat out6 = JavaCVUtil.nonlinearity(image2);
     JavaCVUtil.imShow(out6,"非线性增强【效果图】"); 

     
     //加载原图并显示
     Mat image1 = JavaCVUtil.imRead("image/新垣结衣.jpg");           
     JavaCVUtil.imShow( image1,"【原图】");  

     //加入高斯噪声并显示（因为加入过程耗时较长、改为直接处理噪声图）
	//Mat out0 = JavaCVUtil.AddGaussianNoise(image1);
	//JavaCVUtil.imShow( out0,"【噪声图】"); 
     
     Mat out0 = JavaCVUtil.imRead("image/gaussian.jpg");
     JavaCVUtil.imShow( out0,"【噪声图】"); 
	
	//中值滤波1 并显示
	Mat out1 = new Mat();      
    opencv_imgproc.medianBlur(out0,out1,3);  
    JavaCVUtil.imShow(out1,"中值滤波5【效果图】");  
    
	//中值滤波2 并显示
    Mat out2 = new Mat();      
    opencv_imgproc.medianBlur(out0,out2,7);  
    JavaCVUtil.imShow(out2,"中值滤波11【效果图】"); 
    
	//均值滤波1 并显示
    Mat out3 = new Mat();          
    Size size5 = new Size(3, 3);
    opencv_imgproc.blur(out0, out3, size5);
    JavaCVUtil.imShow(out3,"均值滤波5*5【效果图】");  
    
	//均值滤波2 并显示 
    Mat out4 = new Mat();          
    Size size11 = new Size(7, 7);
    opencv_imgproc.blur(out0, out4, size11);
    JavaCVUtil.imShow(out4,"均值滤波11*11【效果图】");  
    
	*/
	}
}
