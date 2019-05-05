package com.androidwind.annotation.compiler;

import com.androidwind.annotation.annotation.TagInfo;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

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
        //方法参数类型
        ParameterizedTypeName parameterizedTypeName = ParameterizedTypeName.get(
                ClassName.get(Map.class),
                ClassName.get(String.class),
                ClassName.get(TagInfo.class));
        ParameterSpec params = ParameterSpec.builder(parameterizedTypeName, "params").build();

        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("load")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .addParameter(params);

        for (BindTagField item : mFields) {
            String key = item.getTypeName().toString();
            TagInfo.Type type = item.getType();
            String[] value = item.getTag();
            String description = item.getDescription();
            // 添加方法内容
            methodBuilder.addStatement("params.put($S,$T.build($T.$L,$S,$S,$S))",
                    key,
                    ClassName.get(TagInfo.class),
                    ClassName.get(TagInfo.Type.class),
                    type,
                    key,
                    Arrays.toString(value),
                    description);
        }
        //生成类
        TypeSpec finderClass = TypeSpec.classBuilder("TagService")
                .addSuperinterface(ClassName.get("com.androidwind.annotation.core", "ILoad"))
                .addModifiers(Modifier.PUBLIC)
                .addMethod(methodBuilder.build())
                .build();

        return JavaFile.builder("com.androidwind.annotation", finderClass).build();
    }
}
