package javaCVTest2;



import org.bytedeco.javacpp.opencv_core.Mat;
import com.rookiex.image.util.JavaCVUtil;
import com.rookiex.image.util.MyUtil;
import com.rookiex.image.util.MyRoberts;


/**
 * @author RookieXu
 * @功能     实现视觉测量实验2，图像滤波与增强
 * @日期和时间 2016年12月20日 上午12:41:30
 * @版本 V0.2
 *
 */
public class Experiment3 {
	static int CV_BGR2GRAY = 6;

	public static void main(String[] args) {
		
		String filePath = "image/温度计下.bmp";  
		String filePath1 = "image/hui.jpg";
		
		String filePath2 = "image/新垣结衣.jpg";
		String filePath3 = "image/gaussian.jpg";
	
		Mat outGray = new Mat();

		JavaCVUtil util = new JavaCVUtil();
		MyUtil myUtil = new MyUtil(util);
		
	     //显示灰度图原图
		Mat src = myUtil.imRead(filePath2);  
		myUtil.imShow(src, "原图");
		//将图像二值化
		org.bytedeco.javacpp.opencv_imgproc.cvtColor(src, outGray, CV_BGR2GRAY);		
		myUtil.imShow(outGray, "灰度图");
		//Roberts算子处理
		Mat outRoberts = new Mat();			
		myUtil.imShow(MyRoberts.roberts(outGray, outRoberts), "Roberts图");
		//soble算子
		Mat outSobel = new Mat();	
		org.bytedeco.javacpp.opencv_imgproc.Sobel(outGray, outSobel, 0, 1, 1);
		myUtil.imShow(outSobel, "Sobel图");
		//canny算子处理
		Mat outCanny = new Mat();	
		double threshold1 = 70;
		double threshold2 = 100;				
		org.bytedeco.javacpp.opencv_imgproc.Canny(outGray, outCanny, threshold1, threshold2);
		myUtil.imShow(outCanny, "Canny图");
		
		
		/**
		Mat outRoberts = new Mat();			
		myUtil.imShow(MyRoberts.roberts(outGray, outRoberts), "Roberts图");
		util.AddGaussianNoise(out);
		
		myUtil.imShow(outGray, "灰度图");
		 *
		Mat out2 = myRoberts.roberts(out, out1);
		
		myUtil.imShow(out2, "roberts图");
		
		 */
	}
}
