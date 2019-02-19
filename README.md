# AndroidQuick
[![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![Download](https://api.bintray.com/packages/ddnosh/maven/AndroidQuick/images/download.svg) ](https://bintray.com/ddnosh/maven/AndroidQuick/_latestVersion)  
![image](https://github.com/ddnosh/AndroidQuick/blob/master/logo.png)
## Release Notes
### 20190211 -> sdk版本: v1.1.6
1. sdk版本更新, 新增baseObserver,Exeception,生命周期绑定等功能;
### 20190131 -> sdk版本: v1.1.1
1. 优化代码目录结构;
2. 升级sdk版本至v1.1.1, SDK版本功能见下方描述;
### 20190123 -> sdk版本: v1.0.5
1. 全新改版主界面UI;
### 20180721 -> sdk版本: v1.0.5
1. 新增Architecture类型：one_view_with_multiple_modules;
   一个页面分成为多个模块进行开发实现的另一种方式;
2. 新增Architecture类型：webview_with_javascript;
   异步处理js和native多次交互请求;
### 20180624 -> sdk版本: v1.0.5
1. 新增页面状态的demo;
2. 引入AndroidUtilCode开源工具类;
3. 新增Architecture架构分类,介绍一些常用的开发架构(one_layout_with_multiple_pages);
   一个页面分成多个模块进行开发实现的一种方式;
### 20180618 -> sdk版本: v1.0.4
1. 新增HttpURLConnect封装框架;
### 20180407 -> sdk版本: v1.0.3
1. 增加异步任务处理机制;
2. 增加部分demo实例;
### 20180319 -> sdk版本: v1.0.2
1. 升级gradle和android gradle plugin的version;
2. 增加通过novoda上传JCenter的支持;
3. 更新app icon;
### 20180315 -> sdk版本: v1.0.1
1. 增加对Fragmentation开源组件的支持；
2. 增加开源组件Banner的实现；
3. 增加底部tab栏的两种实现（RadioButton、BottomBar）

## 项目简介
AndroidQuick项目旨在提供一套让能Android开发者快速开发APP的框架。
AndroidQuick从开发一个APP所涉及到的常用的架构、模块、功能等方面出发，向Android开发者提供一套快速开发框架，避免开发过程中重复造轮子。
AndroidQuick分为两大部分：sample和core，以下有具体介绍。
## 适用范围
由于本框架目的是为了快速开发一款APP，因此所提供的功能主要是APP经常用到的功能，并不包含所有Android开发可能涉及到的功能。  
如果需要了解Android开发过程中需要的更多功能，请参考另一个项目：https://github.com/ddnosh/SEOP

本项目适用于以下几种情形：
- 开发一款新的APP;
- 对现有APP进行重构设计;
- 常用实现的代码示例;
## 工程目录
1. core
2. sample
3. GreenDaoGenerator
### sample
sample提供了架构、模块、功能的实现示例;
### core
core封装了通用的实现，我们将其打包成一个aar包，提供给APP引用。
引用方式：compile 'la.xiong.androidquick:AndroidQuick:latest.release'
#### 功能说明
1. sample
    1. 目录结构
        1. Module
            1. MVP
                > 1. MVP for Activity
                > 2. MVP for Fragment
            2. Network
                > 1. Retrofit+CommonUrl
                > 2. Retrofit+DifferentUrl
                > 3. Retrofit+Download
                > 4. Retrofit+CommonUrl+Get
                > 5. Common Http
            3. Database
                > 1. GreenDao
                > 2. OrmLite
            4. Image
                > 1. Glide
            5. Bus
                > 1. EventBus
            6. IOC
                > 1. ButterKnife
                > 2. Dagger2
            7. Task
                > 1. Task-Scheduler
                > 2. Task-RxJava
        2. Function
            1. UI
                1. Fragment
                    > 1. CommonFragment
                    > 2. Fragmentation
                2. Adapter
                    > 1. CommonAdapter
                    > 2. MultiViewTypeAdapter
                    > 3. BaseRecycleViewAdapterHelper
                3. Bar-Top
                    > 1. DefaultToolbar
                    > 2. CommonToolbar
                4. Bar-Bottom
                    > 1. RadioButton
                    > 2. BottomBar
                5. Dialog
                    1. DialogActivity
                        > 1. LoadingDialog
                        > 2. CommonDialog
                    2. DialogFragment
                6. Tab
                    > 1. SmartTabLayout
                    > 2. FlycoTabLayout
                7. Refresh
                    > 1. SwipeRefreshLayout
                    > 2. SmartRefreshLayout
                8. Banner
                9. WebView
                10. DataBinding
                11. VaryPageStatus
            2. Permission
                > 1. Permission-Camara
                > 2. Permission-Fragment-Call
            3. Json
            4. RaJava
            5. SharedPreferences
        3. Architecture
            > 1. OneLayout-MultipleViews
            > 2. OneView-MultipleModules
            > 3. WebView-JavaScripts
        4. Other
            > 1. Code
            > 2. RxLifecycle
            > 3. Lambda
    2. 功能说明
        1. BaseActivity
        > 抽象类，继承自QuickActivity，实现了一些QuickActivity的抽象方法；
        2. BaseTActivity
        > 抽象类，采用MVP架构，并用泛型实现，并引入dagger2库；
        3. BaseVActivity
        > 抽象类，引入DataBinding的支持；
        4. BaseTVActivity
        > 抽象类，相比BaseTActivity，增加了DataBinding的支持；
        5. BaseFActivity
        > 抽象类，用于配合Fragmentation组件使用；
        6. BaseFragment、BaseTFragment、BaseVFragment、BaseTVFragment、BaseFFragment
        > 功能类同于对应的Activity；
    3. 开源组件
        > 1. Dagger2
        > 2. GreenDao
        > 3. MarkDown
        > 4. Banner
        > 5. SmartTabLayout
        > 6. FlycoTabLayout
        > 7. FastJson
        > 8. Fragmentation
        > 9. RxJava
        > 10. BaseRecyclerViewAdapterHelper
2. Core
    1. 功能说明
        1. QuickActivity
        > - 多状态页面(loading、empty、error、networkerror)
        > - 设备信息
        > - 页面转场动画
        > - ButterKnife 8+ support
        > - DataBinding support
        > - 沉浸式标题栏
        > - 默认toolbar
        > - ActivityManager
        > - EventBus
        > - Network status monitor
        > - 页面跳转
        > - Android 6.0+权限控制
        > - 包含LoadingDialog和CommonDialog
        2. QuickFragment
        > 基本类同QuickActivity，注意fragment生命周期。
        3. Adapter
        > - CommonAdapter
        > 通用adapter类
        > - MultiItemCommonAdapter
        > 增加ItemType支持的通用adapter类
        > - BaseRecyclerViewAdapterHelper
        > 第三方开源adapter库
        4. Dialog
            1. for activity
            > - LoadingDialog
            > - CommonDialog
            2. for fragment
            > - CommonDialog
        5. 多分辨率适配
        > 实现原理：http://blog.csdn.net/ddnosh/article/details/78941302
        6. 网络请求  
        > HttpURLConnection封装
        > 加入Retrofit2支持
        7. 图片处理
        > 加入Glide支持
        8. styles.xml  
        > 提供上百种style支持，满足各种UI风格设计
        9. tools  
        > DialogUtil、FileUtil、GsonHelper、IOUtil、LogUtil、NetUtil、ReflectUtil、SpUtil、StringUtil、ToastUtil  
        10. task
        > 异步任务处理机制
        11. eventbus
        > 集成通用传递事件
    2. 开源组件
        > 1. EvnentBus
        > 2. ButterKnife
        > 3. Retrofit+OkHttp
        > 4. Gson
        > 5. Glide
        > 6. immersion
        > 7. RxLifecycle
3. GreenDaoGenerator
GreenDao生成数据库文件示例工程





