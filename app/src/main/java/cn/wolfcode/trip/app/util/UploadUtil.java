package cn.wolfcode.trip.app.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 文件上传工具
 */
public class UploadUtil {

	public static final String relativePath = "/upload/";
	public static final String commonPath = "D:/images";

	/**
	 * 处理文件上传
	 * @param file
	 * @param basePath      
	 * @return  123.png
	 */
	public static String upload(MultipartFile file, String basePath) {

		String uuid=UUID.randomUUID().toString();
		String orgFileName=file.getOriginalFilename();
		String ext="."+ FilenameUtils.getExtension(orgFileName);
		String fileName=uuid+ext;
		/*String timeStamp= DateFormat.getDateTimeInstance().format(new Date());
		Long ts=System.currentTimeMillis();
		String base=basePath+"/"+String.valueOf(ts);*/

		try {
			File targetFile=new File(basePath);
			if(!targetFile.exists()){
				boolean flag=targetFile.mkdirs();
				System.out.println(flag);
			}
			targetFile=new File(basePath,fileName);
			FileUtils.writeByteArrayToFile(targetFile,file.getBytes());
			return relativePath+fileName;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

}
























