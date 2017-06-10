package com.sun.util;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.text.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {

    private static ObjectMapper objectMapper = jacksonMapperInit();

    private static ObjectMapper jacksonMapperInit() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        return objectMapper;
    }

    public static String getString(Object obj) {
        if (null == obj) {
            return "";
        }

        return String.valueOf(obj);
    }

    /**
     * 时间分段配置
     */
    public static enum TIMECONF {

        TIMECONF1(1, "09", "10", "9:00-10:00"),
        TIMECONF2(2, "10", "11", "10:00-11:00"),
        TIMECONF3(3, "11", "12", "11:00-12:00"),
        TIMECONF4(4, "12", "13", "12:00-13:00"),
        TIMECONF5(5, "13", "14", "13:00-14:00"),
        TIMECONF6(6, "14", "15", "14:00-15:00"),
        TIMECONF7(7, "15", "16", "15:00-16:00"),
        TIMECONF8(8, "16", "17", "16:00-17:00"),
        TIMECONF9(9, "17", "18", "17:00-18:00"),
        TIMECONF10(10, "18", "19", "18:00-19:00"),
        TIMECONF11(11, "19", "20", "19:00-20:00"),
        TIMECONF12(12, "20", "21", "20:00-21:00"),
        TIMECONF13(13, "21", "22", "21:00-22:00"),
        TIMECONF0(0, "", "", "其他");

        public static String getValueByKey(int key) {

            TIMECONF[] timeconfs = TIMECONF.values();
            for (TIMECONF timeconf : timeconfs) {
                if (timeconf.getKey() == key) {
                    return timeconf.getValue();
                }
            }
            return null;
        }

        TIMECONF(int key, String start, String end, String value) {
            this.key = key;
            this.start = start;
            this.end = end;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        private int key;
        private String start;
        private String end;
        private String value;
    }

    /**
     * 时长分段配置
     */
    public static enum TIMELENGTHCONF {

        TIMELENGTHCONF1(1, 0, 300, "0-5"),
        TIMELENGTHCONF2(2, 300, 600, "5-10"),
        TIMELENGTHCONF3(3, 600, 900, "10-15"),
        TIMELENGTHCONF4(4, 900, 1200, "15-20"),
        TIMELENGTHCONF5(5, 1200, 1500, "20-25"),
        TIMELENGTHCONF6(6, 1500, 1800, "25-30"),
        TIMELENGTHCONF0(0, 1800, Integer.MAX_VALUE, "其他");

        public static String getValueByKey(int key) {

            TIMELENGTHCONF[] timelengthconfs = TIMELENGTHCONF.values();
            for (TIMELENGTHCONF timelengthconf : timelengthconfs) {
                if (timelengthconf.getKey() == key) {
                    return timelengthconf.getValue();
                }
            }
            return null;
        }

        TIMELENGTHCONF(int key, int start, int end, String value) {
            this.key = key;
            this.start = start;
            this.end = end;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        private int key;
        private int start;
        private int end;
        private String value;
    }

    /**
     * 上传文件到微信配置
     **/
    public static enum WECHATFILELIMIT {

        WECHATFILELIMIT1("image", 300, "jpg"),
        WECHATFILELIMIT2("voice", 256, "amr,mp3"),
        WECHATFILELIMIT3("video", 1204, "mp4"),
        WECHATFILELIMIT4("thumb", 64, "jpg");

        public static WECHATFILELIMIT getWechatFileLimitByKey(String key) {
            WECHATFILELIMIT[] wechatFileLimits = WECHATFILELIMIT.values();
            for (WECHATFILELIMIT wechatFileLimit : wechatFileLimits) {
                if (key.equals(wechatFileLimit.getKey())) {
                    return wechatFileLimit;
                }
            }
            return null;
        }

        WECHATFILELIMIT(String key, int size, String type) {
            this.key = key;
            this.size = size;
            this.type = type;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        private String key;
        private int size;
        private String type;
    }

    /**
     * obj 转换成整形
     *
     * @param obj
     * @return
     */
    public static int getInt(Object obj) {
        //对象为空返回0
        if (null == obj) {
            return 0;
        }
        //对像为Integer类型直接返回
        if (obj instanceof Integer) {
            return (Integer) obj;
        }
        //对象为String类型
        else if (obj instanceof String) {
            try {
                return Integer.parseInt(String.valueOf(obj));
            } catch (Exception e) {
                return 0;
            }
        } else if (obj instanceof Number) {
            Number num = (Number) obj;
            return num.intValue();
        } else {
            return 0;
        }
    }

    public static float getFloat(Object obj) {
        float res = 0;
        if (obj instanceof Integer) {
            res = (Integer) obj;
        } else if (obj instanceof Float) {
            res = (Float) obj;
        } else if (obj instanceof Number) {
            Number num = (Number) obj;
            res = num.floatValue();
        } else {
            try {
                res = Float.parseFloat(getString(obj));
            } catch (Exception e) {
            }
        }
        return res;
    }

    public static String getRandomString(int length) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String obj2Json(Object obj) throws Exception {
        String dataLine = null;
        try {
            dataLine = objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw e;
        }
        return dataLine;
    }

    public static String map2Json(Map map) throws Exception {
        String dataLine = null;
        try {
            dataLine = objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            throw e;
        }
        return dataLine;
    }

    public static Map<String, Object> json2Map(String json) throws Exception {
        Map<String, Object> maps = null;
        try {
            maps = objectMapper.readValue(json, Map.class);
        } catch (Exception e) {
            throw e;
        }
        return maps;
    }

    public static Object json2Object(String json) throws Exception {
        Object maps = null;
        try {
            maps = objectMapper.readValue(json, Object.class);
        } catch (Exception e) {
            throw e;
        }
        return maps;
    }

    public static ArrayList<Map<String, Object>> json2ArryList(String json) throws Exception {
        ArrayList<Map<String, Object>> list = null;
        try {
            list = objectMapper.readValue(json, ArrayList.class);
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    public static <T> T json2Bean(String json, Class<T> entityClass) throws Exception {
        if (json == null || "".equals(json)) {
            return null;
        }
        T bean = null;
        try {
            bean = objectMapper.readValue(json, entityClass);
        } catch (Exception e) {
            throw e;
        }
        return bean;
    }

    public static <T> List<T> json2List(String json, Class<T[]> entityClass) throws Exception {
        if (json == null || "".equals(json)) {
            return null;
        }
        List<T> list = null;
        try {
            T[] arr = objectMapper.readValue(json, entityClass);
            list = new ArrayList<T>();
            for (int i = 0; i < arr.length; i++) {
                list.add(arr[i]);
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    // 数组转字符串
    public static String ArrayToString(String[] arr) {
        StringBuffer bf = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            bf.append(arr[i]);
        }
        return bf.toString();
    }

    public static String SHA1Encode(String sourceString) {
        String resultString = null;
        try {
            resultString = new String(sourceString);
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            resultString = byte2hexString(md.digest(resultString.getBytes()));
        } catch (Exception ex) {
        }
        return resultString;
    }

    public static String byte2hexString(byte[] bytes) {
        StringBuffer buf = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            if (((int) bytes[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString((int) bytes[i] & 0xff, 16));
        }
        return buf.toString().toUpperCase();
    }

    public static String obj2json(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 取得集合元素
     *
     * @param list
     * @param key
     * @param val
     * @return
     */
    public static Map<String, Object> getItem(List<Map<String, Object>> list, String key, String val) {
        Map<String, Object> result = null;
        if (null != list) {
            for (Map<String, Object> map : list) {
                String code = CommonUtil.getString(map.get(key));
                if (code.equals(val)) {
                    return map;
                }
            }
        }
        return result;
    }

    /**
     * 以二进制方式克隆对象
     *
     * @param src
     * @return
     * @throws Exception
     */
    public static Object byteClone(Object src) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(baos);
        out.writeObject(src);
        out.close();
        ByteArrayInputStream bin = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream in = new ObjectInputStream(bin);
        Object clone = in.readObject();
        in.close();
        return (clone);
    }

    /**
     * 把Date转换成String型日期
     *
     * @param date   待转换的日期
     * @param format 日期格式
     * @return 转换后的日期
     */
    public static String date2String(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 计算两个日期之间间隔时长
     *
     * @param startday 开始日期
     * @param endday   结束日期
     * @param unit     时长单位（0：秒，1：分钟，2：小时，3：天）
     * @return 间隔时长
     */
    public static int getTimeInterval(String startday, String endday, int unit) {

        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long sl = sf.parse(startday).getTime();
            long el = sf.parse(endday).getTime();
            long ei = el - sl;
            if (ei < 0) {
                return 0;
            }
            switch (unit) {
                case 0:
                    return (int) (ei / (1000));
                case 1:
                    return (int) (ei / (1000 * 60));
                case 2:
                    return (int) (ei / (1000 * 60 * 60));
                case 3:
                    return (int) (ei / (1000 * 60 * 60 * 24));
                default:
                    return (int) (ei / (1000));
            }
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * 左补位，右对齐
     *
     * @param oriStr 原字符串
     * @param len    目标字符串长度
     * @param alexin 补位字符
     * @return 目标字符串
     */
    public static String padLeft(String oriStr, int len, char alexin) {
        int strlen = oriStr.length();
        if (strlen < len) {
            StringBuffer str = new StringBuffer();
            for (int i = 0; i < len - strlen; i++) {
                str.append(alexin);
            }
            return str.toString() + oriStr;
        }
        return oriStr;
    }

    /**
     * 设置日期的时分秒
     *
     * @param date    转换前的日期
     * @param dateKbn 日期区分（0:开始日期，1:结束日期）
     * @return 转换后的日期
     */
    public static String suffixDate(String date, int dateKbn) {
        if (null != date && !"".equals(date)) {
            if (0 == dateKbn) {
                return date + " 00:00:00";
            } else {
                return date + " 23:59:59";
            }
        }
        return date;
    }

    /**
     * 日期增加指定天数
     *
     * @param date 原日期
     * @param days 增加天数
     * @return 增加后的日期
     */
    public static Date addDateByDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

    /**
     * 日期增加指定月数
     *
     * @param date 原日期
     * @return 增加后的日期
     */
    public static Date addDateByMonth(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    /**
     * 参数MAP字符串化
     *
     * @param param
     * @return
     */
    public static String getParams(Map param) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        Iterator<Map.Entry> it = param.entrySet().iterator();
        boolean splitFlag = false;
        while (it.hasNext()) {
            if (splitFlag) {
                sb.append(",");
                splitFlag = true;
            }
            Map.Entry en = it.next();
            sb.append(en.getKey() + "=" + en.getValue());
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * list 比较器
     */
    public static class MulberryComparator implements Comparator<Map<String, Object>> {
        String orderKey = null;
        boolean desc = false;
        String type = "0";

        /**
         * 比较器，字符串升序排列
         *
         * @param orderKey 排序的字段
         */
        public MulberryComparator(String orderKey) {
            super();
            this.orderKey = orderKey;
        }

        /**
         * 比较器，字符串方式排序
         *
         * @param orderKey 排序的字段
         * @param desc     是否降序排列
         */
        public MulberryComparator(String orderKey, boolean desc) {
            super();
            this.orderKey = orderKey;
            this.desc = desc;
        }

        /**
         * 比较器
         *
         * @param orderKey 排序的字段
         * @param keyType  排序字段的类型 ：0表示String，1表示int
         * @param desc     是否降序排列
         */
        public MulberryComparator(String orderKey, String keyType, boolean desc) {
            super();
            this.orderKey = orderKey;
            this.desc = desc;
            this.type = keyType;
        }

        @Override
        public int compare(Map<String, Object> map1, Map<String, Object> map2) {
            if ("1".equalsIgnoreCase(this.type)) {//整形排序
                int temp1 = getInt(map1.get(orderKey));
                int temp2 = getInt(map2.get(orderKey));
                if (temp1 == temp2) {
                    return 0;
                } else if (temp1 > temp2) {
                    return desc ? -1 : 1;
                } else {
                    return desc ? 1 : -1;
                }
            } else { //字符串排序
                String temp1 = getString(map1.get(orderKey));
                String temp2 = getString(map2.get(orderKey));
                if (temp1.compareToIgnoreCase(temp2) == 0) {
                    return 0;
                } else if (temp1.compareToIgnoreCase(temp2) > 0) {
                    return desc ? -1 : 1;
                } else {
                    return desc ? 1 : -1;
                }
            }
        }
    }

    /**
     * 判断是否不为空
     *
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(Object obj) {
        boolean bol = true;
        if (null == obj) {
            bol = false;
        } else {
            if ("".equals(obj)) {
                bol = false;
            }
        }
        return bol;
    }

    /**
     * 判断是否为空
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        if (null == obj) {
            return true;
        }

        if ("".equals(obj.toString())) {
            return true;
        }

        return false;
    }

    public static boolean isNotEmptyString(String str) {
        if (null == str) {
            return false;
        }

        if ("".equals(str)) {
            return false;
        }
        return true;
    }

    /**
     * map转list
     *
     * @param map
     * @return
     */
    public static List<Map<String, Object>> map2List(Map<String, Object> map) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if (null != map) {
            for (Map.Entry<String, Object> en : map.entrySet()) {
                Map<String, Object> temp = (Map<String, Object>) en.getValue();
                list.add(new HashMap<String, Object>(temp));
            }
        }
        return list;
    }





    /**
     * 将bean转换为map
     *
     * @param bean
     * @return
     */
    public static Map<String, Object> bean2Map(Object bean) {
        try {
            if (null == bean) {
                return null;
            }
            Class<? extends Object> entityClass = bean.getClass();
            Map<String, Object> returnMap = new HashMap<String, Object>();
            BeanInfo beanInfo = Introspector.getBeanInfo(entityClass);
            PropertyDescriptor[] propertyDescriptors = beanInfo
                    .getPropertyDescriptors();
            if (null != propertyDescriptors) {
                for (int i = 0; i < propertyDescriptors.length; i++) {
                    PropertyDescriptor descriptor = propertyDescriptors[i];
                    String propertyName = descriptor.getName();
                    if (!propertyName.equals("class")) {
                        Method readMethod = descriptor.getReadMethod();
                        Object result = readMethod.invoke(bean, new Object[0]);
                        if (result != null) {
                            returnMap.put(propertyName, result);
                        } else {
                            returnMap.put(propertyName, null);
                        }
                    }
                }
            }
            return returnMap;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * map转换为bean
     *
     * @param entityClass
     * @param map
     * @return
     * @throws Exception
     */
    public static <T> T map2Bean(Class<T> entityClass, Map<String, Object> map) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(entityClass); // 获取类属性
        T obj = entityClass.newInstance(); // 创建 JavaBean 对象
        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        if (null != propertyDescriptors) {
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (map.containsKey(propertyName)) {//设置属性值
                    try {
                        descriptor.getWriteMethod().invoke(obj, map.get(propertyName));
                    } catch (Exception e) {
                        throw e;
                    }
                }
            }
        }
        return obj;
    }

    /**
     * 格式化积分
     *
     * @param point
     * @return
     */
    public static String formatPoint(String point) {
        if (null == point || "".equals(point)) {
            return "";
        }
        String pattern = "[0-9]+(.[0-9]+)?";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(point);
        if (m.matches()) {
            if (point.indexOf(".") > 1) {
                return point.substring(0, point.indexOf("."));
            } else {
                return point;
            }
        } else {
            return "";
        }
    }

    /**
     * 格式化生日
     *
     * @param birthday
     * @return
     */
    public static String formatBirthday(String birthday) {
        if (null == birthday || "".equals(birthday) || birthday.length() != 4) {
            return "";
        }
        return birthday.substring(0, 2) + "月" + birthday.substring(2, 4) + "日";
    }

    /**
     * 获取百分比
     *
     * @param p1    被除数
     * @param p2    除数
     * @param scale 保留的小数位数
     * @return 百分比
     */
    public static String percent(double p1, double p2, int scale) {
        String str;
        double p3 = p1 / p2;
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(scale);
        str = nf.format(p3);
        return str;
    }



    /**
     * 删除单个文件
     *
     * @param sPath 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            flag = file.delete();
        }
        return flag;
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param sPath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String sPath) {
        // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        // 删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } else {// 删除子目录
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        // 删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 把日期从秒数转换成日期格式
     *
     * @param second 秒数
     * @return 转换后的日期
     */
    public static String getDateFromSecond(String second) {
        try {
            Date date = new Date();
            date.setTime(Long.parseLong(second) * 1000);
            return date2String(date, "yyyy-MM-dd HH:mm:ss");
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 计算两个日期的月份差
     *
     * @param startDate 开始日
     * @param endDate   结束日
     * @return 月份差
     */
    public static int getDifferenceOfMonths(String startDate, String endDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar firstDay = Calendar.getInstance();
            Calendar lastDay = Calendar.getInstance();
            firstDay.setTime(dateFormat.parse(startDate));
            lastDay.setTime(dateFormat.parse(endDate));
            int diffMonth = (lastDay.get(Calendar.YEAR) - firstDay.get(Calendar.YEAR)) * 12 + lastDay.get(Calendar.MONTH) - firstDay.get(Calendar.MONTH);
            return diffMonth;
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * 把字符串转成指定格式的日期
     *
     * @param s       待转换的字符串
     * @param pattern 指定的日期格式
     * @return 如果转换成功返回日期，否则返回null
     */
    public static Date coverString2Date(String s, String pattern) {

        if (s == null || "".equals(s))
            return null;
        if (pattern == null || "".equals(pattern))
            return null;
        try {
            DateFormat df = new SimpleDateFormat(pattern);
            Date d = df.parse(s);
            if (df.format(d).equals(s)) {
                return d;
            } else {
                return null;
            }
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 把字符串转成日期(支持多种格式)
     *
     * @param s 待转换的字符串
     * @return 如果转换成功返回日期，否则返回null
     */
    public static Date coverString2Date(String s) {

        if (s == null || "".equals(s))
            return null;
        Date d = coverString2Date(s, "yyyy-MM-dd");
        if (d != null)
            return d;
        d = coverString2Date(s, "yyyy-M-d");
        if (d != null)
            return d;
        d = coverString2Date(s, "yyyy-MM-d");
        if (d != null)
            return d;
        d = coverString2Date(s, "yyyy-M-dd");
        if (d != null)
            return d;
        d = coverString2Date(s, "yyyy/MM/dd");
        if (d != null)
            return d;
        d = coverString2Date(s, "yyyy/M/d");
        if (d != null)
            return d;
        d = coverString2Date(s, "yyyy/MM/d");
        if (d != null)
            return d;
        d = coverString2Date(s, "yyyy/M/dd");
        if (d != null)
            return d;
        d = coverString2Date(s, "yyyyMMdd");
        if (d != null)
            return d;
        d = coverString2Date(s, "yyyy-MM-dd HH:mm:ss");
        if (d != null)
            return d;
        return null;
    }

    /**
     * 日期增加指定天数
     *
     * @param pattern
     * @param date
     * @param days
     * @return
     */
    public static String addDateByDays(String pattern, String date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(coverString2Date(date));
        cal.add(Calendar.DAY_OF_MONTH, days);
        return date2String(cal.getTime(), pattern);
    }

    /**
     * 日期增加指定时间
     *
     * @param pattern
     * @param date
     * @param hours
     * @return
     */
    public static String addDateByHours(String pattern, String date, int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(coverString2Date(date));
        cal.add(Calendar.HOUR_OF_DAY, hours);
        return date2String(cal.getTime(), pattern);
    }

    /**
     * 取得指定周的最后一天的日期（把星期五作为最后一天）
     *
     * @param n 为推迟的周数，0本周，-1向前推迟一周，1下周，以此类推
     * @return 指定周的最后一天的日期
     */
    public static String getWeekLastDay(int n) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, n * 7);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        return CommonUtil.date2String(cal.getTime(), "yyyy-MM-dd");
    }

    /**
     * 计算两个日期之间的间隔天数
     *
     * @param d1 指定开始日期
     * @param d2 指定结束日期
     * @return 间隔天数
     */
    public static int getDaysBetween(Calendar d1, Calendar d2) {
        if (d1.after(d2)) {
            Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }
        int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
        int y2 = d2.get(Calendar.YEAR);
        if (d1.get(Calendar.YEAR) != y2) {
            do {
                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
                d1.add(Calendar.YEAR, 1);
            } while (d1.get(Calendar.YEAR) != y2);
        }
        return days;
    }

    /**
     * 计算两个日期之间的工作日天数
     *
     * @param d1 指定开始日期
     * @param d2 指定结束日期
     * @return 工作日天数
     */
    public static int getWorkingDay(Calendar d1, Calendar d2) {
        int result = -1;
        if (d1.after(d2)) {
            Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }
        //开始日期的日期偏移量
        int charge_start_date = 0;
        //结束日期的日期偏移量
        int charge_end_date = 0;
        int stmp = 7 - d1.get(Calendar.DAY_OF_WEEK);
        int etmp = 7 - d2.get(Calendar.DAY_OF_WEEK);
        // 开始日期为星期六和星期日时偏移量为0
        if (stmp != 0 && stmp != 6) {
            charge_start_date = stmp;
        }
        // 结束日期为星期六和星期日时偏移量为0
        if (etmp != 0 && etmp != 6) {
            charge_end_date = etmp - 1;
        }
        result = (getDaysBetween(getNextMonday(d1), getNextMonday(d2)) / 7) * 5 + charge_start_date - charge_end_date;
        return result;
    }

    /**
     * 计算两个日期之间的假日天数
     *
     * @param d1 指定开始日期
     * @param d2 指定结束日期
     * @return 假日天数
     */
    public static int getHolidays(Calendar d1, Calendar d2) {
        return getDaysBetween(d1, d2) - getWorkingDay(d1, d2);
    }


    /**
     * 获得日期的下一个星期一的日期
     *
     * @param date 指定日期
     * @return 下一个星期一的日期
     */
    public static Calendar getNextMonday(Calendar date) {
        Calendar result = date;
        do {
            result = (Calendar) result.clone();
            result.add(Calendar.DATE, 1);
        } while (result.get(Calendar.DAY_OF_WEEK) != 2);
        return result;
    }

    /**
     * 去掉字符串中的所有回车换行符
     *
     * @param myString
     * @return
     */
    public static String removeEnterCharacters(String myString) {
        if (null != myString) {
            myString = myString.replaceAll("(\r\n|\r|\n|\n\r)", "");
        }
        return myString;
    }

    public static String addTimeByHours(String pattern, String time, int hours) throws ParseException {
        Calendar c = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat(pattern);
        Date tm = df.parse(time);
        c.setTime(tm);
        c.add(Calendar.HOUR_OF_DAY, hours);
        df.format(c.getTime());
        return df.format(c.getTime());
    }

    /**
     * 将String转换为int型，不能转换的将返回0值
     *
     * @param arg
     * @return
     */
    public static int string2int(String arg) {
        try {
            if ("null".equals(arg) || "NULL".equals(arg)) {
                return 0;
            }
            return Integer.valueOf(arg);
        } catch (Exception ex) {
            return 0;
        }
    }

    public static int obj2int(Object obj) {
        if (null == obj) {
            return 0;
        }
        if (obj instanceof Number) {
            Number num = (Number) obj;
            return num.intValue();
        } else if (obj instanceof String) {
            return string2int((String) obj);
        } else {
            return 0;
        }
    }

    /**
     * Cron表达式时间规则常量定义
     **/
    public static final String EVERY = "*";
    public static final String ANY = "?";
    public static final String RANGES = "-";
    public static final String INCREMENTS = "/";
    public static final String ADDITIONAL = ",";
    public static final String LAST = "L";
    public static final String WEEKDAY = "W";
    public static final String THENTH = "#";
    public static final String CALENDAR = "C";
    public static final String BLANK = " ";

    /**
     * 页面设置转为Cron表达式
     *
     * @param type      时间类型（1：指定时间，2：参考某个时间，3：循环执行，4：条件触发）
     * @param date      日期值（格式为：yyyy-MM-dd）
     * @param time      时间值（格式为：HH:mm:ss）
     * @param week      星期几（范围为：1~7，1代表星期天，以此类推7代表星期六）
     * @param frequency 频率（YY：每年，MM：每月，DD：没天 WEEK：每周）
     * @return Cron表达式
     */
    public static String convertDateToCronExp(String type, String date, String time, int week, String frequency) {
        String cronEx = "";
        try {
            if ("1".equals(type)) {
                String[] dates = date.split("-");
                String[] times = time.split(":");
                cronEx = times[2] + BLANK + times[1] + BLANK + times[0] + BLANK + dates[2] + BLANK + dates[1] + BLANK + ANY + BLANK + dates[0];
            } else if ("2".equals(type)) {
                String[] times = time.split(":");
                cronEx = times[2] + BLANK + times[1] + BLANK + times[0] + BLANK + EVERY + BLANK + EVERY + BLANK + ANY;
            } else if ("3".equals(type)) {
                if ("YY".equals(frequency)) {
                    String[] dates = date.split("-");
                    String[] times = time.split(":");
                    cronEx = times[2] + BLANK + times[1] + BLANK + times[0] + BLANK + dates[1] + BLANK + dates[0] + BLANK + ANY;
                } else if ("MM".equals(frequency)) {
                    String[] times = time.split(":");
                    cronEx = times[2] + BLANK + times[1] + BLANK + times[0] + BLANK + date + BLANK + EVERY + BLANK + ANY;
                } else if ("DD".equals(frequency)) {
                    String[] times = time.split(":");
                    cronEx = times[2] + BLANK + times[1] + BLANK + times[0] + BLANK + EVERY + BLANK + EVERY + BLANK + ANY;
                } else if ("WEEK".equals(frequency)) {
                    String[] times = time.split(":");
                    cronEx = times[2] + BLANK + times[1] + BLANK + times[0] + BLANK + ANY + BLANK + EVERY + BLANK + week;
                }
            } else if ("4".equals(type)) {
                String[] times = time.split(":");
                cronEx = times[2] + BLANK + times[1] + BLANK + times[0] + BLANK + EVERY + BLANK + EVERY + BLANK + ANY;
            }
        } catch (Exception e) {

        }
        return cronEx;
    }

    /**
     * 填充msg信息
     *
     * @return msg信息
     */
    public static String getMessage(String msg, String[] paramArr) {
        if (msg != null && !"".equals(msg)) {
            if (paramArr != null && paramArr.length > 0) {
                for (int i = 0; i < paramArr.length; i++) {
                    msg = msg.replaceAll("\\{" + i + "\\}", paramArr[i]);
                }
            }
        }
        return msg;
    }

    /**
     * 获取文件大小
     *
     * @param file 文件
     * @return 文件字节数
     */
    public static long getTotalSizeOfFile(File file) {
        if (file == null || !file.exists()) {
            return 0;
        }
        if (file.isFile())
            return file.length();
        final File[] children = file.listFiles();
        long total = 0;
        if (children != null)
            for (final File child : children)
                total += getTotalSizeOfFile(child);
        return total;
    }

    /**
     * 格式化文件大小
     *
     * @param fileS 文件字节数
     * @return 格式化后的文件大小
     */
    public static String formetFileSize(long fileS) {
        // 转换文件大小
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * 判断文件是否超过最大值
     *
     * @param file     文件
     * @param maxFileS 最大文件字节数
     * @return true：超过 false：未超过
     */
    public static boolean isOverMaxFileSize(File file, long maxFileS) {
        long fileS = getTotalSizeOfFile(file);
        if (fileS > maxFileS) {
            return true;
        } else {
            return false;
        }
    }

}
