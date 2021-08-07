package com.diao.utils;

import com.diao.pojo.Books;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class FileProcess {
    public static String coverPath = "/static/covers/";
    public static String processFile(CommonsMultipartFile file, String coverID, HttpServletRequest req) throws IOException {
        String cover="";
        String realPath="";
        String realDir = req.getSession().getServletContext().getRealPath("/");
        realPath = realDir+coverPath;
//        System.out.println("处理文件");
        if (file.getFileItem().getSize()>0) {
            String coverRealDirPath=realPath + coverID;
            File coverDir = new File(coverRealDirPath);
            if (!coverDir.exists()){
                coverDir.mkdirs();
            }else {
                deleteAllFiles(coverDir);
            }
            String filename = file.getOriginalFilename();
            String pattern = "";
            if (filename != null) {
                pattern = filename.substring(filename.lastIndexOf("."));
            }
            String realCover = coverRealDirPath+"/"+coverID+pattern;
            cover = ".."+coverPath+coverID+"/"+coverID+pattern;
            InputStream in = file.getInputStream();
            FileOutputStream fos = new FileOutputStream(realCover);
            int len;
            byte[] buff=new byte[1024];
            while ((len=in.read(buff))!=-1){
                fos.write(buff,0,len);
                fos.flush();
            }
            fos.close();
            in.close();
            return cover;
        }
        return null;
    }
    public static void deleteCover(String coverID,HttpServletRequest req){
        String realPath = req.getSession().getServletContext().getRealPath("/");
        String filePath = realPath + coverPath +coverID;
        File file = new File(filePath);
        if (file.exists()){
            deleteAllFiles(file);
            file.delete();
        }

    }
    public static void deleteAllFiles(File file){
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()){
                deleteAllFiles(f);
            }
            f.delete();
        }

    }
}
