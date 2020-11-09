<h1 align="center">
<img src="https://github.com/gzeinnumer/MyLibDynamicCheckBox/blob/master/preview/example1.jpg" width="300"/> <img src="https://github.com/gzeinnumer/MyLibDynamicCheckBox/blob/master/preview/example3.jpg" width="300"/>

</h1>

<h1 align="center">
  MyLibDynamicCheckBox - Easy CheckBox Dynamic
</h1>

<div align="center">
    <a><img src="https://img.shields.io/badge/Version-1.0.3-brightgreen.svg?style=flat"></a>
    <a><img src="https://img.shields.io/badge/ID-gzeinnumer-blue.svg?style=flat"></a>
    <a><img src="https://img.shields.io/badge/Java-Suport-green?logo=java&style=flat"></a>
    <a><img src="https://img.shields.io/badge/Koltin-Suport-green?logo=kotlin&style=flat"></a>
    <a href="https://github.com/gzeinnumer"><img src="https://img.shields.io/github/followers/gzeinnumer?label=follow&style=social"></a>
    <br>
    <p>Simple way to use Dynamic CheckBox</p>
</div>

---

## Download
Add maven `jitpack.io` and `dependencies` in `build.gradle (Project)` :
```gradle
// build.gradle project
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}

// build.gradle app/module
dependencies {
  ...
  implementation 'com.github.gzeinnumer:MyLibDynamicCheckBox:version'
}
```

## Feature List
- [x] [Dynamic CheckBox](#DynamicCheckBox)

---

## DynamicCheckBox

- Widget on `xml`
```xml
<com.gzeinnumer.mylibdynamiccheckbox.DynamicCheckBox
    android:id="@+id/dc"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
```

if you want to custom your `CheckBox` use `app:style="@style/checkBoxStyle"` on view, and make style on your `res->value->style.xml`
```xml
<resources xmlns:tools="http://schemas.android.com/tools">

    <style name="checkBoxStyle" parent="Base.Theme.AppCompat">
        <item name="android:textColor">#FFE500</item>
    </style>
</resources>
```
```xml
<com.gzeinnumer.mylibdynamiccheckbox.DynamicCheckBox
    ...
    app:orientation="horizontal"
    app:style="@style/checkBoxStyle"/>
```

#
- **Content Item** there is 2 type list that you can sent to this `CheckBox`.

**Type 1**
```java
DynamicCheckBox dynamicCheckBox = findViewById(R.id.dc);

ArrayList<String> listString = new ArrayList<String>();
listString.add("Satu");
listString.add("Dua");
listString.add("Tiga");
listString.add("Empat");

dynamicCheckBox.setItemList(listString)
.setOnCheckedChangeListener(new DynamicCheckBox.OnCheckedChangeListenerObject<String>() {
    @Override
    public void onCheckedChanged(ArrayList<String> items) {
        for (int i=0; i<items.size(); i++){
            Log.d(TAG, "onCheckedChanged: "+items.get(i));
        }
    }

    @Override
    public void onCheckedShow(String clickedValue) {
        Log.d(TAG, "onCheckedShow: "+clickedValue);
    }
});
```
#
**Type 2** for this type you should override function `toString()` in your `model pojo`
```java
public class ExampleModel {

    private int id;
    private String name;
    private String address;

    //constructor

    //getter
    //setter

    @Override
    public String toString() {
        return "ExampleModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
```
Use your own model. And dont forget to declare your `model pojo` in `onCallBack`. Example `DynamicCheckBox.OnCheckedChangeListener<ExampleModel>`
```java
DynamicCheckBox dynamicCheckBox = findViewById(R.id.dc);

ArrayList<ExampleModel> listObject = new ArrayList<>();
listObject.add(new ExampleModel(1, "Zein", "Balbar"));
listObject.add(new ExampleModel(2, "Zein2", "Balbar2"));
listObject.add(new ExampleModel(3, "Zein3", "Balbar3"));
listObject.add(new ExampleModel(4, "Zein4", "Balbar4"));

dynamicCheckBox.setItemList(listObject)
.setOnCheckedChangeListener(new DynamicCheckBox.OnCheckedChangeListener<ExampleModel>() {
    @Override
    public void onCheckedChanged(ArrayList<ExampleModel> items) {
        for (int i=0; i<items.size(); i++){
            Log.d(TAG, "onCheckedChanged: "+items.get(i).getName());
            Log.d(TAG, "onCheckedChanged: "+items.get(i).getAddress());
        }
    }

    @Override
    public void onCheckedShow(String clickedValue) {
        Log.d(TAG, "onCheckedShow: "+clickedValue);
    }
});
```
#

**Preview** :

| <img src="https://github.com/gzeinnumer/MyLibDynamicCheckBox/blob/master/preview/example1.jpg"/>| <img src="https://github.com/gzeinnumer/MyLibDynamicCheckBox/blob/master/preview/example3.jpg"/> |
|:---|:---|
| Preview `Single Object`| Preview `Model Pojo`|

| <img src="https://github.com/gzeinnumer/MyLibDynamicCheckBox/blob/master/preview/example2.jpg"/> |
|:---|
| Output data `Single Object`|

| <img src="https://github.com/gzeinnumer/MyLibDynamicCheckBox/blob/master/preview/example4.jpg"/> |
|:---|
|  Output data `Model Pojo`|

---

**FullCode [MainActivity](https://github.com/gzeinnumer/MyLibDynamicCheckBox/blob/master/app/src/main/java/com/gzeinnumer/mylibdynamiccheckbox/MainActivity.java) & [ExampleModel](https://github.com/gzeinnumer/MyLibDynamicCheckBox/blob/master/app/src/main/java/com/gzeinnumer/mylibdynamiccheckbox/ExampleModel.java) & [XML](https://github.com/gzeinnumer/MyLibDynamicCheckBox/blob/master/app/src/main/res/layout/activity_main.xml)**

---

### Version
- **1.0.3**
  - First Release

---

### Contribution
You can sent your constibution to `branche` `open-pull`.

---

```
Copyright 2020 M. Fadli Zein
```