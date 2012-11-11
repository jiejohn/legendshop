/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.common.fck;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import net.fckeditor.connector.exception.InvalidCurrentFolderException;
import net.fckeditor.connector.exception.WriteException;
import net.fckeditor.connector.impl.LocalConnector;
import net.fckeditor.handlers.ResourceType;
import net.fckeditor.requestcycle.ThreadLocalData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.core.AttributeKeys;
import com.legendshop.core.UserManager;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.exception.InvalidFormatException;
import com.legendshop.core.exception.PermissionException;
import com.legendshop.core.helper.FileProcessor;
import com.legendshop.core.helper.PropertiesUtil;

/**
 * 文件上传的操作在ContextConnector类中，这里只是对其进行重命名操作，其实完全可以重新自己写上传的代码
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class ContextConnector extends LocalConnector {
	private final Logger log = LoggerFactory.getLogger(ContextConnector.class);
	
	private List<String> flashFileType;
	
	// 此方法中可以对文件重命名
	/* (non-Javadoc)
	 * @see net.fckeditor.connector.impl.AbstractLocalFileSystemConnector#fileUpload(net.fckeditor.handlers.ResourceType, java.lang.String, java.lang.String, java.io.InputStream)
	 */
	@Override
	public String fileUpload(ResourceType type, String currentFolder, String fileName, InputStream inputStream)
			throws InvalidCurrentFolderException, WriteException {
		String userName = UserManager.getUsername(ThreadLocalData.getRequest());
		if (userName == null) {
			throw new PermissionException("did not logon yet!",EntityCodes.RIGHT);
		}
		String name = fileName;
		// 重命名操作在这里进行
		try {
			int size = inputStream.available();
			if ((size < 0) || (size > PropertiesUtil.getObject(SysParameterEnum.MAX_FILE_SIZE, Long.class))) {
				throw new RuntimeException("File is 0 or File Size exceeded MAX_FILE_SIZE: " + size);
			}
		} catch (IOException e) {
			log.error("fileUpload error",e);
		}
		
		String extName = FileProcessor.getFileExtName(name);
		String fileType = null;
		if(isPic(extName)){
			fileType = "/image/";
		}else if(isFlash(extName)){
			fileType = "/flash/";
		}
		if(fileType != null){
			String path = new StringBuffer().append(PropertiesUtil.getBigFilesAbsolutePath()).append("/").append(userName).append(AttributeKeys.EDITOR_PIC_PATH)
					.append(fileType).append(currentFolder).toString();
			FileProcessor.uploadFile(inputStream, path, "", fileName, true, false);
			return name;
		}else{
			throw new InvalidFormatException("Invalidate File:" + name, EntityCodes.FILE);
		}

	}
	
	/**
	 * 上传的文件是否是图片
	 * @param extName 文件后缀名
	 * @return
	 */
	private boolean isPic(String extName){
		List list = PropertiesUtil.getObject(SysParameterEnum.ALLOWED_UPLOAD_FILE_TPYE, List.class);
		if (list.contains(extName)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 上传的文件是否是Flash
	 * @param extName 文件后缀名
	 * @return
	 */
	private boolean isFlash(String extName){
		if(flashFileType == null){
			flashFileType = new ArrayList<String>();
			flashFileType.add(".swf");
		}
		if (flashFileType.contains(extName)) {
			return true;
		} else {
			return false;
		}
	}
}
