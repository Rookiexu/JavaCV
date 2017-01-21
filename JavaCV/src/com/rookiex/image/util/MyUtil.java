package com.rookiex.image.util;

import org.bytedeco.javacpp.opencv_core.Mat;

/**
 * @author RookieXu
 * @功能		util工具借口
 * @日期和时间 2016年12月18日 下午10:36:47
 * @版本		v0.1
 *
 */
public class MyUtil implements Util {
	
	private Util util;
	public MyUtil(Util util){
		this.util = util;
	}

	public void imShow(Mat mat, String title) {
		util.imShow(mat, title);
	}


	public Mat imRead(String filePath) {
		// 
		return util.imRead(filePath);
	}


	public boolean imWrite(Mat mat, String filePath) {
		// 
		return util.imWrite(mat, filePath);
	}


	public boolean containChinese(String inputString) {
		return util.containChinese(inputString);
	}


	public Mat salt(String filePath, int n) {
		
		return util.salt( filePath, n);
	}


	public Mat AddGaussianNoise(String filePath) {
		Mat mat = util.AddGaussianNoise(filePath);
		util.imShow(mat, "高斯噪声图");
		return mat;
	}
	
	public Mat AddGaussianNoise(Mat filePath) {
		Mat mat = util.AddGaussianNoise(filePath);
		util.imShow(mat, "高斯噪声图");
		return mat;
	}


	public Mat nonlinearity(String filePath) {
		Mat mat = util.nonlinearity( filePath);
		util.imShow(mat, "非线性增强");
		return mat;
	}


	public Mat linearity(String filePath, double coefficient) {
		Mat mat = util.linearity( filePath, coefficient);
		util.imShow(mat, "线性增强图");
		return mat;
	}


	public void medianBlur(String filePath, Mat out, int m) {
		util.medianBlur( filePath, out, m);
		util.imShow(out, "中值滤波"+m);

	}


	public void Blur(String filePath, Mat out, int m) {
		util.Blur( filePath, out, m);
		util.imShow(out, "均值滤波"+m);

	}

}
