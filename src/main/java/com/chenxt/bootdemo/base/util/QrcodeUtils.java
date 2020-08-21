package com.chenxt.bootdemo.base.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 二维码工具类
 *
 * @author chenxt
 * @date 2020/08/19
 */
public class QrcodeUtils {
    public static void main(String[] args) {
        //设置生个图片格式
        String format = "png";
        //设置二维码内容
        String context = "http://120.25.205.85:8080/1.png";
        //设置额外参数
        Map<EncodeHintType, Object> map = new HashMap<>();
        //设置编码集
        map.put(EncodeHintType.CHARACTER_SET, "utf-8");
        //容错率，指定容错等级，例如二维码中使用的ErrorCorrectionLevel
        map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        //生成条码的时候使用，指定边距，单位像素，受格式的影响。类型Integer, 或String代表的数字类型
        map.put(EncodeHintType.MARGIN, 2);
        try {
            //生成二维码，（参数为：编码的内容、编码的方式（二维码、条形码...）、首选的宽度、首选的高度、编码时的额外参数）
            BitMatrix encode = new MultiFormatWriter().encode(context, BarcodeFormat.QR_CODE, 300, 300, map);
            //生成二维码图片，并将二维码写到文件里
            MatrixToImageWriter.writeToPath(encode, format, new File("/Users/chenxt/workspace/qrcode/qrcode.png").toPath());
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }

    }

}
