package PdfToPic;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;

import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.p2p.FileInfo;
import com.izhuantou.third.rpc.impl.ReadPropertiesl;

public class PdfToPic {

	public static void main(String[] args) {
		PdfToPic to = new PdfToPic();
		String oids=to.pdfToPicture();
		System.out.println(oids);
	}

	public String pdfToPicture() {
		try {
			String name = "hht-ZT15832661980-20180622121749.pdf";
			Map<String, String> resMap = ReadPropertiesl.gainPropertiesMap("File.properties");
			String path = resMap.get("pdfFilePath");
			String picPath = resMap.get("pdfPicPath");
			path = path + name;
			Document document = new Document();
			document.setFile(path);
			float scale = 2.5f;// 缩放比例
			float rotation = 0f;// 旋转角度

			List<FileInfo> files = new ArrayList<FileInfo>();
			StringBuilder builder=new StringBuilder();
			
			// 添加pdf单张图片到集合
			for (int i = 0; i < document.getNumberOfPages(); i++) {
				BufferedImage image = (BufferedImage) document.getPageImage(i, GraphicsRenderingHints.SCREEN,
						org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);
				RenderedImage rendImage = image;
				FileInfo info = new FileInfo();

				// 日期路径
				String relativePath = DateUtils.dayTimestamp().toString();
				File file = new File(picPath + relativePath);
				if (!file.exists()) {
					file.mkdirs();
				}
				Long datetime = new Date().getTime();
				// 图片名称
				String filename = datetime.toString();
				File fil = new File(file + File.separator + filename);
				
				ImageIO.write(rendImage, "png", fil);
				String oid=StringUtil.getUUID();
				builder.append(oid+",");
				info.setOID(oid);
				info.setName(name);
				info.setSource("upload");
				info.setPhysicalName(filename);
				info.setRelativePath(relativePath);
				files.add(info);

				image.flush();
				image = null;
			}
			document.dispose();
			return builder.toString();
		} catch (Exception e) {
			
		}
		return null;
	}
}
