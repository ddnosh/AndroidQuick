package com.androidwind.annotation.compiler;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.lang.model.element.Modifier;

class AnnotatedClass {

    private ArrayList<BindTagField> mFields;

    AnnotatedClass() {
        mFields = new ArrayList<>();
    }

    void addField(BindTagField field) {
        mFields.add(field);
    }

    JavaFile generateFile() {
        //生成类变量
        FieldSpec fieldSpec = FieldSpec.builder(ParameterizedTypeName.get(
                ClassName.get(HashMap.class),
                ClassName.get(String.class),
                ClassName.get(String.class)), "map")
                .addModifiers(Modifier.PRIVATE)
                .build();

        // 生成构造函数
        MethodSpec.Builder constructors = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addStatement("map = new $T()", HashMap.class);
        for (BindTagField item : mFields) {
            String key = item.getTypeName().toString();
            String[] value = item.getTag();
            // 添加方法内容
            constructors.addStatement("map.put($S, $S)", key, Arrays.toString(value));
        }
        // 生成Get方法
        MethodSpec method = MethodSpec.methodBuilder("getMap")
                .addModifiers(Modifier.PUBLIC)
                .returns(HashMap.class)
                .addStatement("return map")
                .build();
        //生成类
        TypeSpec finderClass = TypeSpec.classBuilder("TagService")
                .addModifiers(Modifier.PUBLIC)
                .addField(fieldSpec)
                .addMethod(constructors.build())
                .addMethod(method)
                .build();

        return JavaFile.builder("com.androidwind.annotation", finderClass).build();
    }
}
