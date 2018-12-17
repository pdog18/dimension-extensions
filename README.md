# dp px 转换工具类 by Kotlin



Step 1. Add the dependency
```groovy
dependencies {
       api 'com.pdog.util:dimension-extensions:0.9.0'
}
```
Step 2. Use in Kotlin's code(or other file)
```
val height = 48f.dp
```





## 说明


在`android`开发中有的时候处理控件大小我们会需要用到`dp`值来进行处理，针对这种情况，我们一般会写一个类似`dp2px(float dp)`的`static`方法。

然后每次调用的时候这样来使用
```java
float height = dp2px(48f)
//use height
```
当阅读代码时，我们会脑补一个`48f` 被 `dp2px()` 函数调用的“回头”操作，
阅读代码的顺序大概是这样的

```
float height = dp2px(48f)
float height  //定义一个height
dp2px(48f)  // height 值是一个函数结果，函数参数是48f
dp2px(48f)  // 再次，48f是从dp转换成px
```

> 简直就是石器时代的代码啊！


现在我们到了工业时代的`kotlin` 里用扩展字段。我可以将代码写成这样
```kotlin
val height = 48f.dp
```
> 定义一个`height` 值为 `48f` 对应的`dp` 值
> 可以读出来的代码！真香！ 



---

### 扩展字段源码
> 1. 无关`Context` 不需要传入 `context`调用 ,也不需要在 `application`初始化，复制即用！
> 2. 测试通过`displayMetrics` 在 `Activity` 被反射修改后，会使用反射后的值来进行转换



```kotlin 
package com.pdog.dimension

import android.content.res.Resources

/**
 * 正常编码中一般只会用到 [dp]/[sp] ;
 * 其中[dp]/[sp] 会根据系统分辨率将输入的dp/sp值转换为对应的px
 */
val Float.dp: Float                 // [xxhdpi](360 -> 1080)
    get() = android.util.TypedValue.applyDimension(
        android.util.TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics)

val Int.dp: Int
    get() = android.util.TypedValue.applyDimension(
        android.util.TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics).toInt()


val Float.sp: Float                 // [xxhdpi](360 -> 1080)
    get() = android.util.TypedValue.applyDimension(
        android.util.TypedValue.COMPLEX_UNIT_SP, this, Resources.getSystem().displayMetrics)


val Int.sp: Int
    get() = android.util.TypedValue.applyDimension(
        android.util.TypedValue.COMPLEX_UNIT_SP, this.toFloat(), Resources.getSystem().displayMetrics).toInt()
```

