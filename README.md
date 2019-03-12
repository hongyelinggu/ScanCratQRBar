Android 最简单的二维码和条形码扫描生成工具
====

本项目是一个AndroidStudio项目
---

主要功能:
---
  
  >生成条形码  
  >>条形码的内容自定义  
  >扫描条形码  
  >生成二维码  
  >>二维码内容自定义  
  >扫描条形码  
  >扫描形式:  
  >>手动扫描  
  >>机器自动扫码  

=================================================================

使用方法:快速进入
========
![最新版本](https://img.shields.io/badge/%20Gradle-V%201.0.0-brightgreen.svg) 
步奏1:
---
AndroidStudio导入项目库:
```java
  compile 'com.hongyelinggu:scancode:1.0.0'
```

步骤2:
---
调用自己项目用到功能,现在把项目包含的功能做以示范

注意: 
我们要用到的无论是生成二维码还是扫描二维码都是一个Activity,所以我们要做的就是传入参数启动Activity 
现在我们了解一下方法:实例
```android
   intent = new Intent(this, CreateCodeActivity.class);
   intent.putExtra(Constant.REQUEST_SCAN_MODE, Constant.REQUEST_SCAN_MODE_ALL_MODE);
   intent.putExtra(Constant.CREAL_CODE_KEY, "1234567890CSDCECECECSC");
   startActivity(intent);
```
该方法是一个同时生成条形码和二维码的方法,现在以此为例,做详细说明
该方法中有两个重要参数:
  > ● Constant.REQUEST_SCAN_MODE 扫描和生成的模板类型  
  > ● Constant.Constant.CREAL_CODE_KEY 生成图形的内容 
  
  参数说明: 
  ---
  引入包名 com.hylg.scancode.utils
  ---
  类名:Constant
  ---
  类参数说明:
  ```库
  /**
  * 生成二维码/条码 内容
  */
  public static final String CREAL_CODE_KEY = "CreateQRKey";

    /**
     * 扫描方式 KEY
     * 普通类型，扫完即关闭 REQUEST_SCAN_TYPE_COMMON
     * 手动连续扫描,扫完不关闭界面 REQUEST_SCAN_TYPE_REGIST
     * 自动连续扫码,扫完不关闭界面 REQUEST_SCAN_TYPE_REGIST
     */
   public static final String REQUEST_SCAN_TYPE = "ScanType";
    /**
     * 普通类型，扫完即关闭
     */
    public static final int REQUEST_SCAN_TYPE_COMMON = 0X0;
    /**
     * 手动连续扫描,扫完不关闭界面
     * 其他类可以从REQUEST_SCAN_MODE_ALL_DATA获取扫描到的数据
     */
    public static final int REQUEST_SCAN_TYPE_REGIST = 0X1;
    /**
     * 自动连续扫码,扫完不关闭界面
     * 其他类可以从REQUEST_SCAN_MODE_ALL_DATA获取扫描到的数据
     */
    public static final int REQUEST_SCAN_TYPE_REGIST_AUTO = 0X2;


    /**
     * 扫描/生成类型 KEY
     * 条形码或者二维码：REQUEST_SCAN_MODE_ALL_MODE
     * 条形码： REQUEST_SCAN_MODE_BARCODE_MODE
     * 二维码：REQUEST_SCAN_MODE_QRCODE_MODE
     */
    public static final String REQUEST_SCAN_MODE = "ScanCreatMode";
    /**
     * 条形码： REQUEST_SCAN_MODE_BARCODE_MODE
     */
    public static final int REQUEST_SCAN_MODE_BARCODE_MODE = 0X100;
    /**
     * 二维码：REQUEST_SCAN_MODE_ALL_MODE
     */
    public static final int REQUEST_SCAN_MODE_QRCODE_MODE = 0X200;
    /**
     * 条形码/二维码：REQUEST_SCAN_MODE_ALL_MODE
     */
    public static final int REQUEST_SCAN_MODE_ALL_MODE = 0X300;

    /**
     * 扫码结果码code
     */
    public static final int REQUEST_SCAN_MODE_CODE = 0X118;

    /**
     * 持续扫描到的数据
     */
    public static String REQUEST_SCAN_MODE_ALL_DATA = null;
  ```
  两个重要的activity
  ====
  
  生成图形Activity: CreateCodeActivity
  ---
    接收参数: 
      > 1.生成图像的类型 Constant.REQUEST_SCAN_MODE   
          >> 图形类型详情查看Constant类
      > 2.生成图形的内容 Constant.CREAL_CODE_KEY
          >> 图形的内容
     都是放在intent中传递
    
    
    
  
  扫描图形Activity: CommonScanActivity
  ---
    接收参数:
      >1.扫描图形类型 Constant.REQUEST_SCAN_MODE:
        >> 图形类型详情查看Constant类  
      >2.扫描图形方式 Constant.REQUEST_SCAN_TYPE: 
        >> 图形扫描方式详情查看Constant类  
  
  回调方法:
  -----
  ```android
      @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intentDta) {
        super.onActivityResult(requestCode, resultCode, intentDta);
        if (intentDta != null) {
            String extra = intentDta.getStringExtra(Constant.REQUEST_SCAN_MODE);
            textView.setText(extra);
            System.out.println("扫码测试===========data==" +extra );
        }
    }
  ```
  
 如果是连续不断的扫描就得开线程循环不断的判断Constant.REQUEST_SCAN_MODE_ALL_DATA数据是否是null
 ```android循环数据
     /**
     * 测试扫描数据
     */
    private void testData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (Constant.REQUEST_SCAN_MODE_ALL_DATA != null) {
                            System.out.println("扫码测试=====001===" + Constant.REQUEST_SCAN_MODE_ALL_DATA);
                            Constant.REQUEST_SCAN_MODE_ALL_DATA = null;
                        }
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
 ```
 
 
 需要用到的权限
 ```二维码权限
  	<!-- 二维码相关权限 -->
	<uses-permission android:name="android.permission.VIBRATE"/>
	<uses-permission android:name="android.permission.CAMERA"/>
	
	<uses-feature android:name="android.hardware.camera"/>
	<uses-feature android:name="android.hardware.camera.autofocus"/>
	<uses-feature android:name="android.hardware.camera.flash"/>
	
	<uses-permission android:name="android.permission.FLASHLIGHT"/>
	<uses-permission android:name="android.permission.READ_CONTACTS"/>
	<uses-permission android:name="com.android.browser.permission.READ_HISTORY_BOOKMARKS"/>
	<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
	<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
	<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
 ```
 
 以上内容若有不清楚的地方请发送邮箱chaoqianhong@aliyun.com 获 chaoqianhong@163.com  
 =================================
 
 以上内容会不定期的维护,希望能帮助到哟用的人
  
  
