# CustomEditText

```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  ```
  
  ```gradle
 dependencies {
	        implementation 'com.github.faramarzaf:CustomEditText:v1.0.1'
	}
  ```

```xml

    <com.faramarz.material.en.FastEditText
        android:id="@+id/editText"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:backgroundIconColor="#EEFFB100"
        app:hint="Phone number..."
        app:inputType="phone_number"
        app:srcIconEditText="@drawable/ic_phone"
        app:maxLength="10"
        app:textSize="12sp"
        app:defaultTextColor="@color/colorAccent"
        app:defaultText="@string/app_name"
        app:customFontFamily="yourfont.ttf"
        />

```
