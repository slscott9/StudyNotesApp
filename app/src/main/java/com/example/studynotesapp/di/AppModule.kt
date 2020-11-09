package com.example.studynotesapp.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.studynotesapp.data.database.StudyNotesDao
import com.example.studynotesapp.data.database.StudyNotesDatabase
import com.example.studynotesapp.data.local.LocalDataSource
import com.example.studynotesapp.data.local.LocalDataSourceImpl
import com.example.studynotesapp.data.remote.RemoteDataSource
import com.example.studynotesapp.data.remote.RemoteDataSourceImpl
import com.example.studynotesapp.data.repo.Repository
import com.example.studynotesapp.data.repo.RepositoryImpl
import com.example.studynotesapp.network.StudyNotesApi
import com.example.studynotesapp.network.utils.BasicAuthInterceptor
import com.example.studynotesapp.other.Constants.BASE_URL
import com.example.studynotesapp.other.Constants.DATABASE_NAME
import com.example.studynotesapp.other.Constants.ENCRYPTED_SHARED_PREF_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDao(db: StudyNotesDatabase) = db.dao()

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, StudyNotesDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()


    @Singleton
    @Provides
    fun provideRepo(localDataSource : LocalDataSource, service: RemoteDataSource, context: Application) : Repository =
        RepositoryImpl(localDataSource, service, context)

    @Singleton
    @Provides
    fun provideLocalDataSource(dao: StudyNotesDao) : LocalDataSource = LocalDataSourceImpl(dao)


    /*
Why create a specific provides method for the interceptor?
If we created the instance inside of provide note api method we would not be able to access and set the email and password members.
So we need to make a provides method for the interceptor so we can inject an instance and access the fields
*/
    @Singleton
    @Provides
    fun provideBasicAuthInterceptor(sharedPreferences: SharedPreferences) = BasicAuthInterceptor(sharedPreferences)


    //for https athenticates all certificates
    @Singleton
    @Provides
    fun provideHttpClient() : OkHttpClient.Builder {
        val trustAllCertificates: Array<TrustManager> = arrayOf(
            object : X509TrustManager {
                override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) {
                    //no op
                }

                override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) {
                    //no op
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf() //trust manager trusts all certificates
                }
            }
        )
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCertificates, SecureRandom())

        return OkHttpClient.Builder()
            .sslSocketFactory(sslContext.socketFactory, trustAllCertificates[0] as X509TrustManager)
            .hostnameVerifier(HostnameVerifier { _, _ -> true })
    }


    //return ServiceInterface for testing
    @Singleton
    @Provides
    fun provideServiceImpl(notesApi: StudyNotesApi) : RemoteDataSource = RemoteDataSourceImpl(notesApi)


    @Singleton
    @Provides
    fun provideNotesApi(okHttpClient: OkHttpClient.Builder, basicAuthInterceptor: BasicAuthInterceptor): StudyNotesApi {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = okHttpClient
            .addInterceptor(loggingInterceptor)
            .addInterceptor(basicAuthInterceptor)
            .build()



        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(StudyNotesApi::class.java)
    }

    @Singleton
    @Provides
    fun provideEncryptedSharedPreferences(@ApplicationContext context: Context) : SharedPreferences {
        //create master key for android key store
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM) //entrypts data
            .build()
        return EncryptedSharedPreferences.create(
            context,
            ENCRYPTED_SHARED_PREF_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}