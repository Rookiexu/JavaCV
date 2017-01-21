package javaCVTest;

import org.bytedeco.javacpp.opencv_core.Mat;

import com.rookiex.image.util.JavaCVUtil;
import com.rookiex.image.util.MyUtil;
/** 
 * 显示一张图片 
 * @author rookiexu 
 * 修改时间：2016年12月17日 
 * @see javavcCameraTest   
 * @since  javacv1.3
 */ 
public class TestShowPhoto {
	 public static void main(String[] args) { 
		 JavaCVUtil util = new JavaCVUtil();
			MyUtil myUtil = new MyUtil(util);
		    String filePath = "image/温度计下.bmp";  
		    //读取mat  
		    Mat mat = myUtil.imRead(filePath);  
		    //显示图像  
		    myUtil.imShow(mat, "新垣结衣");  
		    //保存mat
		    myUtil.imWrite(mat, "image/新垣结衣-保存.png");  
		    }  
}
