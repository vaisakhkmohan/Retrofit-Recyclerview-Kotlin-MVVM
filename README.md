# Retrofit-Recyclerview-Kotlin-MVVM
Retrofit sample


    implementation 'com.android.support:recyclerview-v7:28.0.0'
    implementation 'com.android.support:cardview-v7:28.0.0'
    implementation 'com.android.support:design:28.0.0'
    
    // Retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.5.0'

    // GSON
    implementation 'com.squareup.retrofit2:converter-gson:2.5.0'

    // Logging
    implementation 'com.squareup.okhttp3:logging-interceptor:3.9.0'



Test Project:

1. install node.js
2. create a folder indesktop and add file server.js in above code
3. open cmd
4. enter into the folder 
5. type "node server.js"
6. check the port is running in chrome
7. just run the code in emulator (remove server.js from android studio).


In Retrofit 2, all network operations are performed via OkHttp library. OkHttp provides HttpLoggingInterceptor which logs HTTP request and response data.
