package com.androidwind.annotation.compiler;

import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

class BindTagField {
    private TypeElement mTypeElement;
    private TagInfo.Type type;
    private String[] mTag;
    private String description;

    BindTagField(Element element) throws IllegalArgumentException {
        if (element.getKind() != ElementKind.CLASS) {
            throw new IllegalArgumentException(String.format("Only classes can be annotated with @%s",
                    BindTag.class.getSimpleName()));
        }
        mTypeElement = (TypeElement) element;

        BindTag bindView = mTypeElement.getAnnotation(BindTag.class);
        type = bindView.type();
        mTag = bindView.tags();
        description = bindView.description();
        if (mTag == null || ! (mTag instanceof String[])) {
            throw new IllegalArgumentException(
                    String.format("value() in %s for field %s is not valid !", BindTag.class.getSimpleName(),
                            mTypeElement.getSimpleName()));
        }
    }

    Name getTypeName() {
        return mTypeElement.getQualifiedName();
    }

    TagInfo.Type getType() {
        return type;
    }

    String[] getTag() {
        return mTag;
    }

    public String getDescription() {
        return description;
    }

    TypeMirror getFieldType() {
        return mTypeElement.asType();
    }
}