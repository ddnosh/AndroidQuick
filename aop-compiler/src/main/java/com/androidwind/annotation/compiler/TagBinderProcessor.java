package com.androidwind.annotation.compiler;

import com.androidwind.annotation.annotation.BindTag;
import com.google.auto.service.AutoService;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

//generate MainActivity$$ViewBinder
@AutoService(Processor.class)
public class TagBinderProcessor extends AbstractProcessor {
    private Filer mFiler;
    private Messager mMessager;
    private AnnotatedClass mAnnotatedClass; //目前只生成一个文件保存所有的tag
    private boolean isGenerated;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mFiler = processingEnv.getFiler();
        mMessager = processingEnv.getMessager();
        mAnnotatedClass = new AnnotatedClass();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (isGenerated)
            return true;
        try {
            processBindView(roundEnv);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            error(e.getMessage());
        }
        try {
            mAnnotatedClass.generateFile().writeTo(mFiler);
        } catch (IOException e) {
            error("Generate file failed, reason: %s", e.getMessage());
        }
        isGenerated = true;
        return true;
    }

    private void processBindView(RoundEnvironment roundEnv) throws IllegalArgumentException {
        for (Element element : roundEnv.getElementsAnnotatedWith(BindTag.class)) {
            BindTagField bindTagField = new BindTagField(element);//获取注解的值
            mAnnotatedClass.addField(bindTagField);
        }
    }

    private void error(String msg, Object... args) {
        mMessager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, args));
    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(BindTag.class.getCanonicalName());
        return types;
    }
}