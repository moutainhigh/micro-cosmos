package com.yichen.cosmos.cloud.platform.util;//package com.tairan.cloud.whale.wisdom.util;
//
//import com.alibaba.fastjson.JSON;
//import com.squareup.javapoet.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.util.CollectionUtils;
//
//import javax.lang.model.element.Modifier;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
///**
// * JavaPoet
//
// JavaPoet is a Java API for generating .java source files.
//
// Source file generation can be useful when doing things such as annotation processing
// or interacting with metadata files (e.g., database schemas, protocol formats).
// By generating code, you eliminate the need to write boilerplate while also keeping a single source of truth for the metadata.
// * Created by thomas on 2017/3/21 14:47.
// */
//public class JavaPoetUtil {
//
//    private final static Logger logger = LoggerFactory.getLogger(JavaPoetUtil.class);
//    //1:表示数组
//    private final static int globalIsArray = 1;
//
//
//    /**
//     * 生成Java源代码文件
//     * @param formEntityMap 表单实体，包括两个属性：formName：表单名，formPackage：表单路径
//     *e.g：
//     *           formName：CourtDishonesty
//     *            formPackage：com.tairan.cloud.whale.wisdom.bean.rule_bean.creditinfo
//     * @param fieldSpecList
//     * @param importPathList
//     * @return
//     */
//    public static String javaFile(Map<String,String> formEntityMap,
//                                  List<FieldSpec> fieldSpecList,
//                                  List<ClassName> importPathList,
//                                  List<MethodSpec> methodSpecList){
//        try{
//            logger.info("formEntityMap={}", JSON.toJSONString(formEntityMap));
//            logger.info("fieldSpecList={}", JSON.toJSONString(fieldSpecList));
//            logger.info("importPathList={}", JSON.toJSONString(importPathList));
//            logger.info("methodSpecList={}", JSON.toJSONString(methodSpecList));
//
//            if(CollectionUtils.isEmpty(formEntityMap)){
//                return null;
//            }
//            String formName = formEntityMap.get("formName");
//            String formPackage =  formEntityMap.get("formPackage");
//
//
//            TypeSpec typeSpec = TypeSpec.classBuilder(formName)
//                    .addModifiers(Modifier.PUBLIC)
//                    .addFields(fieldSpecList)//字段列表
//                    .addMethods(methodSpecList)//方法列表
//                    .build();
//
//            //添加导入项
//            JavaFile.Builder builder = JavaFile.builder(formPackage, typeSpec);
//            if(!CollectionUtils.isEmpty(importPathList)){
//                for(ClassName className:importPathList){
//                    builder.addStaticImport(className);
//                }
//            }
//
//            JavaFile javaFile = builder
//                    .build();
//
//            return javaFile.toString();
//
//        }catch(Exception e){
//            logger.error("生成javafile异常.e={}",e);
//            e.printStackTrace();
//        }
//        return null;
//    }
//    /**
//     * 获取导入项
//     * @param importPkgPath
//     * @return
//     */
//    public static  ClassName importClassName(String importPkgPath){
//        try{
//            if(StringTools.isEmpty(importPkgPath)){
//                return null;
//            }
//            int lastIndex = importPkgPath.lastIndexOf(".");
//            String pkgPath = importPkgPath.substring(0,lastIndex);
//            String pkgName = importPkgPath.substring(lastIndex+1);
//
//            ClassName pkgClassName = ClassName.get(pkgPath, pkgName);
//            return pkgClassName;
//        }catch (Exception e){
//            logger.error("e={}",e);
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 根据字段生成对应的 getter/setter 方法
//     * @param fieldName 字段名称  e.g:age
//     * @param fieldTypePath  字段类型全路径  e.g:java.lang.Integer
//     * @param isArray   1:表示数组 e.g:1
//     * @return
//     */
//    public static List<MethodSpec> methodSpec(String fieldName,String fieldTypePath,Integer isArray){
//        List<MethodSpec> methodSpecList = new ArrayList<>();
//        String setterMethodName = "set"+toUpperCase(fieldName);
//        String getterMethodName = "get"+toUpperCase(fieldName);
//
//        try{
//
//            /*********setter 方法 start.*************************************************/
//            if(isArray == globalIsArray){
//
//                int lastIndex = fieldTypePath.lastIndexOf(".");
//                ClassName fieldType = ClassName.get(fieldTypePath.substring(0,lastIndex), fieldTypePath.substring(lastIndex+1));
//                ClassName list = ClassName.get("java.util", "List");
//                TypeName typeName = ParameterizedTypeName.get(list,fieldType);
//
//                //setter
//                MethodSpec mehtod = MethodSpec.methodBuilder(setterMethodName)
//                        .addModifiers(Modifier.PUBLIC)
//                        .addParameter(typeName, fieldName)
//                        .addStatement("this.$N = $N", fieldName, fieldName)
//                        .build();
//                methodSpecList.add(mehtod);
//            }else{
//                Class fieldTypeClass = Class.forName(fieldTypePath);
//                //setter
//                MethodSpec mehtod = MethodSpec.methodBuilder(setterMethodName)
//                        .addModifiers(Modifier.PUBLIC)
//                        .addParameter(fieldTypeClass, fieldName)
//                        .addStatement("this.$N = $N", fieldName, fieldName)
//                        .build();
//                methodSpecList.add(mehtod);
//            }
//            /*********setter 方法 end.*************************************************/
//
//            /*********getter 方法 start.*************************************************/
//            if(isArray == globalIsArray){
//
//                int lastIndex = fieldTypePath.lastIndexOf(".");
//                ClassName fieldType = ClassName.get(fieldTypePath.substring(0,lastIndex), fieldTypePath.substring(lastIndex+1));
//                ClassName list = ClassName.get("java.util", "List");
//                TypeName typeName = ParameterizedTypeName.get(list,fieldType);
//
//                //getter
//                MethodSpec mehtod = MethodSpec.methodBuilder(getterMethodName)
//                        .addModifiers(Modifier.PUBLIC)
//                        .returns(typeName)
//                        .addStatement("return this.$N", fieldName)
//                        .build();
//
//                methodSpecList.add(mehtod);
//            }else{
//                Class fieldTypeClass = Class.forName(fieldTypePath);
//                //getter
//                MethodSpec mehtod = MethodSpec.methodBuilder(getterMethodName)
//                        .addModifiers(Modifier.PUBLIC)
//                        .returns(fieldTypeClass)
//                        .addStatement("return this.$N", fieldName)
//                        .build();
//
//                methodSpecList.add(mehtod);
//            }
//
//            /*********getter 方法 end.*************************************************/
//
//        }catch (Exception e){
//            logger.error("e={}",e);
//            e.printStackTrace();
//        }
//
//        return methodSpecList;
//    }
//
//    /**
//     * 生成字段
//     * @param fieldName 字段名称  e.g:age
//     * @param fieldTypePath 字段类型全路径  e.g:java.lang.Integer
//     * @param isArray  1:表示数组 e.g:1
//     * @return
//     */
//    public static FieldSpec fieldSpec(String fieldName,String fieldTypePath,Integer isArray) {
//        try{
//            logger.info("fieldName={},fieldTypePath={},isArray={}",fieldName,fieldTypePath,isArray);
//            //数组,以列表的形式加以表示
//            if(isArray == globalIsArray){
//                int lastIndex = fieldTypePath.lastIndexOf(".");
//                ClassName fieldType = ClassName.get(fieldTypePath.substring(0,lastIndex), fieldTypePath.substring(lastIndex+1));
//                ClassName list = ClassName.get("java.util", "List");
//                TypeName typeName = ParameterizedTypeName.get(list,fieldType);
//
//                FieldSpec field = FieldSpec.builder(typeName, fieldName)
//                        .addModifiers(Modifier.PRIVATE)
//                        .build();
//
//                return field;
//            }else{
//                Class fieldTypeClass = Class.forName(fieldTypePath);
//                FieldSpec field = FieldSpec.builder(fieldTypeClass, fieldName)
//                        .addModifiers(Modifier.PRIVATE)
//                        .build();
//
//                return field;
//            }
//        }catch(Exception e){
//            logger.error("e={}",e);
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    /**
//     * 调用该方法前确认首字母小写
//     * 首字母大写
//     * @param path
//     * @return
//     */
//    public static String toUpperCase(String path){
//        char[] cs= path.toCharArray();
//        cs[0]-=32;
//        return String.valueOf(cs);
//    }
//
//    /**
//     * 调用该方法前确认首字母大写
//     * 首字母小写
//     * @param path
//     * @return
//     */
//    public static String toLowerCase(String path){
//        char[] cs= path.toCharArray();
//        cs[0]+=32;
//        return String.valueOf(cs);
//    }
//
//}
