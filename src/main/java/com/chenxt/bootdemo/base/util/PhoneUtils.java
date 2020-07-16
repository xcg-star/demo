package com.chenxt.bootdemo.base.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chenxt.bootdemo.base.expection.BusinessException;
import com.chenxt.bootdemo.base.expection.enumeration.BusinessExceptionCodeEnum;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 电话号码工具类
 *
 * @author chenxt
 * @date 2020/06/17
 */
@Slf4j
public class PhoneUtils {
    private static final Pattern REGION_PATTERN = Pattern.compile("(?<=\\+)\\d*(?=\\s)");
    private static final Pattern INTERNAL_PATTERN = Pattern.compile("^[1][3-9][0-9]{9}$");

    private static final Map<String, Integer> extendRegionMap = getExtendRegionMap();
    /**
     * 默认热门地区列表:澳门特区,台湾地区,香港,中国,泰国,日本,韩国,新加坡,美国
     */
    private static final List<String> defaultHotList = Arrays.asList("MO", "TW", "HK", "CN", "TH", "JP", "KR", "SG", "US");

    private static final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    /**
     * 英文
     */
    private static final Locale enLocale = Locale.ENGLISH;
    /**
     * 简体中文
     */
    private static final Locale zhCnLocale = Locale.SIMPLIFIED_CHINESE;
    /**
     * 繁体中文
     */
    private static final Locale zhTwLocale = Locale.TRADITIONAL_CHINESE;

    private static Map<String, Integer> getExtendRegionMap() {
        Map<String, Integer> extendRegionMap = new HashMap<>();
        extendRegionMap.put("BV", 47);
        extendRegionMap.put("PN", 64);
        extendRegionMap.put("TF", 260);
        extendRegionMap.put("GS", 500);
        extendRegionMap.put("UM", 581);
        extendRegionMap.put("AN", 599);
        extendRegionMap.put("AQ", 672);
        extendRegionMap.put("HM", 672);
        return extendRegionMap;
    }

    /**
     * 国内手机号码是否合法
     *
     * @param fullPhone 11位电话号码
     * @return 是否合法
     */
    public static boolean isPhoneValidForInternal(String fullPhone) {
        return INTERNAL_PATTERN.matcher(fullPhone).matches();
    }

    /**
     * 电话号码是否合法
     *
     * @param fullPhone 电话号码(+XX XXXXXXXX)
     * @return 是否合法
     */
    public static boolean isPhoneValid(String fullPhone) {
        Matcher matcher = REGION_PATTERN.matcher(fullPhone);
        if (matcher.find()) {
            String defaultRegion = matcher.group();
            return isPhoneNumberValid(fullPhone, defaultRegion);
        } else {
            return false;
        }
    }

    /**
     * 电话号码是否国际号码
     *
     * @param phone 电话号码
     * @return 是否国际号码
     */
    public static boolean isPhoneInternational(String phone) {
        return !phone.startsWith("+86 ");
    }

    /**
     * 获取不带国际区号的国内号码
     *
     * @param phone 电话号码
     * @return 国内号码
     */
    public static String getInternalPhone(String phone) {
        return phone.replace("+86 ", "");
    }

    private static boolean isPhoneNumberValid(String numberToParse, String defaultRegion) {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            Phonenumber.PhoneNumber numberProto = PhoneNumberUtil.getInstance().parse(numberToParse, defaultRegion);
            return phoneUtil.isValidNumber(numberProto);
        } catch (NumberParseException e) {
            log.error("手机号码格式不正确:", e);
        }
        return false;
    }

    /**
     * 获取全部国际区号和中文名称
     *
     * @param language 语言
     * @return 国际区号和中文名称列表
     */
    public static JSONArray getAllRegionList(String language) {
        LanguageEnum languageEnum;
        try {
            languageEnum = LanguageEnum.getByCode(language);
        } catch (Exception e) {
            log.error("语言不存在:", e);
            throw new BusinessException(BusinessExceptionCodeEnum.LANGUAGE_NOT_EXIST);
        }
        List<String> countryList = getISOCountryListOrder();
        JSONArray allRegionList = initAllRegionList(languageEnum);
        List<JSONObject> itemList = countryList.stream().map(country -> {
            Locale locale = new Locale(language, country);
            String displayCountry = locale.getDisplayCountry(LanguageEnum.TRADITIONAL_CHINESE.equals(languageEnum) ? zhTwLocale : zhCnLocale);
            String enDisplayCountry = locale.getDisplayCountry(enLocale);
            JSONObject item = new JSONObject();
            item.put("region", "+" + getRegion(country));
            item.put("name", displayCountry);
            item.put("enName", enDisplayCountry);
            item.put("pinyinName", PinyinUtils.toPinYin(displayCountry));
            item.put("isHot", Boolean.toString(defaultHotList.contains(country)));
            return item;
        }).collect(Collectors.toList());
        if (LanguageEnum.ENGLISH.equals(languageEnum)) {
            // 英文按照英文名排序
            itemList = itemList.stream().sorted(Comparator.comparing(item -> item.getString("enName"))).collect(Collectors.toList());
        } else {
            // 中文按照拼音排序
            itemList = itemList.stream().sorted(Comparator.comparing(item -> item.getString("pinyinName"))).collect(Collectors.toList());
        }
        itemList.forEach(item -> {
            String firstLetter = LanguageEnum.ENGLISH.equals(languageEnum) ? item.getString("enName").substring(0, 1) : item.getString("pinyinName").substring(0, 1);
            if (item.getBoolean("isHot")) {
                ((JSONObject) allRegionList.get(0)).getJSONArray("data").add(item);
            } else {
                allRegionList.stream().filter(region -> ((JSONObject) region).getString("section").equalsIgnoreCase(firstLetter))
                        .findFirst().ifPresent(region -> {
                    ((JSONObject) region).getJSONArray("data").add(item);
                });
            }
        });
        return new JSONArray(allRegionList.stream().filter(region -> !((JSONObject) region).getJSONArray("data").isEmpty()).collect(Collectors.toList()));
    }

    private static JSONArray initAllRegionList(LanguageEnum languageEnum) {
        JSONArray allRegionList = new JSONArray();
        String hotName = "热门";
        switch (languageEnum) {
            case ENGLISH:
                hotName = "HOT";
                break;
            case SIMPLIFIED_CHINESE:
                hotName = "热门";
                break;
            case TRADITIONAL_CHINESE:
                hotName = "熱門";
                break;
            default:
                break;
        }
        allRegionList.add(generateRegionItem(hotName));
        for (char section = 'A'; section <= 'Z'; section++) {
            allRegionList.add(generateRegionItem(String.valueOf(section)));
        }
        return allRegionList;
    }

    private static JSONObject generateRegionItem(String section) {
        JSONObject item = new JSONObject();
        item.put("section", section);
        item.put("data", new JSONArray());
        return item;
    }

    private static Integer getRegion(String country) {
        Integer region = phoneNumberUtil.getCountryCodeForRegion(country);
        if (region == 0) {
            //若PhoneNumberUtil返回未知代码，从扩展map里取
            region = extendRegionMap.get(country);
            if (region == null) {
                //若扩展map中也没有，为0
                region = 0;
            }
        }
        return region;
    }

    private static List<String> getISOCountryListOrder() {
        String[] countries = Locale.getISOCountries();
        List<String> countryList = new ArrayList<>();
        countryList.addAll(defaultHotList);
        countryList.addAll(Arrays.stream(countries).filter(country -> !defaultHotList.contains(country)).collect(Collectors.toList()));
        return countryList;
    }

    private enum LanguageEnum {
        ENGLISH("en", "英文"),
        SIMPLIFIED_CHINESE("zh-CN", "简体中文"),
        TRADITIONAL_CHINESE("zh-TW", "繁体中文");

        private String code;
        private String message;

        LanguageEnum(String code, String message) {
            this.code = code;
            this.message = message;
        }

        private String getCode() {
            return code;
        }

        private static LanguageEnum getByCode(String code) {
            return Arrays.stream(LanguageEnum.values())
                    .filter(languageEnum -> languageEnum.getCode().equals(code)).findFirst().get();
        }
    }
}
