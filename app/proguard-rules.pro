# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#androidx
-dontwarn androidx.appcompat.**
-keep class androidx.appcompat.** { *; }
-keep interface androidx.appcompat.** { *; }
#google
-keep class com.google.** {*;}
-dontwarn com.google.**
#facebook
-keep class com.facebook.** {*;}
-dontwarn com.facebook.**
-keep class com.bytedance.** { *; }
-dontwarn com.bytedance.**
#line
-keep class com.linecorp.linesdk.* { *; }
#apache
-keep class org.apache.** {*;}
-dontwarn org.apache.**
#glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.integration.okhttp.OkHttpGlideModule


-keepattributes Exceptions,InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
-ignorewarnings