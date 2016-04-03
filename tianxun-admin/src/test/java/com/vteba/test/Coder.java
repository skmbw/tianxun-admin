package com.vteba.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.generator.GenMain;

import com.vteba.ext.codegen.CodeBuilder;
import com.vteba.ext.codegen.DB;
import com.vteba.ext.codegen.KeyType;
import com.vteba.ext.codegen.TempType;

public class Coder {

	public static void main(String[] args) {
		Map<String, String> tableListMap = new HashMap<String, String>();
		tableListMap.put("comment", "评论表");
//		tableListMap.put("question_image", "问题图片表");
//		tableListMap.put("category", "问题分类");
//		tableListMap.put("tags", "热门标签");
		
	    //项目绝对路径
		String rootPath = "/home/yinlei/git/tianxun-admin/tianxun-admin/";
		CodeBuilder builder = new CodeBuilder(rootPath, TempType.MyBatisBasic);
		builder.setConfigFilePath("src/main/resources/config.properties")
		.setSrcPath("src/main/java/")
		.schema("tianxun_user")
		//.className("AppInfo")// 可以不设
		.setDb(DB.MySQL)// 可以不使用，只要jdbc url是正确的
		.keyType(KeyType.String)
		//.tableDesc("应用配置信息")
		//.tableName("app_info")
		.setTableList(tableListMap)
		.module("com.vteba.tianxun.comment")
		.setPojo(false)
		.setMongo(false)
		.setGenAction(false)
        .setGenDao(false)
        .setGenMapper(false)
        .setGenModel(false)
        .setGenService(false)
        .setMybatisShards(false)
        .setMybatisAction(true)
        .setJsonAction(true);
        //.setOnlyDao(true);//只产生dao
		
		Map<String, List<String>> map = builder.build();

		GenMain.main(map, tableListMap.size(), rootPath);
	}

}
