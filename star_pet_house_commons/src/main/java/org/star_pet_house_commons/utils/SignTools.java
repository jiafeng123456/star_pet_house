package org.star_pet_house_commons.utils;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;


public class SignTools {

    public static boolean signVerify(String appSecret, HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        Map<String, String> data = new HashMap<String, String>();
        for (String key : map.keySet()) {
            data.put(key, SecurityUtil.tracertXSS(map.get(key)[0]));
        }
        return signVerify(appSecret, data);
    }

    public static boolean signVerify(String appSecret, Map<String, String> params) {
        Map<String, String> map = new HashMap<String, String>();

        for (String key : params.keySet()) {
            if (!key.equalsIgnoreCase("sign")) {
                map.put(key, params.get(key));
            }
        }

        String sign = sign(map,appSecret);
        if (sign.equalsIgnoreCase(params.get("sign"))) {
            return true;
        }
        return false;
    }

    private static String toHexValue(byte[] messageDigest) {
        if (messageDigest == null)
            return "";
        StringBuilder hexValue = new StringBuilder();
        for (byte aMessageDigest : messageDigest) {
            int val = 0xFF & aMessageDigest;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * 生成验签--返回参数集合
     * 验签生成规则:
     * 		1.参数按照参数名字母升序生成url参数串;
     * 		2.尾部增加app_secret参数;
     * 		3.进行md5加密生--16进制成验签
     * @return
     */
    public static String createSign(Map<String,Object> paramMap , String app_secret){

        String content = formatUrlMap(paramMap,false,false,"","");
        content +=  app_secret;
        String sign = "";
        try {
            sign = toHexValue(encryptMD5(content.getBytes(Charset.forName("utf-8"))));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("md5 error");
        }
        return sign ;
    }

    /**
     * 验签生成方法
     * 1.参数按ascii进行升序排序 keyvalue拼接；
     * 2.收尾加入appSecret；
     * 3.md5加密--16进制
     * @param params
     * @return
     */
    public static String sign(Map<String, String> params,String appSecret) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String string = "";
        for (String s : keys) {
            string += s + params.get(s);
        }
        string += appSecret;
        String sign = "";
        try {
            sign = toHexValue(encryptMD5(string.getBytes(Charset.forName("utf-8"))));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("md5 error");
        }
        return sign;
    }

    private static byte[] encryptMD5(byte[] data) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(data);
        return md5.digest();
    }

    public static String signObjectMap(Map<String, Object> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        StringBuilder string = new StringBuilder();
        for (String s : keys) {
            string.append(params.get(s).toString());
        }
        String sign = "";
        try {
            sign = toHexValue(encryptMD5(string.toString().getBytes(Charset.forName("utf-8"))));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("md5 error");
        }
        return sign;
    }



    /**
     *
     * 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）并且生成url参数串<br>
     * 实现步骤: <br>
     *
     * @param paraMap
     *            要排序的Map对象
     * @param urlEncode
     *            是否需要URLENCODE
     * @param keyToLower
     *            是否需要将Key转换为全小写 true:key转化成小写，false:不转化
     * @param exps 实际为字符 1位
     * 		  exps[0]:key与value之间的表达式
     * 		  exps[1]:keyvalue之间键值对中间的表达式
     * @return
     */
    public static String formatUrlMap(Map<String, Object> paraMap,
                                      boolean urlEncode, boolean keyToLower ,String ... exps) {
        String buff = "";
        Map<String, Object> tmpMap = paraMap;
        try {
            List<Map.Entry<String, Object>> infoIds = new ArrayList<Map.Entry<String, Object>>(
                    tmpMap.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(infoIds,
                    new Comparator<Map.Entry<String, Object>>() {

                        @Override
                        public int compare(Map.Entry<String, Object> o1,
                                           Map.Entry<String, Object> o2) {
                            return (o1.getKey()).toString().compareTo(
                                    o2.getKey());
                        }
                    });
            // 构造URL 键值对的格式

            String exp1 = "";
            String exp2 = "";
            if(exps.length >= 2){
                exp1 = exps[0];
                exp2 = exps[1];
            }else if(exps.length == 1){
                exp1 = exps[0];
            }
            StringBuilder buf = new StringBuilder();
            for (Map.Entry<String, Object> item : infoIds) {
                if (StringUtils.isNotBlank(item.getKey())) {
                    String key = item.getKey();
                    String val = item.getValue().toString();
                    if (urlEncode) {
                        val = URLEncoder.encode(val, "utf-8");
                    }
                    if (keyToLower) {
                        buf.append(key.toLowerCase()+exp1+ val);
                    } else {
                        buf.append(key +exp1+ val);
                    }
                    buf.append(exp2);
                }

            }
            buff = buf.toString();
            if (buff.isEmpty() == false) {
                if(exp2.length() == 1){
                    buff = buff.substring(0, buff.length() - 1);
                }
            }
        } catch (Exception e) {
            return null;
        }
        return buff;
    }

    public  static void main(String[] s){

        Map<String,Object>  sign =  new  HashMap<String,Object>();
        sign.put("app_key", "1");
        sign.put("method", "/busapi/bp/join_bonus_task.json");
        sign.put("task_no", "1000");
        sign.put("regcust_id", "10108076");
        sign.put("task_code", "1000");
        sign.put("timestamp", "1559532080618");
        String str = createSign(sign,"1");
        Map<String,String>  sign1 =  new  HashMap<String,String>();
        sign1.put("app_key", "1");
        sign1.put("method", "/busapi/bp/join_bonus_task.json");
        sign1.put("regcust_id", "10108076");
        sign1.put("task_no", "1000");
        sign1.put("task_code", "1000");
        sign1.put("timestamp", "1559532080618");
        sign1.put("sign", str);
        System.out.println(signVerify("1", sign1));
    }
}
