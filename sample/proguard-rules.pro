###################################################################################################
#
#                                             通用配置
#
###################################################################################################
#-------------------------------------------1.基本配置区----------------------------------------------
# 指定混淆时采用的算法，后面的参数是一个过滤器
# 这个过滤器是谷歌推荐的算法，一般不做更改
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
# 代码混淆压缩比，在0~7之间，默认为5，一般不做修改
-optimizationpasses 5
# 优化时允许访问并修改有修饰符的类和类的成员
-allowaccessmodification
# 不做预校验，preverify是proguard的四个步骤之一，Android不需要preverify，去掉这一步能够加快混淆速度。
-dontpreverify
# 混合时不使用大小写混合，混合后的类名为小写
-dontusemixedcaseclassnames
# 指定不去忽略非公共库的类
-dontskipnonpubliclibraryclasses
# 这句话能够使我们的项目混淆后产生映射文件
# 包含有类名->混淆后类名的映射关系
-verbose
# 保留Annotation不混淆
-keepattributes *Annotation*,InnerClasses
-keepattributes *JavascriptInterface*
# 避免混淆泛型
-keepattributes Signature
-keepattributes Exceptions
# 抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable
#--------------------------------------------------------------------------------------------------

#-------------------------------------------2.默认保留区--------------------------------------------
#声明第三方jar包,不用管第三方jar包中的.so文件(如果有)
#-libraryjars libs/EZOpenSDK.jar
#-libraryjars libs/gson-2.3.1.jar
#-libraryjars libs/jpush-cloud-sdk-release1.7.0.jar
#-libraryjars libs/sndecode_lib.jar
#-libraryjars libs/wechat-sdk-android-without-mta-1.0.2.jar
#-libraryjars libs/ZBarDecoder.jar
#-libraryjars libs/ZipEntry.jar
#-libraryjars libs/zxing_core.jar
# 保留我们使用的四大组件，自定义的Application等等这些类不被混淆
# 因为这些子类都有可能被外部调用
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Appliction
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class * extends android.app.Dialog
-keep public class com.android.vending.licensing.ILicensingService
-keep public class com.google.vending.licensing.ILicensingService
# androidx的混淆
-keep class com.google.android.material.** {*;}
-keep class androidx.** {*;}
-keep public class * extends androidx.**
-keep interface androidx.** {*;}
-dontwarn com.google.android.material.**
-dontnote com.google.android.material.**
-dontwarn androidx.**
# 保留support下的所有类及其内部类
-keep class android.support.** {*;}
# 保留继承的
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.v7.**
-keep public class * extends android.support.annotation.**
# 保留R下面的资源
-keep class **.R$* {*;}
# 保留本地native方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}
# 保留在Activity中的方法参数是view的方法，
# 这样一来我们在layout中写的onClick就不会被影响
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}
# 保留枚举类不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
# 保留我们自定义控件（继承自View）不被混淆
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
# 保留Parcelable序列化类不被混淆
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# 保留Serializable序列化的类不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
# 对于带有回调函数的onXXEvent、**On*Listener的，不能被混淆
-keepclassmembers class * {
    void *(**On*Event);
    void *(**On*Listener);
}
# keep webview 通用
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.webView, jav.lang.String);
}

# The support library contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version.  We know about them, and they are safe.
-dontwarn android.support.**

# Understand the @Keep support annotation.
-keep class android.support.annotation.Keep

-keep @android.support.annotation.Keep class * {*;}

-keepclasseswithmembers class * {
    @android.support.annotation.Keep <methods>;
}

-keepclasseswithmembers class * {
    @android.support.annotation.Keep <fields>;
}

-keepclasseswithmembers class * {
    @android.support.annotation.Keep <init>(...);
}

-dontwarn com.android.internal.**
-keep class com.android.internal.** { *;}

-dontwarn com.android.server.**
-keep class com.android.server.** { *;}

-dontwarn com.mediatek.**
-keep class com.mediatek.** { *;}

-dontwarn android.**
-keep class android.** { *;}

-dontwarn org.apache.http.**
-keep class org.apache.http.** { *;}

# 保持自定义控件类不被混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}
#--------------------------------------------------------------------------------------------------

###################################################################################################
#
#                                             定制化配置
#
###################################################################################################
#---------------------------------------------1.实体类----------------------------------------------
# keep 使用 webview 的类
-keepclassmembers class com.xxx.ui.fragment.MyFragment {
    public *;
}
# keep 使用 webview 的类的所有的内部类
-keepclassmembers class com.xxx.ui.fragment.device.plugin.MyFragment$*{
    *;
}
# 保持bean目录下文件不被混淆
-keep class com.xxx.bean.** {*;}
#--------------------------------------------------------------------------------------------------

#---------------------------------------------2.第三方引用------------------------------------------
# Bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
-keepattributes EnclosingMethod

# ButterKnife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

# Eventbus
-keepclassmembers class ** {
    @de.greenrobot.event.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }
    # Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(Java.lang.Throwable);
}

# Nineoldandroids
-dontwarn com.nineoldandroids.**
-keep class com.nineoldandroids.** { *;}

# Gson
-keep class sun.misc.Unsafe { *;}
-keep class com.google.gson.stream.** { *;}
-keep class com.google.gson.examples.android.model.** { *;}
-keep class com.google.gson.** { *;}

# Greendao
-keepclassmembers class * extends de.greenrobot.dao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties
-keep class de.greenrobot.dao.** { *;}
-keep class com.xxx.data.db.*$Properties {
    public static <fields>;
}
-keepclassmembers class com.xxx.data.db.** {
    public static final <fields>;
  }
    # If you do not use SQLCipher:
-dontwarn org.greenrobot.greendao.database.**

# Pinyin4j
-dontwarn net.soureceforge.pinyin4j.**
-keep class net.sourceforge.pinyin4j.** { *;}

# Jpush
-dontwarn cn.jpush.**
-keep class cn.jpush.** { *;}

# Zxing
-keep class com.google.zxing.** { *;}
-dontwarn com.google.zxing.**

# Zbar
-dontwarn net.sourceforge.zbar.**
-keep class net.sourceforge.zbar.** { *;}

# Wechat-sdk-android-without-mta
-dontwarn com.tencent.mm.opensdk.**
-keep class com.tencent.mm.opensdk.** { *;}

# Sndecode
-dontwarn com.xxx.test.sndecode.**
-keep class com.xxx.test.sndecode.** { *;}

# Zipentry
-dontwarn com.file.zip.**
-keep class com.file.zip.** { *;}

# 海康威视
-dontwarn com.ezviz.**
-dontwarn com.hik.**
-dontwarn com.hikvision.**
-dontwarn com.videogo.**
-dontwarn org.MediaPlayer.PlayM4.**
-keep class com.ezviz.** { *;}
-keep class com.hik.** { *;}
-keep class com.hikvision.** { *;}
-keep class com.videogo.** { *;}
-keep class org.MediaPlayer.PlayM4.** { *;}

# Umeng
-keepclassmembers class * {
   public <init>(org.json.JSONObject);
}
-keepclassmembers enum com.umeng.analytics.** {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# OkHttp3
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}

# Okio
-dontwarn com.squareup.**
-dontwarn okio.**
-keep public class org.codehaus.* { *; }
-keep public class java.nio.* { *; }

#retroift
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }

#jdplay && judian-fastjson
-dontwarn com.judian.support.jdplay.**
-keep class com.judian.support.jdplay.**{*;}
-dontwarn com.judian.fastjson.**
-keep class com.judian.fastjson.** { *; }
-dontwarn com.nostra13.**
-keep class com.nostra13.** { *; }
#--------------------------------------------------------------------------------------------------