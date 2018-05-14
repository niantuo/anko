# anko
基于anko布局封装的一些组件

在工程项目的build.gradle中添加

     buildscript {
         ext.kotlin_version = '1.2.31'
         ext.anko_version = '0.10.2'

     }


    allprojects {
        repositories {
            google()
            maven { url "https://jitpack.io" }
    }
}



## 更新说明
### v1.0.1 2018/5/14
除去依赖中所有的标准库依赖

        group: 'com.android.support'
