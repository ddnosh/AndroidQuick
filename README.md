# AndroidQuick
[![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![Download](https://api.bintray.com/packages/ddnosh/maven/AndroidQuick/images/download.svg) ](https://bintray.com/ddnosh/maven/AndroidQuick/_latestVersion)  
![image](https://github.com/ddnosh/AndroidQuick/blob/master/logo.png)
## Release Notes
### 20180319 -> v1.0.2
1. 升级gradle和android gradle plugin的version;
2. 增加通过novoda上传JCenter的支持;
3. 更新app icon;
### 20180315 -> v1.0.1
1. 增加对Fragmentation开源组件的支持；
2. 增加开源组件Banner的实现；
3. 增加底部tab栏的两种实现（RadioButton、BottomBar）

## 项目简介
AndroidQuick项目旨在提供一套让能Android开发者快速开发APP的框架。  
AndroidQuick从开发一个APP所涉及到的架构、UI、网络、数据库、工具等方面出发，给Android开发者提供一套快速开发框架，
避免开发过程中重复造轮子。
## 适用范围
由于本框架目的是为了快速开发一款APP，因此所提供的功能主要是APP经常用到的功能，并不包含所有Android开发可能涉及到的功能。  
如果需要了解Android开发过程中需要的更多功能，请参考另一个项目：https://github.com/ddnosh/AndroidBox

本项目适用于以下几种情形：
- 开发一款新的APP;
- 对现有APP进行重构设计;
- 常用模块(架构、网络、数据库、工具等)代码示例参考；
## 工程目录
1. Core
2. Sample
3. GreenDao
### Core
Core是AndroidQuick快速开发框架的核心库，包含各种跟业务无关的通用功能，我们将其打包成一个aar包，提供给APP开发使用。
引用方式：compile 'la.xiong.androidquick:AndroidQuick:latest.release'
#### 功能说明
目前Core所提供的的功能如下：
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
> - LoadingDialog
> - CommonDialog
5. 多分辨率适配  
> 实现原理：http://blog.csdn.net/ddnosh/article/details/78941302
6. 网络请求  
> 加入Retrofit2支持
7. 图片处理  
> 加入Glide支持
8. styles.xml  
> 提供上百种style支持，满足各种UI风格设计
9. tools  
> DialogUtil、FileUtil、GsonHelper、IOUtil、LogUtil、NetUtil、ReflectUtil、SpUtil、StringUtil、ToastUtil  
#### 开源组件
> 1. EvnentBus
> 2. ButterKnife
> 3. RxJava
> 4. Retrofit+OkHttp
> 5. Gson
> 6. Glide
> 7. BaseRecyclerViewAdapterHelper
### Sample  
Sample工程是QuickAndroid快速开发框架的示例工程，作用如下：
1. 验证Core工程提供的功能;
2. 引入更多主流开源库;
#### 模块说明
Sample包含以下几个模块：UI、Network、Database、Tools。
##### UI
1. Fragment
2. Adapter
3. Toolbar
4. BottomBar
5. Dialog
6. Tab
7. Banner
8. Code
9. Permission
10. Refresh
11. WebView
12. DataBinding
##### Network
1. Retrofit+OkHttp+RxJava
2. Retrofit+OkHttp
##### Database
1. GreenDao
##### Tools
1. Json
2. Gson
#### 功能说明
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
#### 开源组件
> 1. Dagger2
> 2. GreenDao
> 3. MarkDown
> 4. Banner
> 5. SmartTabLayout
> 6. FlycoTabLayout
> 7. FastJson
> 8. Fragmentation
### GreenDao
GreenDao生成数据库文件示例工程





