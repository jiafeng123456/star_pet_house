package org.star_pet_house_commons.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 开户相关util方法
 * @author Administrator
 */
public class SecurityUtil {

	/**
	 * 转换html标签
	 * @param content
	 * @return
	 */
	public static String replaceHtmlTag(String content) {
		return content.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;");

	}

	/**
	 * 全角转半角 add by star gao
	 * @date 2014-05-14
	 * @param s
	 * @return
	 */
	public static String tracertXSS(String s) {
		if (s == null || s.isEmpty()) {
			return s;
		}
		StringBuilder sb = new StringBuilder(s.length() + 16);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
				case '＞':
					sb.append('>');// 全角大于号
				break;
				case '＜':
					sb.append('<');// 全角小于号
				break;
				case '‘':
					sb.append('\'');// 全角单引号
				break;
				case '“':
					sb.append('\"');// 全角双引号
				break;
				case '＆':
					sb.append('&');// 全角
				break;
				case '＼':
					sb.append('\\');// 全角斜线
				break;
				case '＃':
					sb.append('#');// 全角井号
				break;
				case '（':
					sb.append('(');// 全角左括号
				break;
				case '）':
					sb.append(')');// 全角右括号
				break;
			default:
				sb.append(c);
				break;
			}
		}
		return sb.toString();
	}

	// 替换换行
	public static String replaceNewLine(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	public static void main(String[] a) {
		System.out.println(replaceHtmlTag("<>\"&"));
	}
}
