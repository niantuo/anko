# anko
基于anko布局封装的一些组件

在工程项目的build.gradle中添加

     buildscript {
         ext.kotlin_version = '1.2.50'
         ext.anko_version = '0.10.2'

     }


    allprojects {
        repositories {
            google()
            maven { url "https://jitpack.io" }
    }

    dependencies{
        implementation "io.reactivex.rxjava2:rxjava:2.1.12"
        implementation 'io.reactivex.rxjava2:rxandroid:2.0.2'
        implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
    }



## 更新说明
### v1.0.1 2018/5/14
除去依赖中所有的标准库依赖

        group: 'com.android.support'

### v1.0.6 2018/9/4
 - 升级 kotlin版本为1.2.50
 - 修复kotlin引入版本
 - 去除kotlin 版本依赖

