# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in d:\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

#########-----混淆的基本配置，一般不需要改变------########
-dontshrink                                                              #不清理未使用到代码
-dontwarn                                                                #不发出警告
-optimizationpasses 5                                                    #压缩级别
-dontusemixedcaseclassnames                                              #不使用大小写混合
-dontskipnonpubliclibraryclasses                                         #不忽略非公共的类库的类
-dontskipnonpubliclibraryclassmembers                                    #不忽略非公共类库的类成员
-dontpreverify                                                           #不做预校验，加快混淆速度
-verbose                                                                 #混淆后生成映射文件
-printmapping proguardMapping.txt                                        #映射文件名称
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/* #指定混淆算法

-keepattributes *Annotation*               #不混淆注解
-keep class * extends Java.lang.annotation.Annotation { *; }
-keepattributes Signature                  #不混淆泛型
-keepattributes SourceFile,LineNumberTable #保留代码行号

-keep public class * extends android.app.Activity                               # 继承Activity的类不被混淆
-keep public class * extends android.app.Application                            # 继承Application的类不被混淆
-keep public class * extends android.app.Service                                # 继承Service的类不被混淆
-keep public class * extends android.content.BroadcastReceiver                  # 继承BroadcastReceiver的类不被混淆
-keep public class * extends android.content.ContentProvider                    # 继承ContentProvider的类不被混淆
-keep public class * extends android.app.backup.BackupAgentHelper               # 继承BackupAgentHelper的类不被混淆
-keep public class * extends android.preference.Preference                      # 继承Preference的类不被混淆
-keep public class com.android.vending.licensing.ILicensingService              # 继承ILicensingService的类不被混淆
-keep public class * extends android.support.v4.app.Fragment                    # V4包不被混淆

-dontwarn android.support.**                                                    #不发出警告 引用了v4或者v7包

-keep class org.apache.http.** { *; }                                           #java开源框架类防止报错
-dontwarn org.apache.http.**
-dontwarn android.net.**

-keepclasseswithmembernames class * {                                           # 保持 native 方法不被混淆
    native <methods>;
}

-keep class  com.android.app.** implements  com.android.app.Platform$ICallback {*;}   # 保持内部接口不被混淆

-keepclasseswithmember class * {                                                #不删除eventBus订阅函数
   public void onEvent(***);
   public void onEvent*(***);
   public void onEvent*(**);
}

# Only required if you use AsyncExecutor
-keepclassmembers class * extends de.greenrobot.event.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

-keepclasseswithmembers class * extends anroid.view.View{                       # 保持自定义控件类不被混淆
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity {                        # 保持onClick(View v)重写不受影响
   public void *(android.view.View);
}

-keepclassmembers enum * {                                                      # 保持枚举 enum 类不被混淆
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {                                # 保持 Parcelable 不被混淆
  public static final android.os.Parcelable$Creator *;
}

-keepclassmembers class * implements java.io.Serializable{                      # 保持 serializable 不被混淆
  static final long serialVersionUID;
  private static final java.io.ObjectStreamField[] serialPersistentFields;
  private void writeObject(java.io.ObjectInputStream);
  private void readObject(java.io.ObjectInputStream);
  java.lang.Object writeReplace();
  java.lang.Object readResolve();
}

-keep class **.R$*{                                                            #保证资源类不被混淆
  *;
}

-keepclassmembers  class  *  extends android.support.v4.app.Fragment {        #保留所有OnClick事件
    public void  *(android.view.View);
    public boolean *(android.view.View);
}

-keepclassmembers  class  *  extends android.app.Activity {        #保留所有OnClick事件
    public void  *(android.view.View);
    public boolean *(android.view.View);
}

-keep public class ** {                                                         #保留所有实体类
  public void set*(***);
  public *** get*();
  public *** is*();
  public *** ***(...);
  public *** ***();
  public static *** ***(...);
  public static *** ***();
}

-keepclassmembers class **.BaseRecyclerAdapter{
    protected <fields>;
}

-keepclassmembers class **.SwipeMenuAdapter{
    protected <fields>;
}
-keep interface com.yanzhenjie.recyclerview.swipe.touch.OnItemStateChangedListener{
    *;
}

