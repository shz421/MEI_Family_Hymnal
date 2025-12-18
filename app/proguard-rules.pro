########## Basic Android ##########
-keep class * extends android.app.Activity
-keep class * extends android.app.Service
-keep class * extends android.content.BroadcastReceiver
-keep class * extends android.content.ContentProvider
-keep class * extends android.app.Application
-keep class * extends androidx.multidex.MultiDexApplication

########## Sketchware-Specific ##########
-keepclassmembers class * {
    public void *(android.view.View);  # onClick handlers
    public void _onCreate(android.os.Bundle);
    public void _initialize(android.os.Bundle);
    public void _initializeLogic();
}

########## Resources & R Classes ##########
-keep class **.R$* { *; }
-keepclassmembers class **.R$* {
    public static <fields>;
}

########## Firebase ##########
-keep class com.google.firebase.** { *; }
-dontwarn com.google.firebase.**
-keep class com.google.android.gms.** { *; }
-dontwarn com.google.android.gms.**

########## Glide (Image Loading) ##########
-keep public class * extends com.bumptech.glide.AppGlideModule
-keep class com.bumptech.glide.GeneratedAppGlideModuleImpl
-keep class com.bumptech.glide.load.resource.bitmap.** { *; }
-keep class com.bumptech.glide.request.** { *; }
-dontwarn com.bumptech.glide.**

########## Gson/JSON ##########
-keep class com.google.gson.** { *; }
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }

########## AndroidX/RecyclerView ##########
-keep class androidx.recyclerview.widget.** { *; }
-keep class androidx.appcompat.widget.** { *; }
-keep class androidx.core.** { *; }
-keep class com.google.android.material.** { *; }  # Material Components

########## WebView/JS Interface ##########
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

########## Networking (OkHttp) ##########
-keep class okhttp3.** { *; }
-dontwarn okhttp3.**
-keep class org.conscrypt.** { *; }
-dontwarn org.conscrypt.**

########## Reflection/Lambdas ##########
-keepattributes Signature, RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
-keepclassmembers class * {
    private synthetic <methods>;  # Lambda support
}

########## Crash Prevention ##########
-keepattributes *Annotation*, SourceFile, LineNumberTable
-renamesourcefileattribute SourceFile  # Clean stack traces
-dontwarn org.codehaus.**
-dontwarn kotlin.**
-dontwarn kotlinx.**
-dontwarn javax.annotation.**