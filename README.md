# ThreadAnnotation
## 通过注解 轻松使用线程和观察方法执行时间
---
功能：

 - 注解设定UiThread，可设置延时时间
 - 注解设定Background 线程
 - 注解观察类中方法的执行时间

 

---
使用方法:

 - 设定Ui线程：

```
    @UiThread
    public void testUiThread() {
        Log.v("ghy", "uiThread:" + Thread.currentThread().getName());
    }
```
- 设定Ui线程，并延时1s：

```
    @UiThread(delayTime = 1000)
    public void testUiThread() {
        Log.v("ghy", "uiThread:" + Thread.currentThread().getName());
    }
```

- 设定BackGround线程：

```
    @Background
    public void testBackThread() {
        Log.v("ghy", "backThread:" + Thread.currentThread().getName());
    }
```
- 观察方法执行时间：

```
   @MethodWatch // 不使用tag，默认tag为方法名
    private void testMethodTag() {
    }
```
- 观察方法执行时间，使用tag：

```
   @MethodWatch(tag = "ghy") 
    private void testMethodTag() {
    }
```
- 也可以混合使用：

```
   @MethodWatch(tag = "ghy") 
   @UiThread(delayTime = 1000)
    private void testMethodTag() {
    }
```



---

引入方法：

 1. 在你的Project的 build.gradle 按下面的操作配置仓库。
 ```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}

 dependencies {
        ...
        classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:1.0.10' 
    }
 ```

 2. 然后在你对应的Modlule内的build.gradle内按下面的方式进行引入。

	

 ```
   apply plugin: 'android-aspectjx' 
   
   dependencies {
    compile 'com.github.guohaiyang1992:ThreadAnnotation:1.0'
	}
 ```
 3. 确保你的设置包含上面的所有设置，现在就可以开始使用了！


