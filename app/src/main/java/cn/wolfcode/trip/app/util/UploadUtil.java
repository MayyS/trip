package cn.wolfcode.trip.app.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传工具
 */
public class UploadUtil {

	//图片共享的的公共区域
	public static final String commonPath = "/Users/ftp1/ftp";

	/**
	 * 处理文件上传
	 * 
	 * @param file
	 * @param basePath
	 *            存放文件的目录的绝对路径 servletContext.getRealPath("/upload")
	 * @return  123.png
	 */
	public static String upload(MultipartFile file, String basePath) {
		//使用UUID生成一个永不重复的文件名
		String uuid = UUID.randomUUID().toString();

		//1.jpg
		String orgFileName = file.getOriginalFilename();
		String ext= "." + FilenameUtils.getExtension(orgFileName);
		String fileName = uuid + ext;
		try {
			File targetFile = new File(basePath);
			if(!targetFile.exists()){
				boolean flag=targetFile.mkdirs();
			}
			targetFile = new File(basePath, fileName);
			FileUtils.writeByteArrayToFile(targetFile, file.getBytes());

			return "/upload/" + fileName;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 删除文件
	 * @param imagePath
     */
	public static void deleteFile(ServletContext servletContext, String imagePath) {
		String path = servletContext.getRealPath("/") + imagePath;
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}
}
























