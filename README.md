## RoundLayout
可以任意定义4个角的角度，还有描边功能。

### 效果图
![](image/display.png)

### 使用

1. 引入第三方库
```gradle
implementation 'me.codego.view:round-layout:1.0@aar'
```

2. 插入控件
```xml
<me.codego.view.RoundLayout
        android:id="@+id/first_layout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="#E6E6E6"
        app:round_stroke_color="#f00"
        app:round_stroke_width="2dp"
        app:round_radius="10dp"
        app:round_topLeftRadius="10dp"
        app:round_topRightRadius="10dp"
        app:round_bottomLeftRadius="10dp"
        app:round_bottomRightRadius="10dp">

</me.codego.view.RoundLayout>
```