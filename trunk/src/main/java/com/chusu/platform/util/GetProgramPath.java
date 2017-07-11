package com.chusu.platform.util;

/**
 * 创建时间：2015-1-14 下午3:58:14 项目名称：PathTest 2015-1-14
 * 
 * @author 张杰
 * @version 1.0 文件名称：GetProgramPath.java
 *          类说明：通过默认web目录结构关系或通过指定工程名称，获取当前工程下根目录绝对路径，
 *          主要用于获取Tomcat下war包部署的Web工程的根目录 适用于Window和Linux环境，
 */
public class GetProgramPath {
	public String DestDir = "WEB-INF";
	//是否指定目录
	private boolean flag=false;

	// 默认采用Web项目下build文件夹与根目录的关系得到项目根目录的绝对路径
	public GetProgramPath() {
		flag=false;
		this.DestDir = "WEB-INF";
	}

	// 利用项目的名字获取根目录的绝对路径
	public GetProgramPath(String programName) {
		flag=true;
		this.DestDir = programName;
	}

	// 利用字符串拼凑方法获取项目中的根目录结构
	public String getPath() {
		// 获取获得当前类所在路径：Window下形如file:/F:/work_litao/uri_test/WebContent/WEB-INF/classes/
		String base = this.getClass().getResource("").getPath();
		// base="E:\\hell\\wile\\my.code";
		System.out.println(base);
		// 字符分割
		String regex = "/";
		// 判断分隔符
		String[] temp = null;
		if (base.contains("\\")) {
			regex = "\\";
			temp = base.split(regex + regex);
		}
		if (base.contains("/")) {
			regex = "/";
			temp = base.split(regex);
		}
		String DestPath = "";
		// 开头存在/ 去掉/前的空元素  否则不去掉
		int i = 0;
		if (temp[0].equals("")) {
			i = 1;
		} else {
			i = 0;
		}
		// 匹配目标文件夹
		for (; i < temp.length - 1; i++) {
			// System.out.println(temp[i]);
			DestPath += temp[i] + regex;
			// 当前目录等于目标目录或者下级目录为build时跳出循环
			if (temp[i].equalsIgnoreCase(DestDir )&& flag) {
				break;
			}
			if (temp[i + 1].equalsIgnoreCase(DestDir)&& !flag) {
				break;
			}
		}
		//Linux系统下加上开头"/"
		String OS = System.getProperty("os.name").toLowerCase();
		if( OS.indexOf( "linux" ) >= 0 ){
			DestPath="/"+DestPath;
		}
		System.out.println(DestPath);
		return DestPath;
	}

	public static void main(String[] args) {
		GetProgramPath getpath = new GetProgramPath();
		getpath.getPath();

	}

}
