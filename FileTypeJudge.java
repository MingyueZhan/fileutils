package com.shiyuesoft.microservice.test.filetype;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/** 文件类型判断
 * @author jeri
 * @version 1.0.0
 * @company www.shiyuesoft.com
 * @date 2019/5/31 14:33
 * @see
 */
public final class FileTypeJudge {
    /** 二进制转化为16进制
     * @Author jeri
     * @Description //TODO
     * @Date 14:35 2019/5/31
     * @Param [bytes] 
     * @return java.lang.String
    **/
    
    private static String bytes2hex(byte[] bytes){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0 ;i <bytes.length;i++){
            String s = Integer.toHexString(bytes[i] & 0xff);
            if (s.length() == 1){
                stringBuilder.append("0");
            }
            stringBuilder.append(s.toUpperCase());
        }
        return stringBuilder.toString();
    }

    private static String getFileHeader(String filePath) throws IOException {
        byte[] b = new byte[28];
        InputStream inputStream = null;
        inputStream = new FileInputStream(filePath);
        inputStream.read(b,0,b.length);
        inputStream.close();
        return bytes2hex(b);
    }


    public static FileType getFileType(String filePath) throws IOException {
        String fileHeader = getFileHeader(filePath);
        if (fileHeader ==null || fileHeader.length()==0){
            return null;
        }
        String s = fileHeader.toUpperCase();
        FileType[] values = FileType.values();
        for (FileType fileType:values){
            if(fileHeader.startsWith(fileType.getValue())){
                return fileType;
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(FileTypeJudge.getFileType("C:\\Users\\admin\\Downloads\\timg (9).txt"));
    }
}
