package javaCVTest2;



import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.UMat;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.opencv_imgproc;
import org.bytedeco.javacpp.helper.opencv_core.CvArr;

import com.rookiex.image.util.JavaCVUtil;
import com.rookiex.image.util.MyGussian;
import com.rookiex.image.util.MyHough;
import com.rookiex.image.util.MyUtil;

import javafx.scene.shape.Line;



/**
 * @author RookieXu
 * @����     ʵ���Ӿ�����ʵ��4,zhxinajiance
 * @���ں�ʱ�� 2016��12��20�� ����12:41:30
 * @�汾 V0.1
 *
 */
public class Experiment4 {
	static int CV_BGR2GRAY = 6;

	
	public static void main(String[] args) {
		//String filePath1 = "image/�¶ȼ���.bmp";  
		String filePath1 = "image/hui.jpg";
		//String filePath1 = "image/�Թ�.jpg";
		String filePath2 = "image/��ԫ����.jpg";
		String filePath3 = "image/gaussian.jpg";
	
		Mat outGray = new Mat();
		
		JavaCVUtil util = new JavaCVUtil();
		MyUtil myUtil = new MyUtil(util);
		
	     //��ʾ�Ҷ�ͼԭͼ
		Mat src = myUtil.imRead(filePath1);  
		myUtil.imShow(src, "ԭͼ");
		
		org.bytedeco.javacpp.opencv_imgproc.cvtColor(src, outGray, CV_BGR2GRAY);		
		myUtil.imShow(outGray, "�Ҷ�ͼ");		
		
		Mat outCanny = new Mat();	
				
		double threshold1 = 110;
		double threshold2 = 255;
				
		org.bytedeco.javacpp.opencv_imgproc.Canny(outGray, outCanny, threshold1, threshold2);
		myUtil.imShow(outCanny, "cannyͼ");
		
		
		
		//outHough = outCanny.clone();

		/**
		 * @param args
		int nbChannels = outHough.channels();  		 
        int h = outHough.rows();  
        int w = outHough.cols() * nbChannels ; 
        for(int i=0; i<h; i++) {
        	BytePointer o = outHough.ptr(i);	
        	for(int j=0;j<w;j++){
        		o.put(j,(byte)0);
        	}
        }		
		 */
		Mat hough = MyHough.myHough(outCanny, outGray, 0);
		myUtil.imShow(hough, "houghͼ1");
		
		UMat cvoutCanny = new UMat(outCanny);
		UMat cvoutHough = new UMat(outGray);
		
		//org.bytedeco.javacpp.opencv_imgproc.HoughLines(cvoutCanny, cvoutHough, org.bytedeco.javacpp.opencv_imgproc.HOUGH_STANDARD,1, 3.14/180, 120,0,0);
		//Mat outHough2 = new Mat(outHough);
		//myUtil.imShow(outHough2, "houghͼ");
		/*
		 * 
		 * 
		System.out.println(Hough.sizeof());
		Mat outHough = new Mat();
		org.bytedeco.javacpp.opencv_imgproc.HoughLines(outCanny, outHough, 1, 3.14/180, 50);
		 * 
		if (outHough.equals(null)){
	} else {
			System.out.println("houghΪ��----"+outHough.sizeof());
		}
		 * */
	}
}
